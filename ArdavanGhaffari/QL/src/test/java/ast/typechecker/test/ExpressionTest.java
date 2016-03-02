package ast.typechecker.test;

import org.junit.Assert;
import org.junit.Test;

import ast.model.Expression;
import ast.model.binaryexpression.Addition;
import ast.model.binaryexpression.Division;
import ast.model.binaryexpression.Equal;
import ast.model.binaryexpression.GreaterThan;
import ast.model.binaryexpression.LessThan;
import ast.model.binaryexpression.Multiplication;
import ast.model.binaryexpression.Subtraction;
import ast.model.literal.BooleanLiteral;
import ast.model.literal.DecimalLiteral;
import ast.model.literal.IntegerLiteral;
import ast.model.literal.StringLiteral;
import ast.model.type.Type;
import ast.model.unaryexpression.Negation;
import ast.typechecker.TypeChecker;
import ast.typechecker.errorhandler.ErrorHandler;

public class ExpressionTest {
	ErrorHandler errorHandler = new ErrorHandler();
	TypeChecker typeChecker = new TypeChecker(errorHandler);
	
	@Test
	public void testNegationWithValidType() {
		Negation negation = new Negation(new BooleanLiteral(true, 1), 1);
		Type type = typeChecker.visit(negation);
		Assert.assertEquals(type.getName(), "Boolean");
	}
	
	@Test
	public void testNegationMissMatchType() {
		Negation negation = new Negation(new IntegerLiteral(2, 1), 1);
		Type type = typeChecker.visit(negation);
		Assert.assertEquals(type.getName(), "Unknown");
		Assert.assertEquals(getLastErrorMessage(), "Type missmatch error at line 1: expected type for 'Negation operation' is 'Boolean'");
	}
	
	@Test
	public void testBinaryMathExpressionTypeInteger() {
		Expression leftExpr = new Subtraction(new IntegerLiteral(4, 2), new IntegerLiteral(2, 2), 2);
		Expression rightExpr = new Multiplication(new IntegerLiteral(6, 2), new IntegerLiteral(5, 2), 2);
		Addition addition = new Addition(leftExpr, rightExpr, 2);
		
		Type type = typeChecker.visit(addition);
		Assert.assertEquals(type.getName(), "Integer");
	}
	
	@Test
	public void testBinaryMathExpressionTypeDecimal() {
		Expression leftExpr = new Subtraction(new DecimalLiteral(2.3, 2), new IntegerLiteral(2, 2), 2);
		Expression rightExpr = new Multiplication(new IntegerLiteral(6, 2), new IntegerLiteral(5, 2), 2);
		Addition addition = new Addition(leftExpr, rightExpr, 2);
		
		Type type = typeChecker.visit(addition);
		Assert.assertEquals(type.getName(), "Decimal");
	}
	
	@Test
	public void testBinaryMathExpressionTypeMissmatch() {
		Division division = new Division(new BooleanLiteral(false, 5), new IntegerLiteral(2, 5), 5);
		Type type = typeChecker.visit(division);
		
		Assert.assertEquals(type.getName(), "Unknown");
		Assert.assertEquals(getLastErrorMessage(), "Type missmatch error at line 5: invalid operand type for 'Divide' operation");
	}
	
	@Test
	public void testEqualOperationWithValidOperands() {
		Equal equal = new Equal(new StringLiteral("test string", 2), new StringLiteral("test string2", 2), 2);
		Assert.assertEquals(typeChecker.visit(equal).getName(), "Boolean");
		
		equal = new Equal(new IntegerLiteral(100, 2), new DecimalLiteral(90.99, 2), 2);
		Assert.assertEquals(typeChecker.visit(equal).getName(), "Boolean");
		
		equal = new Equal(new BooleanLiteral(true, 2), new BooleanLiteral(false, 2), 2);
		Assert.assertEquals(typeChecker.visit(equal).getName(), "Boolean");
	}
	
	@Test
	public void testEqualOperationTypeMissmatch() {
		Equal equal = new Equal(new StringLiteral("test string", 2), new IntegerLiteral(100, 2), 2);
		Type type = typeChecker.visit(equal);
		
		Assert.assertEquals(type.getName(), "Unknown");
		Assert.assertEquals(getLastErrorMessage(), "Type missmatch error at line 2: invalid operand type for 'Equal' operation");
	}
	
	@Test
	public void testComparisonOperationValidOperands() {
		LessThan lessThan = new LessThan(new IntegerLiteral(100, 2), new DecimalLiteral(2.89, 2), 2);
		Assert.assertEquals(typeChecker.visit(lessThan).getName(), "Boolean");
	}
	
	@Test
	public void testComparisonOperationTypeMissmatch() {
		GreaterThan greaterThan = new GreaterThan(new IntegerLiteral(2, 3), new BooleanLiteral(false, 3), 3);
		Assert.assertEquals(typeChecker.visit(greaterThan).getName(), "Unknown");
		Assert.assertEquals(getLastErrorMessage(), "Type missmatch error at line 3: invalid operand type for 'Greater than' operation");
	}
	
	private String getLastErrorMessage() {
		return errorHandler.getErrors().get(errorHandler.getErrors().size() -1 ).getMessage();
	}
}
