package org.uva.sea.ql.ast.checker;

import org.uva.sea.ql.ast.Node;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.stat.AssQuestion;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.ast.stat.Stat;
import org.uva.sea.ql.ast.var.Var;
import org.uva.sea.ql.ast.visitor.BaseVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 11/02/16.
 */
public class DuplicateCheck extends BaseVisitor {

    private final List<Node> decls;
    private final Map<Var, List<Node>> duplicates;

    public DuplicateCheck(Form f) {
        this.decls = new ArrayList<>();
        this.duplicates = new HashMap<>();

        f.accept(this);
    }

    @Override
    public <T> T visit(Question stat) {
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
