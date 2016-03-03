package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.type.ValueType;
import org.uva.sea.ql.ast.visitor.TypeVisitor;
import org.uva.sea.ql.checker.message.ErrorMessage;
import org.uva.sea.ql.checker.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 11/02/16.
 */

public class InvalidExpressionsCheck extends TypeVisitor {
    private final List<Expr> invalidExpressions = new ArrayList<>();

    public InvalidExpressionsCheck(Form f) {
        f.accept(this, null);
    }

    @Override
    public ValueType visit(Add expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(And expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(Div expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(Eq expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(GEq expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(GT expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(LEq expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(LT expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(Mul expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(NEq expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(Or expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(Sub expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(Neg expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(Not expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(Pos expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, context);
    }

    @Override
    public ValueType visit(Primary expr, Void context) {
        ValueType exprType = super.visit(expr, context);
        if (exprType == null  && !subExpressionExists(expr)){
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
            StringBuilder sb = new StringBuilder();
            sb.append("Expression ");
            sb.append(e.toString());
            sb.append(" has incompatible argument types");

            messages.add(new ErrorMessage(sb.toString(),e));
        }
        return messages;
    }

    public List<Expr> getInvalidExpressions() {
        return invalidExpressions;
    }
}
