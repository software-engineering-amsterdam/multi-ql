package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;


/**
 * Created by roy on 5-2-16.
 */
public interface ExprVisitor<T,C> {
    
    T visit(Add expr, C context);
    T visit(And expr, C context);
    T visit(Div expr, C context);
    T visit(Eq expr, C context);
    T visit(GEq expr, C context);
    T visit(GT expr, C context);
    T visit(LEq expr, C context);
    T visit(LT expr, C context);
    T visit(Mul expr, C context);
    T visit(NEq expr, C context);
    T visit(Or expr, C context);
    T visit(Sub expr, C context);
    T visit(Neg expr, C context);
    T visit(Not expr, C context);
    T visit(Pos expr, C context);
    T visit(Primary expr, C context);

}
