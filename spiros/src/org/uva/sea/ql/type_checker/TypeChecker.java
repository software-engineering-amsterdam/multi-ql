package org.uva.sea.ql.type_checker;

import java.util.HashMap;
import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.expression.Comparison.Equal;
import org.uva.sea.ql.ast.expression.Comparison.Greater;
import org.uva.sea.ql.ast.expression.Comparison.GreaterOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.Less;
import org.uva.sea.ql.ast.expression.Comparison.LessOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.NotEqual;
import org.uva.sea.ql.ast.expression.Literal.BooleanLiteral;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.IntegerLiteral;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.expression.Logical.And;
import org.uva.sea.ql.ast.expression.Logical.Binary;
import org.uva.sea.ql.ast.expression.Logical.Or;
import org.uva.sea.ql.ast.expression.Numerical.Add;
import org.uva.sea.ql.ast.expression.Numerical.Div;
import org.uva.sea.ql.ast.expression.Numerical.Mul;
import org.uva.sea.ql.ast.expression.Numerical.Sub;
import org.uva.sea.ql.ast.expression.Parenthesis.Parenthesis;
import org.uva.sea.ql.ast.expression.Unary.Negative;
import org.uva.sea.ql.ast.expression.Unary.Not;
import org.uva.sea.ql.ast.expression.Unary.Positive;
import org.uva.sea.ql.ast.expression.Unary.Unary;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.FormVisitor;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.IfElseStatement;
import org.uva.sea.ql.ast.statement.IfStatement;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.StatementVisitor;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.IntType;
import org.uva.sea.ql.ast.type.StrType;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.ast.type.UndefinedType;
import org.uva.sea.ql.ast.statement.Statement;


public class TypeChecker implements FormVisitor, StatementVisitor, ExpressionVisitor<Type> {
	
	private final Form form;
	private HashMap<String, IdentifierData> questionData;
	
	public TypeChecker(Form form) {
		this.form = form;
		this.questionData = new HashMap<>();
	}
	

	public Form getForm() {
		return form;
	}
	
	public void performTypeChecking() {	
		this.visitForm(form);
	}
	
	
	/****************************
	******* Check Methods *******
	*****************************/
	
	
	private boolean hasExpectedType(Binary node, Type expectedType) {

		Expression rightExpression = node.getRightExpression();
		Expression leftExpression = node.getLeftExpression();
		Type leftExprType = leftExpression.accept(this);
		Type rightExpType = rightExpression.accept(this);
		
		if (typeMatches(rightExpType,expectedType) && typeMatches(leftExprType,expectedType))
			return true;
				
		return false;
	}
	
	
	private boolean hasExpectedType(Unary node, Type expectedType) {

		Expression expression = node.getExpression();
		Type type = expression.accept(this);
		
		if (typeMatches(type,expectedType) )
			return true;
				
		return false;
	}
	

	private boolean typeMatches(Type rightExpType, Type expectedType) {
		return rightExpType.getTypeName().equals(expectedType.getTypeName());
	}
	
	
	private boolean isConditionBooleanType(IfStatement ifStatement) {
		
		Type type = ifStatement.getExpression().accept(this);
		
		if (typeMatches(type, new BoolType()))
			return true;
		
		return false;	// give info about the type
	}
	
	
	private boolean isDeclaredWithDifferentType(Question question) {
		
		Identifier identifier = question.getId();
		
		if (questionData.keySet().contains(identifier.getValue())) {	////
			
			String identifierString = question.getType().getTypeName();	// fix -> demeter

			IdentifierData identifierData = questionData.get(identifier.getValue());	////
			
			if (!identifierString.equals(identifierData.getType().getTypeName()))		// fix-> demeter...
				return true;										//// else update label? ask...
		}
		
		return false;
	}
	

	private boolean labelIsDuplicate(Question question) {
		
		for(IdentifierData identifierData: questionData.values())
			
			if (identifierData.getLabel().equals(question.getLabel()))
				return true;

		return false;
	}
	
	
	private void insertAtHashMap(String id,String label,Type type) {
		this.questionData.put(id, new IdentifierData(type,label));
	}
	
	
	
	/****************************
	*********Form Visitor********
	*****************************/
	
	
	@Override
	public void visitForm(Form form) {
		form.getBlock().accept(this);
		
	}

	@Override
	public void visitBlock(Block block) {
		for (Statement statement: block.getStatements())
			statement.accept(this);
	}
	

	
	/****************************
	******Statement Visitor******
	*****************************/



