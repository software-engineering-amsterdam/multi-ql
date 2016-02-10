package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.form.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.expr.unary.*;
import org.uva.sea.ql.ast.expr.binary.*;
import org.uva.sea.ql.ast.val.*;
import org.uva.sea.ql.ast.var.*;

import java.util.List;


/**
 * Created by roy on 5-2-16.
 */
public interface Visitor {
    Visitor v = null;
    List<? extends Node> visit(Form form);

    List<? extends Node> visit(If stat);
    List<? extends Node> visit(IfElse stat);
    List<? extends Node> visit(Question stat);

    List<? extends Node> visit(Add expr);
    List<? extends Node> visit(And expr);
    List<? extends Node> visit(Div expr);
    List<? extends Node> visit(Eq expr);
    List<? extends Node> visit(GEq expr);
    List<? extends Node> visit(GT expr);
    List<? extends Node> visit(LEq expr);
    List<? extends Node> visit(LT expr);
    List<? extends Node> visit(Mul expr);
    List<? extends Node> visit(NEq expr);
    List<? extends Node> visit(Or expr);
    List<? extends Node> visit(Sub expr);
    List<? extends Node> visit(Neg expr);
    List<? extends Node> visit(Not expr);
    List<? extends Node> visit(Pos expr);

    List<? extends Node> visit(Bool  val);
    List<? extends Node> visit(Int  val);

    List<? extends Node> visit(Var var);

}
