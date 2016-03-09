package org.uva.ql.ast;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.uva.ql.QLInterpreter;
import org.uva.ql.ast.expr.Expr;

public class QLExpressionInterpreterTest {

	@Test
	public void testExpressions() throws IOException {
		// Integer literal
		Assert.assertEquals(1, interpret("1"));
		Assert.assertEquals(2, interpret("2"));

		// Boolean literal
		Assert.assertEquals(true, interpret("true"));
		Assert.assertEquals(false, interpret("false"));

		// String literal
		Assert.assertEquals("test1", interpret("\"test1\""));
		Assert.assertEquals("test2", interpret("\"test2\""));

		// +
		Assert.assertEquals(3, interpret("1+2"));
		Assert.assertEquals(3, interpret("(1+2)"));

		// -
		Assert.assertEquals(-1, interpret("1-2"));
		Assert.assertEquals(-1, interpret("(1-2)"));

		// *
		Assert.assertEquals(2, interpret("1*2"));
		Assert.assertEquals(-2, interpret("-1*2"));
		Assert.assertEquals(2, interpret("-1*-2"));
		Assert.assertEquals(-2, interpret("1*-2"));
		Assert.assertEquals(2, interpret("(1*2)"));
		Assert.assertEquals(-2, interpret("-(1*2)"));

		// /
		Assert.assertEquals(2, interpret("4/2"));
		Assert.assertEquals(2, interpret("(4/2)"));

		// <
		Assert.assertEquals(false, interpret("1<1"));
		Assert.assertEquals(false, interpret("1<-1"));
		Assert.assertEquals(true, interpret("-1<1"));

		// >
		Assert.assertEquals(false, interpret("1>1"));
		Assert.assertEquals(true, interpret("1>-1"));
		Assert.assertEquals(false, interpret("-1>1"));

		// >=
		Assert.assertEquals(false, interpret("1>=2"));
		Assert.assertEquals(true, interpret("1>=1"));
		Assert.assertEquals(true, interpret("1>=0"));
		Assert.assertEquals(true, interpret("1>=-1"));

		// <=
		Assert.assertEquals(true, interpret("1<=2"));
		Assert.assertEquals(true, interpret("1<=1"));
		Assert.assertEquals(false, interpret("1<=0"));
		Assert.assertEquals(false, interpret("1<=-1"));

		// ==
		Assert.assertEquals(true, interpret("1==1"));
		Assert.assertEquals(true, interpret("\"test\"==\"test\""));
		Assert.assertEquals(false, interpret("\"test\"!=\"test\""));
		Assert.assertEquals(false, interpret("\"test\"==\"test2\""));
		Assert.assertEquals(true, interpret("\"test\"!=\"test2\""));

		// ||
		Assert.assertEquals(true, interpret("true || false"));
		Assert.assertEquals(true, interpret("true || true"));
		Assert.assertEquals(true, interpret("false || true"));
		Assert.assertEquals(false, interpret("false || false"));

		// &&
		Assert.assertEquals(false, interpret("true && false"));
		Assert.assertEquals(true, interpret("true && true"));
		Assert.assertEquals(false, interpret("false && true"));
		Assert.assertEquals(false, interpret("false && false"));

		// !
		Assert.assertEquals(false, interpret("!true"));
		Assert.assertEquals(true, interpret("!false"));
	}

	@Test
	public void testOperatorPrecedence() throws IOException {
		Assert.assertEquals(6, interpret("(1+2)*2"));
		Assert.assertEquals(5, interpret("1+2*2"));

		Assert.assertEquals(1, interpret("1"));
		Assert.assertEquals(0, interpret("1-1"));
		Assert.assertEquals(2, interpret("1--1"));
		Assert.assertEquals(1, interpret("1--1-1"));
		Assert.assertEquals(1, interpret("1--(1-1)"));

		Assert.assertEquals(-1, interpret("-1"));
		Assert.assertEquals(0, interpret("-1+1"));
		Assert.assertEquals(-2, interpret("-1-1"));
		Assert.assertEquals(-2, interpret("-(1+1)"));

		Assert.assertEquals(-8, interpret("-1 * (4*2)"));
		Assert.assertEquals(-2, interpret("-1 * (4/2)"));
		Assert.assertEquals(-4, interpret("-1 * (4*2/2)"));
		Assert.assertEquals(0, interpret("-1 * (4*2/2) + 4"));

		Assert.assertEquals(true, interpret("(-1 * (4*2/2) + 4) == 0"));
		Assert.assertEquals(false, interpret("(-1 * (4*2/2) + 4) != 0"));

		Assert.assertEquals(true, interpret("(true || false) && (true || false)"));
		Assert.assertEquals(true, interpret("true || false && true || false"));

		Assert.assertEquals(false, interpret("false || true && false"));
		Assert.assertEquals(false, interpret("false || (true && false)"));
		Assert.assertEquals(false, interpret("(false || true) && false"));

		Assert.assertEquals(false, interpret("false || (false && true) || false"));
		Assert.assertEquals(false, interpret("false || false && true || false"));

		Assert.assertEquals(true, interpret("0 > 1 || 1 > 0"));
		Assert.assertEquals(false, interpret("0 > 1 && 1 > 0"));

		Assert.assertEquals(false, interpret("0>1"));
		Assert.assertEquals(false, interpret("0>(1*1)"));
		Assert.assertEquals(false, interpret("(1-1)>(1*1)"));

		Assert.assertEquals(true, interpret("(1-1)>(1*1) || true"));
		Assert.assertEquals(true, interpret("true || (1-1)>(1*1)"));
	}

	private Object interpret(String input) throws IOException {
		return QLInterpreter.interpret(Expr.create(input), null);
	}

}
