package org.uva.sea.ql.ast;


import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;

public class TypeCheckVisitor extends LeftDFSVisitor {
	
	@Override
	public void visit(And and) {
		validateBooleanExpression(and);
		super.visit(and);
	}

	@Override
	public void visit(Or or) {
		validateBooleanExpression(or);
		super.visit(or);
	}

	@Override
	public void visit(Not not) {
		validateBooleanExpression(not);
		super.visit(not);
	}

	@Override
	public void visit(Eq eq) {
		Expr lhs = eq.getLhs();
		Expr rhs = eq.getRhs();
		if (! childrenEquality(lhs, rhs)) {
			System.out.println("Node " + eq.toString() + " has children of unequal type!");
		}
		super.visit(eq);
	}

	@Override
	public void visit(GEq geq) {
		validateBooleanExpression(geq);
		super.visit(geq);
	}

	@Override
	public void visit(GT gt) {
		validateBooleanExpression(gt);
		super.visit(gt);
	}

	@Override
	public void visit(LEq leq) {
		validateBooleanExpression(leq);
		super.visit(leq);
	}

	@Override
	public void visit(LT lt) {
		validateBooleanExpression(lt);
		super.visit(lt);
	}

	@Override
	public void visit(NEq neq) {
		validateBooleanExpression(neq);
		super.visit(neq);
	}

	@Override
	public void visit(Add add) {
		validateIntegerExpression(add);
		super.visit(add);
	}

	@Override
	public void visit(Sub sub) {
		validateIntegerExpression(sub);
		super.visit(sub);
	}

	@Override
	public void visit(Div div) {
		validateIntegerExpression(div);
		super.visit(div);
	}

	@Override
	public void visit(Mul mul) {
		validateIntegerExpression(mul);
		super.visit(mul);
	}

	@Override
	public void visit(Neg neg) {
		validateIntegerExpression(neg);
		super.visit(neg);
	}

	@Override
	public void visit(Pos pos) {
		validateIntegerExpression(pos);
		super.visit(pos);
	}

	@Override
	public void visit(Variable variable) {
		// TODO Auto-generated method stub
		
	}
	
	private void validateIntegerExpression(BinaryExpr e) {
		Expr lhs = e.getLhs();
		Expr rhs = e.getRhs();
		if (! childrenEquality(lhs, rhs)) {
			System.out.println("Node " + e.toString() + " has children of unequal type!");
		}
		if (! integerExpressionCheck(lhs, rhs)) {
			System.out.println("Node " + e.toString() + " is an integer expression, but has children that are not of type integer!");
		}
	}
	
	private void validateIntegerExpression(UnaryExpr e) {
		Expr lhs = e.getChild();
		if (! integerExpressionCheck(lhs)) {
			System.out.println("Node " + e.toString() + " is an integer expression, but has children that are not of type integer!");
		}
	}
	
	private void validateBooleanExpression(BinaryExpr e) {
		Expr lhs = e.getLhs();
		Expr rhs = e.getRhs();
		if (! childrenEquality(lhs, rhs)) {
			System.out.println("Node " + e.toString() + " has children of unequal type!");
		}
		if (! booleanExpressionCheck(lhs, rhs)) {
			System.out.println("Node " + e.toString() + " is a boolean expression, but has children that are not of type boolean!");
		}
	}
	
	private void validateBooleanExpression(UnaryExpr e) {
		Expr lhs = e.getChild();
		if (! booleanExpressionCheck(lhs)) {
			System.out.println("Node " + e.toString() + " is a boolean expression, but has children that are not of type boolean!");
		}
	}
	
	
	
	private boolean childrenEquality(Expr lhs, Expr rhs) {
		return lhs.getType().equals(rhs.getType());
	}
	
	private boolean integerExpressionCheck(Expr lhs, Expr rhs) {
		return lhs.getType().equals(Type.INT) && rhs.getType().equals(Type.INT);
	}
	
	private boolean integerExpressionCheck(Expr lhs) {
		return lhs.getType().equals(Type.INT);
	}
	
	private boolean booleanExpressionCheck(Expr lhs, Expr rhs) {
		return lhs.getType().equals(Type.BOOLEAN) && rhs.getType().equals(Type.BOOLEAN);
	}
	
	private boolean booleanExpressionCheck(Expr lhs) {
		return lhs.getType().equals(Type.BOOLEAN);
	}
	
}
