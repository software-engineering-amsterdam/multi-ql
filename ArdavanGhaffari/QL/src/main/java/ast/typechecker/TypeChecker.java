package ast.typechecker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import ast.model.Box;
import ast.model.Expression;
import ast.model.Form;
import ast.model.Statement;
import ast.model.binaryexpression.Addition;
import ast.model.binaryexpression.BinaryExpression;
import ast.model.binaryexpression.Conjunction;
import ast.model.binaryexpression.Disjunction;
import ast.model.binaryexpression.Division;
import ast.model.binaryexpression.Equal;
import ast.model.binaryexpression.GreaterThan;
import ast.model.binaryexpression.GreaterThanEqual;
import ast.model.binaryexpression.LessThan;
import ast.model.binaryexpression.LessThanEqual;
import ast.model.binaryexpression.Multiplication;
import ast.model.binaryexpression.NotEqual;
import ast.model.binaryexpression.Subtraction;
import ast.model.literal.BooleanLiteral;
import ast.model.literal.DecimalLiteral;
import ast.model.literal.Identifier;
import ast.model.literal.IntegerLiteral;
import ast.model.literal.StringLiteral;
import ast.model.statement.ComputedQuestion;
import ast.model.statement.IfElseStatement;
import ast.model.statement.IfStatement;
import ast.model.statement.Question;
import ast.model.type.BooleanType;
import ast.model.type.DecimalType;
import ast.model.type.IntegerType;
import ast.model.type.StringType;
import ast.model.type.Type;
import ast.model.type.UnknownType;
import ast.model.unaryexpression.Negation;
import ast.typechecker.errorhandler.CyclicDependencyError;
import ast.typechecker.errorhandler.DuplicateDeclarationError;
import ast.typechecker.errorhandler.DuplicateLabelWarning;
import ast.typechecker.errorhandler.ErrorHandler;
import ast.typechecker.errorhandler.NoDeclarationError;
import ast.typechecker.errorhandler.OperationTypeMissmatchError;
import ast.typechecker.errorhandler.TypeMissmatchError;
import ast.visitor.ExpressionVisitor;
import ast.visitor.FormVisitor;
import ast.visitor.StatementVisitor;

public class TypeChecker implements ExpressionVisitor<Type>, StatementVisitor, FormVisitor{
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
	public void visit(Box box) {
		for(Statement statement: box.getStatements()){
			statement.accept(this);
		}
	}

	@Override
	public void visit(Question question) {
		// check if question identifier is not declared before, with a different type
		checkDeclaration(question);
		
		// check if label is duplicate, add to warnings
		checkDuplicateLabel(question);
	}

	@Override
	public void visit(ComputedQuestion computedQuestion) {
		// check if expression type matches computed question type
		Expression expression = computedQuestion.getExpression();
		Type type = expression.accept(this);
		// if question type is decimal (money), then integer type would be acceptable as well for the expression type
		Boolean isMatched;
		if (computedQuestion.getType() instanceof DecimalType) {
			isMatched = matchTypes(type, computedQuestion.getType(), new IntegerType());
		} else {
			isMatched = matchTypes(type, computedQuestion.getType());
		}
		if(!isMatched){
			errorHandler.addError(new TypeMissmatchError(computedQuestion.getLine(),
					computedQuestion.getType().getName(), "question's expression"));
		}
		
		// check if question identifier is not declared before, with a different type
		checkDeclaration(computedQuestion);
		
		// check if label is duplicate, add to warnings
		checkDuplicateLabel(computedQuestion);
		
		// adds dependencies if any + check for cyclic dependencies
		Identifier identifier = computedQuestion.getIdentifier();
		IdentifierVisitor identifierVisitor = new IdentifierVisitor();
		Set<String> directDependencies = expression.accept(identifierVisitor);
		Set<String> dependencySet = new HashSet<>(directDependencies);
		for (String dependency: directDependencies) {
			addDependencies(identifier, dependencySet, dependency);
		}
		identifierInfoMap.get(identifier.getIdentifier()).setDependencies(dependencySet);
	}

	@Override
	public void visit(IfStatement ifStatement) {
		// check the if-condition to match boolean type
		checkIfCondition(ifStatement);
		
		// type-check the if block
		ifStatement.getIfBox().accept(this);
	}

