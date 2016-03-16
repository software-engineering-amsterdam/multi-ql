package qlTests;

import static org.junit.Assert.*;

import org.junit.Test;

import ql.ast.expression.Add;
import ql.ast.expression.Mul;
import ql.ast.literal.IntLiteral;
import ql.ast.visitor.Context;
import ql.ast.visitor.Evaluation;

public class ExpressionEvaluation {
	private Evaluation evalVisitor;
	
	@Test
	public void testPrecedence1(){
		//expr = "3+5*2"
		Mul expr = new Mul(1, new Add(1, new IntLiteral(3, 1), new IntLiteral(5, 1)), new IntLiteral(2, 1));
		evalVisitor = new Evaluation(new Context());
		int resultExpression = (int) evalVisitor.visit(expr);
		assertEquals(16, resultExpression);
	}
}