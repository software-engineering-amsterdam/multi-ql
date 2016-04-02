package ql.typechecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ql.ast.ASTNode;
import ql.ast.ASTStorage;
import ql.ast.IVisitor;
import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BinaryExpr;
import ql.ast.expression.Div;
import ql.ast.expression.Eq;
import ql.ast.expression.Expr;
import ql.ast.expression.GEq;
import ql.ast.expression.GT;
import ql.ast.expression.Ident;
import ql.ast.expression.LEq;
import ql.ast.expression.LT;
import ql.ast.expression.Mul;
import ql.ast.expression.NEq;
import ql.ast.expression.Neg;
import ql.ast.expression.Not;
import ql.ast.expression.Or;
import ql.ast.expression.Pos;
import ql.ast.expression.Sub;
import ql.ast.form.Block;
import ql.ast.form.Form;
import ql.ast.literal.BoolLiteral;
import ql.ast.literal.IntegerLiteral;
import ql.ast.literal.Literal;
import ql.ast.literal.StringLiteral;
import ql.ast.question.ComputedQuestion;
import ql.ast.question.Label;
import ql.ast.question.Question;
import ql.ast.question.QuestionTypeMap;
import ql.ast.question.SimpleQuestion;
import ql.ast.statement.IfElseStatement;
import ql.ast.statement.IfStatement;
import ql.ast.type.BoolType;
import ql.ast.type.IntType;
import ql.ast.type.Type;
import ql.ast.type.StringType;

/**
 *
 * @author sander
 */
public class ASTTypechecker implements IVisitor {

    private final ASTStorage _StoredASTNodes;
    private final List<String> _ListOfErrors;
    private final List<String> _VerboseLogs;
    private final QuestionTypeMap _StoredQuestionTypes;

    public ASTTypechecker() {
        this._StoredASTNodes = new ASTStorage();
        this._ListOfErrors = new ArrayList<>();
        this._VerboseLogs = new ArrayList<>();
        this._StoredQuestionTypes = new QuestionTypeMap();
    }

    public List GetErrors() {
        return Collections.unmodifiableList(this._ListOfErrors);
    }

    public List GetVerboseLogs() {
        return Collections.unmodifiableList(this._VerboseLogs);
    }

    @Override
    public void visit(ASTNode node) {
        this._ListOfErrors.add("Visited an ASTNode... Not useful! " + node.getClass() + " " + node.toString());
    }

    @Override
    public void visit(Form node) {
        String id = node.getIdentifier().getName();
        if (this._StoredASTNodes.KeyIsInStorage(id)) {
            this._ListOfErrors.add(ErrorConstants.DUPLICATE_FORMID + id);
        } else {
            this._VerboseLogs.add("No duplicate forms found for formId " + id);
            String value = node.toString();
            this._StoredASTNodes.AddToStorage(id, value);
            // now visit the block child
            node.visitChildren(this);
        }
    }

    @Override
    public void visit(Block node) {
        this._VerboseLogs.add("Visited a Block");
        node.visitChildren(this);
    }

    @Override
    public void visit(SimpleQuestion node) {
        this._VerboseLogs.add("Found a SimpleQuestion ");
        checkQuestion(node);
        node.visitChildren(this);
    }

    @Override
    public void visit(ComputedQuestion node) {
        this._VerboseLogs.add("Found a ComputedQuestion ");
        checkQuestion(node);
        node.visitChildren(this);
    }

    private void checkQuestion(Question node) {
        String questionId = node.getId().getName();
        if (this._StoredASTNodes.KeyIsInStorage(questionId)) {
            this._ListOfErrors.add(ErrorConstants.DUPLICATE_QUESTIONID + questionId);
        } else {
            this._StoredASTNodes.AddToStorage(questionId, node.getLabel().getLabelText());
            this._StoredQuestionTypes.addQuestion(node.getId(), node.getType());
            typeCheckQuestion(node);
        }
    }

    private Boolean typeCheckQuestion(Question node) {
        Ident questionId = node.getId();
        Type q = node.getType();
        if (q instanceof BoolType) {
            if (!questionId.isCompatibleToBool()) {
                this._ListOfErrors.add("QuestionType of " + questionId.getName() + " is not compatible with the variable");
                return false;
            }
            return true;
        } else if ((q instanceof IntType)) {
            if (!questionId.isCompatibleToInt()) {
                this._ListOfErrors.add("QuestionType of " + questionId.getName() + " is not compatible with the variable");
                return false;
            }
            return true;
        } else if ((q instanceof StringType)) {
            if (!questionId.isCompatibleToString()) {
                this._ListOfErrors.add("QuestionType of " + questionId.getName() + " is not compatible with the variable");
                return false;
            }
            return true;
        } else {
            this._ListOfErrors.add(ErrorConstants.UKNOWN_QUESTIONTYPE + questionId);
            return false;
        }
    }

    @Override
    public void visit(Label node) {
        this._VerboseLogs.add("Found a Label " + node.getLabelText());
    }

    @Override
    public void visit(IfStatement node) {
        this._VerboseLogs.add("Found an IfStatement ");
        node.visitChildren(this);
    }

    @Override
    public void visit(IfElseStatement node) {
        this._VerboseLogs.add("Found an IfElseStatement ");
        node.visitChildren(this);
    }

