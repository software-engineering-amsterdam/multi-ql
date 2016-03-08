package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.ArithmeticOperator;
import uva.ql.ast.expressions.types.Add;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ExpAdd extends ArithmeticOperator {

	private Add type = new Add();
	
	public ExpAdd(Node parent, Node lhs, Node rhs, int startLine, int startColumn) {
		super(parent, startLine, startColumn, lhs, rhs);
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
		visitor.visitExpAdd(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitExpAdd(this);
	}
	
	@Override
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {
		visitor.visitExpAdd(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitExpAdd(this);
	}
}
