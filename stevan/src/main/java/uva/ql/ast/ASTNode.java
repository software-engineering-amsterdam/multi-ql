package uva.ql.ast;

import uva.ql.interfaces.IASTNode;
import uva.ql.interfaces.IASTNodeVisitor;


public abstract class ASTNode implements IASTNode {

	public final static int FORM = 1;
	public final static int QUESTION = 2;
	public final static int EXPRESSION = 3;
	public final static int BLOCK = 4;
	public final static int VARIABLE = 5;
	public final static int NUMBER = 6;
	public final static int IF_STATEMENT = 7;
	
	// Variables that apply to all Nodes
	final AST ast;
	private int nodeType = 0;
	private ASTNode parent = null;
	
	// Variables that are optional, dependent on NodeType
	/*private ASTNode leftNode = null;
	private ASTNode rightNode = null;
	private ArrayList<Object> store = new ArrayList<Object>(0);*/
	
	ASTNode(AST ast) {
		if (ast == null) {
			throw new IllegalArgumentException();
		}
		
		this.ast = ast;
		setNodeType(getNodeType0());
	}

	abstract int getNodeType0();
	
	public final int getNodeType() {
		return this.nodeType;
	}
	
	private void setNodeType(int nodeType) {
		this.nodeType = nodeType;
	}

	public ASTNode getParent() {
		return parent;
	}

	public void setParent(ASTNode parent) {
		this.parent = parent;
	}
	
	@Override
	public void accept(IASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
