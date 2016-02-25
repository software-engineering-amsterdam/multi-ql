package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class EvalVisitor<F,S,T> extends BaseVisitor<F,S,Object,T,Object> {
    
    List<Question> questions;
    Map<Var,Object> decls;

    public EvalVisitor(Form f) {

        questions = new ArrayList<>();
        decls = new HashMap<>();

        f.accept(this);
    }

    @Override
    public F visit(Form form) {
        super.visit(form);
        return null;
    }

    @Override
    public S visit(Question stat) {
        questions.add(stat);
        decls.put(stat.getVarname(), stat.getExpr().accept(this));
        super.visit(stat);
        return null;
    }


    @Override
    public S visit(If stat) {
        if((Boolean) stat.getCond().accept(this)){
            for(Stat s : stat.getStms())
                s.accept(this);
        }
        return null;
    }

    @Override
    public S visit(IfElse stat) {
        if((Boolean)stat.getCond().accept(this)){
            for(Stat s : stat.getIfStms())
                s.accept(this);
        }
        else {
            for(Stat s : stat.getElseStms())
                s.accept(this);
        }
        return null;
    }

    @Override
    public Object visit(Primary expr) {
        return expr.getValue().accept(this);
    }

    @Override
    public Object visit(Pos expr) {
        Integer value = (Integer) expr.getValue().accept(this);
        return value;
    }

    @Override
    public Object visit(Not expr) {
        Boolean value = (Boolean) expr.getValue().accept(this);
        return !value;
    }

    @Override
    public Object visit(Neg expr) {
        Integer value = (Integer) expr.getValue().accept(this);
        return -value;
    }

    @Override
    public Object visit(Sub expr) {
        Integer lhs = (Integer) expr.getLhs().accept(this);
        Integer rhs = (Integer) expr.getRhs().accept(this);
        return lhs - rhs;
    }

    @Override
    public Object visit(Or expr) {
        Boolean lhs = (Boolean) expr.getLhs().accept(this);
        Boolean rhs = (Boolean) expr.getRhs().accept(this);
        return lhs || rhs;
    }

    @Override
    public Object visit(NEq expr) {
        Object lhs = expr.getLhs().accept(this);
        Object rhs = expr.getRhs().accept(this);
        return lhs != rhs;
    }

    @Override
    public Object visit(Mul expr) {
        Integer lhs = (Integer) expr.getLhs().accept(this);
        Integer rhs = (Integer) expr.getRhs().accept(this);
        return lhs * rhs;
    }

    @Override
    public Object visit(LT expr) {
        Integer lhs = (Integer) expr.getLhs().accept(this);
        Integer rhs = (Integer) expr.getRhs().accept(this);
        return lhs < rhs;
    }

    @Override
    public Object visit(LEq expr) {
        Integer lhs = (Integer) expr.getLhs().accept(this);
        Integer rhs = (Integer) expr.getRhs().accept(this);
        return lhs <= rhs;
    }

    @Override
    public Object visit(GT expr) {
        Integer lhs = (Integer) expr.getLhs().accept(this);
        Integer rhs = (Integer) expr.getRhs().accept(this);
        return lhs > rhs;
    }

    @Override
    public Object visit(GEq expr) {
        Integer lhs = (Integer) expr.getLhs().accept(this);
        Integer rhs = (Integer) expr.getRhs().accept(this);
        return lhs >= rhs;
    }

    @Override
    public Object visit(Eq expr) {
        Object lhs = expr.getLhs().accept(this);
        Object rhs = expr.getRhs().accept(this);
        return lhs == rhs;
    }

    @Override
    public Object visit(Div expr) {
        Integer lhs = (Integer) expr.getLhs().accept(this);
        Integer rhs = (Integer) expr.getRhs().accept(this);
        return lhs / rhs;
    }

    @Override
    public Object visit(And expr) {
        Boolean lhs = (Boolean) expr.getLhs().accept(this);
        Boolean rhs = (Boolean) expr.getRhs().accept(this);
        return lhs && rhs;
    }

    @Override
    public Object visit(Add expr) {
        Integer lhs = (Integer) expr.getLhs().accept(this);
        Integer rhs = (Integer) expr.getRhs().accept(this);
        return lhs + rhs;
    }

    @Override
    public Object visit(Var var) {
        Object val = decls.get(var);
        return val;
    }

    @Override
    public Object visit(Bool val) {
        return val.getValue();
    }

    @Override
    public Object visit(Int val) {
        return val.getValue();
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
