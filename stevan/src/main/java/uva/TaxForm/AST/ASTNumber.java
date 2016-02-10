package uva.TaxForm.AST;

import uva.TaxForm.Interfaces.IASTNode;
import uva.TaxForm.Interfaces.IASTNodeVisitor;
import uva.TaxForm.Interfaces.INumber;

public class ASTNumber extends ASTNode implements INumber, IASTNode {
	
	private String value;
	private int type = 0;
	
	ASTNumber(AST ast) {
		super(ast);
	}

	@Override
	int getNodeType0() {
		return ASTNode.NUMBER;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public void accept(IASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
