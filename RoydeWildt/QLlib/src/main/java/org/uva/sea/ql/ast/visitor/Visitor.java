package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.form.*;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.val.*;
import org.uva.sea.ql.ast.tree.type.*;


/**
 * Created by roy on 5-2-16.
 */
public interface Visitor <T,U> {
    Visitor v = null;

    T visit(Form form, U context);

    T visit(If          stat, U context);
    T visit(IfElse      stat, U context);
    T visit(Question    stat, U context);
    T visit(AssQuestion stat, U context);

    T visit(Add     expr, U context);
    T visit(And     expr, U context);
    T visit(Div     expr, U context);
    T visit(Eq      expr, U context);
    T visit(GEq     expr, U context);
    T visit(GT      expr, U context);
    T visit(LEq     expr, U context);
    T visit(LT      expr, U context);
    T visit(Mul     expr, U context);
    T visit(NEq     expr, U context);
    T visit(Or      expr, U context);
    T visit(Sub     expr, U context);
    T visit(Neg     expr, U context);
    T visit(Not     expr, U context);
    T visit(Pos     expr, U context);
    T visit(Primary expr, U context);

    T visit(Boolean val, U context);
    T visit(Money   val, U context);

    T visit(Bool val, U context);
    T visit(Int  val, U context);
    T visit(Var  var, U context);

}
