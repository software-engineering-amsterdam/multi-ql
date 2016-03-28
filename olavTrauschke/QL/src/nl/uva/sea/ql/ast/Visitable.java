package nl.uva.sea.ql.ast;

import nl.uva.sea.ql.interpreter.DisplayableQuestionGenerator;
import nl.uva.sea.ql.generalPurposeVisitors.Visitor;

/**
 * Interface declaring that objects of a class can accept <code>Visitor</code>s.
 * This interface implements the visitor-pattern together with
 * {@link nl.uva.sea.ql.generalPurposeVisitors.Visitor Visitor}.
 * 
 * @author Olav Trauschke
 * @version 25-mar-2016
 */
public interface Visitable {
    
    /**
     * Accept a <code>Visitor</code> and have it <code>visit</code> the
     * children of <code>this Visitable</code> and
     * <code>this Visitable</code> itself.
     * 
     * @param visitor the <code>Visitor</code> visiting <code>this Visitable</code>
     */
    void accept(Visitor visitor);
    
    /**
     * Accept a <code>DisplayableQuestionGenerator</code> and have it <code>visit</code>
     * the children of <code>this Visitable</code> and
     * <code>this Visitable</code> itself. Dispatches to
     * {@link #accept(nl.uva.sea.ql.generalPurposeVisitors.Visitor)  accept(Visitor)}
     * by default.
     * 
     * @param visitor the <code>DisplayableQuestionGenerator</code> visiting
     *                  <code>this Visitable</code>
     */
    default void accept(DisplayableQuestionGenerator visitor) {
        accept((Visitor) visitor);
    }
    
}
