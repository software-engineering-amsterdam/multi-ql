package eu.bankersen.kevin.ql.ast.expr;

import eu.bankersen.kevin.ql.ast.var.Type;

public class strLiteral extends strExpr{

	private final String value;
	
	public strLiteral(String value) {
		this.value = value;
	}
	
	public Type getType(){
		return Type.STRING;
	}

	@Override
	public String result(interpExpr expr) {
		return value;
	}
}
