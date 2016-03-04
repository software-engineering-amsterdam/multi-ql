package nl.uva.sea.ql.checker;

import nl.uva.sea.ql.ast.question.*;

/**
 * Class that generalizes the interface specified in {@link ASTVisitor ASTVisitor}
 * to do the same for all types of <code>Question</code>s.
 * 
 * @author Olav Trauschke
 * @version 3-mrt-2016
 */
public abstract class GeneralizedASTVisitor implements ASTVisitor {
    
        /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param q the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(BooleanQuestion q) {
        visit((Question) q);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param q the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(DateQuestion q) {
        visit((Question) q);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param q the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(DecimalQuestion q) {
        visit((Question) q);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param q the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(IntQuestion q) {
        visit((Question) q);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param q the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(MoneyQuestion q) {
        visit((Question) q);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param q the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(StringQuestion q) {
        visit((Question) q);
    }
    
    /**
     * Method that specifies what should be done when visiting any type of
     * <code>Question</code>.
     * 
     * @param q a <code>Question</code> to process
     */
    public abstract void visit(Question q);
    
}
