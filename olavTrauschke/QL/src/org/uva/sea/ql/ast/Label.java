package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.Str;

/**
 * Representation of <code>Label</code>s for questions in an AST.
 * 
 * @author Olav Trauschke, 10329463
 * @version 24-feb-2016
 */
public class Label extends ASTNode {
    
    /**
     * Start value used to calculate hashes for objects of this class.
     */
    public static final int HASH_ORIGIN = 237;
    
    private final String text;
    
    /**
     * Constructor for <code>Label</code>s.
     * 
     * @param theText a <code>String</code> containing the text to display on 
     *                  the constructed <code>Label</code>
     */
    public Label(String theText) {
        assert theText != null;
        text = theText;
    }
    
    /**
     * Constructor for <code>Label</code>s.
     * 
     * @param theText a <code>Str</code> containing the text to display on 
     *                  the constructed <code>Label</code>
     */
    public Label(Str theText) {
        assert theText != null;
        text = theText.getValue();
    }
    
    /**
     * Compares <code>this Label</code> to another <code>Object</code>. A
     * <code>Label</code> is considered equal only to other objects of this
     * class for which <code>theText</code> represents the same text as its own
     * value for this field.
     * 
     * @param o the <code>Object</code> to compare to <code>this Label</code>
     * @return <code>true</code> if and only if o is equal to <code>this Label</code> 
     */
    @Override
    public boolean equals(Object o) {
        return o != null
                && getClass() == o.getClass()
                && text.equals(((Label) o).text);
    }

    /**
     * @return an <code>int</code> containing a hash for <code>this Label</code>
     */
    @Override
    public int hashCode() {
        return HASH_ORIGIN + text.hashCode();
    }
}
