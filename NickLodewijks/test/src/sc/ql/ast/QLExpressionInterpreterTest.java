package sc.ql.ast;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import sc.ql.Interpreter;
import sc.ql.ast.value.BooleanValue;
import sc.ql.ast.value.NumberValue;
import sc.ql.ast.value.StringValue;

public class QLExpressionInterpreterTest {

	@Test
	public void testExpressions() throws IOException {
		// Integer literal
		Assert.assertEquals(new NumberValue(1), interpret("1"));
		Assert.assertEquals(new NumberValue(2), interpret("2"));

		// Boolean literal
		Assert.assertEquals(BooleanValue.TRUE, interpret("true"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("false"));

		// String literal
		Assert.assertEquals(new StringValue("test1"), interpret("\"test1\""));
		Assert.assertEquals(new StringValue("test2"), interpret("\"test2\""));

		// +
		Assert.assertEquals(new NumberValue(3), interpret("1+2"));
		Assert.assertEquals(new NumberValue(3), interpret("(1+2)"));

		// -
		Assert.assertEquals(new NumberValue(-1), interpret("1-2"));
		Assert.assertEquals(new NumberValue(-1), interpret("(1-2)"));

		// *
		Assert.assertEquals(new NumberValue(2), interpret("1*2"));
		Assert.assertEquals(new NumberValue(-2), interpret("-1*2"));
		Assert.assertEquals(new NumberValue(2), interpret("-1*-2"));
		Assert.assertEquals(new NumberValue(-2), interpret("1*-2"));
		Assert.assertEquals(new NumberValue(2), interpret("(1*2)"));
		Assert.assertEquals(new NumberValue(-2), interpret("-(1*2)"));

		// /
		Assert.assertEquals(new NumberValue(2), interpret("4/2"));
		Assert.assertEquals(new NumberValue(2), interpret("(4/2)"));

		// <
		Assert.assertEquals(BooleanValue.FALSE, interpret("1<1"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("1<-1"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("-1<1"));

		// >
		Assert.assertEquals(BooleanValue.FALSE, interpret("1>1"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("1>-1"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("-1>1"));

		// >=
		Assert.assertEquals(BooleanValue.FALSE, interpret("1>=2"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("1>=1"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("1>=0"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("1>=-1"));

		// <=
		Assert.assertEquals(BooleanValue.TRUE, interpret("1<=2"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("1<=1"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("1<=0"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("1<=-1"));

		// ==
		Assert.assertEquals(BooleanValue.TRUE, interpret("1==1"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("\"test\"==\"test\""));
		Assert.assertEquals(BooleanValue.FALSE, interpret("\"test\"!=\"test\""));
		Assert.assertEquals(BooleanValue.FALSE, interpret("\"test\"==\"test2\""));
		Assert.assertEquals(BooleanValue.TRUE, interpret("\"test\"!=\"test2\""));

		// ||
		Assert.assertEquals(BooleanValue.TRUE, interpret("true || false"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("true || true"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("false || true"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("false || false"));

		// &&
		Assert.assertEquals(BooleanValue.FALSE, interpret("true && false"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("true && true"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("false && true"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("false && false"));

		// !
		Assert.assertEquals(BooleanValue.FALSE, interpret("!true"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("!false"));
	}

	@Test
	public void testOperatorPrecedence() throws IOException {
		Assert.assertEquals(new NumberValue(6), interpret("(1+2)*2"));
		Assert.assertEquals(new NumberValue(5), interpret("1+2*2"));

		Assert.assertEquals(new NumberValue(1), interpret("1"));
		Assert.assertEquals(new NumberValue(0), interpret("1-1"));
		Assert.assertEquals(new NumberValue(2), interpret("1--1"));
		Assert.assertEquals(new NumberValue(1), interpret("1--1-1"));
		Assert.assertEquals(new NumberValue(1), interpret("1--(1-1)"));

		Assert.assertEquals(new NumberValue(-1), interpret("-1"));
		Assert.assertEquals(new NumberValue(0), interpret("-1+1"));
		Assert.assertEquals(new NumberValue(-2), interpret("-1-1"));
		Assert.assertEquals(new NumberValue(-2), interpret("-(1+1)"));

		Assert.assertEquals(new NumberValue(-8), interpret("-1 * (4*2)"));
		Assert.assertEquals(new NumberValue(-2), interpret("-1 * (4/2)"));
		Assert.assertEquals(new NumberValue(-4), interpret("-1 * (4*2/2)"));
		Assert.assertEquals(new NumberValue(0), interpret("-1 * (4*2/2) + 4"));

		Assert.assertEquals(BooleanValue.TRUE, interpret("(-1 * (4*2/2) + 4) == 0"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("(-1 * (4*2/2) + 4) != 0"));

		Assert.assertEquals(BooleanValue.TRUE, interpret("(true || false) && (true || false)"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("true || false && true || false"));

		Assert.assertEquals(BooleanValue.FALSE, interpret("false || true && false"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("false || (true && false)"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("(false || true) && false"));

		Assert.assertEquals(BooleanValue.FALSE, interpret("false || (false && true) || false"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("false || false && true || false"));

		Assert.assertEquals(BooleanValue.TRUE, interpret("0 > 1 || 1 > 0"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("0 > 1 && 1 > 0"));

		Assert.assertEquals(BooleanValue.FALSE, interpret("0>1"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("0>(1*1)"));
		Assert.assertEquals(BooleanValue.FALSE, interpret("(1-1)>(1*1)"));

		Assert.assertEquals(BooleanValue.TRUE, interpret("(1-1)>(1*1) || true"));
		Assert.assertEquals(BooleanValue.TRUE, interpret("true || (1-1)>(1*1)"));
	}

	private Object interpret(String input) throws IOException {
		return Interpreter.interpret(Expression.create(input), null);
	}

}
