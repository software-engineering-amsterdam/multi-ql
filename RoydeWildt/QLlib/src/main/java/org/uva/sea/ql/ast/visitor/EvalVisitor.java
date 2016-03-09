package org.uva.sea.ql.ast.visitor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.*;

import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class EvalVisitor<F,S,T> extends BaseVisitor<F,S,UnaryExpr,T,Val,ObservableMap<Var, Question>> {

    private final Map<Var,Expr>  decls;
    private ObservableMap<Var, Question> symbolTable;

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
    public S visit(If stat, ObservableMap<Var, Question> symbolTable) {
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
    public S visit(IfElse stat, ObservableMap<Var, Question> symbolTable) {
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

        try {
            return new Primary(expr.getValue().accept(this, symbolTable));
        }
        catch (Exception e){
            System.out.println("Log: invalid value x");
            return null;
        }
    }

    @Override
    public Primary visit(Pos expr, ObservableMap<Var, Question> symbolTable) {
        Int value = expr.getValue().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Int(value.getLine(), value.getValue()));
        }
        catch (Exception e){
            System.out.println("Log: invalid argument for x in +x");
            return null;
        }
    }

    @Override
    public Primary visit(Not expr, ObservableMap<Var, Question> symbolTable) {
        Bool value = expr.getValue().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Bool(value.getLine(), !value.getValue()));
        }
        catch (Exception e){
            System.out.println("Log: invalid argument for x in !x");
            return null;
        }
    }

    @Override
    public Primary visit(Neg expr, ObservableMap<Var, Question> symbolTable) {
        Int value = expr.getValue().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Int(value.getLine(), -value.getValue()));
        }
        catch (Exception e){
            System.out.println("Log: invalid argument for x in -x");
            return null;
        }
    }

    @Override
    public Primary visit(Sub expr, ObservableMap<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Int(lhs.getLine(), lhs.getValue() - rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x - y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(Or expr, ObservableMap<Var, Question> symbolTable) {
        Bool lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Bool rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Bool(lhs.getLine(), lhs.getValue() || rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x || y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(NEq expr, ObservableMap<Var, Question> symbolTable) {
        Val lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Val rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Bool(lhs.getLine(), !lhs.getValue().equals(rhs.getValue())));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x != y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(Mul expr, ObservableMap<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Int(lhs.getLine(), lhs.getValue() * rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x * y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(LT expr, ObservableMap<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Bool(lhs.getLine(), lhs.getValue() < rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x < y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(LEq expr, ObservableMap<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Bool(lhs.getLine(), lhs.getValue() <= rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x <= y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(GT expr, ObservableMap<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Bool(lhs.getLine(), lhs.getValue() > rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x > y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(GEq expr, ObservableMap<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Bool(lhs.getLine(), lhs.getValue() >= rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x >= y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(Eq expr, ObservableMap<Var, Question> symbolTable) {
        Val lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Val rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Bool(lhs.getLine(), lhs.getValue().equals(rhs.getValue())));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x == y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(Div expr, ObservableMap<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Int(lhs.getLine(), lhs.getValue() / rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x / y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(And expr, ObservableMap<Var, Question> symbolTable) {
        Bool lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Bool rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Bool(lhs.getLine(), lhs.getValue() && rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x && y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
    }

    @Override
    public Primary visit(Add expr, ObservableMap<Var, Question> symbolTable) {
        Int lhs = expr.getLhs().accept(this, symbolTable).getValue();
        Int rhs = expr.getRhs().accept(this, symbolTable).getValue();

        try {
            return new Primary(new Int(lhs.getLine(), lhs.getValue() + rhs.getValue()));
        }
        catch (Exception e){
            System.out.println(String.format("Log: invalid argument for %1$s in x + y", getBadArgumentLetter(lhs, rhs)));
            return null;
        }
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
        return value.getValue();
    }

    @Override
    public Val visit(Bool val, ObservableMap<Var, Question> symbolTable) {
        return val;
    }

    @Override
    public Val visit(Int val, ObservableMap<Var, Question> symbolTable) {
        return val;
    }

    @Override
    public Val visit(Str val, ObservableMap<Var, Question> context) {
        return val;
    }

    private String getBadArgumentLetter(Val lhs, Val rhs){
        if(lhs.getValue() == null){
            return "x";
        }
        else if(rhs.getValue() == null){
            return "y";
        }
        else{
            return null;
        }
    }

    public ObservableMap<Var, Question> getSymbolTable() {
        return symbolTable;
    }
}
