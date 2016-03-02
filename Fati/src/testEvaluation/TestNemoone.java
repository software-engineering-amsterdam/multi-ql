package testEvaluation;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import semanticAction.evaluation.EvalVisit;
import semanticAction.evaluation.ValueHolder;
import semanticAction.evaluation.Value;
import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.expressionNode.calculation.Add;
import semanticAction.tree.expressionNode.calculation.Division;
import semanticAction.tree.expressionNode.calculation.SUB;
import semanticAction.tree.expressionNode.calculation.Time;
import semanticAction.tree.expressionNode.comparison.Equal;
import semanticAction.tree.expressionNode.comparison.GreaterEqual;
import semanticAction.tree.expressionNode.comparison.GreaterThan;
import semanticAction.tree.expressionNode.comparison.LessEqual;
import semanticAction.tree.expressionNode.comparison.LessThan;
import semanticAction.tree.expressionNode.comparison.NotEqual;
import semanticAction.tree.expressionNode.literal.Booleanliteral;
import semanticAction.tree.expressionNode.literal.Integerliteral;
import semanticAction.tree.expressionNode.logical.And;
import semanticAction.tree.expressionNode.logical.OR;
import semanticAction.tree.expressionNode.unary.NOT;

public class TestNemoone extends TestAbstract {

	
	
		
		@Test
		public void testADD()  {
			Value value = evaluate( new Add(new Integerliteral(3), new Integerliteral(5)));
			Value value2 = evaluate( new Add(new Integerliteral(1), new Integerliteral(4)));
			
			
			assertEquals("3+5 = 8", value.getValue(), 8);
			assertEquals("1+4 = 5", value2.getValue(), 5); 
			
		}
		
		@Test
		public void testSUB() {
			//Value value = evaluate( new SUB(new Integerliteral(5), new Integerliteral(3)));
			Value value2 = evaluate( new SUB(new Integerliteral(4), new Integerliteral(1)));
			//Value value3 = evaluate( new SUB(new Add(new Integerliteral(1), new Integerliteral(4)), int4));
			
			
			//assertEquals("5-3 = 2", value.getValue(), 2);
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
		@Test
		public void testCalculations() {
			Value value1 = evaluate( new Time(
					new SUB(new Integerliteral(7), new Integerliteral(2)),
					new Integerliteral(5)) );
			Value value2 = evaluate(new Time(
					new Division(new Integerliteral(48), new Integerliteral(8)),
					new Integerliteral(2)) );
					
			assertEquals("(7-2)*5=25", value1.getValue(), 25);
			assertEquals("(48/8)*2=12", value2.getValue(), 12);
		}
		@Test
		public void testOrComparisons() throws Exception {
			Value value1 = evaluate(new OR(new Booleanliteral(true), new Booleanliteral(true)));
			Value value2 = evaluate(new OR(new Booleanliteral(true), new Booleanliteral(false)));
			Value value3 = evaluate(new OR(new Booleanliteral(false), new Booleanliteral(false)));
			
			assertEquals("t || t = t", value1.getValue(), true);
			assertEquals("t || f = t", value2.getValue(), true);
			assertEquals("f || f = f", value3.getValue(), false);
		}
		@Test
		public void testAndComparisons() throws Exception {
			Value value1 = evaluate(new And(new Booleanliteral(true), new Booleanliteral(true)));
			Value value2 = evaluate(new And(new Booleanliteral(true), new Booleanliteral(false)));
			Value value3 = evaluate(new And(new Booleanliteral(false), new Booleanliteral(false)));
			
			assertEquals("t && t = t", value1.getValue(), true);
			assertEquals("t && f = f", value2.getValue(), false);
			assertEquals("f && f = f", value3.getValue(), false);
			
		}
		@Test
		public void testNotComparisons() throws Exception {
			Value value1 = evaluate(new NOT(new Booleanliteral(true)));
			Value value2 = evaluate(new NOT(new Booleanliteral(false)));
			
			assertEquals("!t = f", value1.getValue(), false);
			assertEquals("!f = t", value2.getValue(), true);
			
		}
		@Test
		public void testLogicalComparisons() throws Exception {
			Value value1 = evaluate(new NOT(new OR(new Booleanliteral(true), new Booleanliteral(true))));
			Value value2 = evaluate(new OR(new Booleanliteral(true),
					new And( new Booleanliteral(false),new Booleanliteral(false))));
			
			assertEquals("!(t || t) = f", value1.getValue(), false);
			assertEquals("t || (f && f) = t", value2.getValue(), true);
		}
		

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
		@Test
		public void testMix() throws Exception {
			Value value1 = evaluate(new OR(
					new Equal(new Integerliteral(7), new Integerliteral(8)), 
					new Booleanliteral(true))
			);
			Value value2 = evaluate(new And(
					new LessThan(new Integerliteral(7), new Integerliteral(8)), 
					new Booleanliteral(true))
			);
			
			assertEquals("(7 == 8) || true", value1.getValue(), true);
			assertEquals("(7 > 8 ) && true", value2.getValue(), false);
		}
		
		}




