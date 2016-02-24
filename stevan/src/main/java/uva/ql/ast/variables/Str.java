package uva.ql.ast.variables;

import uva.ql.ast.AST;
import uva.ql.ast.AVariable;
import uva.ql.interfaces.IVariable;

public class Str extends AVariable {

	public Str(AST ast) {
		super(ast);
	}

	@Override
	protected int getVarType0() {
		return IVariable.STRING;
	}
}
