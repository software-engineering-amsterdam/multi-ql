package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.type.BooleanType;
import org.uva.sea.ql.ast.type.MoneyType;
import org.uva.sea.ql.ast.type.ValueType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roydewildt on 17/02/16.
 */

//TODO: Replace void with environment and remove global decls var


public class TypeVisitor extends BaseVisitor<ValueType,Void> {
    private final Map<Node,Node> decls = new HashMap<>();

    @Override
    public ValueType visit(Question stat, Void env) {
        decls.put(stat.getVarname(), stat);
        return null;
    }

    @Override
    public ValueType visit(Add expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(And expr, Void env) {
        if(incompatibleTypes(expr, new BooleanType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(Div expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(Eq expr, Void env) {
        if(!incompatibleTypes(expr, new BooleanType()) ||
                !incompatibleTypes(expr, new MoneyType())){
            return new BooleanType();
        }
        return null;
    }

    @Override
    public ValueType visit(GEq expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(GT expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(LEq expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(LT expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(Mul expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(NEq expr, Void env) {
        if(!incompatibleTypes(expr, new BooleanType()) ||
                !incompatibleTypes(expr, new MoneyType())){
            return new BooleanType();
        }
        return null;

    }

    @Override
    public ValueType visit(Or expr, Void env) {
        if(incompatibleTypes(expr, new BooleanType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(Sub expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(Neg expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(Not expr, Void env) {
        if(incompatibleTypes(expr, new BooleanType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(Pos expr, Void env) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(Primary expr, Void env) {
        return expr.getValue().accept(this,null);
    }

    @Override
    public ValueType visit(Var var, Void env) {
        if (decls.containsKey(var)){

            Question q = (Question) decls.get(var);
            Type t = q.getType();
            return t.getType();

        }
        else
            return null;
    }

    @Override
    public ValueType visit(Int val, Void env) {
        return new MoneyType();
    }

    @Override
    public ValueType visit(Bool val, Void env) {
        return new BooleanType();
    }

    private boolean incompatibleTypes(BinaryExpr expr, ValueType type){
        ValueType vlhs = expr.getLhs().accept(this,null);
        ValueType vrhs = expr.getRhs().accept(this,null);

        if(vlhs == null ||
                vrhs == null ||
                !vlhs.equals(type) ||
                !vrhs.equals(type) ||
                !vlhs.equals(vrhs))
            return true;
        return false;
    }

    private boolean incompatibleTypes(UnaryExpr expr, ValueType type){
        Expr e = expr.getValue();
        ValueType v = e.accept(this,null);
        if(v == null ||
                !v.equals(type))
            return true;
        return false;
    }

}
