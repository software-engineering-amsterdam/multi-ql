package org.uva.sea.ql.evaluator;

import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.evaluator.value.Value;

/**
 * Created by roydewildt on 16/03/16.
 */
public class EvaluatedQuestion {
    private final String label;
    private final Var varname;
    private final Type type;
    private final Value value;

    public EvaluatedQuestion(String label, Var varname, Type type, Value value){
        this.label = label.replace("\"", "");
        this.varname = varname;
        this.type = type;
        this.value = value;
    }
}
