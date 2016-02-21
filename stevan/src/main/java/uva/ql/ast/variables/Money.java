package uva.ql.ast.variables;

import uva.ql.ast.AST;
import uva.ql.ast.AVariable;
import uva.ql.interfaces.IVariable;

public class Money<T> extends AVariable {

	private T value;
	
	public Money(AST ast) {
		super(ast);
	}

	@Override
	protected int getVarType0() {
		return IVariable.MONEY;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T eval() {
		return value;
	}
}
