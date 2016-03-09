package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.visitor.TypeVisitor;
import org.uva.sea.ql.checker.message.ErrorMessage;
import org.uva.sea.ql.checker.message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 17/02/16.
 */
public class InvalidConditionsCheck extends TypeVisitor<Void,Void,Void> {
    private final List<Node> invalidConditions = new ArrayList<>();

    public InvalidConditionsCheck(Form f) {
        f.accept(this, null);
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
            StringBuilder sb = new StringBuilder();
            sb.append("Condition ");
            sb.append(e.toString());
            sb.append(" is not of type Boolean");

            messages.add(new ErrorMessage(sb.toString(),e));
        }
        return messages;

    }

    public List<Node> getInvalidConditions() {
        return invalidConditions;
    }
}
