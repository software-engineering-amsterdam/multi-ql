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
 * @version 3-mrt-2016
 */
public interface ASTVisitor {
    
    default void visit(ConditionalStatement s) {}
    
    default void visit(Form f) {}
    
    default void visit(Label l) {}
    
    default void visit(StatementSet s) {}
    
    default void visit(BooleanQuestion q) {}
    
    default void visit(DateQuestion q) {}
    
    default void visit(DecimalQuestion q) {}
    
    default void visit(IntQuestion q) {}
    
    default void visit(MoneyQuestion q) {}
    
    default void visit(StringQuestion q) {}
    
    default void visit(Add a) {}
    
    default void visit(BinaryNumericOperatorExpr e) {}
    
    default void visit(BooleanConjunctiveExpr e) {}
    
    default void visit(Bool b) {}
    
    default void visit(ComparisonExpr e) {}
    
    default void visit(Ident i) {}
    
    default void visit(Int i) {}
    
    default void visit(Neg n) {}
    
    default void visit(Not n) {}
    
    default void visit(OrderedComparisonExpr e) {}
    
    default void visit(Str s) {}
    
}
