package uva.ql.ast;

import uva.ql.interfaces.IASTNode;
import uva.ql.interfaces.IASTNodeVisitor;
import uva.ql.interfaces.IVariable;

public class ASTVariable extends ASTNode implements IVariable, IASTNode {
	
	private String name = "";
	private int type = 0;
	private String value = "";
	
	ASTVariable(AST ast) {
		super(ast);
	}

	@Override
	int getNodeType0() {
		return ASTNode.VARIABLE;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void accept(IASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
