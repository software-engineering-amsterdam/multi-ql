package uva.ql.interfaces;

import uva.ql.visitors.INodeVisitor;

public interface INode {
	
	public final static int FORM = 1;
	public final static int QUESTION = 2;
	public final static int EXPRESSION = 3;
	public final static int BLOCK = 4;
	public final static int IF_STATEMENT = 7;
	
	public void accept(INodeVisitor visitor);
}
