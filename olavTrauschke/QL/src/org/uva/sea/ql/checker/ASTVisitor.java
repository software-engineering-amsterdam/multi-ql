package org.uva.sea.ql.checker;

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
    
    default void visit(ComputedQuestion q) {}
    
    default void visit(ConditionalStatement s) {}
    
    default void visit(Date d) {}
    
    default void visit(Form f) {}
    
    default void visit(Label l) {}
    
    default void visit(Question q) {}
    
    default void visit(StatementSet s) {}
    
    default void visit(Add a) {}
    
    default void visit(And a) {}
    
    default void visit(Bool b) {}
    
    default void visit(Decimal d) {}
    
    default void visit(Div d) {}
    
    default void visit(Eq e) {}
    
    default void visit(GEq g) {}
    
    default void visit(GT g) {}
    
    default void visit(Ident i) {}
    
    default void visit(Int i) {}
    
    default void visit(LEq l) {}
    
    default void visit(LT l) {}
    
    default void visit(Money m) {}
    
    default void visit(Mul m) {}
    
    default void visit(NEq n) {}
    
    default void visit(Neg n) {}
    
    default void visit(Not n) {}
    
    default void visit(Or o) {}
    
    default void visit(Str s) {}
    
    default void visit(Sub s) {}
    
}
