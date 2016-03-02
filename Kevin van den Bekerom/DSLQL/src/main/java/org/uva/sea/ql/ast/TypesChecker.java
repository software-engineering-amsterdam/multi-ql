package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.Type;
import org.uva.sea.ql.ast.form.Context;
import org.uva.sea.ql.ast.visit.LeftDFSVisitor;
import org.uva.sea.ql.errors.QLError;

//TODO: update for MONEY Types
// MONEY + | - MONEY OK
// MONEY * | / MONEY NOT OK
// MONEY (+ | - | * | /) INT OK
public class TypesChecker extends LeftDFSVisitor<Context> {
	
	private List<QLError> errorMessages;
	private Context context;
	
	private TypesChecker(Context context) {
		this.context = context;
		errorMessages = new ArrayList<QLError>();
	}
	//TODO: encapsulate static method calls private copnstructor
	//
	
	@Override
	public void visit(And and, Context context) {
		childrenOfType(and);
		super.visit(and, null);
	}

	@Override
	public void visit(Or or, Context context) {
		childrenOfType(or);
		super.visit(or, null);
	}

	@Override
	public void visit(Not not, Context context) {
		childrenOfType(not);
		super.visit(not, null);
	}

	@Override
	public void visit(Eq eq, Context context) {
		childrenEqualityWrapper(eq);
		super.visit(eq, null);
	}

	@Override
	public void visit(GEq geq, Context context) {
		childrenOfType(geq);
		super.visit(geq, null);
	}

	@Override
	public void visit(GT gt, Context context) {
		childrenOfType(gt);
		super.visit(gt, null);
	}

	@Override
	public void visit(LEq leq, Context context) {
		childrenOfType(leq);
		super.visit(leq, null);
	}

	@Override
	public void visit(LT lt, Context context) {
		childrenOfType(lt);
		super.visit(lt, null);
	}

	@Override
	public void visit(NEq neq, Context context) {
		childrenEqualityWrapper(neq);
		super.visit(neq, null);
	}

	@Override
	public void visit(Add add, Context context) {
		childrenOfType(add);
		super.visit(add, null);
	}

	@Override
	public void visit(Sub sub, Context context) {
		childrenEqualityWrapper(sub);
		childrenOfType(sub);
		super.visit(sub, null);
	}

	@Override
	public void visit(Div div, Context context) {
		childrenOfType(div);
		super.visit(div, null);
	}

	@Override
	public void visit(Mul mul, Context context) {
		childrenOfType(mul);
		super.visit(mul, null);
	}

	@Override
	public void visit(Neg neg, Context context) {
		childrenOfType(neg);
		super.visit(neg, null);
	}

	@Override
	public void visit(Pos pos, Context context) {
		childrenOfType(pos);
		super.visit(pos, null);
	}

	private void childrenEqualityWrapper(BinaryExpr expr) {
		if (! childrenEqualType(expr)) {
			String errorMessage = "has children of unequal type: (" 
					+ expr.getLhs().getType(context).toString() 
					+ " | "
					+ expr.getRhs().getType(context).toString()
					+ ")";
			errorMessages.add(new QLError(expr, errorMessage));
		}
	}
	
	private void childrenOfType(BinaryExpr expr) {
		if (! exprTypeCheck(expr)) {
			String errorMessage = "of type " + expr.getType(context).toString()
					+ " has children of the wrong type: ("
					+ expr.getLhs().getType(context).toString() 
					+ " | "
					+ expr.getRhs().getType(context).toString()
					+ ")";
			errorMessages.add(new QLError(expr, errorMessage));
		}
	}
	
	private void childrenOfType(UnaryExpr expr) {
		if (! exprTypeCheck(expr)) {
			String errorMessage = "of type " + expr.getType(context).toString()
					+ " has children of the wrong type: ("
					+ expr.getChild().getType(context).toString() 
					+ ")";
			errorMessages.add(new QLError(expr, errorMessage));
		}
	}
	
	private boolean childrenEqualType(BinaryExpr expr) {
		Expr lhs = expr.getLhs();
		Expr rhs = expr.getRhs();
		return lhs.getType(context).equals(rhs.getType(context));
	}

	private boolean exprTypeCheck(BinaryExpr expr) {
		Expr lhs = expr.getLhs();
		Expr rhs = expr.getRhs();
		Type type = expr.getType(context);
		return type.equals(lhs.getType(context)) && type.equals(rhs.getType(context));
	}
	
	private boolean exprTypeCheck(UnaryExpr expr) {
		Expr lhs = expr.getChild();
		return lhs.getType(context).equals(expr.getType(context));
	}
	
	public static List<QLError> getErrorMessages(ASTNode startNode, Context context) {
		TypesChecker v = new TypesChecker(context);
		startNode.accept(v, context);
		return v.errorMessages;
	}
	
}
