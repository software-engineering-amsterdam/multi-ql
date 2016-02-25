package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 11/02/16.
 */
public class DuplicateVarsCheck extends BaseVisitor<Void,Void,Void,Void,Void> {

    private final Map<Var, List<Node>> duplicates = new HashMap<>();

    public DuplicateVarsCheck(Form f) {
        f.accept(this);
    }


    @Override
    public Void visit(Question stat) {

        Var key = stat.getVarname();
        if(duplicates.containsKey(key))
            duplicates.get(key).add(stat);
        else{
            List<Node> value = new ArrayList<>();
            value.add(stat);
            duplicates.put(key,value);
        }

        return null;
    }

    public List<List<Node>> getDuplicates() {

        List<List<Node>> result = new ArrayList<>();
        for (List<Node> value : duplicates.values()){
            if (value.size() > 1){
                result.add(value);
            }
        }
        return result;
    }
}
