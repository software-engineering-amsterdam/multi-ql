/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typechecker;

import AST.expressions.Add;
import AST.expressions.And;
import AST.expressions.Div;
import AST.expressions.Eq;
import AST.expressions.GEq;
import AST.expressions.GT;
import AST.expressions.LEq;
import AST.expressions.LT;
import AST.expressions.Mul;
import AST.expressions.NEq;
import AST.expressions.Neg;
import AST.expressions.Not;
import AST.expressions.OneExpr;
import AST.expressions.Or;
import AST.expressions.Pos;
import AST.expressions.Sub;
import AST.form.Block;
import AST.form.ComputedQuestion;
import AST.form.Form;
import AST.form.IfElseStatement;
import AST.form.IfStatement;
import AST.form.NormalQuestion;
import AST.form.Question;
import AST.form.Statement;
import AST.literals.BoolLiteral;
import AST.literals.IntLiteral;
import AST.literals.Literal;
import AST.literals.MoneyLiteral;
import AST.literals.StrLiteral;
import AST.types.Bool;
import AST.types.Ident;
import AST.types.Int;
import AST.types.Label;
import AST.types.Money;
import AST.types.Str;
import AST.types.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ql.ASTNode;

/**
 *
 * @author Dominique
 */
public class Typechecker implements TypecheckInterface<Boolean> {

    private Map<String, String> ASTvalues;
    private QuestionTypes QuestionTypes;
    private List<String> ErrorList;
    private List<String> SuccesList;

    public Typechecker() {
        this.ASTvalues = new HashMap();
        this.QuestionTypes = new QuestionTypes();
        this.ErrorList = new ArrayList();
        this.SuccesList = new ArrayList();
    }

    public List getErrorList() {
        return this.ErrorList;
    }

    public List getSuccesList() {
        return this.SuccesList;
    }

    @Override
    public Boolean visit(ASTNode node) {
        this.SuccesList.add("Visited an ASTNode. " + node.getClass() + " " + node.toString());
        return false;
    }

    @Override
    public Boolean visit(Form node) {
        String id = node.getID();
        Block value = node.getBlockresult();
        if (ASTvalues.containsKey(id)) {
            ErrorList.add("Duplicate formname " + id);
            return false;
        } else {
            String stringvalue = value.toString();
            ASTvalues.put(id, stringvalue);
            SuccesList.add("Formname with name " + id + " added.");
            node.getBlockresult().accept(this);
            return true;
        }
    }

    @Override
    public Boolean visit(Block node) {
        String statements = node.getStatements();
        String questions = node.getQuestions();
        if (ASTvalues.containsKey(statements)) {
            if (ASTvalues.containsKey(questions)) {
                ErrorList.add("Duplicate block with following statements: " + statements + " and following questions: " + questions);
                return false;
            }
            return true;
        } else {
            ASTvalues.put(statements, questions);
            SuccesList.add("Block with following statements: " + statements + "added.");
            node.getStatementResult().stream().forEach((Statement s) -> {
                s.accept(this);
            });
            node.getQuestionResult().stream().forEach((Question q) -> {
                q.accept(this);
            });
            return true;
        }

    }

    @Override
    public Boolean visit(IfStatement node) {
        String ifstatements = node.getIfStatement().toString();
        String thenstatements = node.getThenStatement().toString();
        if (ASTvalues.containsKey(ifstatements)) {
            if (ASTvalues.containsKey(thenstatements)) {
                ErrorList.add("The following duplicate ifstatement is found " + ifstatements + ". This statement had the following thenstatement " + thenstatements);
                return false;
            }
            return true;
        } else {
            ASTvalues.put(ifstatements, thenstatements);
            SuccesList.add("The following ifstatement is added " + ifstatements + ". This statement had the following thenstatement " + thenstatements);
            node.getIfStatement().accept(this);
            node.getThenStatement().accept(this);
            return true;
        }
    }

