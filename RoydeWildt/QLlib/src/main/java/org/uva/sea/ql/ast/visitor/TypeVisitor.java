package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Text;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Str;
import org.uva.sea.ql.ast.tree.var.Var;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roydewildt on 17/02/16.
 */

public class TypeVisitor<FORM,STAT,TYPE> extends BaseVisitor<FORM,STAT,Type,TYPE,Type,Type,Void> {
    private final Map<Node,Node> decls = new HashMap<>();

    @Override
    public STAT visit(Question stat, Void context) {
        decls.put(stat.getVarname(), stat);
        return null;
    }

    @Override
    public Type visit(Add expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Money();
    }

    @Override
    public Type visit(And expr, Void context) {
        if(incompatibleTypes(expr, new Boolean()))
            return null;
        return new Boolean();
    }

    @Override
    public Type visit(Div expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Money();
    }

    @Override
    public Type visit(Eq expr, Void context) {
        if(!incompatibleTypes(expr, new Boolean()) ||
                !incompatibleTypes(expr, new Money()) ||
                !incompatibleTypes(expr, new Text())){
            return new Boolean();
        }
        return null;
    }

    @Override
    public Type visit(GEq expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Boolean();
    }

    @Override
    public Type visit(GT expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Boolean();
    }

    @Override
    public Type visit(LEq expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Boolean();
    }

    @Override
    public Type visit(LT expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Boolean();
    }

    @Override
    public Type visit(Mul expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Money();
    }

    @Override
    public Type visit(NEq expr, Void context) {
        if(!incompatibleTypes(expr, new Boolean()) ||
                !incompatibleTypes(expr, new Money()) ||
                !incompatibleTypes(expr, new Text())){
            return new Boolean();
        }
        return null;

    }

    @Override
    public Type visit(Or expr, Void context) {
        if(incompatibleTypes(expr, new Boolean()))
            return null;
        return new Boolean();
    }

    @Override
    public Type visit(Sub expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Money();
    }

    @Override
    public Type visit(Neg expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Money();
    }

    @Override
    public Type visit(Not expr, Void context) {
        if(incompatibleTypes(expr, new Boolean()))
            return null;
        return new Boolean();
    }

    @Override
    public Type visit(Pos expr, Void context) {
        if(incompatibleTypes(expr, new Money()))
            return null;
        return new Money();
    }

    @Override
    public Type visit(Primary expr, Void context) {
        return expr.getValue().accept(this, context);
    }

    @Override
    public Type visit(Int val, Void context) {
        return new Money();
    }

    @Override
    public Type visit(Bool val, Void context) {
        return new Boolean();
    }

    @Override
    public Type visit(Str val, Void context) {
        return new Text();
    }

    @Override
    public Type visit(Var val, Void context) {
        if (decls.containsKey(val)){

            Question q = (Question) decls.get(val);
            Type t = q.getType();
            return t.getType();

        }
        else
            return null;
    }

    private boolean incompatibleTypes(BinaryExpr expr, Type type){
        Type vlhs = expr.getLhs().accept(this, null);
        Type vrhs = expr.getRhs().accept(this, null);

        if(vlhs == null ||
                vrhs == null ||
                !vlhs.equals(type) ||
                !vrhs.equals(type) ||
                !vlhs.equals(vrhs))
            return true;
        return false;
    }

    private boolean incompatibleTypes(UnaryExpr expr, Type type){
        Expr e = expr.getValue();
        Type v = e.accept(this, null);
        if(v == null ||
                !v.equals(type))
            return true;
        return false;
    }

}
