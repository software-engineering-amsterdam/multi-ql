package uva.ql.ast.expressions;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.expressions.abstracts.RelationalOperator;
import uva.ql.ast.expressions.types.LessThen;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ExpLessThen extends RelationalOperator {

	private Type type = new LessThen();
	
	public ExpLessThen(Node parent, Node lhs, Node rhs, int startLine, int startColumn) {
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
		visitor.visitExpLessThen(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitExpLessThen(this);
	}
	
	@Override
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {
		visitor.visitExpLessThen(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitExpLessThen(this);
	}
}
