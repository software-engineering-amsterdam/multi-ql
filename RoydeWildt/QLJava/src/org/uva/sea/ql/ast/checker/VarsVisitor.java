package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.val.*;
import org.uva.sea.ql.ast.var.Var;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class VarsVisitor extends BaseVisitor{

    @Override
    public List<? extends Node> visit(Expr expr) {
        v = new VarsVisitor();
        List<Node> result = (List<Node>) super.visit(expr);

        //get var from primary
        if (expr instanceof Var) {
            result.addAll((expr.accept(v)));
        }

        return result;
    }

    @Override
    public List<? extends Node> visit(Var var) {

        List<Node> result = new ArrayList<>();

        v = new VarsVisitor();

        result.add(var);

        return result;
    }
}
