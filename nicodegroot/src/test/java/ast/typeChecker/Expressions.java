package test.java.ast.typeChecker;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import nl.nicasso.ql.ast.nodes.expressions.additive.Addition;
import nl.nicasso.ql.ast.nodes.expressions.additive.Subtraction;
import nl.nicasso.ql.ast.nodes.expressions.conditional.And;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Not;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Or;
import nl.nicasso.ql.ast.nodes.expressions.equality.Equal;
import nl.nicasso.ql.ast.nodes.expressions.equality.NotEqual;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Division;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Multiplication;
import nl.nicasso.ql.ast.nodes.literals.BooleanLit;
import nl.nicasso.ql.ast.nodes.literals.IntegerLit;
import nl.nicasso.ql.ast.nodes.literals.MoneyLit;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.semanticAnalysis.TypeChecker;

public class Expressions {
	
	@Test
	public void testAddition() {
		Addition addition = new Addition(new IntegerLit(1), new IntegerLit(1), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(addition);
		
		Assert.assertEquals(type.getType(), "Integer");
	}
	
	@Test
	public void testAdditionMoney() {
		Addition addition = new Addition(new MoneyLit(BigDecimal.valueOf(1.00)), new MoneyLit(BigDecimal.valueOf(1.00)), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(addition);
		
		Assert.assertEquals(type.getType(), "Money");
	}
	
	@Test
	public void testSubtraction() {
		Subtraction subtraction = new Subtraction(new IntegerLit(1), new IntegerLit(1), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(subtraction);
		
		Assert.assertEquals(type.getType(), "Integer");
	}
	
	@Test
	public void testNot() {
		Not negation = new Not(new BooleanLit(true), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(negation);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testAnd() {
		And and = new And(new BooleanLit(true), new BooleanLit(true), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(and);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testOr() {
		Or or = new Or(new BooleanLit(true), new BooleanLit(true), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(or);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testEqual() {
		Equal equal = new Equal(new BooleanLit(true), new BooleanLit(true), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(equal);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testNotEqual() {
		NotEqual notEqual = new NotEqual(new BooleanLit(true), new BooleanLit(true), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(notEqual);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testDivision() {
		Division division = new Division(new IntegerLit(10), new IntegerLit(2), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(division);
		
		Assert.assertEquals(type, new IntegerType());
	}
	
	@Test
	public void testMultiplication() {
		Multiplication multiplication = new Multiplication(new IntegerLit(10), new IntegerLit(2), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(multiplication);
				
		Assert.assertEquals(type, new IntegerType());
	}
	
	@Test
	public void testMultiplicationMoney() {
		Multiplication multiplication = new Multiplication(new MoneyLit(BigDecimal.valueOf(15.00)), new MoneyLit(BigDecimal.valueOf(3.00)), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(multiplication);
				
		Assert.assertEquals(type, new MoneyType());
	}
	
	@Test
	public void testMultiplicationCombined() {
		Multiplication multiplication = new Multiplication(new IntegerLit(2), new MoneyLit(BigDecimal.valueOf(3.00)), null);

		TypeChecker visitor = new TypeChecker(null);
		
		Type type = visitor.visit(multiplication);
				
		Assert.assertEquals(type, new MoneyType());
	}
	
	/*
	@Test
	public void testDouble() {
		double a = 0.7;
	    double b = 0.9;
	    
	    double x = a + 0.1;
	    double y = b - 0.1;
	    
	    x = Math.round(x);
	    y = Math.round(y);
	    
		Assert.assertEquals(x, y);
	}
	
	@Test
	public void testBigDecimal() {
		BigDecimal a = new BigDecimal(0.7);
		BigDecimal b = new BigDecimal(0.9);
		
		BigDecimal x = a.add(new BigDecimal(0.1)).setScale(2, RoundingMode.HALF_UP);
		BigDecimal y = b.subtract(new BigDecimal(0.1)).setScale(2, RoundingMode.HALF_UP);
		
		Assert.assertEquals(x, y);
	}
	*/
}
