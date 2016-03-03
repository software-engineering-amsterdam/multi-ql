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
    
    @Override
    public void visit(Question q) {
        Label label = q.getLabel();
        if (firstQuestionsForLabels.containsKey(label)) {
            Question firstQuestionWithLabel = firstQuestionsForLabels.get(label);
            if (!q.hasEqualType(firstQuestionWithLabel)) {
               warnings.add(DUPLICATE_LABEL_ERROR + label);
            }
        }
        else {
            firstQuestionsForLabels.put(label, q);
        }
    }
    
}
