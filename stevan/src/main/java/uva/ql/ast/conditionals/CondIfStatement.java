package uva.ql.ast.conditionals;

import uva.ql.ast.Block;
import uva.ql.ast.EnumType;
import uva.ql.ast.conditionals.abstracts.Condition;
import uva.ql.ast.conditionals.types.IfStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.INodeVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class CondIfStatement extends Condition {

	private IfStatement type = new IfStatement();
	private Block lhs;
	
	public CondIfStatement(Expression expression, Block block, int startLine, int startColumn) {
		super(null, expression, startLine, startColumn);
		this.lhs = block;
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	public void setLhs(Block lhs) {
		this.lhs = lhs;
	}
	
	public Block getLhs() {
		return this.lhs;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitIfCondition(this);
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitCondIfStatement(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitCondIfStatement(this);
	}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitCondIfStatement(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitCondIfStatement(this);
	}
}