    @Override
    public Boolean visit(IfElseStatement node) {
        String ifstatements = node.getIfStatement().toString();
        String thenstatements = node.getThenStatement().toString();
        String elsestatements = node.getElseStatement().toString();
        if (ASTvalues.containsKey(ifstatements)) {
            if (ASTvalues.containsKey(thenstatements)) {
                if (ASTvalues.containsKey(elsestatements)) {
                    ErrorList.add("The following duplicate if/else statement is found " + ifstatements + ". This statement had the following thenstatement " + thenstatements + " and the following elsestatement " + elsestatements);
                    return false;
                }
                return true;
            }
            return true;
        } else {
            ASTvalues.put(ifstatements, elsestatements);
            SuccesList.add("The following ifstatement is added " + ifstatements + ". This statement had the following thenstatement " + thenstatements + " and the following elsestatement " + elsestatements);
            node.getIfStatement().accept(this);
            node.getThenStatement().accept(this);
            node.getElseStatement().accept(this);
            return true;
        }
    }

    @Override
    public Boolean visit(NormalQuestion node) {

        String questions = node.getId().getName();
        String labels = node.getLabel().getText();
        String idname = node.getId().getName();
        String labeltext = node.getLabel().getText();

        if (ASTvalues.containsKey(questions)) {
            ErrorList.add("The following duplicate normal question is found " + idname + ". This question had the following label " + labeltext);
            return false;
        } else {
            ASTvalues.put(questions, labels);
            QuestionTypes.addQuestionTypes(node.getId(),node.getType());
            SuccesList.add("The following normal question is added " + idname + ". This question had the following label " + labeltext);
            node.getId().accept(this);
            node.getLabel().accept(this);
            node.getType().accept(this);
            return true;
        }
    }

    @Override
    public Boolean visit(ComputedQuestion node) {
        String questions = node.getId().getName();
        String labels = node.getLabel().getText();
        String expression = node.getExpr().toString();
        String idname = node.getId().getName();
        String labeltext = node.getLabel().getText();

        if (ASTvalues.containsKey(questions)) {
            ErrorList.add("The following duplicate computed question is found " + idname + ". This question had the following label " + labeltext + " and the following expression " + expression);
            return false;
        } else {
            ASTvalues.put(questions, labels);
            QuestionTypes.addQuestionTypes(node.getId(),node.getType());
            SuccesList.add("The following computed question is added " + idname + ". This question had the following label " + labeltext);
            node.getId().accept(this);
            node.getLabel().accept(this);
            node.getType().accept(this);
            node.getExpr().accept(this);
            return true;
        }
    }

    @Override
    public Boolean visit(OneExpr node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Add node) {
        String lhs = node.getLhs().toString();
        String rhs = node.getRhs().toString();
        String lhsname = ((Ident) node.getLhs()).getName();
        String rhsname = ((Ident) node.getLhs()).getName();
        SuccesList.add("Expression added with on the left hand side the following value " + lhsname + " and on the right hand side the following value " + rhsname);
        ASTvalues.put(lhs, rhs);
        return true;
    }

