package nl.uva.sea.ql.ast.expr;

import java.util.Map;
import nl.uva.sea.ql.ast.question.Question;

/**
 * Representation of <code>Expr</code>s which are sure to have a boolean value
 * in an AST.
 * 
 * @author Olav Trauschke
 * @version 3-mar-2016
 */
public abstract class BooleanExpr extends Expr {
    
    /**
     * Returns whether <code>this ASTNode</code> represents a boolean value.
     * 
     * @param questionTypes a <code>Map</code> from each <code>Ident</code>
     *                      <code>this BooleanExpr</code> might contain to a
     *                      <code>Question</code> with that <code>Ident</code>
     * @return <code>true</code>, because objects of this class represent booleans by definition
     */
    @Override
    public boolean isBoolean(Map<Ident,Question> questionTypes) {
        return true;
    }
    
}
