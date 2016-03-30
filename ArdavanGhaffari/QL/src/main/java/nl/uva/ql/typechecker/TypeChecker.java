package nl.uva.ql.typechecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import nl.uva.ql.ast.Box;
import nl.uva.ql.ast.Form;
import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.ast.expression.binaryexpression.Addition;
import nl.uva.ql.ast.expression.binaryexpression.Conjunction;
import nl.uva.ql.ast.expression.binaryexpression.Disjunction;
import nl.uva.ql.ast.expression.binaryexpression.Division;
import nl.uva.ql.ast.expression.binaryexpression.Equal;
import nl.uva.ql.ast.expression.binaryexpression.GreaterThan;
import nl.uva.ql.ast.expression.binaryexpression.GreaterThanEqual;
import nl.uva.ql.ast.expression.binaryexpression.LessThan;
import nl.uva.ql.ast.expression.binaryexpression.LessThanEqual;
import nl.uva.ql.ast.expression.binaryexpression.Multiplication;
import nl.uva.ql.ast.expression.binaryexpression.NotEqual;
import nl.uva.ql.ast.expression.binaryexpression.Subtraction;
import nl.uva.ql.ast.expression.unaryexpression.Negation;
import nl.uva.ql.ast.literal.BooleanLiteral;
import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.ast.literal.IntegerLiteral;
import nl.uva.ql.ast.literal.MoneyLiteral;
import nl.uva.ql.ast.literal.StringLiteral;
import nl.uva.ql.ast.statement.ComputedQuestion;
import nl.uva.ql.ast.statement.IfElseStatement;
import nl.uva.ql.ast.statement.IfStatement;
import nl.uva.ql.ast.statement.Question;
import nl.uva.ql.ast.statement.Statement;
import nl.uva.ql.ast.type.BooleanType;
import nl.uva.ql.ast.type.IntegerType;
import nl.uva.ql.ast.type.MoneyType;
import nl.uva.ql.ast.type.StringType;
import nl.uva.ql.ast.type.Type;
import nl.uva.ql.ast.type.UnknownType;
import nl.uva.ql.typechecker.errorhandler.ErrorHandler;
import nl.uva.ql.typechecker.errorhandler.error.CyclicDependencyError;
import nl.uva.ql.typechecker.errorhandler.error.DuplicateDeclarationError;
import nl.uva.ql.typechecker.errorhandler.error.NoDeclarationError;
import nl.uva.ql.typechecker.errorhandler.error.OperationTypeMissmatchError;
import nl.uva.ql.typechecker.errorhandler.error.TypeMissmatchError;
import nl.uva.ql.typechecker.errorhandler.warning.DuplicateLabelWarning;
import nl.uva.ql.visitors.ExpressionVisitor;
import nl.uva.ql.visitors.FormVisitor;
import nl.uva.ql.visitors.StatementVisitor;

public class TypeChecker implements ExpressionVisitor<Type>, StatementVisitor<Void>, FormVisitor{
	private HashMap<String, IdentifierInfo> identifierInfoMap;
	private List<String> labels;
	
	private ErrorHandler errorHandler;

	public TypeChecker(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
		this.identifierInfoMap = new HashMap<>();
		this.labels = new LinkedList<>();
	}
	
	@Override
	public void visit(Form form) {
		form.getBox().accept(this);
	}

	@Override
	public Void visit(Box box) {
		for(Statement statement: box.getStatements()){
			statement.accept(this);
		}
		return null;
	}

	@Override
	public Void visit(Question question) {
		checkForRedeclaration(question);
		checkDuplicateLabel(question);
		return null;
	}

