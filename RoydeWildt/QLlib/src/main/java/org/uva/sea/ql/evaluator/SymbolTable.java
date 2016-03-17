package org.uva.sea.ql.evaluator;

import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.evaluator.value.Value;

import java.util.Map;

/**
 * Created by roy on 25-2-16.
 */
public class SymbolTable extends BaseVisitor<Void,Void,Void,Void,Void,Map<Var, Value>> {

    private final Map<Var,Value> symbolTable;
    private final ExprEvaluator evaluator;

    public SymbolTable(Form f, Map<Var,Value> symbolTable) {
        this.symbolTable = symbolTable;
        this.evaluator = new ExprEvaluator();
        f.accept(this, symbolTable);
    }

    @Override
    public Void visit(Question stat, Map<Var, Value> symbolTable) {

        Value value;

        if(symbolTable.containsKey(stat.getVarname())){
            value = symbolTable.get(stat.getVarname());
        }
        else {
            value = (Value) stat.getExpr().accept(evaluator, symbolTable);
        }

        symbolTable.put(stat.getVarname(), value);
        return super.visit(stat,null);
    }

    public Map<Var,Value> getSymbolTable() {
        return symbolTable;
    }
}
