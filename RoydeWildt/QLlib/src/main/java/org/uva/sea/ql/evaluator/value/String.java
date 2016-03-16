package org.uva.sea.ql.evaluator.value;

/**
 * Created by roydewildt on 16/03/16.
 */
public class String extends Value {
    private java.lang.String value;

    public String(java.lang.String value) {
        this.value = value;
    }

    public java.lang.String getValue() {
        return value;
    }
}
