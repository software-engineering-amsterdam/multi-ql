package uva.ql.ast.conditionals;

import uva.ql.ast.Block;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.conditionals.abstracts.Condition;
import uva.ql.ast.conditionals.types.IfElseStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.INodeVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class CondIfElseStatement extends Condition {

	private Type type = new IfElseStatement();
	private Block lhs;
	private Block rhs;
	
	public CondIfElseStatement(Expression expression, Block lhsBlock, Block rhsBlock, int startLine, int startColumn) {
		super(null, expression, startLine, startColumn);
		this.lhs = lhsBlock;
		this.rhs = rhsBlock;
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
	
	@Override
	public String typeToString() {
		return this.type.getType();
	}
	
	public void setLhs(Block lhs) {
		this.lhs = lhs;
	}
	
	public Block getLhs() {
		return this.lhs;
	}
	
	public void setRhs(Block rhs) {
		this.rhs = rhs;
	}
	
	public Block getRhs() {
		return this.rhs;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitIfElseCondition(this);
	}

	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitCondIfElseStatement(this);
	}
	
	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {
		visitor.visitCondIfElseStatement(this);
	}

	@Override
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {
		visitor.visitCondIfElseStatement(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitCondIfElseStatement(this);
	}
}
