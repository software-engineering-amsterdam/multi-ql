package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.atom.var.Var;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.decl.Computed;
import org.uva.sea.ql.ast.tree.stat.decl.Declaration;
import org.uva.sea.ql.ast.tree.stat.decl.Question;
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

    private final Map<String, Declaration> labels;
    private final List<Declaration> duplicatelabels;

    public DuplicateLabelsCheck(Form f) {
        labels = new HashMap<>();
        duplicatelabels = new ArrayList<>();
        f.accept(this, null);
    }


    @Override
    public Void visit(Question stat, Void context) {
        if(labels.containsKey(stat.getLabel())){
            duplicatelabels.add(stat);
        }
        else {
            labels.put(stat.getLabel(), stat);
        }

        return null;
    }

    @Override
    public Void visit(Computed stat, Void context) {
        if(labels.containsKey(stat.getLabel())){
            duplicatelabels.add(stat);
        }
        else {
            labels.put(stat.getLabel(), stat);
        }

        return null;
    }

    public List<Declaration> getDuplicatelabels() {
        return duplicatelabels;
    }

    public List<Message> duplicateQuestionLabelChecker(){
        List<Message> messages = new ArrayList<>();
        List<Declaration> duplicates = getDuplicatelabels();

        for(Declaration declaration : duplicates){
            Var v = declaration.getVarname();
            String sb = "Question " +
                    v.toString() +
                    " uses a label that has already been used";

            messages.add(new WarningMessage(sb,v));
        }
        return messages;
    }

}
