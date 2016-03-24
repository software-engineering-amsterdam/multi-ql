package nl.uva.sea.ql.interpreter;

import java.util.List;
import nl.uva.sea.ql.GeneralizedASTVisitor;
import nl.uva.sea.ql.ast.expr.Expr;

/**
 * 
 * 
 * @author Olav Trauschke
 * @version 24-mar-2016
 */
public class QuestionGeneratingVisitor extends GeneralizedASTVisitor {
    
    @Override
    public void visit(nl.uva.sea.ql.ast.question.Question question) {
        //TODO create nl.uva.sea.ql.interpreter.Question with dependency on the
        //conjunction of the conditions
    }
    
    public void addCondition(Expr condition) {
        //TODO add condition
    }
    
    public void removeLastCondition() {
        //TODO remove last condition
    }
    
    public List<nl.uva.sea.ql.interpreter.Question> getResult() {
        //TODO return result
    }
    
}
