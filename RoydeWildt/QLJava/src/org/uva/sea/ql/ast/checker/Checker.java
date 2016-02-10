package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.ast.var.Var;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class Checker {

    public Checker(){}

    public void undefinedChecker(Form f){
        List<? extends Node> decls = f.accept(new DeclVisitor());
        List<? extends Node> vars = f.accept(new VarsVisitor());

        List<Node> result = new ArrayList<>();
        result.addAll(vars);

        for (Node v : vars)
            for (Node d : decls)
                if(v.toString().equals(((Question)d).getVarname().toString()))
                    result.remove(v);

        for(Node n : result)
            System.out.println("Error: variable " + ((Var) n).getValue() + " is undefined.");

    }

    public void duplicateChecker(Form f){
        List<Question> decls = (List<Question>) f.accept(new DeclVisitor());


        for (int i = 0; i < decls.size(); i++) {
            Question d1 = decls.get(i);
            for (int j = i + 1; j < decls.size(); j++) {
                Question d2 = decls.get(j);
                if (d1.getVarname().getValue().equals(d2.getVarname().getValue())) {
                    if (!d1.getType().toString().equals(d2.getType().toString()))
                        System.out.println("Error: variable " + d1.getVarname().getValue() + " is declared multiple times using different types.");
                    else
                        System.out.println("Warning: variable " + d1.getVarname().getValue() + " is declared multiple times.");
                }
            }
        }
    }


}
