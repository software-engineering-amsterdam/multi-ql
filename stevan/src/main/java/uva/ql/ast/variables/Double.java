package uva.ql.ast.variables;

import uva.ql.ast.AST;
import uva.ql.ast.AVariable;
import uva.ql.interfaces.IVariable;

public class Double extends AVariable {

	public Double(AST ast) {
		super(ast);
	}

	@Override
	protected int getVarType0() {
		return IVariable.DOUBLE;
	}

}
