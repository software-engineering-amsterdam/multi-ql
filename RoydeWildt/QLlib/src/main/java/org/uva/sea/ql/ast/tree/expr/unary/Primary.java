package org.uva.sea.ql.ast.tree.expr.unary;

import org.uva.sea.ql.ast.tree.val.Val;
import org.uva.sea.ql.ast.visitor.interfaces.IExprVisitor;

/**
 * Created by roy on 12-2-16.
 */
public class Primary extends UnaryExpr {

    private Val value;

    public Primary (Val value){
        super(0);
        this.value = value;
    }

    public Primary(int line, Val value) {
        super(line);
        this.value = value;
    }

    public Val getValue() {
        return value;
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
    public <E, C> E accept(IExprVisitor<E, C> visitor, C context) {
        return visitor.visit(this,context);
    }
}
