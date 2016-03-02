package nl.uva.sea.ql.checker;

import java.util.*;
import nl.uva.sea.ql.ast.expr.Ident;
import nl.uva.sea.ql.ast.question.Question;

/**
 * Visitor to obtain a map from all 
 * 
 * @author Olav Trauschke
 * @version 2-mrt-2016
 */
public class QuestionCollectingVisitor implements ASTVisitor {
    
    public static final String REDEFINED_QUESTION_ERROR = "Question defined"
            + "with different return types found: ";
    
    private final List<String> errors;
    private Map<Ident,Type> result;
    
    public QuestionCollectingVisitor() {
        errors = new ArrayList<>();
        result = new HashMap<>();
    }
    
    @Override
    public void visit(Question q) {
        Ident identifier = q.getIdentifier();
        Type type = q.getType();
        if (result.containsKey(identifier)) {
            Type previousType = result.get(identifier);
            if (!type.equals(previousType)) {
               errors.add(REDEFINED_QUESTION_ERROR + identifier);
            }
        }
        else {
            result.put(identifier, type);
        }
    }
    
    public Map<Ident,Type> getResult() {
        return result;
    }
    
}
