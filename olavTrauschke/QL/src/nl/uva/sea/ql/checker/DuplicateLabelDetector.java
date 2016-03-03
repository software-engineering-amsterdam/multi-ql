package nl.uva.sea.ql.checker;

import java.util.*;
import nl.uva.sea.ql.ast.Label;
import nl.uva.sea.ql.ast.question.Question;

/**
 * Visitor to detect identical labels that were used for different questions.
 * 
 * @author Olav Trauschke
 * @version 3-mrt-2016
 */
public class DuplicateLabelDetector implements ASTVisitor {
    
    /**
     * Error presented to the user when a <code>Question</code> was found to be
     * defined with a <code>Label</code> that was used before for a
     * <code>Question</code> with another <code>type</code>.
     */
    public static final String DUPLICATE_LABEL_ERROR = "Label detected that was"
            + " used for different questions: ";
    
    private final List<String> warnings;
    private final Map<Label,Question> firstQuestionsForLabels;
    
    /**
     * Constructor for objects of this class.
     */
    public DuplicateLabelDetector() {
        warnings = new ArrayList<>();
        firstQuestionsForLabels = new HashMap<>();
    }
    
    /**
     * Checks whether a <code>Question</code> has a <code>Label</code> that was
     * used for another <code>Question</code> with a different <code>type</code>
     * before.
     * 
     * @param q the <code>Question</code> to check
     */
    @Override
    public void visit(Question q) {
        Label label = q.getLabel();
        if (firstQuestionsForLabels.containsKey(label)) {
            Question firstQuestionWithLabel = firstQuestionsForLabels.get(label);
            if (!q.equals(firstQuestionWithLabel)) {
               warnings.add(DUPLICATE_LABEL_ERROR + label);
            }
        }
        else {
            firstQuestionsForLabels.put(label, q);
        }
    }
    
    /**
     * @return a <code>List</code> of all warnings produced by
     *          <code>this DuplicateLabelDetector</code>
     */
    public List<String> getWarnings() {
        return warnings;
    }
    
}
