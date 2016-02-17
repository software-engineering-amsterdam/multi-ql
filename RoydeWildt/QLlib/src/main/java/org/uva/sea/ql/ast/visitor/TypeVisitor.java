package org.uva.sea.ql.ast.visitor;

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

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roydewildt on 17/02/16.
 */
public class TypeVisitor<T,U> extends BaseVisitor<T,U> {
    private final Map<Node,Node> decls;

    public TypeVisitor(Form f){
        this.decls = new HashMap<>();
    }

    @Override
    public T visit(Question stat, U context) {
        decls.put(stat.getVarname(), stat);
        return null;
    }

    @Override
    public T visit(AssQuestion stat, U context) {
        decls.put(stat.getVarname(), stat);
        return null;
    }

    @Override
    public T visit(Add expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.MONEY;
    }

    @Override
    public T visit(And expr, U context) {
        if(isInvalidExpression(expr, Type.Types.BOOLEAN))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public T visit(Div expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.MONEY;
    }

    @Override
    public T visit(Eq expr, U context) {
        if(rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(),null), Type.Types.BOOLEAN)){
            return (T) Type.Types.BOOLEAN;
        }
        else if (rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(),null), Type.Types.MONEY)) {
            return (T) Type.Types.BOOLEAN;
        }
        else {
            return null;
        }
    }

    @Override
    public T visit(GEq expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public T visit(GT expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public T visit(LEq expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public T visit(LT expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public T visit(Mul expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.MONEY;
    }

    @Override
    public T visit(NEq expr, U context) {
        if(rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(),null), Type.Types.BOOLEAN)){
            return (T) Type.Types.BOOLEAN;
        }
        else if (rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(),null), Type.Types.MONEY)) {
            return (T) Type.Types.BOOLEAN;
        }
        else {
            return null;
        }
    }

    @Override
    public T visit(Or expr, U context) {
        if(isInvalidExpression(expr, Type.Types.BOOLEAN))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public T visit(Sub expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.MONEY;
    }

    @Override
    public T visit(Neg expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.MONEY;
    }

    @Override
    public T visit(Not expr, U context) {
        if(isInvalidExpression(expr, Type.Types.BOOLEAN))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public T visit(Pos expr, U context) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.MONEY;
    }

    @Override
    public T visit(Primary expr, U context) {
        return (T) expr.getValue().accept(this.getV(),null);
    }

    @Override
    public T visit(Var var, U context) {
        if (decls.containsKey(var)){

            Question q = (Question) decls.get(var);
            Type t = q.getType();
            return (T) t.getType();

        }
        else
            return null;
    }

    @Override
    public T visit(Int val, U context) {
        return (T) Type.Types.MONEY;
    }

    @Override
    public T visit(Bool val, U context) {
        return (T) Type.Types.BOOLEAN;
    }

    private boolean isInvalidExpression(BinaryExpr expr, Type.Types type){
        if(!rightType(expr.getLhs().accept(this.getV(),null), expr.getRhs().accept(this.getV(), null), type))
            return true;
        return false;
    }

    private boolean isInvalidExpression(UnaryExpr expr, Type.Types type){
        Expr e = expr.getValue();
        if(!rightType(e.accept(this.getV(),null), type))
            return true;
        return false;
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

}
