package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.decl.Question;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.checker.message.ErrorMessage;
import org.uva.sea.ql.checker.message.Message;
import org.uva.sea.ql.checker.message.WarningMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 11/02/16.
 */
public class DuplicateVarsCheck extends BaseVisitor<Void,Void,Void,Void,Void,Void> {

    private final Map<Var, List<Node>> duplicates = new HashMap<>();

    public DuplicateVarsCheck(Form f) {
        f.accept(this, null);
    }


    @Override
    public Void visit(Question stat, Void context) {

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

    public List<Message> duplicateChecker(){
        List<Message> messages = new ArrayList<>();
        List<List<Node>> duplicates = getDuplicates();

        for(List<Node> dups : duplicates){
            StringBuilder sb = new StringBuilder("");
            Question org = (Question) dups.get(0);
            for (int i = 1; i < dups.size(); i++) {
                Question dup = (Question) dups.get(i);
                sb.append("Variable ");
                sb.append(dup.getVarname().toString()).append(" : ").append(dup.getType().getClass().getSimpleName());
                sb.append(" is already defined as ");
                sb.append(org.getVarname().toString()).append(" : ").append(org.getType().getClass().getSimpleName());

                if(dup.getType().getClass().getSimpleName() == org.getType().getClass().getSimpleName())
                    messages.add(new WarningMessage(sb.toString(), dup.getVarname()));
                else
                    messages.add(new ErrorMessage(sb.toString(), dup.getVarname()));
            }
        }
        return messages;
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
