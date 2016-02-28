package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
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


public class TypeVisitor<F,S,T> extends BaseVisitor<F,S,ValueType,T,ValueType,Void> {
    private final Map<Node,Node> decls = new HashMap<>();

    @Override
    public S visit(Question stat, Void context) {
        decls.put(stat.getVarname(), stat);
        return null;
    }

    @Override
    public ValueType visit(Add expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(And expr, Void context) {
        if(incompatibleTypes(expr, new BooleanType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(Div expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(Eq expr, Void context) {
        if(!incompatibleTypes(expr, new BooleanType()) ||
                !incompatibleTypes(expr, new MoneyType())){
            return new BooleanType();
        }
        return null;
    }

    @Override
    public ValueType visit(GEq expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(GT expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(LEq expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(LT expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(Mul expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(NEq expr, Void context) {
        if(!incompatibleTypes(expr, new BooleanType()) ||
                !incompatibleTypes(expr, new MoneyType())){
            return new BooleanType();
        }
        return null;

    }

    @Override
    public ValueType visit(Or expr, Void context) {
        if(incompatibleTypes(expr, new BooleanType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(Sub expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(Neg expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(Not expr, Void context) {
        if(incompatibleTypes(expr, new BooleanType()))
            return null;
        return new BooleanType();
    }

    @Override
    public ValueType visit(Pos expr, Void context) {
        if(incompatibleTypes(expr, new MoneyType()))
            return null;
        return new MoneyType();
    }

    @Override
    public ValueType visit(Primary expr, Void context) {
        return expr.getValue().accept(this, context);
    }

    @Override
    public ValueType visit(Var val, Void context) {
        if (decls.containsKey(val)){

            Question q = (Question) decls.get(val);
            Type t = q.getType();
            return t.getType();

        }
        else
            return null;
    }

    @Override
    public ValueType visit(Int val, Void context) {
        return new MoneyType();
    }

    @Override
    public ValueType visit(Bool val, Void context) {
        return new BooleanType();
    }

    private boolean incompatibleTypes(BinaryExpr expr, ValueType type){
        ValueType vlhs = expr.getLhs().accept(this, null);
        ValueType vrhs = expr.getRhs().accept(this, null);

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
        ValueType v = e.accept(this, null);
        if(v == null ||
                !v.equals(type))
            return true;
        return false;
    }

}
