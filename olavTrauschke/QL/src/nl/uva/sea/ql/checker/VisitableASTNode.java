package nl.uva.sea.ql.checker;

import nl.uva.sea.ql.ASTVisitor;

/**
 * Interface declaring that objects of a class can accept <code>ASTVisitor</code>s.
 * This interface implements the visitor-pattern together with
 * {@link nl.uva.sea.ql.checker.ASTVisitor ASTVisitor}.
 * 
 * @author Olav Trauschke
 * @version 1-mar-2016
 */
public interface VisitableASTNode {
    
    /**
     * Accept an <code>ASTVisitor</code> and have it the children of
     * <code>this VisitableASTNode</code> and <code>this VisitableASTNode</code>
     * itself.
     * 
     * @param visitor the <code>ASTVisitor</code> visiting <code>this VisitableASTNode</code>
     */
    void accept(ASTVisitor visitor);
    
}
