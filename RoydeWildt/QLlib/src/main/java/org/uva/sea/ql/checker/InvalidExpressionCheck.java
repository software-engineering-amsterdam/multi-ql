package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.AssQuestion;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 11/02/16.
 */
@SuppressWarnings("unchecked")
public class InvalidExpressionCheck extends BaseVisitor{
    private final Map<Node,Node> decls = new HashMap<>();
    private final List<Node> invalidExpressions = new ArrayList<>();

    public InvalidExpressionCheck(Form f){
        f.accept(this);
    }

    @Override
    public <T> T visit(Question stat) {
        decls.put(stat.getVarname(), stat);
        return null;
    }

    @Override
    public <T> T visit(AssQuestion stat) {
        decls.put(stat.getVarname(), stat);
        return null;
    }

    @Override
    public <T> T visit(Add expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(And expr) {
        addInvalidExpression(expr, Type.Types.BOOLEAN);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Div expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Eq expr) {
        if(rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.BOOLEAN)){
            return (T) Type.Types.BOOLEAN;
        }
        else if (rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY)) {
            return (T) Type.Types.BOOLEAN;
        }
        else {
            if(!subExpressionExists(invalidExpressions, expr))
                invalidExpressions.add(expr);
            return null;
        }
    }

    @Override
    public <T> T visit(GEq expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(GT expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(LEq expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(LT expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Mul expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(NEq expr) {
        if(rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.BOOLEAN)){
            return (T) Type.Types.BOOLEAN;
        }
        else if (rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY)) {
            return (T) Type.Types.BOOLEAN;
        }
        else {
            if(!subExpressionExists(invalidExpressions, expr))
                invalidExpressions.add(expr);
            return null;
        }
    }

    @Override
    public <T> T visit(Or expr) {
        addInvalidExpression(expr, Type.Types.BOOLEAN);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Sub expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Neg expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Not expr) {
        addInvalidExpression(expr, Type.Types.BOOLEAN);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Pos expr) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Primary expr) {
        return expr.getValue().accept(this.getV());
    }

    @Override
    public <T> T visit(Var var) {
        if (decls.containsKey(var)){

            Question q = (Question) decls.get(var);
            Type t = q.getType();
            return (T) t.getType();

        }
        else
            return null;
    }

    @Override
    public <T> T visit(Int val) {
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Bool val) {
        return (T) Type.Types.BOOLEAN;
    }

    private void addInvalidExpression(BinaryExpr expr, Type.Types type){
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), type))
            if(!subExpressionExists(invalidExpressions, expr))
                invalidExpressions.add(expr);
    }

    private void addInvalidExpression(UnaryExpr expr, Type.Types type){
        Expr e = expr.getValue();
        if(!rightType(e.accept(this.getV()), type))
            if(!subExpressionExists(invalidExpressions, expr))
                invalidExpressions.add(expr);
    }

    private boolean rightType(Object x, Object type)
    {
        if(x instanceof Type.Types && type instanceof Type.Types)
            if (x == type)
                return true;
        return false;
    }

    private boolean rightType(Object x, Object y, Object type)
    {
        if(x instanceof Type.Types && y instanceof Type.Types && type instanceof Type.Types)
            if (x == y && y == type)
                return true;
        return false;
    }

    private boolean subExpressionExists(List<Node> invalidargs, Node x){
        for (Node invalidarg : invalidargs){
            String istr = invalidarg.toString();
            String xstr = x.toString();
            if (xstr.contains(istr))
                return true;
        }
        return false;
    }

    public List<Node> getInvalidExpressions() {
        return invalidExpressions;
    }
}
