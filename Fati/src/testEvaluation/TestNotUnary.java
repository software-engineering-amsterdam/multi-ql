package testEvaluation;

import static org.junit.Assert.*;

import org.junit.Test;

import semanticAction.evaluation.Value;
import semanticAction.tree.expressionNode.literal.Booleanliteral;
import semanticAction.tree.expressionNode.unary.NOT;

public class TestNotUnary extends TestAbstract{

	@Test
	public void testNotComparisons() throws Exception {
		Value<?> value1 = evaluate(new NOT(new Booleanliteral(true)));
		Value<?> value2 = evaluate(new NOT(new Booleanliteral(false)));
		
		assertEquals("!t = f", value1.getValue(), false);
		assertEquals("!f = t", value2.getValue(), true);
		
	}
}
