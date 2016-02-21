package ast.typechecker;

import java.util.HashMap;
import java.util.List;

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
import ast.typechecker.errorhandler.ErrorHandler;
import ast.typechecker.errorhandler.NoDeclarationError;
import ast.typechecker.errorhandler.TypeMissmatchError;
import ast.visitor.ExpressionVisitor;
import ast.visitor.FormVisitor;
import ast.visitor.StatementVisitor;

public class TypeChecker implements ExpressionVisitor<Type>, StatementVisitor, FormVisitor{
	private HashMap<String, IdentifierInfo> identifierInfoMap;
	
	private ErrorHandler errorHandler;

	public TypeChecker(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
		this.identifierInfoMap = new HashMap<>();
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
		checkDeclaration(question);
	}

	@Override
	public void visit(ComputedQuestion computedQuestion) {
		// check if question identifier is not declared before
		checkDeclaration(computedQuestion);
		
		// check if expression type matches question type
		Expression expression = computedQuestion.getExpression();
		Type type = expression.accept(this);
		Boolean isMatched = matchTypes(type, computedQuestion.getType());
		if(!isMatched){
			errorHandler.addError(new TypeMissmatchError(computedQuestion.getType().getName(), "question's expression"));
		}
		
		// adds dependencies if any + check for cyclic dependencies
		Identifier identifier = computedQuestion.getIdentifier();
		IdentifierVisitor identifierVisitor = new IdentifierVisitor();
		List<Identifier> dependencies = expression.accept(identifierVisitor);
		for (Identifier dependency: dependencies) {
			identifierInfoMap.get(identifier.getIdentifier()).addDependency(dependency.getIdentifier());
			checkCyclicDependency(identifier, dependency);
		}
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
			errorHandler.addError(new TypeMissmatchError("Boolean", "Negation operation"));
			return new UnknownType();
		}
		return type;
	}

	@Override
	public Type visit(Addition addition) {
		Type expressionType = getTypeForMathExpression(addition);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Integer or Money", "Add operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Subtraction subtraction) {
		Type expressionType = getTypeForMathExpression(subtraction);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Integer or Money", "Substraction operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Multiplication multiplication) {
		Type expressionType = getTypeForMathExpression(multiplication);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Integer or Money", "Multiply operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Division division) {
		Type expressionType = getTypeForMathExpression(division);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Integer or Money", "Divide operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Conjunction conjunction) {
		Type expressionType = getTypeForBooleanExpression(conjunction);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Boolean", "And operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Disjunction disjunction) {
		Type expressionType = getTypeForBooleanExpression(disjunction);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Boolean", "OR operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Equal equal) {
		Type expressionType = getTypeForBooleanExpression(equal);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Boolean", "Equal operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(NotEqual notEqual) {
		Type expressionType = getTypeForBooleanExpression(notEqual);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Boolean", "Not-Equal operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(GreaterThan greaterThan) {
		Type expressionType = getTypeForBooleanExpression(greaterThan);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Boolean", "Greater-than operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(GreaterThanEqual greaterThanEqual) {
		Type expressionType = getTypeForBooleanExpression(greaterThanEqual);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Boolean", "Greater-Equal operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(LessThan LessThan) {
		Type expressionType = getTypeForBooleanExpression(LessThan);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Boolean", "Less-than operation"));
		}
		return expressionType;
	}

	@Override
	public Type visit(LessThanEqual LessThanEqual) {
		Type expressionType = getTypeForBooleanExpression(LessThanEqual);
		if (expressionType instanceof UnknownType) {
			errorHandler.addError(new TypeMissmatchError("Boolean", "Less-Equal operation"));
		}
		return expressionType;
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
		// check if declared, return the type which was set at declare time
		if (identifierInfoMap.keySet().contains(identifier.getIdentifier())) {
			return identifierInfoMap.get(identifier.getIdentifier()).getType();
		} else {
			errorHandler.addError(new NoDeclarationError(identifier.getIdentifier()));
			return new UnknownType();
		}
	}
	

	private boolean matchTypes(Type type, Type... expectedTypes) {
		for (Type expectedType: expectedTypes) {
			if(type.getName().equals(expectedType.getName())){
				return true;
			}
		}
		return false;
	}
	
	private void checkDeclaration(Question question) {
		Identifier identifier = question.getIdentifier();
		Type type = question.getType();
		if (isDeclared(identifier, type)) {
			errorHandler.addError(new DuplicateDeclarationError(identifier.getIdentifier(), type.getName()));
		} else {
			IdentifierInfo identifierInfo = new IdentifierInfo();
			identifierInfo.setType(type);
			identifierInfoMap.put(identifier.getIdentifier(), identifierInfo);
		}
	}
	
	private boolean isDeclared(Identifier identifier, Type type) {
		if (identifierInfoMap.containsKey(identifier.getIdentifier())) {
			IdentifierInfo identifierInfo = identifierInfoMap.get(identifier.getIdentifier());
			if (!identifierInfo.getType().getName().equals(type.getName())) {
				return true;
			}
		}
		return false;
	}
	
	// checks if the dependency has the identifier as a dependency. hence cyclic dependency
	private void checkCyclicDependency(Identifier identifier, Identifier dependency) {
		if (identifierInfoMap.containsKey(dependency.getIdentifier())) {
			List<String> dependencies = identifierInfoMap.get(dependency.getIdentifier()).getDependencies();
			if (dependencies != null && dependencies.contains(identifier.getIdentifier())) {
				errorHandler.addError(new CyclicDependencyError(identifier, dependency));
			}
		}
	}
	
	private void checkIfCondition(IfStatement ifStatement) {
		Expression expression = ifStatement.getExpression();
		Type type = expression.accept(this);
		boolean isMatched = matchTypes(type, new BooleanType());
		if (!isMatched) {
			errorHandler.addError(new TypeMissmatchError("Boolean", "If condition"));
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
			if (leftType.getName().equals(priorityType.getName()) || rightType.getName().equals(baseType.getName())) {
				return priorityType;
			} else {
				return baseType;
			}
		}
		return new UnknownType();
	}
	
	private Type getTypeForBooleanExpression(BinaryExpression expression) {
		Type expectedType = new BooleanType();
		Type leftType = expression.getLeftExpression().accept(this);
		Type rightType = expression.getRightExpression().accept(this);
		
		if (matchTypes(leftType, expectedType) && matchTypes(rightType, expectedType)) {
			return expectedType;
		}
		return new UnknownType();
	}

}
