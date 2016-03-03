package testEvaluation;

import static org.junit.Assert.*;

import org.junit.Test;

import semanticAction.evaluation.Value;
import semanticAction.tree.expressionNode.comparison.Equal;
import semanticAction.tree.expressionNode.comparison.GreaterEqual;
import semanticAction.tree.expressionNode.comparison.GreaterThan;
import semanticAction.tree.expressionNode.comparison.LessEqual;
import semanticAction.tree.expressionNode.comparison.LessThan;
import semanticAction.tree.expressionNode.comparison.NotEqual;
import semanticAction.tree.expressionNode.literal.Integerliteral;

public class TestComparison extends TestAbstract {

	@Test
	public void testComparisons() throws Exception {
		Value value1 = evaluate(new Equal(new Integerliteral(7), new Integerliteral(8)));
		Value value2 = evaluate(new GreaterEqual(new Integerliteral(8), new Integerliteral(8)));
		Value value3 = evaluate(new GreaterThan(new Integerliteral(78), new Integerliteral(8)));
		Value value4 = evaluate(new LessThan(new Integerliteral(666), new Integerliteral(-2)));
		Value value5 = evaluate(new LessEqual(new Integerliteral(102), new Integerliteral(103)));
		Value value6 = evaluate(new NotEqual(new Integerliteral(102), new Integerliteral(103)));
		
		assertEquals("7 ==8", value1.getValue(), false);
		assertEquals("8 >= 8", value2.getValue(), true);
		assertEquals("78 > 8", value3.getValue(), true);
		assertEquals("666 < -2", value4.getValue(), false);
		assertEquals("102 <= 103", value5.getValue(), true);
		assertEquals("102 != 103", value6.getValue(), true);
	}
}