	@Override
	public void visitQuestion(Question question) {
		
		if (labelIsDuplicate(question))
			System.out.println("Duplicate label found!");
		
		if (isDeclaredWithDifferentType(question)) {
			System.out.println("Question is declared with different type");
			System.exit(0);
		}
		
		else {
			
			Identifier identifier = question.getId();
			insertAtHashMap(identifier.getValue(),question.getLabel(),question.getType());
			
		}
	}
	
	
	@Override
	public void visitComputedQuestion(ComputedQuestion computedQuestion) {
		
		if (labelIsDuplicate(computedQuestion))
			System.out.println("Duplicate label found!");
		
		if (isDeclaredWithDifferentType(computedQuestion)) {
			System.out.println("Question is declared with different type");
			System.exit(0);
		}
		
		if (!typeMatches(computedQuestion.getType(), computedQuestion.getExpression().accept(this))) {
			System.out.println("Wrong expression in the computed question");
			System.exit(0);			
		}
		
		// below do sth with cyclicDependenciesVisitor!
		
//		CyclicDependenciesVisitor cyclicDependenciesVisitor = new CyclicDependenciesVisitor();
//		cyclicDependenciesVisitor.visit(computedQuestion.getExpression());
		
		Identifier identifier = computedQuestion.getId();
		insertAtHashMap(identifier.getValue(),computedQuestion.getLabel(),computedQuestion.getType());
		
	}

	

	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		
		if (isConditionBooleanType(ifStatement))
			ifStatement.getBlock().accept(this);
		
		else {
			System.out.println("Condition is not boolean");
			System.exit(0);
		}
	}

	
	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		ifElseStatement.getBlock().accept(this);
		ifElseStatement.getElseBlock().accept(this);
	}
	
	
	/****************************
	**** Expression Visitor *****
	*****************************/	
	

	@Override
	public Type visit(Equal node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("Equal comparison expects integers!");
			System.exit(0);
		}
			
		return new BoolType();
	}

	

	@Override
	public Type visit(NotEqual node) {
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("NotEqual comparison expects integers!");
			System.exit(0);
		}
			
		return new BoolType();
	}
	

	@Override
	public Type visit(Greater node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("Greater comparison expects integers!");
			System.exit(0);
		}
			
		return new BoolType();
	}
	

	@Override
	public Type visit(GreaterOrEqual node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("GreaterOrEqualcomparison expects integers!");
			System.exit(0);
		}
			
		return new BoolType();	
	}
	

	@Override
	public Type visit(Less node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("Less comparison expects integers!");
			System.exit(0);
		}
			
		return new BoolType();
	}

	
	@Override
	public Type visit(LessOrEqual node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("LessOrEqual comparison expects integers!");
			System.exit(0);
		}
		
		return new BoolType();
	}
	

	@Override
	public Type visit(BooleanLiteral node) {
		return new BoolType();
	}

	
	@Override
	public Type visit(Identifier node) {
		
		String nodeString = node.getValue();
		if (questionData.containsKey(nodeString)) {
			IdentifierData identifierData = questionData.get(nodeString);
			//System.out.println(identifierData.getType().getTypeName());
			return identifierData.getType();
		}
	
		return new UndefinedType();
	}

	
	@Override
	public Type visit(IntegerLiteral node) {
		return new IntType();
	}

	
	@Override
	public Type visit(StringLiteral node) {
		return new StrType();
	}
	
	
	// change below to call method typeMatches...
	
	@Override
	public Type visit(And node) {	
		
		if (!hasExpectedType(node,new BoolType())) {
			System.out.println("Logican 'and' expects booleans!");
			System.exit(0);
		}
		
		return new BoolType();
	}
	

	@Override
	public Type visit(Or node) {
		if (!hasExpectedType(node,new BoolType())) {
			System.out.println("Logican 'or' expects booleans!");
			System.exit(0);
		}
		
		return new BoolType();
	}

	
	@Override
	public Type visit(Add node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("Addition expects integers!");
			System.exit(0);
		}
		
		return new IntType();
	}

	
	@Override
	public Type visit(Sub node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("Subtraction expects integers!");
			System.exit(0);
		}
		
		return new IntType();
	}

	
	@Override
	public Type visit(Mul node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("Multiplication expects integers!");
			System.exit(0);
		}
		
		return new IntType();
	}

	
	@Override
	public Type visit(Div node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("Division expects integers!");
			System.exit(0);
		}
		
		return new IntType();
	}
	
	
	/** parenthsis to be removed! **/

	@Override
	public Type visit(Parenthesis node) {
		return null;
	}
	

	@Override
	public Type visit(Not node) {
		
		if (!hasExpectedType(node,new BoolType())) {
			System.out.println("Negation expects boolean!");
			System.exit(0);
		}
		
		return new BoolType();
	}
	

	@Override
	public Type visit(Positive node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("'+' expects integer!");
			System.exit(0);
		}
		
		return new IntType();
	}

	
	@Override
	public Type visit(Negative node) {
		
		if (!hasExpectedType(node,new IntType())) {
			System.out.println("'-' expects integer!");
			System.exit(0);
		}
		
		return new IntType();
	}
	

}