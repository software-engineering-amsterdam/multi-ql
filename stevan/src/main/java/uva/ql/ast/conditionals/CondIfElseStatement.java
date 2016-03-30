package uva.ql.ast.conditionals;

import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.EnumType;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.types.conditional.IfElseStatement;
import uva.ql.typechecker.visitors.IArithmeticOperatorVisitor;
import uva.ql.typechecker.visitors.IBinaryOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;
import uva.ql.visitors.IGUIVisitor;
import uva.ql.visitors.INodeVisitor;

public class CondIfElseStatement<T> extends Condition<T> {

	private IfElseStatement type = new IfElseStatement();
	private Block lhs;
	private Block rhs;
	
	public CondIfElseStatement(Expression<T> expression, Block lhsBlock, Block rhsBlock, int startLine, int startColumn) {
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
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitCondIfElseStatement(this, panel);
	}
}
