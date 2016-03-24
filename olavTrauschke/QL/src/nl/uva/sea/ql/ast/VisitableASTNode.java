package nl.uva.sea.ql.ast;

import nl.uva.sea.ql.ASTVisitor;
import nl.uva.sea.ql.interpreter.QuestionGeneratingVisitor;

/**
 * Interface declaring that objects of a class can accept <code>ASTVisitor</code>s.
 * This interface implements the visitor-pattern together with
 * {@link nl.uva.sea.ql.checker.ASTVisitor ASTVisitor}.
 * 
 * @author Olav Trauschke
 * @version 24-mar-2016
 */
public interface VisitableASTNode {
    
    /**
     * Accept an <code>ASTVisitor</code> and have it <code>visit</code> the
     * children of <code>this VisitableASTNode</code> and
     * <code>this VisitableASTNode</code> itself.
     * 
     * @param visitor the <code>ASTVisitor</code> visiting <code>this VisitableASTNode</code>
     */
    void accept(ASTVisitor visitor);
    
    /**
     * Accept a <code>QuestionGeneratingVisitor</code> and have it <code>visit</code>
     * the children of <code>this VisitableASTNode</code> and
     * <code>this VisitableASTNode</code> itself. Dispatches to
     * {@link #accept(nl.uva.sea.ql.ASTVisitor)  accept(ASTVisitor)} by default.
     * 
     * @param visitor the <code>QuestionGeneratingVisitor</code> visiting
     *                  <code>this VisitableASTNode</code>
     */
    default void accept(QuestionGeneratingVisitor visitor) {
        accept((ASTVisitor) visitor);
    }
    
}
