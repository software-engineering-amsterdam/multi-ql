package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.Stat;
import org.uva.sea.ql.ast.val.Val;
import org.uva.sea.ql.ast.var.Var;

import java.util.List;


/**
 * Created by roy on 5-2-16.
 */
public interface Visitor {
    Visitor v = null;
    List<? extends Node> visit(Form form);
    List<? extends Node> visit(Stat stat);
    List<? extends Node> visit(Expr expr);
    List<? extends Node> visit(Val  val);
    List<? extends Node> visit(Var var);

}
