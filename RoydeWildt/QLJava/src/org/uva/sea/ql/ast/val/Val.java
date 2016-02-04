package org.uva.sea.ql.ast.val;

/**
 * Created by roydewildt on 04/02/16.
 */
abstract public class Val {
    private String x;

    public Val(String x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(" + x + ")";
    }
}
