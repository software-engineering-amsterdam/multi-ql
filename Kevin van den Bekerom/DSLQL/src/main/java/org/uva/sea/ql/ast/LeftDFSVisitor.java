package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.Add;
import org.uva.sea.ql.ast.expr.And;
import org.uva.sea.ql.ast.expr.BinaryExpr;
import org.uva.sea.ql.ast.expr.BooleanLiteral;
import org.uva.sea.ql.ast.expr.Div;
import org.uva.sea.ql.ast.expr.Eq;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.expr.GEq;
import org.uva.sea.ql.ast.expr.GT;
import org.uva.sea.ql.ast.expr.IntegerLiteral;
import org.uva.sea.ql.ast.expr.LEq;
import org.uva.sea.ql.ast.expr.LT;
import org.uva.sea.ql.ast.expr.Mul;
import org.uva.sea.ql.ast.expr.NEq;
import org.uva.sea.ql.ast.expr.Neg;
import org.uva.sea.ql.ast.expr.Not;
import org.uva.sea.ql.ast.expr.Or;
import org.uva.sea.ql.ast.expr.Pos;
import org.uva.sea.ql.ast.expr.StringLiteral;
import org.uva.sea.ql.ast.expr.Sub;
import org.uva.sea.ql.ast.expr.UnaryExpr;
import org.uva.sea.ql.ast.expr.VariableLiteral;
import org.uva.sea.ql.ast.stat.Block;
import org.uva.sea.ql.ast.stat.ElseStatement;
import org.uva.sea.ql.ast.stat.IfStatement;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.ast.stat.Stat;
import org.uva.sea.ql.ast.stat.Variable;


public class LeftDFSVisitor implements Visitor {

	/* Visitor that visits every node in the AST based on Depth First Search 
	 * starting with the leftmost node.
	 */
	
	public void visit(Expr expr) {
		System.out.println(expr.getType().name());
	}

	public void visit(BinaryExpr binExpr) {
		
	}

	public void visit(UnaryExpr unExpr) {
		
	}
	public void visit(ASTNode node) {
		System.out.println("hello!");
	}

	public void visit(And and) {
		dfs(and, this);
		System.out.println("Hello!");
	}

	public void visit(Or or) {
		dfs(or, this);
	}

	public void visit(Not not) {
		not.getChild().accept(this);
	}

	public void visit(Eq eq) {
		dfs(eq, this);
	}

	public void visit(GEq geq) {
		dfs(geq, this);
	}

	public void visit(GT gt) {
		dfs(gt, this);
	}

	public void visit(LEq leq) {
		dfs(leq, this);
	}

	public void visit(LT lt) {
		dfs(lt, this);
	}

	public void visit(NEq neq) {
		dfs(neq, this);
	}

	public void visit(Add add) {
		dfs(add, this);
	}

	public void visit(Sub sub) {
		System.out.println("hello! sub" + sub.getType().name());
		dfs(sub, this);
	}

	public void visit(Div div) {
		dfs(div, this);
	}

	public void visit(Mul mul) {
		dfs(mul, this);
	}

	public void visit(Neg neg) {
		neg.getChild().accept(this);
	}

	public void visit(Pos pos) {
		pos.getChild().accept(this);
	}

	public void visit(IntegerLiteral integerLiteral) {
		// leaf node
	}

	public void visit(StringLiteral stringLiteral) {
		// leaf node
	}

	public void visit(BooleanLiteral booleanLiteral) {
		// leaf node
	}

	public void visit(VariableLiteral variableLiteral) {
		// leaf node
	}

	public void visit(Block block) {
		for (Stat stmt : block.getStmts()) {
			stmt.accept(this);
		}
	}

	public void visit(ElseStatement elseStatement) {
		elseStatement.getBlock().accept(this); 
	}

	public void visit(IfStatement ifStatement) {
		System.out.println("hello!");
		ifStatement.getClause().accept(this);
		ifStatement.getBlock().accept(this);
	}

	public void visit(Question question) {
		System.out.println("hello question!");
		int size = question.getComputedResult().size();
		if (size == 1) {
			question.getComputedResult().get(0).accept(this);
		}
	}

	public void visit(Stat stat) {
		
	}

	public void visit(Variable variable) {
		
	}
	
	public void dfs(BinaryExpr expr, Visitor v) {
		if (expr.getLhs() != null) {
			expr.getLhs().accept(v);
		}
		if (expr.getLhs() != null) {
			expr.getRhs().accept(v);
		}
	}

}
