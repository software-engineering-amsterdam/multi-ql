package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.checker.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 09/03/16.
 */
public class Checker {
    List<Message> messages;

    public Checker(Form f){
        messages = new ArrayList<>();
        messages.addAll(new CyclicQuestionsCheck(f).cyclicQuestionChecker());
        messages.addAll(new DuplicateLabelsCheck(f).duplicateQuestionLabelChecker());
        messages.addAll(new DuplicateVarsCheck(f).duplicateChecker());
        messages.addAll(new InvalidConditionsCheck(f).invalidConditionChecker());
        messages.addAll(new InvalidExpressionsCheck(f).invalidExpressionChecker());
        messages.addAll(new UndefinedVarsCheck(f).undefinedChecker());
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getMessageStrings(){
        List<String> messageStrings = new ArrayList<>();

        for(Message message : messages){
            messageStrings.add(message.toString());
        }

        return messageStrings;
    }
}
