package org.uva.sea.ql.checker;

/**
 * Interface declaring that objects of a class can accept <code>ASTVisitor</code>s.
 * This interface implements the visitor-pattern together with
 * {@link org.uva.sea.ql.checker.ASTVisitor ASTVisitor}.
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
     * @param v the <code>ASTVisitor</code> visiting <code>this VisitableASTNode</code>
     */
    void accept(ASTVisitor v);
    
}
