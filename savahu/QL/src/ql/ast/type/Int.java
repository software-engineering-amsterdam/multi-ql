package ql.ast.type;

import ql.ast.expression.Expr;

public class Int extends Expr {

	private final Integer value;

        public Int(Integer value)
        {
            this.value = value;
        }

	public Integer getValue() {
		return value;
	}
	
}