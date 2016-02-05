package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.Stat;
import org.uva.sea.ql.ast.val.Val;

import java.util.List;


/**
 * Created by roy on 5-2-16.
 */
public interface Visitor {

    List<String> visit(Form form);
    List<String> visit(Stat stat);
    List<String> visit(Expr expr);
    List<String> visit(Val  val);

}
