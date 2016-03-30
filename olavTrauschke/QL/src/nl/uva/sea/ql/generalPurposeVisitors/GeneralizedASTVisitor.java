package nl.uva.sea.ql.generalPurposeVisitors;

import nl.uva.sea.ql.ast.question.*;

/**
 * Class that generalizes the interface specified in {@link Visitor Visitor}
 * to do the same for all types of <code>Question</code>s.
 * 
 * @author Olav Trauschke
 * @version 14-mar-2016
 */
public abstract class GeneralizedASTVisitor implements Visitor {
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param question the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(BooleanQuestion question) {
        visit((Question) question);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param question the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(DateQuestion question) {
        visit((Question) question);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param question the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(DecimalQuestion question) {
        visit((Question) question);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param question the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(IntQuestion question) {
        visit((Question) question);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param question the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(MoneyQuestion question) {
        visit((Question) question);
    }
    
    /**
     * Forwards calls to this method (specified in the interface) to
     * {@link #visit(Question) visit(Question)}.
     * 
     * @param question the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(StringQuestion question) {
        visit((Question) question);
    }
    
    /**
     * Method that specifies what should be done when visiting any type of
     * <code>Question</code>.
     * 
     * @param question a <code>Question</code> to process
     */
    public abstract void visit(Question question);
    
}
