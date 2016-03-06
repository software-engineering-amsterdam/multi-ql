package org.uva.sea.ql.type_checker;

import java.util.HashSet;
import java.util.Set;
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


public class DependentVariables implements ExpressionVisitor<Set<String>> {
	
	private Set<String> dependencies;
	
	public DependentVariables() {
		this.dependencies = new HashSet<String>();
	}

	@Override
	public Set<String> visit(Equal node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public Set<String> visit(NotEqual node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public Set<String> visit(Greater node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public Set<String> visit(GreaterOrEqual node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public Set<String> visit(Less node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	
	@Override
	public Set<String> visit(LessOrEqual node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public Set<String> visit(BooleanLiteral node) {
		return this.dependencies;
	}
	

	@Override
	public Set<String> visit(Identifier node) {
		this.dependencies.add(node.getValue());
		return this.dependencies;
	}

	
	@Override
	public Set<String> visit(IntegerLiteral node) {
		return this.dependencies;
	}
	

	@Override
	public Set<String> visit(StringLiteral node) {
		return this.dependencies;
	}

	
	@Override
	public Set<String> visit(And node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	
	@Override
	public Set<String> visit(Or node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public Set<String> visit(Add node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}
	

	@Override
	public Set<String> visit(Sub node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	@Override
	public Set<String> visit(Mul node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	@Override
	public Set<String> visit(Div node) {
		node.getLeftExpression().accept(this);
		node.getRightExpression().accept(this);
		return this.dependencies;
	}

	
	// below to be removed . . .
	
	@Override
	public Set<String> visit(Parenthesis node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> visit(Not node) {
		node.getExpression().accept(this);
		return this.dependencies;
	}

	@Override
	public Set<String> visit(Positive node) {
		node.getExpression().accept(this);
		return this.dependencies;
	}

	@Override
	public Set<String> visit(Negative node) {
		node.getExpression().accept(this);
		return this.dependencies;
	}

}
