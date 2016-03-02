package testEvaluation;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import semanticAction.evaluation.Value;
import semanticAction.tree.expressionNode.literal.Booleanliteral;
import semanticAction.tree.expressionNode.logical.And;
import semanticAction.tree.expressionNode.logical.OR;
import semanticAction.tree.expressionNode.unary.NOT;

public class TestMixAndORNot extends TestAbstract{

	@Test
	public void testLogicalComparisons() throws Exception {
		Value value1 = evaluate(new NOT(new OR(new Booleanliteral(true), new Booleanliteral(true))));
		Value value2 = evaluate(new OR(new Booleanliteral(true),
				new And( new Booleanliteral(false),new Booleanliteral(false))));
		
		assertEquals("!(t || t) = f", value1.getValue(), false);
		assertEquals("t || (f && f) = t", value2.getValue(), true);
	}

}
