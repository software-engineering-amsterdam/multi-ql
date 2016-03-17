package org.uva.sea.ql.ast.visitor;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.evaluator.value.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roy on 25-2-16.
 */
public class DeclVisitor extends BaseVisitor <Void,Void,Void,Void,Void,Map<Var, EvaluatedQuestion>> {

    private final Map<Var,Value> decls;

    public DeclVisitor() {
        decls = new HashMap<>();
    }

    @Override
    public Void visit(Question stat, Map<Var, EvaluatedQuestion> symbolTable) {
        if(symbolTable.containsKey(stat.getVarname())){
            EvaluatedQuestion q = symbolTable.get(stat.getVarname());
            decls.put(q.getVarname(), q.getValue());
            return super.visit(q, null);
        }
        else {
            decls.put(stat.getVarname(), stat.getExpr());
            return super.visit(stat,null);
        }
    }

    public Map<Var,Value> getDecls() {
        return decls;
    }
}
