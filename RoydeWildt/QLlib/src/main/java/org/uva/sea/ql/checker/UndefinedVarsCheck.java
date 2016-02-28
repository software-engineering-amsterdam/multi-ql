package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.ast.visitor.DeclVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by roy on 5-2-16.
 */
public class UndefinedVarsCheck extends BaseVisitor<Void,Void,Void,Void,Void,Void> {

    private final List<Node> undefined = new ArrayList<>();
    private final Map<Var,Expr> decls;

    public UndefinedVarsCheck(Form f) {
        DeclVisitor dv = new DeclVisitor();
        f.accept(dv, null);
        this.decls = dv.getDecls();

        f.accept(this, null);
    }


    @Override
    public Void visit(Var var, Void context) {
        if (!decls.containsKey(var)) {
            undefined.add(var);
        }
        return null;
    }

    public List<Node> getUndefined() {
        return undefined;
    }
}
