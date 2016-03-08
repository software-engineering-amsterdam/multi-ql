package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.SingleLogicalOperator;
import uva.ql.ast.expressions.types.Not;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ExpNot extends SingleLogicalOperator {

	private Type type = new Not();
	
	public ExpNot(Node parent, Node lhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs);
	}

	@Override
	public Type getType() {
		return this.type;
	}
	
	@Override
	public String typeToString() {
		return this.type.getType();
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitExpNot(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitExpNot(this);
	}
	
	@Override
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {
		visitor.visitExpNot(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitExpNot(this);
	}
}
