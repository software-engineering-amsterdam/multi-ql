package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.question.Question;
import org.uva.sea.ql.ast.*;
import org.uva.sea.ql.ast.expr.*;

/**
 * Subclasses of this class can visit all all kinds of <code>ASTNode</code>s.
 * This interface implements the visitor-pattern together
 * with {@link org.uva.sea.ql.checker.VisitableASTNode VisitableNode}. The
 * methods visit have default implementations that do nothing, to allow
 * subclasses to implement this method only methods for objects of classes they
 * actually need to handle (in addition to just passing them in an AST).
 * 
 * @author Olav Trauschke
 * @version 25-feb-2016
 */
public interface ASTVisitor {
    
    default void visit(ConditionalStatement s) {}
    
    default void visit(Date d) {}
    
    default void visit(Form f) {}
    
    default void visit(Label l) {}
    
    default void visit(Question q) {}
    
    default void visit(StatementSet s) {}
    
    default void visit(Add a) {}
    
    default void visit(BinaryNumericOperatorExpr e) {}
    
    default void visit(BooleanConjunctiveExpr e) {}
    
    default void visit(Bool b) {}
    
    default void visit(ComparisonExpr e) {}
    
    default void visit(Decimal d) {}
    
    default void visit(Ident i) {}
    
    default void visit(Int i) {}
    
    default void visit(Money m) {}
    
    default void visit(Neg n) {}
    
    default void visit(Not n) {}
    
    default void visit(OrderedComparisonExpr e) {}
    
    default void visit(Str s) {}
    
}
