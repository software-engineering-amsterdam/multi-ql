package uva.TaxForm.AST;

import uva.TaxForm.Interfaces.IASTNode;
import uva.TaxForm.Interfaces.IASTNodeVisitor;

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
		visitor.visit(this);
	}
}
