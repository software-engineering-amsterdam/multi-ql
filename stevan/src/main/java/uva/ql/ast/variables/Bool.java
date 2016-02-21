package uva.ql.ast.variables;

import uva.ql.ast.AST;
import uva.ql.ast.AVariable;
import uva.ql.interfaces.IVariable;

public class Bool extends AVariable {

	public Bool(AST ast) {
		super(ast);
	}

	@Override
	protected int getVarType0() {
		return IVariable.BOOLEAN;
	}

}
