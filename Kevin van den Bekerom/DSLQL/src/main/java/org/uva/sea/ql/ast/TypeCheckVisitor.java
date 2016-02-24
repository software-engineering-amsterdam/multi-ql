package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.LeftDFSVisitor;
import org.uva.sea.ql.errors.QLError;

//TODO: update for MONEY Types
// MONEY + | - MONEY OK
// MONEY * | / MONEY NOT OK
// MONEY (+ | - | * | /) INT OK
public class TypeCheckVisitor extends LeftDFSVisitor<Void> {
	
	private List<QLError> errorMessages;
	private Context context;
	
	private TypeCheckVisitor(Context context) {
		this.context = context;
	}
	//TODO: encapsulate static method calls private copnstructor
	//
	
	@Override
	public void visit(And and, Void context) {
		childrenOfType(and, Type.BOOLEAN);
		super.visit(and, null);
	}

	@Override
	public void visit(Or or, Void context) {
		childrenOfType(or, Type.BOOLEAN);
		super.visit(or, null);
	}

	@Override
	public void visit(Not not, Void context) {
		childrenOfType(not, Type.BOOLEAN);
		super.visit(not, null);
	}

	@Override
	public void visit(Eq eq, Void context) {
		childrenEqualityWrapper(eq);
		super.visit(eq, null);
	}

	@Override
	public void visit(GEq geq, Void context) {
		childrenOfType(geq, Type.INT);
		super.visit(geq, null);
	}

	@Override
	public void visit(GT gt, Void context) {
		childrenOfType(gt, Type.INT);
		super.visit(gt, null);
	}

	@Override
	public void visit(LEq leq, Void context) {
		childrenOfType(leq, Type.INT);
		super.visit(leq, null);
	}

	@Override
	public void visit(LT lt, Void context) {
		childrenOfType(lt, Type.INT);
		super.visit(lt, null);
	}

	@Override
	public void visit(NEq neq, Void context) {
		childrenEqualityWrapper(neq);
		super.visit(neq, null);
	}

	@Override
	public void visit(Add add, Void context) {
		childrenOfType(add, Type.INT);
		super.visit(add, null);
	}

	@Override
	public void visit(Sub sub, Void context) {
		childrenEqualityWrapper(sub);
		childrenOfType(sub, Type.INT);
		super.visit(sub, null);
	}

	@Override
	public void visit(Div div, Void context) {
		childrenOfType(div, Type.INT);
		super.visit(div, null);
	}

	@Override
	public void visit(Mul mul, Void context) {
		childrenOfType(mul, Type.INT);
		super.visit(mul, null);
	}

	@Override
	public void visit(Neg neg, Void context) {
		childrenOfType(neg, Type.INT);
		super.visit(neg, null);
	}

	@Override
	public void visit(Pos pos, Void context) {
		childrenOfType(pos, Type.INT);
		super.visit(pos, null);
	}

	private void childrenEqualityWrapper(BinaryExpr expr) {
		if (! childrenEqualType(expr)) {
			String errorMessage = "has children of unequal type: (" 
					+ expr.getLhs().getType(context).name() 
					+ " | "
					+ expr.getRhs().getType(context).name()
					+ ")";
			errorMessages.add(new QLError(expr, errorMessage));
		}
	}
	
	private void childrenOfType(BinaryExpr expr, Type type) {
		if (! exprTypeCheck(expr, type)) {
			String errorMessage = "of type " + type.name()
					+ " has children of the wrong type: ("
					+ expr.getLhs().getType(context).name() 
					+ " | "
					+ expr.getRhs().getType(context).name()
					+ ")";
			errorMessages.add(new QLError(expr, errorMessage));
		}
	}
	
	private void childrenOfType(UnaryExpr expr, Type type) {
		if (! exprTypeCheck(expr, type)) {
			String errorMessage = "of type " + type.name()
					+ " has children of the wrong type: ("
					+ expr.getChild().getType(context).name() 
					+ ")";
			errorMessages.add(new QLError(expr, errorMessage));
		}
	}
	
	private boolean childrenEqualType(BinaryExpr expr) {
		Expr lhs = expr.getLhs();
		Expr rhs = expr.getRhs();
		return lhs.getType(context).equals(rhs.getType(context));
	}

	private boolean exprTypeCheck(BinaryExpr expr, Type type) {
		Expr lhs = expr.getLhs();
		Expr rhs = expr.getRhs();
		return lhs.getType(context).equals(type) && rhs.getType(context).equals(type);
	}
	
	private boolean exprTypeCheck(UnaryExpr expr, Type type) {
		Expr lhs = expr.getChild();
		return lhs.getType(context).equals(type);
	}
	
	public static List<QLError> getErrorMessages(ASTNode startNode, Context context) {
		TypeCheckVisitor v = new TypeCheckVisitor(context);
		startNode.accept(v, context);
		return v.errorMessages;
	}
	
}
