package org.uva.sea.ql.ast.visitor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.uva.sea.ql.ast.tree.atom.val.*;
import org.uva.sea.ql.ast.tree.atom.val.numeric.Float;
import org.uva.sea.ql.ast.tree.atom.val.numeric.Int;
import org.uva.sea.ql.ast.tree.atom.val.numeric.Numeric;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.stat.*;

import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class EvalVisitor<FORM,STAT,TYPE> extends BaseVisitor<FORM,STAT,UnaryExpr,TYPE,Val,ObservableMap<Var, Question>> {

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
        Bool value = stat.getCond().accept(this, symbolTable).getValue();

        if(value.getValue() == null){
            return null;
        }

        if(value.getValue()){
            for(Stat s : stat.getStms())
                s.accept(this, symbolTable);
        }
        return null;
    }

    @Override
    public STAT visit(IfElse stat, ObservableMap<Var, Question> symbolTable) {
        Bool value = stat.getCond().accept(this, symbolTable).getValue();

        if(value.getValue() == null){
            return null;
        }

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
    public Primary visit(Primary expr, ObservableMap<Var, Question> symbolTable) {
        return new Primary(expr.getValue().accept(this, symbolTable));
    }

    @Override
    public Primary visit(Pos expr, ObservableMap<Var, Question> symbolTable) {
        Numeric value = expr.getValue().accept(this, symbolTable).getValue();
        return new Primary(value.Pos());
    }

    @Override
    public Primary visit(Not expr, ObservableMap<Var, Question> symbolTable) {
        Bool value = expr.getValue().accept(this, symbolTable).getValue();
        return new Primary(new Bool(value.getLine(), !value.getValue()));
    }

    @Override
    public Primary visit(Neg expr, ObservableMap<Var, Question> symbolTable) {
        Numeric value = expr.getValue().accept(this, symbolTable).getValue();
        return new Primary(value.Neg());
    }

    @Override
    public Primary visit(Sub expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Numeric rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(lhs.Sub(rhs));
    }

    @Override
    public Primary visit(Or expr, ObservableMap<Var, Question> symbolTable) {
        Bool lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Bool rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getLine(), lhs.getValue() || rhs.getValue()));
    }

    @Override
    public Primary visit(NEq expr, ObservableMap<Var, Question> symbolTable) {
        Val lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Val rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getLine(), !lhs.getValue().equals(rhs.getValue())));
    }

    @Override
    public Primary visit(Mul expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Numeric rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(lhs.Mul(rhs));
    }

    @Override
    public Primary visit(LT expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Numeric rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getLine(), (Double) lhs.getValue() < (Double) rhs.getValue()));
    }

    @Override
    public Primary visit(LEq expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Numeric rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getLine(), (Double) lhs.getValue() <= (Double) rhs.getValue()));
    }

    @Override
    public Primary visit(GT expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Numeric rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getLine(), (Double) lhs.getValue() > (Double) rhs.getValue()));
    }

    @Override
    public Primary visit(GEq expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Numeric rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getLine(), (Double) lhs.getValue() >= (Double) rhs.getValue()));
    }

    @Override
    public Primary visit(Eq expr, ObservableMap<Var, Question> symbolTable) {
        Val lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Val rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getLine(), lhs.getValue().equals(rhs.getValue())));
    }

    @Override
    public Primary visit(Div expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Numeric rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(lhs.Div(rhs));
    }

    @Override
    public Primary visit(And expr, ObservableMap<Var, Question> symbolTable) {
        Bool lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Bool rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(new Bool(lhs.getLine(), lhs.getValue() && rhs.getValue()));
    }

    @Override
    public Primary visit(Add expr, ObservableMap<Var, Question> symbolTable) {
        Numeric lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Numeric rhs = expr.getRhs().accept(this, symbolTable).getValue();
        return new Primary(lhs.Add(rhs));
    }

    @Override
    public Val visit(Bool atom, ObservableMap<Var, Question> symbolTable) {
        return atom;
    }

    @Override
    public Val visit(Int atom, ObservableMap<Var, Question> symbolTable) {
        return atom;
    }

    @Override
    public Val visit(Float atom, ObservableMap<Var, Question> symbolTable) {
        return atom;
    }

    @Override
    public Val visit(Str atom, ObservableMap<Var, Question> context) {
        return atom;
    }


    @Override
    public Val visit(Var val, ObservableMap<Var, Question> symbolTable) {
        Expr expr;

        if(symbolTable.containsKey(val)){
            expr = symbolTable.get(val).getExpr();
        }
        else {
            expr = decls.get(val);
        }

        Primary value = (Primary) expr.accept(this,symbolTable);
        return (Val) value.getValue();
    }

    public Map<Var, Question> getSymbolTable() {
        return symbolTable;
    }
}
