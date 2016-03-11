package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.object.value.QLValue;

public class EvaluateExeption extends Exception {

    private final QLValue value;

    public EvaluateExeption(QLValue object) {
	this.value = object;
    }

    public QLValue getValue() {
	return value;
    }

}
