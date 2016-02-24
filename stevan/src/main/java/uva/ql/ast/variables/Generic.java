package uva.ql.ast.variables;

import uva.ql.ast.AST;
import uva.ql.ast.AVariable;

public class Generic extends AVariable {

	public Generic(AST ast) {
		super(ast);
	}

	@Override
	protected int getVarType0() {
		return 0;
	}

}
