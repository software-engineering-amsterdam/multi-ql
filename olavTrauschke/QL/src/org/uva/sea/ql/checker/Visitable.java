package org.uva.sea.ql.checker;

/**
 * Interface declaring that objects of a class can accept (a certain kind of)
 * <code>Visitor</code>s. This interface implements the visitor-pattern together
 * with {@link org.uva.sea.ql.checker.Visitor Visitor}.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 * @param <T> the type of <code>Object</code>s that can be visited in the objects
 *              of a class realising this interface
 */
public interface Visitable<T extends Visitable> {
    
    /**
     * Accept a <code>Visitor</code> and have it visit
     * <code>this Visitable</code> and its children.
     * 
     * @param visitor the <code>Visitor</code> visiting <code>this Visitable</code>
     */
    void accept(Visitor<T> visitor);
    
}
