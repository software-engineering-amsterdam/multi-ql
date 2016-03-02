package nl.uva.sea.ql.checker;

import nl.uva.sea.ql.ast.ASTNode;
import nl.uva.sea.ql.ast.ConditionalStatement;
import nl.uva.sea.ql.ast.expr.*;
import java.util.*;
import nl.uva.sea.ql.ast.question.Question;

/**
 * Visitor to check the types of objects in an AST.
 * 
 * @author Olav Trauschke
 * @version 2-mrt-2016
 */
public class TypeCheckingVisitor implements ASTVisitor {
    
    /**
     * Error presented to the user when a conditional statement has a
     * non-boolean condition.
     */
    public static final String NON_BOOLEAN_CONDITION_ERROR
            = "Non-boolean condition for conditional statement found";
    
    private final List<String> errors;
    private final Map<Ident,Question> questionTypes;
    
    public TypeCheckingVisitor(Map<Ident,Question> theQuestionTypes) {
        errors = new ArrayList();
        questionTypes = theQuestionTypes;
    }
    
    /**
     * @return a <code>List</code> of the errors generated during the
     *          <code>visit</code>s <code>this TypeCheckingVisitor</code> has
     *          performed
     */
    public List<String> getErrors() {
        return errors;
    }
    
    @Override
    public void visit(ConditionalStatement s) {
        Expr condition = s.getCondition();
        if (isBoolean(condition)) {
            errors.add(NON_BOOLEAN_CONDITION_ERROR);
        }
    }
    
    @Override
    public void visit(Add a) {
        //TODO check that expressions are either both numeric or both strings
    }
    
    @Override
    public void visit(BinaryNumericOperatorExpr e) {
        //TODO check that expressions are both numeric
    }
    
    @Override
    public void visit(BooleanConjunctiveExpr e) {
        //TODO check that expressions are both boolean
    }
    
    @Override
    public void visit(ComparisonExpr e) {
        //TODO check that expressions have the same types
    }
    
    @Override
    public void visit(Neg n) {
        //TODO check that expression is numeric
    }
    
    @Override
    public void visit(Not n) {
        //TODO check that expression is boolean
    }
    
    @Override
    public void visit(OrderedComparisonExpr e) {
        //TODO check that expressions are both numeric
    }
    
    /**
     * Checks whether an <code>ASTNode</code> has a boolean value.
     * 
     * @param n the <code>ASTNode</code> to check
     * @return <code>true</code> if and only if <code>n.isBoolean()</code>
     *          returns <code>true</code> or n is an <code>Ident</code> that
     *          refers to a <code>Question</code> for which this is the case
     */
    private boolean isBoolean(ASTNode n) {
        if (n instanceof Ident) {
            n = questionTypes.get((Ident) n);
        }
        return n.isBoolean();
    }
}
