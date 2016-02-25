package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Var;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roy on 25-2-16.
 */
public class DeclVisitor extends BaseVisitor <Void,Void,Void,Void,Void> {

    private final Map<Var,Expr> decls = new HashMap<>();

    @Override
    public Void visit(Question stat) {
        decls.put(stat.getVarname(), stat.getExpr());
        return super.visit(stat);
    }

    public Map<Var,Expr> getDecls() {
        return decls;
    }
}
