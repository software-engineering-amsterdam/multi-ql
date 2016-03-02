package uva.ql.interfaces;

import uva.ql.visitors.INodeVisitor;

public interface INode {
	
	public void accept(INodeVisitor visitor);
}
