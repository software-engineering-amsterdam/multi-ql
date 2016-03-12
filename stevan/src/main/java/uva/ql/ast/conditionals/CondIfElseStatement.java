package uva.ql.ast.conditionals;

import uva.ql.ast.Block;
import uva.ql.ast.EnumType;
import uva.ql.ast.conditionals.abstracts.Condition;
import uva.ql.ast.conditionals.types.IfElseStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.visitors.interfaces.INodeVisitor;
import uva.ql.visitors.interfaces.typechecker.IArithmeticOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.IBinaryOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.ICyclicDependencyVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateLabelsVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.visitors.interfaces.typechecker.IUndefinedQuestionVisitor;

public class CondIfElseStatement extends Condition {

	private IfElseStatement type = new IfElseStatement();
	private Block lhs;
	private Block rhs;
	
	public CondIfElseStatement(Expression expression, Block lhsBlock, Block rhsBlock, int startLine, int startColumn) {
		super(null, expression, startLine, startColumn);
		this.lhs = lhsBlock;
		this.rhs = rhsBlock;
	}
	
	@Override
	public EnumType evalType() {
		return this.expression.evalType();
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
	public void accept(ICyclicDependencyVisitor visitor) {
		visitor.visitCondIfElseStatement(this);
	}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {
		visitor.visitCondIfElseStatement(this);
	}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitCondIfElseStatement(this);
	}

	@Override
	public void accept(IBinaryOperatorVisitor visitor) {
		visitor.visitCondIfElseStatement(this);
	}
}
