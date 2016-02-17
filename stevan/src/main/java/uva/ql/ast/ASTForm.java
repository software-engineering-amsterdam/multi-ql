package uva.ql.ast;

import uva.ql.interfaces.IASTNode;
import uva.ql.interfaces.IASTNodeVisitor;

public class ASTForm extends ASTBlock implements IASTNode {
	
	private String name;
	
	ASTForm(AST ast) {
		super(ast);
	}

	@Override
	int getNodeType0() {
		return FORM;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void accept(IASTNodeVisitor visitor) {
		visitor.visitForm(this);
	}
}