	@Override
	public Void visit(ComputedQuestion computedQuestion) {
		Expression expression = computedQuestion.getExpression();
		Type expressionType = expression.accept(this);
		Type computedQuestionType = computedQuestion.getType();
		// if question type is money, then integer type would be acceptable as well for the expression type
		Boolean isMatched;
		if (computedQuestion.getType().equals(new MoneyType())) {
			isMatched = matchTypes(expressionType, computedQuestionType, new IntegerType());
		} else {
			isMatched = matchTypes(expressionType, computedQuestionType);
		}
		if(!isMatched){
			errorHandler.addError(new TypeMissmatchError(computedQuestion.getLine(),
					computedQuestionType.getName(), "Computed Question Expression"));
		}
		
//		if (!computedQuestionType.isCompatible(expressionType)) {
//			errorHandler.addError(new TypeMissmatchError(computedQuestion.getLine(),
//					computedQuestionType.getName(), "Computed Question Expression"));
//		}
		
		checkForRedeclaration(computedQuestion);
		checkDuplicateLabel(computedQuestion);
		
		// adds dependencies if any + check for cyclic dependencies
		Identifier identifier = computedQuestion.getIdentifier();
		IdentifierVisitor identifierVisitor = new IdentifierVisitor();
		Set<String> directDependencies = expression.accept(identifierVisitor);
		Set<String> dependencySet = new HashSet<>(directDependencies);
		for (String dependency: directDependencies) {
			addDependencies(identifier, dependencySet, dependency);
		}
		identifierInfoMap.get(identifier.getName()).updateDependencies(dependencySet);
		
//		Identifier identifier = computedQuestion.getIdentifier();
//		IdentifierVisitor identifierVisitor = new IdentifierVisitor();
//		Set<String> directDependencies = expression.accept(identifierVisitor);
//		Set<String> transitiveDependencies = transitiveClosure(directDependencies));
//		if (hasCycle(transitiveDepedencies, identifier)) {
//			errorHandler.addError();
//		}
//		for (String dependency: directDependencies) {
//			addDependencies(identifier, transitiveDependencies, dependency);
//		}
//		identifierInfoMap.get(identifier.getIdentifier()).updateDependencies(transitiveDependencies);
		return null;
	}

