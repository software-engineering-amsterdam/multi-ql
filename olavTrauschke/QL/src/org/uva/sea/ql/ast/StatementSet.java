package org.uva.sea.ql.ast;

import java.util.HashSet;
import java.util.Set;

public class StatementSet extends ASTNode {
    
    private final Set<ASTNode> set;
    
    public StatementSet() {
        set = new HashSet<>();
    }
    
    //N.b. Returns self, not a boolean as in java.util.Set
    public ASTNode add(ASTNode e) {
        set.add(e);
        return this;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && getClass() == o.getClass()) {
            StatementSet other = (StatementSet) o;
            return set == null ? other.set == null : set.equals(other.set);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + set.hashCode();
        return hash;
    }
}
