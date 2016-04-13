package uva.ql.ast;

import uva.ql.visitors.INodeVisitor;

public interface INode {

	public void accept(INodeVisitor visitor);
		
}
