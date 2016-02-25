package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.ConditionalStatement;
import org.uva.sea.ql.ast.expr.*;

/**
 * Class to check the types of objects in an AST.
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
 */
public class TypeChecker implements ASTVisitor {
    
    @Override
    public void visit(ConditionalStatement s) {
        //TODO check that condition is boolean
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
}
