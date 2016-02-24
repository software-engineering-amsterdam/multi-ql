package uva.ql.deprecated;

import uva.ql.ast.AST;
import uva.ql.ast.variables.Str;
import uva.ql.interfaces.IASTNode;
import uva.ql.interfaces.IASTNodeVisitor;

public class ASTForm extends ASTBlock implements IASTNode {
	
	private Str name;
	
	ASTForm(AST ast) {
		super(ast);
	}

	@Override
	int getNodeType0() {
		return FORM;
	}

	public Str getName() {
		return name;
	}

	public void setName(Str name) {
		this.name = name;
	}
	
	@Override
	public void accept(IASTNodeVisitor visitor) {
		visitor.visitForm(this);
	}
}
