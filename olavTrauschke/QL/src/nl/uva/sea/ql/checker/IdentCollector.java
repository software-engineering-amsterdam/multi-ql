package nl.uva.sea.ql.checker;

import java.util.HashSet;
import java.util.Set;
import nl.uva.sea.ql.ast.expr.Ident;

/**
 * Visitor to collect all <code>Ident</code>s in a part of an ast.
 * 
 * @author Olav Trauschke
 * @version 9-mrt-2016
 */
public class IdentCollector implements ASTVisitor {
    
    private final Set<Ident> identifiers;
    
    /**
     * Constructor for objects of this class.
     */
    public IdentCollector() {
        identifiers = new HashSet<>();
    }
    
    /**
     * Add an <code>Ident</code> to the <code>Set</code> of <code>Ident</code>s
     * <code>this IdentCollector visit</code>ed.
     * 
     * @param i the <code>Ident</code> to add
     */
    @Override
    public void visit(Ident i) {
        identifiers.add(i);
    }
    
    /**
     * @return an <code>Iterable</code> containing the <code>Ident</code>s that
     *          were <code>visit</code>ed by <code>this IdentCollector</code>
     */
    public Iterable<Ident> getIdentifiers() {
        return identifiers;
    }
    
}
