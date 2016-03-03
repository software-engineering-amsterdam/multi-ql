package testEvaluation;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

import semanticAction.evaluation.Value;
import semanticAction.tree.expressionNode.calculation.Add;
import semanticAction.tree.expressionNode.calculation.Division;
import semanticAction.tree.expressionNode.calculation.SUB;
import semanticAction.tree.expressionNode.calculation.Time;
import semanticAction.tree.expressionNode.literal.Integerliteral;

public class TestCalculation extends TestAbstract {
	@Test
	public void testADD()  {
		Value value = evaluate( new Add(new Integerliteral(3), new Integerliteral(5)));
		Value value2 = evaluate( new Add(new Integerliteral(1), new Integerliteral(4)));
		
		
		assertEquals("3+5 = 8", value.getValue(), 8);
		assertEquals("1+4 = 5", value2.getValue(), 5); 
		//assertEquals("(1+4)+4 = 9", value3.getValue(), 9);
	}
	
	@Test
	public void testSUB() {
		//Value value = evaluate( new SUB(new Integerliteral(5), new Integerliteral(3)));
		Value value2 = evaluate( new SUB(new Integerliteral(4), new Integerliteral(1)));
		//Value value3 = evaluate( new SUB(new Add(new Integerliteral(1), new Integerliteral(4)), int4));
		
		
		assertEquals("4-1 = 3", value2.getValue(), 3); 
		//assertEquals("(1+4)-4 = 1", value3.getValue(), 1);
	}
	@Test
	public void testTime() {
		Value value = evaluate( new Time(new Integerliteral(5), new Integerliteral(4)));
		
		assertEquals("5*4 = 20", value.getValue(), 20);
	}
	@Test
	public void testDivision() {
		Value value1 = evaluate(new Division(new Integerliteral(20), new Integerliteral(4)));
		Value value2 = evaluate(new Division(new Integerliteral(45), new Integerliteral(9)));
		
		assertEquals("20/4 = 5", value1.getValue(), 5);
		assertEquals("45/9 = 5", value2.getValue(), 5);
	}

	

}
