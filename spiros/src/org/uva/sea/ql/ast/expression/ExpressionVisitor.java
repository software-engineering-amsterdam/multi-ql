package org.uva.sea.ql.ast.expression;

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

public interface ExpressionVisitor<ASTNode> {
	
	public ASTNode visit(Equal node);
	public ASTNode visit(NotEqual node);
	public ASTNode visit(Greater node);
	public ASTNode visit(GreaterOrEqual node);
	public ASTNode visit(Less node);
	public ASTNode visit(LessOrEqual node);
	
	public ASTNode visit(BooleanLiteral node);
	public ASTNode visit(Identifier node);
	public ASTNode visit(IntegerLiteral node);
	public ASTNode visit(StringLiteral node);
	
	public ASTNode visit(And node);
	public ASTNode visit(Or node);
	
	public ASTNode visit(Add node);
	public ASTNode visit(Sub node);
	public ASTNode visit(Mul node);
	public ASTNode visit(Div node);
	
	public ASTNode visit(Parenthesis node);
	
	public ASTNode visit(Not node);
	public ASTNode visit(Positive node);
	public ASTNode visit(Negative node);
	

}
