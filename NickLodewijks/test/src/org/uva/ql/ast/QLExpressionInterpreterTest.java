package org.uva.ql.ast;

import org.junit.Assert;
import org.junit.Test;
import org.uva.ql.QLInterpreter;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.math.Add;
import org.uva.ql.ast.literal.IntegerLiteral;

public class QLExpressionInterpreterTest {

	@Test
	public void testAdd() {

		IntegerLiteral one = new IntegerLiteral(null, 1);
		IntegerLiteral two = new IntegerLiteral(null, 2);
		Add add = new Add(null, new LiteralExpr(null, one), new LiteralExpr(null, two));

		Assert.assertEquals(3, (int) QLInterpreter.interpret(add, null));
	}

}
