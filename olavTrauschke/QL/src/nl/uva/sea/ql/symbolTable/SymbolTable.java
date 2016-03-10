package nl.uva.sea.ql.symbolTable;

import java.util.HashMap;
import java.util.Map;
import nl.uva.sea.ql.ast.expr.Ident;

/**
 * Class for representing symbol tables for QL. A <code>SymbolTable</code> maps
 * <code>Ident</code>s to <code>Value</code>s.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class SymbolTable {
    
    private Map<Ident,Value> symbolTable;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param identifiers an <code>Iterable</code> over the <code>Ident</code>s
     *                      the constructed <code>SymbolTable</code> should map
     */
    public SymbolTable(Iterable<Ident> identifiers) {
        symbolTable = new HashMap<>();
        for (Ident identifier : identifiers) {
            symbolTable.put(identifier, null);
        }
    }
    
    /**
     * Update the <code>Value</code> for a given <code>Ident</code> in
     * <code>this SymbolTable</code>.
     * 
     * @param identifier the <code>Ident</code> to update the <code>Value</code>
     *                      of, that must be in <code>this SymbolTable</code>
     * @param newValue the new <code>Value this SymbolTable</code> should map
     *                  <code>identifier</code> to
     */
    public void update(Ident identifier, Value newValue) {
        assert symbolTable.containsKey(identifier);
        symbolTable.put(identifier, newValue);
    }
    
    /**
     * Get the <code>Value this SymbolTable</code> maps a given
     * <code>Ident</code> to.
     * 
     * @param identifier the <code>Ident</code> to get the <code>Value</code> of
     * @return the <code>Value this SymbolTable</code> maps <code>identifier</code>
     *          to
     */
    public Value get(Ident identifier) {
        return symbolTable.get(identifier);
    }
    
}
