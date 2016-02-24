package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.form.*;

/* Visitor that visits every node in the AST based on Depth First Search 
 * starting with the leftmost node.
 */
public class LeftDFSVisitor<U> implements Visitor<U> {
	protected Visitor v;
	
	protected LeftDFSVisitor() {
		this.v = this;
	}
	

	public void visit(BinaryExpr binExpr, U context) {
		dfs(binExpr, this);	
	}

	public void visit(UnaryExpr unExpr, U context) {
		
	}
	
	public void visit(ASTNode node, U context) {
		
	}

	public void visit(And and, U context) {
		dfs(and, this);
	}

	public void visit(Or or, U context) {
		dfs(or, this);
	}

	public void visit(Not not, U context) {
		not.getChild().accept(this);
	}

	public void visit(Eq eq, U context) {
		dfs(eq, this);
	}

	public void visit(GEq geq, U context) {
		dfs(geq, this);
	}

	public void visit(GT gt, U context) {
		dfs(gt, this);
	}

	public void visit(LEq leq, U context) {
		dfs(leq, this);
	}

	public void visit(LT lt, U context) {
		dfs(lt, this);
	}

	public void visit(NEq neq, U context) {
		dfs(neq, this);
	}

	public void visit(Add add, U context) {
		dfs(add, this);
	}

	public void visit(Sub sub, U context) {
		dfs(sub, this);
	}

	public void visit(Div div, U context) {
		dfs(div, this);
	}

	public void visit(Mul mul, U context) {
		dfs(mul, this);
	}

	public void visit(Neg neg, U context) {
		neg.getChild().accept(this);
	}

	public void visit(Pos pos, U context) {
		pos.getChild().accept(this);
	}

	public void visit(IntegerLiteral integerLiteral, U context) {
		// leaf node
	}

	public void visit(StringLiteral stringLiteral, U context) {
		// leaf node
	}

	public void visit(BooleanLiteral booleanLiteral, U context) {
		// leaf node
	}

	public void visit(Variable variable, U context) {
		// leaf node
	}

	public void visit(Block block, U context) {
		for (ASTNode stmt : block.getStmts()) {
			stmt.accept(this);
		}
	}

	public void visit(ElseStatement elseStatement, U context) {
		elseStatement.getBlock().accept(this); 
	}

	public void visit(IfStatement ifStatement, U context) {
		ifStatement.getClause().accept(this);
		ifStatement.getBlock().accept(this);
	}

	public void visit(Question question, U context) {
		// no further action required
	}
	
	public void visit(ComputedQuestion computedQuestion, U context) {
		computedQuestion.getExpr().accept(this);
	}

	public void dfs(BinaryExpr e, Visitor v) {
		assert e.getLhs() != null;
		assert e.getRhs() != null;
		e.getLhs().accept(v);
		e.getRhs().accept(v);
	}
}
