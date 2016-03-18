package org.uva.sea.ql.evaluator;

import org.uva.sea.ql.adt.value.Bool;
import org.uva.sea.ql.adt.value.Str;
import org.uva.sea.ql.adt.value.numeric.Double;
import org.uva.sea.ql.adt.value.numeric.Int;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.decl.Computed;
import org.uva.sea.ql.ast.tree.stat.decl.Question;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Number;
import org.uva.sea.ql.ast.tree.type.Text;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.adt.value.Value;

import java.util.Map;

/**
 * Created by roy on 25-2-16.
 */
public class SymbolTable extends BaseVisitor<Void,Void,Void,Value,Void,Map<Var, Value>> {
    private final Map<Var,Value> symbolTable;

    public SymbolTable(Form f, Map<Var,Value> symbolTable) {
        this.symbolTable = symbolTable;
        f.accept(this, symbolTable);
    }

    @Override
    public Void visit(Question stat, Map<Var, Value> symbolTable) {
        Value value;

        if(symbolTable.containsKey(stat.getVarname())){
            value = symbolTable.get(stat.getVarname());
        }
        else {
            value = stat.getType().accept(this, symbolTable);
        }

        symbolTable.put(stat.getVarname(), value);

        return super.visit(stat,symbolTable);
    }

    @Override
    public Void visit(Computed stat, Map<Var, Value> symbolTable) {
        Value value = (Value) stat.getExpr().accept(new ExprEvaluator(), symbolTable);
        symbolTable.put(stat.getVarname(), value);
        return super.visit(stat,symbolTable);
    }

    @Override
    public Value visit(Boolean type, Map<Var, Value> symbolTable) {
        return new Bool(false);
    }

    @Override
    public Value visit(Money type, Map<Var, Value> symbolTable) {
        return new Double(0.0);
    }

    @Override
    public Value visit(Number type, Map<Var, Value> symbolTable) {
        return new Int(0);
    }

    @Override
    public Value visit(Text type, Map<Var, Value> symbolTable) {
        return new Str("");
    }

    public Map<Var,Value> getSymbolTable() {
        return symbolTable;
    }
}
