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
        f.accept(this);
    }

    @Override
    public ValueType visit(Add expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(And expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(Div expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(Eq expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(GEq expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(GT expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(LEq expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(LT expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(Mul expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(NEq expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(Or expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(Sub expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(Neg expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(Not expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(Pos expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
    }

    @Override
    public ValueType visit(Primary expr) {
        ValueType exprType = super.visit(expr);
        if (exprType == null  && !subExpressionExists(expr)){
            invalidExpressions.add(expr);
        }
        return super.visit(expr);
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