    public Boolean isNumericExpression(BinaryExpr node) {
        Boolean exprIsInt = node.getType().isCompatibleToInteger();
        Boolean lhsIsInt = false;
        Boolean rhsIsInt = false;
        if (!(node.getLhs().isLiteral() && node.getRhs().isLiteral())) {
            Ident lhs = (Ident) node.getLhs();
            Ident rhs = (Ident) node.getRhs();
            lhsIsInt = this._StoredQuestionTypes.getTypeForQuestion(lhs).isCompatibleToInteger();
            rhsIsInt = this._StoredQuestionTypes.getTypeForQuestion(rhs).isCompatibleToInteger();
        } else {
            if (node.getLhs().getType().isCompatibleToInteger()) {
                lhsIsInt = node.getLhs().getType().isCompatibleToInteger();
            }
            if (node.getRhs().getType().isCompatibleToInteger()) {
                rhsIsInt = node.getRhs().getType().isCompatibleToInteger();
            }
        }
        return exprIsInt && lhsIsInt && rhsIsInt;
    }

    @Override
    public void visit(Add node) {
        this._VerboseLogs.add("Found a Add " + node.getLhs().toString() + node.getRhs().toString());
        Boolean exprIsNumeric = node.getType().isCompatibleToInteger();
        Boolean lhsIsNumeric = node.getLhs().getType().isCompatibleToInteger();
        Boolean rhsIsNumeric = node.getRhs().getType().isCompatibleToInteger();
        if (!(exprIsNumeric && lhsIsNumeric && rhsIsNumeric)) {
            this._ListOfErrors.add("AddExpression corrupt");
        }
    }

    @Override
    public void visit(And node) {
        this._VerboseLogs.add("Found a And " + node.getLhs().toString() + node.getRhs().toString());
        Boolean exprIsBool = node.getType().isCompatibleToBoolean();
        Boolean lhsIsBool = false;
        Boolean rhsIsBool = false;
        if (!node.getLhs().isLiteral()) {
            lhsIsBool = ((Ident) node.getLhs()).getType().isCompatibleToBoolean();
        } else {
            lhsIsBool = node.getLhs().getType().isCompatibleToBoolean();
        }
        if (!node.getRhs().isLiteral()) {
            lhsIsBool = ((Ident) node.getLhs()).getType().isCompatibleToBoolean();
        } else {
            rhsIsBool = node.getRhs().getType().isCompatibleToBoolean();
        }
        if (!(exprIsBool && lhsIsBool && rhsIsBool)) {
            this._ListOfErrors.add("AndExpression corrupt");
        }
    }

    @Override
    public void visit(Div node) {
        this._VerboseLogs.add("Found a Div");
    }

    @Override
    public void visit(Eq node) {
        this._VerboseLogs.add("Found an Eq");
    }

    @Override
    public void visit(GEq node) {
        this._VerboseLogs.add("Found a GEq");
    }

    @Override
    public void visit(GT node) {
        this._VerboseLogs.add("Found a GT");
    }

    @Override
    public void visit(LEq node) {
        this._VerboseLogs.add("Found a LEq");
    }

    @Override
    public void visit(LT node) {
        this._VerboseLogs.add("Found a LT");
    }

    @Override
    public void visit(Mul node) {
        this._VerboseLogs.add("Found a Mul");
    }

    @Override
    public void visit(Neg node) {
        this._VerboseLogs.add("Found a Neg ");
    }

    @Override
    public void visit(NEq node) {
        this._VerboseLogs.add("Found a NEq ");
    }

    @Override
    public void visit(Not node) {
        this._VerboseLogs.add("Found a Not ");
    }

    @Override
    public void visit(Or node) {
        this._VerboseLogs.add("Found an OrExpr " + node.getLhs().toString() + node.getRhs().toString());
    }

    @Override
    public void visit(Pos node) {
        this._VerboseLogs.add("Found a Pos");
    }

    @Override
    public void visit(Sub node) {
        this._VerboseLogs.add("Found a Sub");
        if (!isNumericExpression(node)) {
            this._ListOfErrors.add("SubExpression corrupt");
        }
        node.visitChildren(this);
    }

    @Override
    public void visit(Ident node) {
        this._VerboseLogs.add("Found an Ident " + node.getName());
    }

    @Override
    public void visit(Literal node) {
        this._VerboseLogs.add("Found a Literal " + node.toString());
    }

    @Override
    public void visit(BoolLiteral node) {
        this._VerboseLogs.add("Found a BoolLiteral " + node.getValue());
        node.getType().accept(this);
    }

    @Override
    public void visit(IntegerLiteral node) {
        this._VerboseLogs.add("Found an IntegerLiteral " + node.getValue());
        node.getType().accept(this);
    }

    @Override
    public void visit(StringLiteral node) {
        this._VerboseLogs.add("Found a StringLiteral " + node.getValue());
        node.getType().accept(this);
    }

    @Override
    public void visit(Type node) {
        this._VerboseLogs.add("Found a Type ");
    }

    @Override
    public void visit(BoolType node) {
        this._VerboseLogs.add("Found a BoolType ");
    }

    @Override
    public void visit(IntType node) {
        this._VerboseLogs.add("Found an IntType ");
    }

    @Override
    public void visit(StringType node) {
        this._VerboseLogs.add("Found a StringType ");
    }

    @Override
    public void visit(Expr node) {
        this._VerboseLogs.add("Found an Expr of type " + node.getClass().getName());
    }
}
