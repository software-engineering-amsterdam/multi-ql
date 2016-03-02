package test.java.ast.typeChecker;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import nl.nicasso.ql.TypeCheckerVisitor;
import nl.nicasso.ql.ast.expression.additive.Addition;
import nl.nicasso.ql.ast.expression.additive.Subtraction;
import nl.nicasso.ql.ast.expression.conditional.And;
import nl.nicasso.ql.ast.expression.conditional.Not;
import nl.nicasso.ql.ast.expression.conditional.Or;
import nl.nicasso.ql.ast.expression.equality.Equal;
import nl.nicasso.ql.ast.expression.equality.NotEqual;
import nl.nicasso.ql.ast.literal.BooleanLit;
import nl.nicasso.ql.ast.literal.MoneyLit;
import nl.nicasso.ql.ast.literal.IntegerLit;
import nl.nicasso.ql.ast.type.Type;

public class Expressions {
	
	@Test
	public void testAddition() {
		Addition addition = new Addition(new IntegerLit(1), new IntegerLit(1), null);

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(addition);
		
		Assert.assertEquals(type.getType(), "Integer");
	}
	
	@Test
	public void testAdditionDecimal() {
		Addition addition = new Addition(new MoneyLit(BigDecimal.valueOf(1.00)), new MoneyLit(BigDecimal.valueOf(1.00)), null);

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(addition);
		
		Assert.assertEquals(type.getType(), "Decimal");
	}
	
	@Test
	public void testAdditionCombined() {
		Addition addition = new Addition(new IntegerLit(1), new MoneyLit(BigDecimal.valueOf(1.00)), null);

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(addition);
		
		Assert.assertEquals(type.getType(), "Decimal");
	}
	
	@Test
	public void testSubtraction() {
		Subtraction subtraction = new Subtraction(new IntegerLit(1), new IntegerLit(1), null);

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(subtraction);
		
		Assert.assertEquals(type.getType(), "Integer");
	}
	
	@Test
	public void testNot() {
		Not negation = new Not(new BooleanLit(true), null);

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(negation);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testAnd() {
		And and = new And(new BooleanLit(true), new BooleanLit(true), null);

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(and);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testOr() {
		Or or = new Or(new BooleanLit(true), new BooleanLit(true), null);

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(or);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testEqual() {
		Equal equal = new Equal(new BooleanLit(true), new BooleanLit(true), null);

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(equal);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testNotEqual() {
		NotEqual notEqual = new NotEqual(new BooleanLit(true), new BooleanLit(true), null);

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(notEqual);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	/*
	@Test
	public void testDivision() {
		Division division = new Division(new IntegerLit(10), new IntegerLit(2));

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(division);
		
		Assert.assertEquals(type.getType(), "Money");
	}
	
	@Test
	public void testMultiplication() {
		Multiplication multiplication = new Multiplication(new IntegerLit(10), new IntegerLit(2));

		TypeCheckerVisitor visitor = new TypeCheckerVisitor(null);
		
		Type type = visitor.visit(multiplication);
		
		Assert.assertEquals(type.getType(), "Money");
	}
	*/

}
