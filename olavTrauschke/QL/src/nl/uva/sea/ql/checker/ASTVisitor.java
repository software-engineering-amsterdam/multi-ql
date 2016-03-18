package nl.uva.sea.ql.checker;

import nl.uva.sea.ql.ast.*;
import nl.uva.sea.ql.ast.expr.*;
import nl.uva.sea.ql.ast.question.*;

/**
 * Subclasses of this class can visit all all kinds of <code>ASTNode</code>s.
 * This interface implements the visitor-pattern together
 * with {@link nl.uva.sea.ql.checker.VisitableASTNode VisitableNode}. The
 * methods visit have default implementations that do nothing, to allow
 * subclasses to implement this method only methods for objects of classes they
 * actually need to handle (in addition to just passing them in an AST).
 * 
 * @author Olav Trauschke
 * @version 17-mar-2016
 */
public interface ASTVisitor {
    
    default void visit(ConditionalStatement statement) {}
    
    default void visit(Form form) {}
    
    default void visit(Label label) {}
    
    default void visit(StatementSet statement) {}
    
    default void visit(BooleanQuestion question) {}
    
    default void visit(DateQuestion question) {}
    
    default void visit(DecimalQuestion question) {}
    
    default void visit(IntQuestion question) {}
    
    default void visit(MoneyQuestion question) {}
    
    default void visit(StringQuestion question) {}
    
    default void visit(Add addition) {}
    
    default void visit(BinaryNumericOperatorExpr expression) {}
    
    default void visit(BooleanConjunctiveExpr expression) {}
    
    default void visit(Bool bool) {}
    
    default void visit(ComparisonExpr expression) {}
    
    default void visit(Div division) {
        visit((BinaryNumericOperatorExpr) division);
    }
    
    default void visit(Ident identifier) {}
    
    default void visit(Int integer) {}
    
    default void visit(Neg negative) {}
    
    default void visit(Not negation) {}
    
    default void visit(OrderedComparisonExpr expression) {}
    
    default void visit(Str string) {}
    
}
