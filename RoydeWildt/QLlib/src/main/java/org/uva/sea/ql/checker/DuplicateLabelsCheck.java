package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.visitor.BaseVisitor;
import org.uva.sea.ql.checker.message.Message;
import org.uva.sea.ql.checker.message.WarningMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 18/02/16.
 */
public class DuplicateLabelsCheck extends BaseVisitor<Void,Void,Void,Void,Void,Void>{

    private final Map<String, Var> questions = new HashMap<>();
    private final List<Node> duplicatelabels = new ArrayList<>();

    public DuplicateLabelsCheck(Form f) {
        f.accept(this, null);
    }


    @Override
    public Void visit(Question stat, Void context) {

        if(questions.containsKey(stat.getLabel())){
            Var v = questions.get(stat.getVarname());
            if(v != stat.getVarname())
                duplicatelabels.add(stat);
        }
        else {
            questions.put(stat.getLabel(), stat.getVarname());
        }

        return null;
    }

    public List<Node> getDuplicatelabels() {
        return duplicatelabels;
    }

    public List<Message> duplicateQuestionLabelChecker(){
        List<Message> messages = new ArrayList<>();
        List<Node> duplicates = getDuplicatelabels();

        for(Node n : duplicates){
            Var v = ((Question) n).getVarname();
            StringBuilder sb = new StringBuilder();
            sb.append("EvaluatedQuestion ");
            sb.append(v.toString());
            sb.append(" uses a label that has already been used");

            messages.add(new WarningMessage(sb.toString(),v));
        }
        return messages;
    }

}
