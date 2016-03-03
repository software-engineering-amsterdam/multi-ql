package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.Bool;
import org.uva.sea.ql.ast.tree.val.Int;
import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.ast.visitor.interfaces.IExprVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class EvalVisitor<F,S,T> extends BaseVisitor<F,S,UnaryExpr,T,Val,Map<Var, Question>> {

    private final Map<Var,Expr>  decls;

    public EvalVisitor(Form f, Map<Var, Question> symbolTable) {
        DeclVisitor dv = new DeclVisitor();
        f.accept(dv, symbolTable);
        this.decls = dv.getDecls();
    }


    @Override
    public S visit(If stat, Map<Var, Question> symbolTable) {
        Bool value = stat.getCond().accept(this, symbolTable).getValue();
        if(value.getValue()){
            for(Stat s : stat.getStms())
                s.accept(this, symbolTable);
        }
        return null;
    }

    @Override
    public S visit(IfElse stat, Map<Var, Question> symbolTable) {
        Bool value = stat.getCond().accept(this, symbolTable).getValue();
        if(value.getValue()){
            for(Stat s : stat.getIfStms())
                s.accept(this, symbolTable);
        }
        else {
            for(Stat s : stat.getElseStms())
                s.accept(this, symbolTable);
        }
        return null;
    }

    @Override
    public Primary visit(Primary expr, Map<Var, Question> symbolTable) {
        return new Primary(expr.getValue().accept(this, symbolTable));
    }

    @Override
    public Primary visit(Pos expr, Map<Var, Question> symbolTable) {
        Int value = expr.getValue().accept(this, symbolTable).getValue();
        return new Primary(new Int(value.getValue()));
    }

    @Override
    public Primary visit(Not expr, Map<Var, Question> symbolTable) {
        Bool value = expr.getValue().accept(this, symbolTable).getValue();
        return new Primary(new Bool(!value.getValue()));
    }

    @Override
    public Primary visit(Neg expr, Map<Var, Question> symbolTable) {
        Int value = expr.getValue().accept(this, symbolTable).getValue();
        return new Primary(new Int(-value.getValue()));
    }

    @Override
    public Primary visit(Sub expr, Map<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Int(lhs.getValue() - rhs.getValue()));
    }

    @Override
    public Primary visit(Or expr, Map<Var, Question> symbolTable) {
        Bool lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Bool rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getValue() || rhs.getValue()));
    }

    @Override
    public Primary visit(NEq expr, Map<Var, Question> symbolTable) {
        Val lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Val rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getValue() != rhs.getValue()));
    }

    @Override
    public Primary visit(Mul expr, Map<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Int(lhs.getValue() * rhs.getValue()));
    }

    @Override
    public Primary visit(LT expr, Map<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getValue() < rhs.getValue()));
    }

    @Override
    public Primary visit(LEq expr, Map<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getValue() <= rhs.getValue()));
    }

    @Override
    public Primary visit(GT expr, Map<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getValue() > rhs.getValue()));
    }

    @Override
    public Primary visit(GEq expr, Map<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getValue() >= rhs.getValue()));
    }

    @Override
    public Primary visit(Eq expr, Map<Var, Question> symbolTable) {
        Val lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Val rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getValue() == rhs.getValue()));
    }

    @Override
    public Primary visit(Div expr, Map<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Int(lhs.getValue() / rhs.getValue()));
    }

    @Override
    public Primary visit(And expr, Map<Var, Question> symbolTable) {
        Bool lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Bool rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getValue() && rhs.getValue()));
    }

    @Override
    public Primary visit(Add expr, Map<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Int(lhs.getValue() + rhs.getValue()));
    }

    @Override
    public Val visit(Var val, Map<Var, Question> symbolTable) {
        Expr expr;

        if(symbolTable.containsKey(val)){
            expr = symbolTable.get(val).getExpr();
        }
        else {
            expr = decls.get(val);
        }

        Primary value = (Primary) expr.accept(this,symbolTable);
        return value.getValue();
    }

    @Override
    public Bool visit(Bool val, Map<Var, Question> symbolTable) {
        return val;
    }

    @Override
    public Int visit(Int val, Map<Var, Question> symbolTable) {
        return val;
    }

}
