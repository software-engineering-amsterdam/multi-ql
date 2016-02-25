package org.uva.sea.ql.checker;

/**
 * Interface declaring that objects of a class can accept <code>ASTVisitor</code>s.
 * This interface implements the visitor-pattern together with
 * {@link org.uva.sea.ql.checker.ASTVisitor ASTVisitor}.
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
 */
public interface VisitableASTNode {
    
    /**
     * Accept an <code>ASTVisitor</code> and have it visit
     * <code>this VisitableASTNode</code> and its children.
     * 
     * @param visitor the <code>ASTVisitor</code> visiting <code>this VisitableASTNode</code>
     */
    void accept(ASTVisitor visitor);
    
}
