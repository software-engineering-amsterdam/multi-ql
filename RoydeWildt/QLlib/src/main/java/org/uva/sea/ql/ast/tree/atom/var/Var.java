package org.uva.sea.ql.ast.tree.atom.var;

import org.uva.sea.ql.ast.tree.atom.Literal;
import org.uva.sea.ql.ast.visitor.interfaces.AtomVisitor;

/**
 * Created by roydewildt on 04/02/16.
 */
public class  Var extends Literal {
    private final String name;

    public Var(int line, String value) {
        super(line);
        this.name = value;
    }

    public String getName() {
        return name;
    }

    @Override
    public <ATOM, CONTEXT> ATOM accept(AtomVisitor<ATOM, CONTEXT> visitor, CONTEXT context) {
        return visitor.visit(this,context);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Var){
            Var toCompare = (Var) obj;
            return this.name.equals(toCompare.name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
