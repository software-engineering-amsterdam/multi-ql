package org.uva.sea.ql.ast.visitor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.uva.sea.ql.ast.tree.atom.val.*;
import org.uva.sea.ql.ast.tree.atom.val.Float;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.stat.*;
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
public class EvalVisitor<FORM,STAT,TYPE> extends BaseVisitor<FORM,STAT,Value,TYPE,Value,ObservableMap<Var, Question>> {

    private final Map<Var,Expr>  decls;
    private Map<Var, Question> symbolTable;

    public EvalVisitor(Form f) {
        this.symbolTable = FXCollections.observableHashMap();
        DeclVisitor dv = new DeclVisitor();
        f.accept(dv, this.symbolTable);
        this.decls = dv.getDecls();
    }

    public EvalVisitor(Form f, ObservableMap<Var, Question> symbolTable) {
        this.symbolTable = symbolTable;
        DeclVisitor dv = new DeclVisitor();
        f.accept(dv, this.symbolTable);
        this.decls = dv.getDecls();
    }


    @Override
    public STAT visit(If stat, ObservableMap<Var, Question> symbolTable) {
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
    public STAT visit(IfElse stat, ObservableMap<Var, Question> symbolTable) {
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
    public Value visit(Primary expr, ObservableMap<Var, Question> symbolTable) {
        return expr.accept(this, symbolTable);
    }

    @Override
    public Value visit(Pos expr, ObservableMap<Var, Question> symbolTable) {
        Numeric value = (Numeric) expr.accept(this, symbolTable);
        return value.Pos();
    }

    @Override
    public Value visit(Not expr, ObservableMap<Var, Question> symbolTable) {
        Numeric value = (Numeric) expr.accept(this, symbolTable);
        return value.Pos();
    }

    @Override
    public Value visit(Neg expr, ObservableMap<Var, Question> symbolTable) {
        Numeric value = (Numeric) expr.accept(this, symbolTable);
        return value.Neg();
    }

    @Override
    public Value visit(Sub expr, ObservableMap<Var, Question> symbolTable){
    Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
    Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
    return lhs.Sub(rhs);
    }

    @Override
    public Value visit(Or expr, ObservableMap<Var, Question> symbolTable) {
        Bool lhs = (Bool) expr.getLhs().accept(this, symbolTable);
        Bool rhs = (Bool) expr.getRhs().accept(this, symbolTable);
        return lhs.Or(rhs);
    }

    @Override
    public Value visit(NEq expr, ObservableMap<Var, Question> symbolTable) {
        Value lhs = expr.getLhs().accept(this, symbolTable);
        Value rhs = expr.getRhs().accept(this, symbolTable);
        return lhs.NEq(rhs);
    }

    @Override
    public Value visit(Mul expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.Mul(rhs);
    }

    @Override
    public Value visit(LT expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.LT(rhs);
    }

    @Override
    public Value visit(LEq expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.LEq(rhs);
    }

    @Override
    public Value visit(GT expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.GT(rhs);
    }

    @Override
    public Value visit(GEq expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.GEq(rhs);
    }

    @Override
    public Value visit(Eq expr, ObservableMap<Var, Question> symbolTable) {
        Value lhs = expr.getLhs().accept(this, symbolTable);
        Value rhs = expr.getRhs().accept(this, symbolTable);
        return lhs.Eq(rhs);
    }

    @Override
    public Value visit(Div expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.Div(rhs);
    }

    @Override
    public Value visit(And expr, ObservableMap<Var, Question> symbolTable) {
        Bool lhs = (Bool) expr.getLhs().accept(this, symbolTable);
        Bool rhs = (Bool) expr.getRhs().accept(this, symbolTable);
        return lhs.And(rhs);
    }

    @Override
    public Value visit(Add expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.Add(rhs);
    }

    @Override
    public Value visit(org.uva.sea.ql.ast.tree.atom.val.Bool atom, ObservableMap<Var, Question> symbolTable) {
        return new Bool(atom.getValue());
    }

    @Override
    public Value visit(org.uva.sea.ql.ast.tree.atom.val.Int atom, ObservableMap<Var, Question> symbolTable) {
        return new Int(atom.getValue());
    }

    @Override
    public Value visit(Float atom, ObservableMap<Var, Question> symbolTable) {
        return new Double(atom.getValue());
    }

    @Override
    public Value visit(Str atom, ObservableMap<Var, Question> context) {
        return new String(atom.getValue());
    }


    @Override
    public Value visit(Var val, ObservableMap<Var, Question> symbolTable) {
        Expr expr;

        if(symbolTable.containsKey(val)){
            expr = symbolTable.get(val).getExpr();
        }
        else {
            expr = decls.get(val);
        }

        return expr.accept(this,symbolTable);
    }

    public Map<Var, Question> getSymbolTable() {
        return symbolTable;
    }
}
