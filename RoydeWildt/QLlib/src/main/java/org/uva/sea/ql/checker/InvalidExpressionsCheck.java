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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 11/02/16.
 */

public class InvalidExpressionsCheck extends TypeVisitor {
    private final List<Expr> invalidExpressions = new ArrayList<>();

    public InvalidExpressionsCheck(Form f) {
        f.accept(this,null);
    }

    @Override
    public ValueType visit(Add expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(And expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(Div expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(Eq expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(GEq expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(GT expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(LEq expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(LT expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(Mul expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(NEq expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(Or expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(Sub expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(Neg expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(Not expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(Pos expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
    }

    @Override
    public ValueType visit(Primary expr, Void env) {
        ValueType exprType = super.visit(expr, env);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr, env);
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

    public List<Expr> getInvalidExpressions() {
        return invalidExpressions;
    }
}