    @Override
    public Boolean visit(And node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Div node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Eq node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(GEq node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(GT node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(LEq node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(LT node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Mul node) {
        String lhs = node.getLhs().toString();
        String rhs = node.getRhs().toString();
        String lhsname = ((Ident) node.getLhs()).getName();
        String rhsname = ((Ident) node.getLhs()).getName();
        if (((Ident) node.getLhs()).getType().isMoney() == true || ((Ident) node.getLhs()).getType().isMoney() == true) {
            if (((Ident) node.getRhs()).getType().isInt() == true || ((Ident) node.getRhs()).getType().isMoney() == true) {
                SuccesList.add("Expression added with on the left hand side the following value " + lhsname + " and on the right hand side the following value " + rhsname);
                ASTvalues.put(lhs, rhs);
                return true;
            }
        } else {
            ErrorList.add("One or more of the values in this expression aren't of the integer or money types.");
            return false;
        }
        return true;
    }

    @Override
    public Boolean visit(NEq node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Neg node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Not node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Or node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Pos node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Sub node) {
        Boolean lhsIsInt = false;
        Boolean rhsIsInt = false;
        Boolean lhsIsMoney = false;
        Boolean rhsIsMoney = false;
        if (!(node.getLhs().isLiteral() && node.getRhs().isLiteral())) {
            Ident lhs = (Ident) node.getLhs();
            Ident rhs = (Ident) node.getRhs();
            lhsIsInt = this.QuestionTypes.getType(lhs).isInt();
            rhsIsInt = this.QuestionTypes.getType(rhs).isInt();
            lhsIsMoney = this.QuestionTypes.getType(lhs).isMoney();
            rhsIsMoney = this.QuestionTypes.getType(rhs).isMoney();
        } 
        else {
            if (node.getLhs().getType().isInt()) {
                lhsIsInt = node.getLhs().getType().isInt();
            }
            if (node.getRhs().getType().isInt()) {
                rhsIsInt = node.getRhs().getType().isInt();
            }
            if (node.getLhs().getType().isMoney()) {
                lhsIsMoney = node.getLhs().getType().isMoney();
            }
            if (node.getRhs().getType().isMoney()) {
                rhsIsMoney = node.getRhs().getType().isMoney();
            }
        }
        if (!(lhsIsInt && rhsIsInt)) {
            ErrorList.add("One or more of the values in this expression aren't of the integer or money types.");
            return false;
        }
        else 
        {
            SuccesList.add("Expression added with on the left hand side the following value " + node.getLhs().toString() + " and on the right hand side the following value " + node.getRhs().toString());
            return true;
        }
    }
//public void visit(Sub node) {
//        this._VerboseLogs.add("Found a Sub");
//        Boolean exprIsInt = node.getType().isCompatibleToInteger();
//        Boolean lhsIsInt = false;
//        Boolean rhsIsInt = false;
//        if (!node.isLiteral()) {
//            Ident lhs = (Ident) node.getLhs();
//            Ident rhs = (Ident) node.getRhs();
//            lhsIsInt = this._StoredQuestionTypes.getTypeForQuestion(lhs).isCompatibleToInteger();
//            rhsIsInt = this._StoredQuestionTypes.getTypeForQuestion(rhs).isCompatibleToInteger();
//        } else {
//            if (node.getLhs().getType().isCompatibleToInteger()) {
//                lhsIsInt = node.getLhs().getType().isCompatibleToInteger();
//            }
//            if (node.getRhs().getType().isCompatibleToInteger()) {
//                rhsIsInt = node.getRhs().getType().isCompatibleToInteger();
//            }
//        }
//        if (!(exprIsInt && lhsIsInt && rhsIsInt)) {
//            this._ListOfErrors.add("SubExpression corrupt");
//        }
//        node.visitChildren(this);
    @Override
    public Boolean visit(Literal node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(BoolLiteral node) {
        return true;
    }

    @Override
    public Boolean visit(IntLiteral node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(MoneyLiteral node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(StrLiteral node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Bool node) {
        return true;
    }

    @Override
    public Boolean visit(Ident node) {
        String idents = node.getName();
        if (idents != null && !idents.isEmpty()) {
            return true;
        } else {
            System.out.println("ID is leeg");
            return false;
        }
    }

    @Override
    public Boolean visit(Int node) {
        return true;
    }

    @Override
    public Boolean visit(Label node) {
        String labels = node.getText();
        if (labels != null && !labels.isEmpty()) {
            return true;
        } else {
            System.out.println("Label is leeg");
            return false;
        }
    }

    @Override
    public Boolean visit(Money node) {
        return true;
    }

    @Override
    public Boolean visit(Str node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean visit(Boolean node) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