	@Override
	public Void visit(IfStatement ifStatement) {
		// check the if-condition to match boolean type
		checkIfCondition(ifStatement);
		
		// type-check the if block
		ifStatement.getIfBox().accept(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement ifElseStatement) {
		// check the if-condition to match boolean type
		checkIfCondition(ifElseStatement);
		
		// type-check the if block
		ifElseStatement.getIfBox().accept(this);
		
		// type-check the else block
		ifElseStatement.getElseBox().accept(this);
		return null;
	}
	
	@Override
	public Type visit(Negation negation) {
		Type type = negation.getExpression().accept(this);
		Boolean isMatched = matchTypes(type, new BooleanType());
		if(!isMatched){
			errorHandler.addError(new TypeMissmatchError(negation.getLine(), "Boolean", "Negation operation"));
			return new UnknownType();
		}
		return type;
	}

	@Override
	public Type visit(Addition addition) {
		// check if addition operation is to concatenate two Strings
		Type leftType = addition.getLeftExpression().accept(this);
		Type rightType = addition.getRightExpression().accept(this);
		Type expectedString = new StringType();
		
		if (matchTypes(leftType, expectedString) && matchTypes(rightType, expectedString)) {
			return expectedString;
		}
		
		// if addition is not a String-concatenation, check for Math addition
		Type expressionType = getTypeForMathExpression(leftType, rightType);
		if (expressionType.equals(new UnknownType())) {
			errorHandler.addError(new OperationTypeMissmatchError(addition.getLine(), "Add"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Subtraction subtraction) {
		Type leftType = subtraction.getLeftExpression().accept(this);
		Type rightType = subtraction.getRightExpression().accept(this);
		Type expressionType = getTypeForMathExpression(leftType, rightType);
		if (expressionType.equals(new UnknownType())) {
			errorHandler.addError(new OperationTypeMissmatchError(subtraction.getLine(), "Subtraction"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Multiplication multiplication) {
		Type leftType = multiplication.getLeftExpression().accept(this);
		Type rightType = multiplication.getRightExpression().accept(this);
		Type expressionType = getTypeForMathExpression(leftType, rightType);
		if (expressionType.equals(new UnknownType())) {
			errorHandler.addError(new OperationTypeMissmatchError(multiplication.getLine(), "Multiply"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Division division) {
		Type leftType = division.getLeftExpression().accept(this);
		Type rightType = division.getRightExpression().accept(this);
		Type expressionType = getTypeForMathExpression(leftType, rightType);
		if (expressionType.equals(new UnknownType())) {
			errorHandler.addError(new OperationTypeMissmatchError(division.getLine(), "Divide"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Conjunction conjunction) {
		Type leftType = conjunction.getLeftExpression().accept(this);
		Type rightType = conjunction.getRightExpression().accept(this);
		Type expectedBoolean = new BooleanType();
		
		if (matchTypes(leftType, expectedBoolean) && matchTypes(rightType, expectedBoolean)) {
			return expectedBoolean;
		}
		errorHandler.addError(new OperationTypeMissmatchError(conjunction.getLine(), "And"));
		return new UnknownType();
	}

	@Override
	public Type visit(Disjunction disjunction) {
		Type leftType = disjunction.getLeftExpression().accept(this);
		Type rightType = disjunction.getRightExpression().accept(this);
		Type expectedBoolean = new BooleanType();
		
		if (matchTypes(leftType, expectedBoolean) && matchTypes(rightType, expectedBoolean)) {
			return expectedBoolean;
		}
		errorHandler.addError(new OperationTypeMissmatchError(disjunction.getLine(), "Or"));
		return new UnknownType();
	}

	@Override
	public Type visit(Equal equal) {
		Type leftType = equal.getLeftExpression().accept(this);
		Type rightType = equal.getRightExpression().accept(this);
		if(matchNumericType(leftType, rightType)){
			return new BooleanType();
		}
		if(matchStringORBooleanType(leftType, rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(equal.getLine(), "Equal"));
		return new UnknownType();
	}

	@Override
	public Type visit(NotEqual notEqual) {
		Type leftType = notEqual.getLeftExpression().accept(this);
		Type rightType = notEqual.getRightExpression().accept(this);
		if(matchNumericType(leftType, rightType)){
			return new BooleanType();
		}
		if(matchStringORBooleanType(leftType, rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(notEqual.getLine(), "Not equal"));
		return new UnknownType();
	}

	@Override
	public Type visit(GreaterThan greaterThan) {
		Type leftType = greaterThan.getLeftExpression().accept(this);
		Type rightType = greaterThan.getRightExpression().accept(this);
		if(matchNumericType(leftType, rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(greaterThan.getLine(), "Greater than"));
		return new UnknownType();
	}

	@Override
	public Type visit(GreaterThanEqual greaterThanEqual) {
		Type leftType = greaterThanEqual.getLeftExpression().accept(this);
		Type rightType = greaterThanEqual.getRightExpression().accept(this);
		if(matchNumericType(leftType, rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(greaterThanEqual.getLine(), "Greater than equal"));
		return new UnknownType();
	}

	@Override
	public Type visit(LessThan lessThan) {
		Type leftType = lessThan.getLeftExpression().accept(this);
		Type rightType = lessThan.getRightExpression().accept(this);
		if(matchNumericType(leftType, rightType)){
			return new BooleanType();
		}
//		if(leftType.isCompatible(new NumericType())){
//			return new BooleanType();
//		}
		errorHandler.addError(new OperationTypeMissmatchError(lessThan.getLine(), "Less than"));
		return new UnknownType();
	}

	@Override
	public Type visit(LessThanEqual lessThanEqual) {
		Type leftType = lessThanEqual.getLeftExpression().accept(this);
		Type rightType = lessThanEqual.getRightExpression().accept(this);
		if(matchNumericType(leftType, rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(lessThanEqual.getLine(), "Less than equal"));
		return new UnknownType();
	}

	@Override
	public Type visit(BooleanLiteral booleanLiteral) {
		return new BooleanType();
	}

	@Override
	public Type visit(StringLiteral stringLiteral) {
		return new StringType();
	}

	@Override
	public Type visit(IntegerLiteral integerLiteral) {
		return new IntegerType();
	}

	@Override
	public Type visit(Identifier identifier) {
		// check if declared, return the type which was set at declaration time
		if (identifierInfoMap.keySet().contains(identifier.getName())) {
			return identifierInfoMap.get(identifier.getName()).getType();
		} else {
			errorHandler.addError(new NoDeclarationError(identifier.getLine(), identifier.getName()));
			return new UnknownType();
		}
	}
	
	@Override
	public Type visit(MoneyLiteral moneyLiteral) {
		return new MoneyType();
	}
	

	private boolean matchTypes(Type type, Type... expectedTypes) {
		for (Type expectedType: expectedTypes) {
			if(type.equals(expectedType)){
				return true;
			}
		}
		return false;
	}
	
	private Boolean matchNumericType(Type leftType, Type rightType) {
		Type expectedInteger = new IntegerType();
		Type expectedMoney = new MoneyType();
		if(matchTypes(leftType, expectedInteger, expectedMoney)
				&& matchTypes(rightType, expectedInteger, expectedMoney)){
			return true;
		}
		return false;
	}
	
	private Boolean matchStringORBooleanType(Type leftType,Type rightType){
		Type expectedString = new StringType();
		Type expectedBoolean = new BooleanType();
		if(leftType.equals(rightType)){
			if(matchTypes(leftType, expectedString, expectedBoolean)){
				return true;
			}
		}
		return false;
	}
	
	private void checkForRedeclaration(Question question) {
		Identifier identifier = question.getIdentifier();
		String identifierString = identifier.getName();
		Type type = question.getType();
		if(identifierInfoMap.containsKey(identifierString)){
			IdentifierInfo identifierInfo = identifierInfoMap.get(identifierString);
			if(!identifierInfo.getType().equals(type)){
				errorHandler.addError(new DuplicateDeclarationError(question.getLine(),
						identifierString, identifierInfoMap.get(identifierString).getType().getName()));
			}
		}
		else {
			IdentifierInfo identifierInfo = new IdentifierInfo();
			identifierInfo.setType(type);
			identifierInfoMap.put(identifierString, identifierInfo);
		}
	}
	
	private void checkIfCondition(IfStatement ifStatement) {
		Expression expression = ifStatement.getExpression();
		Type type = expression.accept(this);
		boolean isMatched = matchTypes(type, new BooleanType());
		if (!isMatched) {
			errorHandler.addError(new TypeMissmatchError(ifStatement.getLine(), "Boolean", "If condition"));
		}
	}
	
	private Type getTypeForMathExpression(Type leftType, Type rightType) {
		// both operands should be of valid types (either integer or money)
		// if at least one of the operands are of type money, the result type would be money
		Type priorityType = new MoneyType();
		Type baseType = new IntegerType();
		
		if (matchTypes(leftType, priorityType, baseType) && matchTypes(rightType, priorityType, baseType)) {
			if (leftType.equals(priorityType) || rightType.equals(priorityType)) {
				return priorityType;
			} else {
				return baseType;
			}
		}
		return new UnknownType();
	}
	
	private void addDependencies(Identifier identifier, Set<String> dependencySet, String dependency) {
		if (identifierInfoMap.containsKey(dependency)) {
			Set<String> dependencies = identifierInfoMap.get(dependency).getDependencies();
			
			if (dependencies != null && dependencies.size() > 0) {
				dependencySet.addAll(dependencies);
				
				for (String newDependency: dependencies) {
					if (newDependency.equals(identifier.getName())) {
						errorHandler.addError(new CyclicDependencyError(
								identifier.getLine(), identifier.getName(), dependency));
					} 
					else {
						addDependencies(identifier, dependencySet, newDependency);
					}
				}
			}
		}
	}
	
	private void checkDuplicateLabel(Question question) {
		String label = question.getLabel();
		if (labels.contains(label.toLowerCase())) {
			errorHandler.addWarning(new DuplicateLabelWarning(label, question.getLine()));
		} else {
			labels.add(label.toLowerCase());
		}
	}

}
