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
import nl.nicasso.ql.ast.nodes.literals.BooleanLiteral;
import nl.nicasso.ql.ast.nodes.literals.IntegerLiteral;
import nl.nicasso.ql.ast.nodes.literals.MoneyLiteral;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.semanticAnalysis.TypeChecker;

public class Expressions {
	
	@Test
	public void testAddition() {
		Addition addition = new Addition(new IntegerLiteral(1), new IntegerLiteral(1), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(addition);
		
		Assert.assertEquals(type.getType(), "Integer");
	}
	
	@Test
	public void testAdditionMoney() {
		Addition addition = new Addition(new MoneyLiteral(BigDecimal.valueOf(1.00)), new MoneyLiteral(BigDecimal.valueOf(1.00)), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(addition);
		
		Assert.assertEquals(type.getType(), "Money");
	}
	
	@Test
	public void testSubtraction() {
		Subtraction subtraction = new Subtraction(new IntegerLiteral(1), new IntegerLiteral(1), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(subtraction);
		
		Assert.assertEquals(type.getType(), "Integer");
	}
	
	@Test
	public void testNot() {
		Not negation = new Not(new BooleanLiteral(true), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(negation);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testAnd() {
		And and = new And(new BooleanLiteral(true), new BooleanLiteral(true), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(and);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testOr() {
		Or or = new Or(new BooleanLiteral(true), new BooleanLiteral(true), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(or);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testEqual() {
		Equal equal = new Equal(new BooleanLiteral(true), new BooleanLiteral(true), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(equal);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testNotEqual() {
		NotEqual notEqual = new NotEqual(new BooleanLiteral(true), new BooleanLiteral(true), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(notEqual);
		
		Assert.assertEquals(type.getType(), "Boolean");
	}
	
	@Test
	public void testDivision() {
		Division division = new Division(new IntegerLiteral(10), new IntegerLiteral(2), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(division);
		
		Assert.assertEquals(type, new IntegerType());
	}
	
	@Test
	public void testMultiplication() {
		Multiplication multiplication = new Multiplication(new IntegerLiteral(10), new IntegerLiteral(2), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(multiplication);
				
		Assert.assertEquals(type, new IntegerType());
	}
	
	@Test
	public void testMultiplicationCombined() {
		Multiplication multiplication = new Multiplication(new IntegerLiteral(2), new MoneyLiteral(BigDecimal.valueOf(3.00)), null);

		TypeChecker visitor = new TypeChecker(null, null);
		
		Type type = visitor.visit(multiplication);
				
		Assert.assertEquals(type, new MoneyType());
	}
}
