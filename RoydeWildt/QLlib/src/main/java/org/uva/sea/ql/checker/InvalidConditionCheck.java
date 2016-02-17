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
public class InvalidConditionCheck<T,U> extends TypeVisitor<T,U>{
    private final List<Node> invalidConditions;

    public InvalidConditionCheck(Form f) {
        super(f);
        this.invalidConditions = new ArrayList<>();
        f.accept(this,null);

    }

    @Override
    public T visit(IfElse stat, U context) {
        if(stat.getCond().accept(this.getV(),null) != Type.Types.BOOLEAN)
            invalidConditions.add(stat.getCond());
        return (T) super.visit(stat,null);
    }

    @Override
    public T visit(If stat, U context) {
        if(stat.getCond().accept(this.getV(),null) != Type.Types.BOOLEAN)
            invalidConditions.add(stat.getCond());
        return (T) super.visit(stat,null);
    }

    public List<Node> getInvalidConditions() {
        return invalidConditions;
    }
}
