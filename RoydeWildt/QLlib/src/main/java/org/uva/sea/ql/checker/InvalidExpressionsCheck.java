package org.uva.sea.ql.checker;

import org.uva.sea.ql.adt.type.Null;
import org.uva.sea.ql.adt.type.Type;
import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.checker.message.ErrorMessage;
import org.uva.sea.ql.checker.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 11/02/16.
 */

public class InvalidExpressionsCheck extends TypeCheck {
    private final List<Expr> invalidExpressions = new ArrayList<>();

    public InvalidExpressionsCheck(Form form) {
        super(form);
        form.accept(this, null);
    }

    @Override
    public Type visit(Add expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(And expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(Div expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(Eq expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(GEq expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(GT expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(LEq expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(LT expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(Mul expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(NEq expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(Or expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(Sub expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(Neg expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(Not expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public Type visit(Pos expr, Void context) {
        Type exprType = super.visit(expr, context);
        if (exprType.equals(new Null()) && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    private boolean subExpressionExists(Node x){
        for (Node invalidarg : invalidExpressions){
            String istr = invalidarg.toString();
            String xstr = x.toString();
            if (xstr.contains(istr))
                return true;
        }
        return false;
    }

    public List<Message> invalidExpressionChecker(){
        List<Message> messages = new ArrayList<>();
        List<Expr> invalidExpressions = getInvalidExpressions();

        for(Node n : invalidExpressions){
            Expr e = (Expr) n;
            String sb = "Expression " +
                    e.toString() +
                    " has incompatible argument types";

            messages.add(new ErrorMessage(sb,e));
        }
        return messages;
    }

    public List<Expr> getInvalidExpressions() {
        return invalidExpressions;
    }
}
