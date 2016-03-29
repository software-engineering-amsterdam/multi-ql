package org.uva.sea.ql.evaluator;

import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.evaluator.adt.value.Value;

/**
 * Created by roydewildt on 16/03/16.
 */
public class EvaluatedQuestion {
    private final String label;
    private final Var varname;
    private final Type type;
    private final Value value;
    private final boolean computed;

    public EvaluatedQuestion(String label, Var varname, Type type, Value value, boolean computed){
        this.label = label.replace("\"", "");
        this.varname = varname;
        this.type = type;
        this.value = value;
        this.computed = computed;
    }

    public String getLabel() {
        return label;
    }

    public Var getVarname() {
        return varname;
    }

    public Type getType() {
        return type;
    }

    public Value getValue() {
        return value;
    }

    public boolean isComputed() {
        return computed;
    }
}
