package org.uva.sea.ql.type_checker;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.expression.Comparison.Equal;
import org.uva.sea.ql.ast.expression.Comparison.Greater;
import org.uva.sea.ql.ast.expression.Comparison.GreaterOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.Less;
import org.uva.sea.ql.ast.expression.Comparison.LessOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.NotEqual;
import org.uva.sea.ql.ast.expression.Literal.BooleanLiteral;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.IntegerLiteral;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.expression.Logical.And;
import org.uva.sea.ql.ast.expression.Logical.Or;
import org.uva.sea.ql.ast.expression.Numerical.Add;
import org.uva.sea.ql.ast.expression.Numerical.Div;
import org.uva.sea.ql.ast.expression.Numerical.Mul;
import org.uva.sea.ql.ast.expression.Numerical.Sub;
import org.uva.sea.ql.ast.expression.Parenthesis.Parenthesis;
import org.uva.sea.ql.ast.expression.Unary.Negative;
import org.uva.sea.ql.ast.expression.Unary.Not;
import org.uva.sea.ql.ast.expression.Unary.Positive;
import org.uva.sea.ql.ast.expression.*;

// change name

public class CyclicDependenciesVisitor implements ExpressionVisitor<List<String>> {
	
	private List<String> dependencies;
	
	public CyclicDependenciesVisitor() {
		this.dependencies = new ArrayList<String>();
	}

	@Override
	public List<String> visit(Equal node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public List<String> visit(NotEqual node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public List<String> visit(Greater node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public List<String> visit(GreaterOrEqual node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public List<String> visit(Less node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	
	@Override
	public List<String> visit(LessOrEqual node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public List<String> visit(BooleanLiteral node) {
		return this.dependencies;
	}
	

	@Override
	public List<String> visit(Identifier node) {
		this.dependencies.add(node.getValue());
		return this.dependencies;
	}

	
	@Override
	public List<String> visit(IntegerLiteral node) {
		return this.dependencies;
	}
	

	@Override
	public List<String> visit(StringLiteral node) {
		return this.dependencies;
	}

	
	@Override
	public List<String> visit(And node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	
	@Override
	public List<String> visit(Or node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public List<String> visit(Add node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public List<String> visit(Sub node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	@Override
	public List<String> visit(Mul node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	@Override
	public List<String> visit(Div node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	
	// below to be removed . . .
	
	@Override
	public List<String> visit(Parenthesis node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> visit(Not node) {
		node.getExpression().accept(this);
		return this.dependencies;
	}

	@Override
	public List<String> visit(Positive node) {
		node.getExpression().accept(this);
		return this.dependencies;
	}

	@Override
	public List<String> visit(Negative node) {
		node.getExpression().accept(this);
		return this.dependencies;
	}

}
