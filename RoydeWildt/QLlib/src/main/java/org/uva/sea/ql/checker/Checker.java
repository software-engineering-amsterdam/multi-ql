package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.Question;
import org.uva.sea.ql.ast.tree.val.Var;
import org.uva.sea.ql.checker.message.ErrorMessage;
import org.uva.sea.ql.checker.message.Message;
import org.uva.sea.ql.checker.message.WarningMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roy on 5-2-16.
 */
public class Checker {

    public Checker(){}

    public List<Message> undefinedChecker(Form f){
        List<Message> messages = new ArrayList<>();
        List<Node> undefined = (new UndefinedVarsCheck(f)).getUndefined();

        for(Node n : undefined){
            StringBuilder sb = new StringBuilder();
            sb.append("Variable ");
            sb.append(((Var) n).getValue());
            sb.append(" is undefined");
            messages.add(new ErrorMessage(sb.toString(), n));
        }
        return messages;
    }

    public List<Message> duplicateChecker(Form f){
        List<Message> messages = new ArrayList<>();
        List<List<Node>> duplicates = (new DuplicateVarsCheck(f)).getDuplicates();

        for(List<Node> dups : duplicates){
            StringBuilder sb = new StringBuilder("");
            Question org = (Question) dups.get(0);
            for (int i = 1; i < dups.size(); i++) {
                Question dup = (Question) dups.get(i);
                sb.append("Variable ");
                sb.append(dup.getVarname().getValue() + " : " + dup.getType().getClass().getSimpleName());
                sb.append(" is already defined as ");
                sb.append(org.getVarname().getValue() + " : " + org.getType().getClass().getSimpleName());

                if(dup.getType().getClass().getSimpleName() == org.getType().getClass().getSimpleName())
                    messages.add(new WarningMessage(sb.toString(), dup.getVarname()));
                else
                    messages.add(new ErrorMessage(sb.toString(), dup.getVarname()));
            }
        }
        return messages;
    }

    public List<Message> invalidExpressionChecker(Form f){
        List<Message> messages = new ArrayList<>();
        List<Node> invalidExpressions = (new InvalidExpressionsCheck(f)).getInvalidExpressions();

        for(Node n : invalidExpressions){
            Expr e = (Expr) n;
            StringBuilder sb = new StringBuilder();
            sb.append("Expression ");
            sb.append(e.toString());
            sb.append(" has incompatible argument types");

            messages.add(new ErrorMessage(sb.toString(),e));
        }
        return messages;
    }

    public List<Message> invalidConditionChecker(Form f){
        List<Message> messages = new ArrayList<>();
        List<Node> invalidConditions = (new InvalidConditionsCheck(f)).getInvalidConditions();

        for(Node n : invalidConditions){
            Expr e = (Expr) n;
            StringBuilder sb = new StringBuilder();
            sb.append("Condition ");
            sb.append(e.toString());
            sb.append(" is not of type Boolean");

            messages.add(new ErrorMessage(sb.toString(),e));
        }
        return messages;

    }

    public List<Message> cyclicQuestionChecker(Form f){
        List<Message> messages = new ArrayList<>();
        List<Node> cyclics = (new CyclicQuestionsCheck(f)).getCyclics();

        for(Node n : cyclics){
            Var v = (Var) n;
            StringBuilder sb = new StringBuilder();
            sb.append("Question ");
            sb.append(v.toString());
            sb.append(" contains a cyclic cyclic dependencies");

            messages.add(new ErrorMessage(sb.toString(),v));
        }
        return messages;

    }
}
