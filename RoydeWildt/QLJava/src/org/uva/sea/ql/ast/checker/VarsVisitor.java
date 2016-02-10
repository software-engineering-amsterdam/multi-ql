package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.ast.val.*;
import org.uva.sea.ql.ast.var.Var;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class VarsVisitor extends CoreVisitor{

    private List<Node> vars;

    public VarsVisitor() {
        vars = new ArrayList<>();
    }

    @Override
    public <T> T visit(Var var) {

        vars.add(var);

        return null;
    }

    public List<Node> getVars() {
        return vars;
    }
}
