package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.RelationalOperator;
import uva.ql.ast.expressions.types.NotEqualTo;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ExpNotEqualTo extends RelationalOperator {

	private Type type = new NotEqualTo();
	
	public ExpNotEqualTo(Node parent, Node lhs, Node rhs, int startLine, int startColumn) {
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
		visitor.visitExpNotEqualTo(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitExpNotEqualTo(this);
	}
	
	@Override
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {
		visitor.visitExpNotEqualTo(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitExpNotEqualTo(this);
	}
}
