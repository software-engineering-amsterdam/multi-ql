package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.form.*;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.val.*;


/**
 * Created by roy on 5-2-16.
 */
public interface Visitor {
    Visitor v = null;
    <T> T visit(Form form);

    <T> T visit(If stat);
    <T> T visit(IfElse stat);
    <T> T visit(Question stat);
    <T> T visit(AssQuestion stat);

    <T> T visit(Add expr);
    <T> T visit(And expr);
    <T> T visit(Div expr);
    <T> T visit(Eq expr);
    <T> T visit(GEq expr);
    <T> T visit(GT expr);
    <T> T visit(LEq expr);
    <T> T visit(LT expr);
    <T> T visit(Mul expr);
    <T> T visit(NEq expr);
    <T> T visit(Or expr);
    <T> T visit(Sub expr);
    <T> T visit(Neg expr);
    <T> T visit(Not expr);
    <T> T visit(Pos expr);
    <T> T visit(Primary expr);

    <T> T visit(Bool  val);
    <T> T visit(Int  val);

    <T> T visit(Var var);

}
