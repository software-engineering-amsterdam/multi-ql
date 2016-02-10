package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.var.Type;

public class strLiteral extends strExpr{

	private final String value;
	
	public strLiteral(String value) {
		this.value = value;
	}

	@Override
	public String result(SymbolTabel table) {
		return value;
	}

	@Override
	public Boolean checkType() {
		return true;
	}
}