	@Override
	public void visit(IfElseStatement ifElseStatement) {
		// check the if-condition to match boolean type
		checkIfCondition(ifElseStatement);
		
		// type-check the if block
		ifElseStatement.getIfBox().accept(this);
		
		// type-check the else block
		ifElseStatement.getElseBox().accept(this);
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
		Type expressionType = getTypeForMathExpression(addition);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new OperationTypeMissmatchError(addition.getLine(), "Add"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Subtraction subtraction) {
		Type expressionType = getTypeForMathExpression(subtraction);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new OperationTypeMissmatchError(subtraction.getLine(), "Substraction"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Multiplication multiplication) {
		Type expressionType = getTypeForMathExpression(multiplication);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new OperationTypeMissmatchError(multiplication.getLine(), "Multiply"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Division division) {
		Type expressionType = getTypeForMathExpression(division);
		if (expressionType instanceof UnknownType) {
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
		if (identifierInfoMap.keySet().contains(identifier.getIdentifier())) {
			return identifierInfoMap.get(identifier.getIdentifier()).getType();
		} else {
			errorHandler.addError(new NoDeclarationError(identifier.getLine(), identifier.getIdentifier()));
			return new UnknownType();
		}
	}
	
	@Override
	public Type visit(DecimalLiteral decimalLiteral) {
		return new DecimalType();
	}
	

	private boolean matchTypes(Type type, Type... expectedTypes) {
		for (Type expectedType: expectedTypes) {
			if(type.getName().equals(expectedType.getName())){
				return true;
			}
		}
		return false;
	}
	
	private Boolean matchNumericType(Type leftType, Type rightType) {
		Type expectedInteger = new IntegerType();
		Type expectedDecimal = new DecimalType();
		if(matchTypes(leftType, expectedInteger, expectedDecimal)
				&& matchTypes(rightType, expectedInteger, expectedDecimal)){
			return true;
		}
		return false;
	}
	
	private Boolean matchStringORBooleanType(Type leftType,Type rightType){
		Type expectedString = new StringType();
		Type expectedBoolean = new BooleanType();
		if(leftType.getName().equals(rightType.getName())){
			if(matchTypes(leftType, expectedString, expectedBoolean)){
				return true;
			}
		}
		return false;
	}
	
	private void checkDeclaration(Question question) {
		Identifier identifier = question.getIdentifier();
		String identifierString = identifier.getIdentifier();
		Type type = question.getType();
		if (isDeclared(identifier, type)) {
			errorHandler.addError(new DuplicateDeclarationError(question.getLine(),
					identifierString, identifierInfoMap.get(identifierString).getType().getName()));
		} else {
			//either the identifier has already been declared with the same type or it is it's first declaration
			IdentifierInfo identifierInfo = new IdentifierInfo();
			identifierInfo.setType(type);
			identifierInfoMap.put(identifierString, identifierInfo);
		}
	}
	
	private boolean isDeclared(Identifier identifier, Type type) {
		String identifierString = identifier.getIdentifier();
		if (identifierInfoMap.containsKey(identifierString)) {
			IdentifierInfo identifierInfo = identifierInfoMap.get(identifierString);
			if (!identifierInfo.getType().getName().equals(type.getName())) {
				return true;
			}
		}
		return false;
	}
	
	private void checkIfCondition(IfStatement ifStatement) {
		Expression expression = ifStatement.getExpression();
		Type type = expression.accept(this);
		boolean isMatched = matchTypes(type, new BooleanType());
		if (!isMatched) {
			errorHandler.addError(new TypeMissmatchError(ifStatement.getLine(), "Boolean", "If condition"));
		}
	}
	
	private Type getTypeForMathExpression(BinaryExpression expression) {
		// both operands should be of valid types (either integer or decimal)
		// if at least one of the operands are of type decimal, the result type would be decimal
		Type priorityType = new DecimalType();
		Type baseType = new IntegerType();
		
		Type leftType = expression.getLeftExpression().accept(this);
		Type rightType = expression.getRightExpression().accept(this);
		
		if (matchTypes(leftType, priorityType, baseType) && matchTypes(rightType, priorityType, baseType)) {
			if (leftType.getName().equals(priorityType.getName()) || rightType.getName().equals(priorityType.getName())) {
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
					if (newDependency.equals(identifier.getIdentifier())) {
						errorHandler.addError(new CyclicDependencyError(identifier.getLine(), identifier.getIdentifier(), dependency));
					}
					addDependencies(identifier, dependencySet, newDependency);
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
