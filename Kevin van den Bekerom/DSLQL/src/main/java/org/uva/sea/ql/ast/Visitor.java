package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.form.*;

public interface Visitor <U>{ 

	// visit expression nodes
	public void visit(BinaryExpr binExpr, U context);
	public void visit(UnaryExpr unExpr, U context);
	
	public void visit(And and, U context);
	public void visit(Or or, U context);
	public void visit(Not not, U context);
	
	public void visit(Eq eq, U context);
	public void visit(GEq geq, U context);
	public void visit(GT gt, U context);
	public void visit(LEq leq, U context);
	public void visit(LT lt, U context);
	public void visit(NEq neq, U context);
	
	public void visit(Add add, U context);
	public void visit(Sub sub, U context);
	public void visit(Div div, U context);
	public void visit(Mul mul, U context);
	
	public void visit(Neg neg, U context);
	public void visit(Pos pos, U context);
	
	public void visit(IntegerLiteral integerLiteral, U context);
	public void visit(StringLiteral stringLiteral, U context);
	public void visit(BooleanLiteral booleanLiteral, U context);
	public void visit(Variable variable, U context);
	
	// visit statement nodes
	public void visit(Block block, U context);
	public void visit(ElseStatement elseStatement, U context);
	public void visit(IfStatement ifStatement, U context);
	public void visit(Question question, U context);
	public void visit(ComputedQuestion question, U context);
	
}
