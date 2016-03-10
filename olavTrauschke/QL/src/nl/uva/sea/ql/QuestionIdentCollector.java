package nl.uva.sea.ql;

import java.util.*;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.ast.question.Question;
import nl.uva.sea.ql.checker.GeneralizedASTVisitor;

/**
 * Visitor to obtain a map from all <code>Ident</code>s in an <code>ast</code>
 * to a <code>Question</code> with that <code>Ident</code> and produce errors
 * when a question is redefined (another <code>Question</code> was parsed with
 * the same <code>Ident</code>) with a different type.
 * 
 * @author Olav Trauschke
 * @version 10-mrt-2016
 */
public class QuestionIdentCollector extends GeneralizedASTVisitor {
    
    /**
     * Error presented to the user when a question was found to be redefined
     * with another type.
     */
    public static final String REDEFINED_QUESTION_ERROR
            = "Question defined with different return types found: ";
    
    private final List<String> errors;
    private final Map<Ident,Question> firstQuestionsForIdentifiers;
    
    /**
     * Constructor for objects of this class.
     */
    public QuestionIdentCollector() {
        errors = new ArrayList<>();
        firstQuestionsForIdentifiers = new HashMap<>();
    }
    
    /**
     * When a <code>QuestionIdentCollector visit</code>s a <code>Question</code>
     * it checks whether a <code>Question</code> with the same <code>identifier</code>
     * was <code>visit</code>ed before and checks whether the <code>Question</code>
     * was defined with the same type before if this is the case and safes the
     * <code>Question</code> as the first <code>Question</code> found with its
     * <code>identifier</code> if this is not the case.
     * 
     * @param q the <code>Question</code> to <code>visit</code>
     */
    @Override
    public void visit(Question q) {
        Ident identifier = q.getIdentifier();
        if (firstQuestionsForIdentifiers.containsKey(identifier)) {
            Question firstQuestionWithSameIdent = firstQuestionsForIdentifiers.get(identifier);
            if (!q.hasEqualType(firstQuestionWithSameIdent)) {
               errors.add(REDEFINED_QUESTION_ERROR + identifier);
            }
        }
        else {
            firstQuestionsForIdentifiers.put(identifier, q);
        }
    }
    
    /**
     * @return a <code>Map</code> from each <code>Ident</code> that was found in
     *          the <code>visit</code>ed ast's to the first questions that were
     *          found with these <code>Ident</code>s
     */
    public Map<Ident,Question> getFirstQuestionsForIdentifiers() {
        return firstQuestionsForIdentifiers;
    }
    
    /**
     * @return an <code>Iterable</code> over all <code>Ident</code>s that were
     *          found in the <code>visit</code>ed ast's
     */
    public Iterable<Ident> obtainIdentifiers() {
        return firstQuestionsForIdentifiers.keySet();
    }
    
    /**
     * @return a <code>List</code> of all errors produced by
     *          <code>this QuestionIdentCollector</code>
     */
    public List<String> getErrors() {
        return errors;
    }
}
