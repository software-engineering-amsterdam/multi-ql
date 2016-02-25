package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.type.BooleanType;
import org.uva.sea.ql.ast.type.ValueType;
import org.uva.sea.ql.ast.visitor.TypeVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by roydewildt on 17/02/16.
 */
public class InvalidConditionsCheck extends TypeVisitor<Void,Void,Void> {
    private final List<Node> invalidConditions;

    public InvalidConditionsCheck(Form f) {
        this.invalidConditions = new ArrayList<>();
        f.accept(this);

    }

    @Override
    public Void visit(IfElse stat) {
        ValueType exprType = stat.getCond().accept(this);
        if(exprType == null || !exprType.equals(new BooleanType()))
            invalidConditions.add(stat.getCond());
        return null;
    }

    @Override
    public Void visit(If stat) {
        ValueType exprType = stat.getCond().accept(this);
        if(exprType == null || !exprType.equals(new BooleanType()))
            invalidConditions.add(stat.getCond());
        return null;
    }

    public List<Node> getInvalidConditions() {
        return invalidConditions;
    }
}
