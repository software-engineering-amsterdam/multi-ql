package org.uva.sea.ql.ast.visitor;

import javafx.collections.FXCollections;
import org.uva.sea.ql.ast.tree.atom.val.*;
import org.uva.sea.ql.ast.tree.atom.val.Float;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.evaluator.value.*;
import org.uva.sea.ql.evaluator.value.Bool;
import org.uva.sea.ql.evaluator.value.String;
import org.uva.sea.ql.evaluator.value.numeric.Double;
import org.uva.sea.ql.evaluator.value.numeric.Int;
import org.uva.sea.ql.evaluator.value.numeric.Numeric;

import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class EvalVisitor<FORM,STAT,TYPE> extends BaseVisitor<FORM,STAT,Value,TYPE,Value,Map<Var, EvaluatedQuestion>> {

    private final Map<Var,Expr>  decls;
    private Map<Var, EvaluatedQuestion> symbolTable;

    public EvalVisitor(Form f) {
        this.symbolTable = FXCollections.observableHashMap();
        DeclVisitor dv = new DeclVisitor();
        f.accept(dv, this.symbolTable);
        this.decls = dv.getDecls();
    }

    public EvalVisitor(Form f, Map<Var, EvaluatedQuestion> symbolTable) {
        this.symbolTable = symbolTable;
        DeclVisitor dv = new DeclVisitor();
        f.accept(dv, this.symbolTable);
        this.decls = dv.getDecls();
    }


    @Override
    public STAT visit(If stat, Map<Var, EvaluatedQuestion> symbolTable) {
        Boolean value = (Boolean) stat.getCond().accept(this, symbolTable).getValue();

        if(value == null){
            return null;
        }

        if(value){
            for(Stat s : stat.getStms())
                s.accept(this, symbolTable);
        }
        return null;
    }

    @Override
    public STAT visit(IfElse stat, Map<Var, EvaluatedQuestion> symbolTable) {
        Boolean value = (Boolean) stat.getCond().accept(this, symbolTable).getValue();

        if(value == null){
            return null;
        }

        if(value){
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
    public Value visit(Primary expr, Map<Var, EvaluatedQuestion> symbolTable) {
        return expr.accept(this, symbolTable);
    }

    @Override
    public Value visit(Pos expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric value = (Numeric) expr.accept(this, symbolTable);
        return value.Pos();
    }

    @Override
    public Value visit(Not expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric value = (Numeric) expr.accept(this, symbolTable);
        return value.Pos();
    }

    @Override
    public Value visit(Neg expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric value = (Numeric) expr.accept(this, symbolTable);
        return value.Neg();
    }

    @Override
    public Value visit(Sub expr, Map<Var, EvaluatedQuestion> symbolTable){
    Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
    Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
    return lhs.Sub(rhs);
    }

    @Override
    public Value visit(Or expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Bool lhs = (Bool) expr.getLhs().accept(this, symbolTable);
        Bool rhs = (Bool) expr.getRhs().accept(this, symbolTable);
        return lhs.Or(rhs);
    }

    @Override
    public Value visit(NEq expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Value lhs = expr.getLhs().accept(this, symbolTable);
        Value rhs = expr.getRhs().accept(this, symbolTable);
        return lhs.NEq(rhs);
    }

    @Override
    public Value visit(Mul expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.Mul(rhs);
    }

    @Override
    public Value visit(LT expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.LT(rhs);
    }

    @Override
    public Value visit(LEq expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.LEq(rhs);
    }

    @Override
    public Value visit(GT expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.GT(rhs);
    }

    @Override
    public Value visit(GEq expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.GEq(rhs);
    }

    @Override
    public Value visit(Eq expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Value lhs = expr.getLhs().accept(this, symbolTable);
        Value rhs = expr.getRhs().accept(this, symbolTable);
        return lhs.Eq(rhs);
    }

    @Override
    public Value visit(Div expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.Div(rhs);
    }

    @Override
    public Value visit(And expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Bool lhs = (Bool) expr.getLhs().accept(this, symbolTable);
        Bool rhs = (Bool) expr.getRhs().accept(this, symbolTable);
        return lhs.And(rhs);
    }

    @Override
    public Value visit(Add expr, Map<Var, EvaluatedQuestion> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.Add(rhs);
    }

    @Override
    public Value visit(org.uva.sea.ql.ast.tree.atom.val.Bool atom, Map<Var, EvaluatedQuestion> symbolTable) {
        return new Bool(atom.getValue());
    }

    @Override
    public Value visit(org.uva.sea.ql.ast.tree.atom.val.Int atom, Map<Var, EvaluatedQuestion> symbolTable) {
        return new Int(atom.getValue());
    }

    @Override
    public Value visit(Float atom, Map<Var, EvaluatedQuestion> symbolTable) {
        return new Double(atom.getValue());
    }

    @Override
    public Value visit(Str atom, Map<Var, EvaluatedQuestion> context) {
        return new String(atom.getValue());
    }


    @Override
    public Value visit(Var val, Map<Var, EvaluatedQuestion> symbolTable) {
        Expr expr;

        if(symbolTable.containsKey(val)){
            expr = symbolTable.get(val).getExpr();
        }
        else {
            expr = decls.get(val);
        }

        return expr.accept(this,symbolTable);
    }

    public Map<Var, EvaluatedQuestion> getSymbolTable() {
        return symbolTable;
    }
}
