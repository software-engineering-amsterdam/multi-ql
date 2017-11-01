package ql2;

import ql2.ast.expression.BinaryExpr;
import ql2.ast.expression.IdentityExpr;
import ql2.ast.expression.LiteralExpr;
import ql2.ast.expression.UnaryExpr;
import ql2.ast.expression.arithmatic.Addition;
import ql2.ast.expression.arithmatic.Divide;
import ql2.ast.expression.arithmatic.Multiply;
import ql2.ast.expression.arithmatic.Negative;
import ql2.ast.expression.arithmatic.Positive;
import ql2.ast.expression.logic.And;
import ql2.ast.expression.logic.Equal;
import ql2.ast.expression.logic.GreaterThan;
import ql2.ast.expression.logic.GreaterThanOrEqual;
import ql2.ast.expression.logic.LesserThan;
import ql2.ast.expression.logic.LesserThanOrEqual;
import ql2.ast.expression.logic.Not;
import ql2.ast.expression.logic.NotEqual;
import ql2.ast.expression.logic.Or;
import ql2.ast.literal.BooleanLiteral;
import ql2.ast.literal.IntegerLiteral;
import ql2.ast.literal.StringLiteral;

public class EvalVisitor extends BaseVisitor<Object>{

	private Context context;
	
	public EvalVisitor(Context ctx) {
		this.context = ctx;
	}
	
	@Override
	public Object visit(And node) {
		if (node.getLefthand().accept(this) == null || node.getRighthand().accept(this) == null) { 
			return null; 
		}
		return (boolean) node.getLefthand().accept(this) && (boolean) node.getRighthand().accept(this);
	}

	@Override
	public Object visit(Divide node) {
		if (node.getLefthand().accept(this) == null || node.getRighthand().accept(this) == null) { 
			return null; 
		}
		return (int) node.getLefthand().accept(this) / (int) node.getRighthand().accept(this);
	}

	@Override
	public Object visit(Equal node) {
		if (node.getLefthand().accept(this) == null || node.getRighthand().accept(this) == null) { 
			return null; 
		}
		return node.getLefthand().accept(this) == node.getRighthand().accept(this);
	}

	@Override
	public Object visit(GreaterThanOrEqual node) {
		if (node.getLefthand().accept(this) == null || node.getRighthand().accept(this) == null) { 
			return null; 
		}
		return (int) node.getLefthand().accept(this) >= (int) node.getRighthand().accept(this);
	}

	@Override
	public Object visit(GreaterThan node) {
		if (node.getLefthand().accept(this) == null || node.getRighthand().accept(this) == null) { 
			return null; 
		}
		return (int) node.getLefthand().accept(this) > (int) node.getRighthand().accept(this);
	}

	@Override
	public Object visit(LesserThan node) {
		if (node.getLefthand().accept(this) == null || node.getRighthand().accept(this) == null) { 
			return null; 
		}
		return (int) node.getLefthand().accept(this) < (int) node.getRighthand().accept(this);
	}

	@Override
	public Object visit(LesserThanOrEqual node) {
		if (node.getLefthand().accept(this) == null || node.getRighthand().accept(this) == null) { 
			return null; 
		}
		return (int) node.getLefthand().accept(this) <= (int) node.getRighthand().accept(this);
	}

	@Override
	public Object visit(Multiply node) {
		if (node.getLefthand().accept(this) == null || node.getRighthand().accept(this) == null) { 
			return null; 
		}
		return (int) node.getLefthand().accept(this) * (int) node.getRighthand().accept(this);
	}

	@Override
	public Object visit(Negative node) {
		if (node.getExpr().accept(this) == null) return null;
		return -Math.abs((int) node.getExpr().accept(this));
	}

	@Override
	public Object visit(NotEqual node) {
		if (node.getLefthand() == null || node.getRighthand() == null) { 
			return null; 
		}
		// no typecast to allow int, str bool vals.
		return node.getLefthand().accept(this) != node.getRighthand().accept(this);
	}

	@Override
	public Object visit(Or node) {
		if (node.getLefthand() == null || node.getRighthand() == null) { 
			return null; 
		}
		return (boolean) node.getLefthand().accept(this) || (boolean) node.getRighthand().accept(this);
	}

	@Override
	public Object visit(Positive node) {
		if (node.getExpr().accept(this) == null) return null;

		return Math.abs((int) node.getExpr().accept(this)); // positive?
	}

	@Override
	public Object visit(Addition node) {
		if (node.getLefthand() == null || node.getRighthand() == null) { 
			return null; 
		}
		return (int) node.getLefthand().accept(this) + (int) node.getRighthand().accept(this);
	}

	@Override
	public Object visit(Not node) {
		if (node.getExpr().accept(this) == null) return null;

		return ! (boolean) node.getExpr().accept(this);
	}
	
	@Override
	public Object visit(BooleanLiteral node) {
		return node.getValue();
	}

	@Override
	public Object visit(IntegerLiteral node) {
		return node.getValue();
	}

	@Override
	public Object visit(StringLiteral node) {
		return node.getValue();
	}
	
	@Override
	public Object visit(LiteralExpr node) {
		return node.getLiteral().accept(this);
	}
	

	@Override
	public Object visit(IdentityExpr node) {
		return context.getVariable(node.getID());
	}

	public Context getContext() {
		return context;
	}
	
	

	
	
}
