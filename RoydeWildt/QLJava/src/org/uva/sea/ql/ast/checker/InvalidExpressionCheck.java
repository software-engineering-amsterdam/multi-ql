package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.AssQuestion;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.var.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 11/02/16.
 */
public class InvalidExpressionCheck extends BaseVisitor{
    Map<Node,Node> decls;
    List<Node> invalidExpressions;

    public InvalidExpressionCheck(Form f){
        this.decls = new HashMap<>();
        this.invalidExpressions = new ArrayList<>();
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
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(And expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.BOOLEAN))
            invalidExpressions.add(expr);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Div expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Eq expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.BOOLEAN))
            invalidExpressions.add(expr);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(GEq expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(GT expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(LEq expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(LT expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Mul expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(NEq expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.BOOLEAN))
            invalidExpressions.add(expr);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Or expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.BOOLEAN))
            invalidExpressions.add(expr);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Sub expr) {
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Neg expr) {
        if(!rightType(expr.getVal().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Not expr) {
        if(!rightType(expr.getVal().accept(this.getV()), Type.Types.BOOLEAN))
            invalidExpressions.add(expr);
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Pos expr) {
        if(!rightType(expr.getVal().accept(this.getV()), Type.Types.MONEY))
            invalidExpressions.add(expr);
        return (T) Type.Types.MONEY;
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

    private boolean rightType(Type.Types x, Type.Types type)
    {
        if (x == type)
            return true;
        else
            return false;
    }

    private boolean rightType(Type.Types x, Type.Types y, Type.Types type)
    {
        if (x == y && y == type)
            return true;
        else
            return false;
    }

    public List<Node> getInvalidExpressions() {
        return invalidExpressions;
    }
}
