package test.java.ast.typeChecker;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.literals.BooleanLiteral;
import nl.nicasso.ql.ast.nodes.literals.IntegerLiteral;
import nl.nicasso.ql.ast.nodes.literals.MoneyLiteral;
import nl.nicasso.ql.ast.nodes.literals.StringLiteral;
import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.semanticAnalysis.TypeChecker;

public class Statements {
	
	private final TypeChecker typeChecker = new TypeChecker();
	
	private final IntegerLiteral testInteger = new IntegerLiteral(1);
	private final MoneyLiteral testMoney = new MoneyLiteral(BigDecimal.valueOf(1.00));
	private final BooleanLiteral testBoolean = new BooleanLiteral(true);
	private final StringLiteral testString = new StringLiteral("a");
	
	@Test
	public void testComputedQuestion() {
		ComputedQuestion question = new ComputedQuestion(new Identifier("Q1", null), "Question 1", new IntegerType(), testInteger, null); 
		
		//Remove the constructors for the typchecker and the other which do not need any parameters.
		typeChecker.visit(question, null);
		//Assert.assertEquals(type, new IntegerType());
	}

}
