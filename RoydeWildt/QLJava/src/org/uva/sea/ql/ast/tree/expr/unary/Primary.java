package org.uva.sea.ql.ast.tree.expr.unary;

import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.visitor.Visitor;

/**
 * Created by roy on 12-2-16.
 */
public class Primary extends UnaryExpr {

    private Val value;

    public Primary(int line, Val value) {
        super(line);
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String getSymbol() {
        return "";
    }

    @Override
    public <T> T accept(Visitor visitor) {
        return visitor.visit(this);
    }

    public Val getValue() {
        return value;
    }
}
