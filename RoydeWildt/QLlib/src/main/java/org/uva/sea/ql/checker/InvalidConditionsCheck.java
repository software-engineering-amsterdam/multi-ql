package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.block.If;
import org.uva.sea.ql.ast.tree.stat.block.IfElse;
import org.uva.sea.ql.checker.message.ErrorMessage;
import org.uva.sea.ql.checker.message.Message;
import org.uva.sea.ql.evaluator.adt.type.Boolean;
import org.uva.sea.ql.evaluator.adt.type.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 17/02/16.
 */
public class InvalidConditionsCheck extends TypeCheck<Void,Void,Void> {
    private final List<Node> invalidConditions = new ArrayList<>();

    public InvalidConditionsCheck(Form form) {
        super(form);
        form.accept(this, null);
    }


    @Override
    public Void visit(IfElse stat, Void context) {
        Type exprType = stat.getCond().accept(this, context);
        if(exprType == null || !exprType.equals(new Boolean()))
            invalidConditions.add(stat.getCond());
        return null;
    }

    @Override
    public Void visit(If stat, Void context) {
        Type exprType = stat.getCond().accept(this, context);
        if(exprType == null || !exprType.equals(new Boolean()))
            invalidConditions.add(stat.getCond());
        return null;
    }

    public List<Message> invalidConditionChecker(){
        List<Message> messages = new ArrayList<>();
        List<Node> invalidConditions = getInvalidConditions();

        for(Node n : invalidConditions){
            Expr e = (Expr) n;
            String sb = "Condition " +
                    e.toString() +
                    " is not of type Boolean";

            messages.add(new ErrorMessage(sb,e));
        }
        return messages;

    }

    public List<Node> getInvalidConditions() {
        return invalidConditions;
    }
}
