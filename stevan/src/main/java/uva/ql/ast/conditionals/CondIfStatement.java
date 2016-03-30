package uva.ql.ast.conditionals;

import javax.swing.JPanel;

import uva.ql.ast.Block;
import uva.ql.ast.EnumType;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.types.conditional.IfStatement;
import uva.ql.typechecker.visitors.IArithmeticOperatorVisitor;
import uva.ql.typechecker.visitors.IBinaryOperatorVisitor;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;
import uva.ql.typechecker.visitors.IDupllicateLabelsVisitor;
import uva.ql.typechecker.visitors.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.typechecker.visitors.IUndefinedQuestionVisitor;
import uva.ql.visitors.IGUIVisitor;
import uva.ql.visitors.INodeVisitor;

public class CondIfStatement<T> extends Condition<T> {

	private IfStatement type = new IfStatement();
	private Block lhs;
	
	public CondIfStatement(Expression<T> expression, Block block, int startLine, int startColumn) {
		super(null, expression, startLine, startColumn);
		this.lhs = block;
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

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {
		visitor.visitCondIfStatement(this);
	}
	
	@Override
	public void accept(IBinaryOperatorVisitor visitor) {
		visitor.visitCondIfStatement(this);
	}
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitCondIfStatement(this, panel);
	}
}
