package org.uva.sea.ql.checker;

/**
 * Interface declaring that the objects of a class can visit (some kind of)
 * <code>Object</code>s. This interface implements the visitor-pattern together
 * with {@link org.uva.sea.ql.checker.Visitable Visitable}.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 * @param <T> the type of <code>Object</code>s objects of a class realizing this
 *              interface can visit
 */
public interface Visitor<T extends Visitable> {
    
    /**
     * Process <code>Visitable</code> object.
     * 
     * @param toVisit the <code>Visitable</code> to process
     */
    void visit(T toVisit);
    
}
