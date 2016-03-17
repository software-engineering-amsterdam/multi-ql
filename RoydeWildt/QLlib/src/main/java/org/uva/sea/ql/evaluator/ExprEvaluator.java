package org.uva.sea.ql.evaluator;

import org.uva.sea.ql.ast.tree.atom.val.Float;
import org.uva.sea.ql.ast.tree.atom.val.Str;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.evaluator.value.Bool;
import org.uva.sea.ql.evaluator.value.String;
import org.uva.sea.ql.evaluator.value.Value;
import org.uva.sea.ql.evaluator.value.numeric.Double;
import org.uva.sea.ql.evaluator.value.numeric.Int;
import org.uva.sea.ql.evaluator.value.numeric.Numeric;

import java.util.Map;

/**
 * Created by roydewildt on 22/02/16.
 */
public class ExprEvaluator<FORM,TYPE> extends BaseVisitor<FORM,Void,Value,TYPE,Value,Map<Var, Value>> {

    public ExprEvaluator() {
    }

    @Override
    public Value visit(Primary expr, Map<Var, Value> symbolTable) {
        Value value = expr.getValue().accept(this, symbolTable);
        return value;
    }

    @Override
    public Value visit(Pos expr, Map<Var, Value> symbolTable) {
        Numeric value = (Numeric) expr.getValue().accept(this, symbolTable);
        return value.Pos();
    }

    @Override
    public Value visit(Not expr, Map<Var, Value> symbolTable) {
        Bool value = (Bool) expr.getValue().accept(this, symbolTable);
        return value.Not();
    }

    @Override
    public Value visit(Neg expr, Map<Var, Value> symbolTable) {
        Numeric value = (Numeric) expr.getValue().accept(this, symbolTable);
        return value.Neg();
    }

    @Override
    public Value visit(Sub expr, Map<Var, Value> symbolTable){
    Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
    Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
    return lhs.Sub(rhs);
    }

    @Override
    public Value visit(Or expr, Map<Var, Value> symbolTable) {
        Bool lhs = (Bool) expr.getLhs().accept(this, symbolTable);
        Bool rhs = (Bool) expr.getRhs().accept(this, symbolTable);
        return lhs.Or(rhs);
    }

    @Override
    public Value visit(NEq expr, Map<Var, Value> symbolTable) {
        Value lhs = expr.getLhs().accept(this, symbolTable);
        Value rhs = expr.getRhs().accept(this, symbolTable);
        return lhs.NEq(rhs);
    }

    @Override
    public Value visit(Mul expr, Map<Var, Value> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.Mul(rhs);
    }

    @Override
    public Value visit(LT expr, Map<Var, Value> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.LT(rhs);
    }

    @Override
    public Value visit(LEq expr, Map<Var, Value> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.LEq(rhs);
    }

    @Override
    public Value visit(GT expr, Map<Var, Value> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.GT(rhs);
    }

    @Override
    public Value visit(GEq expr, Map<Var, Value> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.GEq(rhs);
    }

    @Override
    public Value visit(Eq expr, Map<Var, Value> symbolTable) {
        Value lhs = expr.getLhs().accept(this, symbolTable);
        Value rhs = expr.getRhs().accept(this, symbolTable);
        return lhs.Eq(rhs);
    }

    @Override
    public Value visit(Div expr, Map<Var, Value> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.Div(rhs);
    }

    @Override
    public Value visit(And expr, Map<Var, Value> symbolTable) {
        Bool lhs = (Bool) expr.getLhs().accept(this, symbolTable);
        Bool rhs = (Bool) expr.getRhs().accept(this, symbolTable);
        return lhs.And(rhs);
    }

    @Override
    public Value visit(Add expr, Map<Var, Value> symbolTable) {
        Numeric lhs = (Numeric) expr.getLhs().accept(this, symbolTable);
        Numeric rhs = (Numeric) expr.getRhs().accept(this, symbolTable);
        return lhs.Add(rhs);
    }

    @Override
    public Value visit(org.uva.sea.ql.ast.tree.atom.val.Bool atom, Map<Var, Value> symbolTable) {
        return new Bool(atom.getValue());
    }

    @Override
    public Value visit(org.uva.sea.ql.ast.tree.atom.val.Int atom, Map<Var, Value> symbolTable) {
        return new Int(atom.getValue());
    }

    @Override
    public Value visit(Float atom, Map<Var, Value> symbolTable) {
        return new Double(atom.getValue());
    }

    @Override
    public Value visit(Str atom, Map<Var, Value> context) {
        return new String(atom.getValue());
    }


    @Override
    public Value visit(Var val, Map<Var, Value> symbolTable) {
        return symbolTable.get(val);
    }
}
