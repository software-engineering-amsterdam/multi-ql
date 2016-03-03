package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Var;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roy on 25-2-16.
 */
public class DeclVisitor extends BaseVisitor <Void,Void,Void,Void,Void,Map<Var, Question>> {

    private final Map<Var,Expr> decls = new HashMap<>();

    @Override
    public Void visit(Question stat, Map<Var, Question> symbolTable) {
        if(symbolTable.containsKey(stat.getVarname())){
            Question q = symbolTable.get(stat.getVarname());
            decls.put(q.getVarname(), q.getExpr());
            return super.visit(q, null);
        }
        else {
            decls.put(stat.getVarname(), stat.getExpr());
            return super.visit(stat,null);
        }
    }

    public Map<Var,Expr> getDecls() {
        return decls;
    }
}
