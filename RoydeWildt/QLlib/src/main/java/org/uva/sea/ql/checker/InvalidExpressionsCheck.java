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
public class InvalidExpressionsCheck<T,U> extends BaseVisitor<Type.Types,Void>{
    private final Map<Node,Node> decls = new HashMap<>();
    private final List<Node> invalidExpressions = new ArrayList<>();

    public InvalidExpressionsCheck(Form f){
        f.accept(this,null);
    }

    @Override
    public Type.Types visit(Question stat, Void env) {
        decls.put(stat.getVarname(), stat);
        return null;
    }

    @Override
    public Type.Types visit(AssQuestion stat, Void env) {
        decls.put(stat.getVarname(), stat);
        return null;
    }

    @Override
    public Type.Types visit(Add expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.MONEY;
    }

    @Override
    public Type.Types visit(And expr, Void env) {
        addInvalidExpression(expr, Type.Types.BOOLEAN);
        return  Type.Types.BOOLEAN;
    }

    @Override
    public Type.Types visit(Div expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.MONEY;
    }

    @Override
    public Type.Types visit(Eq expr, Void env) {
        if(rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(),null), Type.Types.BOOLEAN)){
            return  Type.Types.BOOLEAN;
        }
        else if (rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(),null), Type.Types.MONEY)) {
            return  Type.Types.BOOLEAN;
        }
        else {
            if(!subExpressionExists(invalidExpressions, expr))
                invalidExpressions.add(expr);
            return null;
        }
    }

    @Override
    public Type.Types visit(GEq expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.BOOLEAN;
    }

    @Override
    public Type.Types visit(GT expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.BOOLEAN;
    }

    @Override
    public Type.Types visit(LEq expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.BOOLEAN;
    }

    @Override
    public Type.Types visit(LT expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.BOOLEAN;
    }

    @Override
    public Type.Types visit(Mul expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.MONEY;
    }

    @Override
    public Type.Types visit(NEq expr, Void env) {
        if(rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(),null), Type.Types.BOOLEAN)){
            return  Type.Types.BOOLEAN;
        }
        else if (rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(),null), Type.Types.MONEY)) {
            return  Type.Types.BOOLEAN;
        }
        else {
            if(!subExpressionExists(invalidExpressions, expr))
                invalidExpressions.add(expr);
            return null;
        }
    }

    @Override
    public Type.Types visit(Or expr, Void env) {
        addInvalidExpression(expr, Type.Types.BOOLEAN);
        return  Type.Types.BOOLEAN;
    }

    @Override
    public Type.Types visit(Sub expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.MONEY;
    }

    @Override
    public Type.Types visit(Neg expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.MONEY;
    }

    @Override
    public Type.Types visit(Not expr, Void env) {
        addInvalidExpression(expr, Type.Types.BOOLEAN);
        return  Type.Types.BOOLEAN;
    }

    @Override
    public Type.Types visit(Pos expr, Void env) {
        addInvalidExpression(expr, Type.Types.MONEY);
        return  Type.Types.MONEY;
    }

    @Override
    public Type.Types visit(Primary expr, Void env) {
        return (Type.Types) expr.getValue().accept(this.getV(),null);
    }

    @Override
    public Type.Types visit(Var var, Void env) {
        if (decls.containsKey(var)){

            Question q = (Question) decls.get(var);
            Type t = q.getType();
            return  t.getType();

        }
        else
            return null;
    }

    @Override
    public Type.Types visit(Int val, Void env) {
        return  Type.Types.MONEY;
    }

    @Override
    public Type.Types visit(Bool val, Void env) {
        return  Type.Types.BOOLEAN;
    }

    private void addInvalidExpression(BinaryExpr expr, Type.Types type){
        if(!rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(),null), type))
            if(!subExpressionExists(invalidExpressions, expr))
                invalidExpressions.add(expr);
    }

    private void addInvalidExpression(UnaryExpr expr, Type.Types type){
        Expr e = expr.getValue();
        if(!rightType(e.accept(this.getV(),null), type))
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
