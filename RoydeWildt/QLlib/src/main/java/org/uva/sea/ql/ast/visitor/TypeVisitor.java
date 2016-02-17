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
public class TypeVisitor extends BaseVisitor {
    private final Map<Node,Node> decls;

    public TypeVisitor(Form f){
        this.decls = new HashMap<>();
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
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(And expr) {
        if(isInvalidExpression(expr, Type.Types.BOOLEAN))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Div expr) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
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
            return null;
        }
    }

    @Override
    public <T> T visit(GEq expr) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(GT expr) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(LEq expr) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(LT expr) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Mul expr) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
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
            return null;
        }
    }

    @Override
    public <T> T visit(Or expr) {
        if(isInvalidExpression(expr, Type.Types.BOOLEAN))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Sub expr) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Neg expr) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
        return (T) Type.Types.MONEY;
    }

    @Override
    public <T> T visit(Not expr) {
        if(isInvalidExpression(expr, Type.Types.BOOLEAN))
            return null;
        return (T) Type.Types.BOOLEAN;
    }

    @Override
    public <T> T visit(Pos expr) {
        if(isInvalidExpression(expr, Type.Types.MONEY))
            return null;
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

    private Boolean isInvalidExpression(BinaryExpr expr, Type.Types type){
        if(!rightType(expr.getLhs().accept(this.getV()), expr.getRhs().accept(this.getV()), type))
            return true;
        return false;
    }

    private Boolean isInvalidExpression(UnaryExpr expr, Type.Types type){
        Expr e = expr.getValue();
        if(!rightType(e.accept(this.getV()), type))
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
