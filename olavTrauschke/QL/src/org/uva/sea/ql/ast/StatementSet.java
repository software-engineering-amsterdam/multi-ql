package org.uva.sea.ql.ast;

import java.util.HashSet;
import java.util.Set;

public class StatementSet extends ASTNode {
    
    public static final int HASH_ORIGIN = 119;
    
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
        if (o == null || getClass() != o.getClass()) return false;
        
        StatementSet other = (StatementSet) o;
        return set == null ? other.set == null : set.equals(other.set);
    }

    @Override
    public int hashCode() {
        return HASH_ORIGIN + set.hashCode();
    }
}
