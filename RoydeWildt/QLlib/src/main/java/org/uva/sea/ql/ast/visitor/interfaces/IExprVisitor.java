package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;


/**
 * Created by roy on 5-2-16.
 */
public interface IExprVisitor<T> {
    
    T visit(Add expr);
    T visit(And expr);
    T visit(Div expr);
    T visit(Eq expr);
    T visit(GEq expr);
    T visit(GT expr);
    T visit(LEq expr);
    T visit(LT expr);
    T visit(Mul expr);
    T visit(NEq expr);
    T visit(Or expr);
    T visit(Sub expr);
    T visit(Neg expr);
    T visit(Not expr);
    T visit(Pos expr);
    T visit(Primary expr);

}
