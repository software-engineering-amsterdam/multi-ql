package org.uva.sea.ql.ast.tree.stat.decl;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.stat.Stat;
import org.uva.sea.ql.ast.tree.type.Type;

/**
 * Created by roy on 3/18/16.
 */
public abstract class Declaration extends Stat {
    protected final String label;
    protected final Var varname;
    protected final Type type;

    public Declaration(Token token, String label, Type type, Var varname) {
        super(token);
        this.label = label.replace("\"", "");
        this.type = type;
        this.varname = varname;
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
}
