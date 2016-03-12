package uva.ql.ast.variables.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.variables.IVariable;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.IBinaryOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.INodeVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public abstract class Variable extends Expression implements IVariable {

	private String name;
	
	public Variable(Node parent, String name, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLine(int line) {
		this.startLine = line;
	}
	
	public void setColumn(int col) {
		this.startColumn = col;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitVar(this);
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {}

	@Override
	public void accept(IBinaryOperatorVisitor visitor) {}
}
