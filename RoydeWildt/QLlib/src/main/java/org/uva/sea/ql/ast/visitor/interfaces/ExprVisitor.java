package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.Neg;
import org.uva.sea.ql.ast.tree.expr.unary.Not;
import org.uva.sea.ql.ast.tree.expr.unary.Pos;
import org.uva.sea.ql.ast.tree.expr.unary.Primary;


/**
 * CONTEXTreated by roy on 5-2-16.
 */

public interface ExprVisitor<EXPR,CONTEXT> {
    
    EXPR visit(Add expr, CONTEXT context);
    EXPR visit(And expr, CONTEXT context);
    EXPR visit(Div expr, CONTEXT context);
    EXPR visit(Eq expr, CONTEXT context);
    EXPR visit(GEq expr, CONTEXT context);
    EXPR visit(GT expr, CONTEXT context);
    EXPR visit(LEq expr, CONTEXT context);
    EXPR visit(LT expr, CONTEXT context);
    EXPR visit(Mul expr, CONTEXT context);
    EXPR visit(NEq expr, CONTEXT context);
    EXPR visit(Or expr, CONTEXT context);
    EXPR visit(Sub expr, CONTEXT context);
    EXPR visit(Neg expr, CONTEXT context);
    EXPR visit(Not expr, CONTEXT context);
    EXPR visit(Pos expr, CONTEXT context);
    EXPR visit(Primary expr, CONTEXT context);

}
