package org.uva.sea.ql.evaluator;

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
public class EvalVisitor extends BaseVisitor<Object,Map<Var,Object>> {
    List<Question> questions;

    public EvalVisitor(Form f) {
        questions = new ArrayList<>();
        f.accept(this,new HashMap<>());
    }

    @Override
    public Object visit(Form form, Map<Var, Object> env) {
        super.visit(form, env);
        return null;
    }

    @Override
    public Object visit(Question stat, Map<Var,Object> env) {
        questions.add(stat);
        env.put(stat.getVarname(), stat.getExpr().accept(this,env));
        super.visit(stat, env);
        return null;
    }


    @Override
    public Object visit(If stat, Map<Var, Object> env) {
        if((Boolean) stat.getCond().accept(this,env)){
            for(Stat s : stat.getStms())
                s.accept(this,env);
        }
        return null;
    }

    @Override
    public Object visit(IfElse stat, Map<Var, Object> env) {
        if((Boolean)stat.getCond().accept(this,env)){
            for(Stat s : stat.getIfStms())
                s.accept(this,env);
        }
        else {
            for(Stat s : stat.getElseStms())
                s.accept(this,env);
        }
        return null;
    }

    @Override
    public Object visit(Primary expr, Map<Var,Object> env) {
        return expr.getValue().accept(this,env);
    }

    @Override
    public Object visit(Pos expr, Map<Var,Object> env) {
        Integer value = (Integer) expr.getValue().accept(this,env);
        return value;
    }

    @Override
    public Object visit(Not expr, Map<Var,Object> env) {
        Boolean value = (Boolean) expr.getValue().accept(this,env);
        return !value;
    }

    @Override
    public Object visit(Neg expr, Map<Var,Object> env) {
        Integer value = (Integer) expr.getValue().accept(this,env);
        return -value;
    }

    @Override
    public Object visit(Sub expr, Map<Var,Object> env) {
        Integer lhs = (Integer) expr.getLhs().accept(this,env);
        Integer rhs = (Integer) expr.getRhs().accept(this,env);
        return lhs - rhs;
    }

    @Override
    public Object visit(Or expr, Map<Var,Object> env) {
        Boolean lhs = (Boolean) expr.getLhs().accept(this,env);
        Boolean rhs = (Boolean) expr.getRhs().accept(this,env);
        return lhs || rhs;
    }

    @Override
    public Object visit(NEq expr, Map<Var,Object> env) {
        Object lhs = expr.getLhs().accept(this,env);
        Object rhs = expr.getRhs().accept(this,env);
        return lhs != rhs;
    }

    @Override
    public Object visit(Mul expr, Map<Var,Object> env) {
        Integer lhs = (Integer) expr.getLhs().accept(this,env);
        Integer rhs = (Integer) expr.getRhs().accept(this,env);
        return lhs * rhs;
    }

    @Override
    public Object visit(LT expr, Map<Var,Object> env) {
        Integer lhs = (Integer) expr.getLhs().accept(this,env);
        Integer rhs = (Integer) expr.getRhs().accept(this,env);
        return lhs < rhs;
    }

    @Override
    public Object visit(LEq expr, Map<Var,Object> env) {
        Integer lhs = (Integer) expr.getLhs().accept(this,env);
        Integer rhs = (Integer) expr.getRhs().accept(this,env);
        return lhs <= rhs;
    }

    @Override
    public Object visit(GT expr, Map<Var,Object> env) {
        Integer lhs = (Integer) expr.getLhs().accept(this,env);
        Integer rhs = (Integer) expr.getRhs().accept(this,env);
        return lhs > rhs;
    }

    @Override
    public Object visit(GEq expr, Map<Var,Object> env) {
        Integer lhs = (Integer) expr.getLhs().accept(this,env);
        Integer rhs = (Integer) expr.getRhs().accept(this,env);
        return lhs >= rhs;
    }

    @Override
    public Object visit(Eq expr, Map<Var,Object> env) {
        Object lhs = expr.getLhs().accept(this,env);
        Object rhs = expr.getRhs().accept(this,env);
        return lhs == rhs;
    }

    @Override
    public Object visit(Div expr, Map<Var,Object> env) {
        Integer lhs = (Integer) expr.getLhs().accept(this,env);
        Integer rhs = (Integer) expr.getRhs().accept(this,env);
        return lhs / rhs;
    }

    @Override
    public Object visit(And expr, Map<Var,Object> env) {
        Boolean lhs = (Boolean) expr.getLhs().accept(this,env);
        Boolean rhs = (Boolean) expr.getRhs().accept(this,env);
        return lhs && rhs;
    }

    @Override
    public Object visit(Add expr, Map<Var,Object> env) {
        Integer lhs = (Integer) expr.getLhs().accept(this,env);
        Integer rhs = (Integer) expr.getRhs().accept(this,env);
        return lhs + rhs;
    }

    @Override
    public Object visit(Var var, Map<Var,Object> env) {
        Object val = env.get(var);
        return val;
    }

    @Override
    public Object visit(Bool val, Map<Var,Object> env) {
        return val.getValue();
    }

    @Override
    public Object visit(Int val, Map<Var,Object> env) {
        return val.getValue();
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
