package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.var.Var;

import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class Checker {

    public Checker(){}

    public void undefinedChecker(Form f){
        List<Node> undefined = (new UndefinedVarsCheck(f)).getUndefined();

        for(Node n : undefined){
            StringBuilder sb = new StringBuilder();
            sb.append("Variable ");
            sb.append(((Var) n).getValue());
            sb.append(" (line " + n.getLine() + ")");
            sb.append(" is undefined");
            System.out.println(sb.toString());
        }

    }

    public void duplicateChecker(Form f){
        List<List<Node>> duplicates = (new DuplicateVarsCheck(f)).getDuplicates();

        for(List<Node> dups : duplicates){
            StringBuilder sb = new StringBuilder("");
            Question org = (Question) dups.get(0);
            for (int i = 1; i < dups.size(); i++) {
                Question dup = (Question) dups.get(i);
                sb.append("Variable ");
                sb.append(dup.getVarname().getValue() + " : " + dup.getType().getClass().getSimpleName());
                sb.append(" (line " + dup.getLine() + ")");
                sb.append(" is already defined as ");
                sb.append(org.getVarname().getValue() + " : " + org.getType().getClass().getSimpleName());
                sb.append(" (line " + org.getLine() + ")");
            }

            System.out.println(sb.toString());
        }
    }

    public void invalidConditionChecker(Form f){
        List<Node> invalidExpressions = (new InvalidExpressionCheck(f)).getInvalidExpressions();

        for(Node n : invalidExpressions){
            Expr e = (Expr) n;
            StringBuilder sb = new StringBuilder();
            sb.append("Expression ");
            sb.append(e.toString());
            sb.append(" (line " + e.getLine() + ")");
            sb.append(" contains incompatible types");
            System.out.println(sb.toString());
        }
    }
}
