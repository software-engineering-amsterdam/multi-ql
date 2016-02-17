package org.uva.sea.ql.checker;

import org.uva.sea.ql.ast.tree.Node;
import org.uva.sea.ql.ast.tree.form.Form;
import org.uva.sea.ql.ast.tree.stat.If;
import org.uva.sea.ql.ast.tree.stat.IfElse;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.visitor.TypeVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by roydewildt on 17/02/16.
 */
public class InvalidConditionCheck extends TypeVisitor{
    private final List<Node> invalidConditions;

    public InvalidConditionCheck(Form f) {
        super(f);
        this.invalidConditions = new ArrayList<>();
        f.accept(this);

    }

    @Override
    public <T> T visit(IfElse stat) {
        if(stat.getCond().accept(this.getV()) != Type.Types.BOOLEAN)
            invalidConditions.add(stat.getCond());
        return super.visit(stat);
    }

    @Override
    public <T> T visit(If stat) {
        Type.Types x = stat.getCond().accept(this.getV());
        if(stat.getCond().accept(this.getV()) != Type.Types.BOOLEAN)
            invalidConditions.add(stat.getCond());
        return super.visit(stat);
    }

    public List<Node> getInvalidConditions() {
        return invalidConditions;
    }
}
