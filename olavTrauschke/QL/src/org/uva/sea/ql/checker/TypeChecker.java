package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.question.ComputedQuestion;
import java.util.ArrayList;
import java.util.List;
import org.uva.sea.ql.ast.*;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.question.BooleanQuestion;
import org.uva.sea.ql.ast.question.ComputedBooleanQuestion;

/**
 * Class to check the types of objects in an AST.
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
 */
public class TypeChecker implements ASTVisitor {
    
    public static final String NON_BOOLEAN_CONDITION_ERROR
            = "The condition in a conditional statement must be boolean";
    
    private List<String> errors = new ArrayList<>();
    
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
    
    @Override
    public void visit(ComputedQuestion q) {
        //TODO check type of question and type of computation are equal
    }
    
    private boolean isBoolean(BooleanExpr e) {
        return true;
    }
    
    private boolean isBoolean(BooleanQuestion q) {
        return true;
    }
    
    private boolean isBoolean(ComputedBooleanQuestion q) {
        return true;
    }
    
    private boolean isBoolean(Ident id) {
        return false; //TODO look up Question and call isBoolean on it
    }
    
    private boolean isBoolean(ASTNode n) {
        return false;
    }
}
