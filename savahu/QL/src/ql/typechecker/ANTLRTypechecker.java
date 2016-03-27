/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.typechecker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ql.antlr.generatedcode.QLBaseVisitor;
import ql.antlr.generatedcode.QLParser;
import ql.ast.ASTStorage;
import ql.ast.type.BoolType;
import ql.ast.type.IntType;
import ql.ast.type.Type;
import ql.ast.type.StringType;

/**
 *
 * @author sander
 */
public class ANTLRTypechecker extends QLBaseVisitor<Boolean> {

    private final ASTStorage _StoredASTNodes;
    private final List<String> _ListOfErrors;

    public ANTLRTypechecker() {
        this._StoredASTNodes = new ASTStorage();
        this._ListOfErrors = new ArrayList<>();
    }

    public List GetErrors() {
        return Collections.unmodifiableList(this._ListOfErrors);
    }

    @Override
    public Boolean visitForm(QLParser.FormContext ctx) {
        String id = ctx.Ident.getText();
        if (this._StoredASTNodes.KeyIsInStorage(id)) {
            this._ListOfErrors.add("Duplicate form in questionnare: " + ctx.Ident.getText());
            return false;
        } else {
            this._ListOfErrors.add("No duplicate forms found for formId " + ctx.Ident.getText());
            String value = ctx.getText();
            this._StoredASTNodes.AddToStorage(id, value);
            ctx.block().accept(this);
            return true;
        }
    }

    @Override
    public Boolean visitBlock(QLParser.BlockContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        this._ListOfErrors.add("Block seems ok");
        return true;
    }

    @Override
    public Boolean visitStatement(QLParser.StatementContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitIfstatement(QLParser.IfstatementContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitIfelsestatement(QLParser.IfelsestatementContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitQuestion(QLParser.QuestionContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitSimplequestion(QLParser.SimplequestionContext ctx) {
        Type q = ctx.type.result;
        if (q instanceof BoolType) {
            this._ListOfErrors.add("QuestionType of " + ctx.Ident().getText() + " is " + "Bool");
            // do something to check it
        } else if ((q instanceof IntType)) {
            this._ListOfErrors.add("QuestionType of " + ctx.Ident().getText() + " is " + "Integer");
        } else if ((q instanceof StringType)) {
            this._ListOfErrors.add("QuestionType of " + ctx.Ident().getText() + " is " + "String");
        } else {
            this._ListOfErrors.add("QuestionType of " + ctx.Ident().getText() + " is " + "Unknown!");
        }
        return true;
    }

    @Override
    public Boolean visitComputedquestion(QLParser.ComputedquestionContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitType(QLParser.TypeContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitLabel(QLParser.LabelContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitPrimary(QLParser.PrimaryContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitBool(QLParser.BoolContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitUnaryExpr(QLParser.UnaryExprContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitMulExpr(QLParser.MulExprContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitAddExpr(QLParser.AddExprContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitRelExpr(QLParser.RelExprContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitAndExpr(QLParser.AndExprContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visitOrExpr(QLParser.OrExprContext ctx) {
        ctx.children.stream().forEach((p) -> {
            p.accept(this);
        });
        return true;
    }

    @Override
    public Boolean visit(ParseTree pt) {
        return true;
    }

    @Override
    public Boolean visitChildren(RuleNode rn) {
        return true;
    }

    @Override
    public Boolean visitTerminal(TerminalNode tn) {
        return true;
    }

    @Override
    public Boolean visitErrorNode(ErrorNode en) {
        return true;
    }
}
