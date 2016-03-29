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
		if(!computedQuestionType.isCompatible(expressionType)){
			errorHandler.addError(new TypeMissmatchError(computedQuestion.getLine(),
					"Computed Question Expression", computedQuestionType.getName()));
		}
		
		checkForRedeclaration(computedQuestion);
		checkDuplicateLabel(computedQuestion);
		checkCyclicDependecy(computedQuestion);
		return null;
	}

	@Override
	public Void visit(IfStatement ifStatement) {
		checkConditionType(ifStatement);
		ifStatement.getIfBox().accept(this);
		return null;
	}

	@Override
	public Void visit(IfElseStatement ifElseStatement) {
		checkConditionType(ifElseStatement);
		ifElseStatement.getIfBox().accept(this);
		ifElseStatement.getElseBox().accept(this);
		return null;
	}
	
	@Override
	public Type visit(Negation negation) {
		Type type = negation.getExpression().accept(this);
		if(!type.isBooleanCompatible(new BooleanType())){
			errorHandler.addError(new TypeMissmatchError(negation.getLine(), "Negation operation", "Boolean"));
			return new UnknownType();
		}
		return type;
	}

	@Override
	public Type visit(Addition addition) {
		Type leftType = addition.getLeftExpression().accept(this);
		Type rightType = addition.getRightExpression().accept(this);
		if(leftType.isStringCompatible(rightType)){
			return new StringType();
		}
		
		Type expressionType = getTypeForNumericExpression(leftType, rightType);
		if (expressionType.isUnknownType()) {
			errorHandler.addError(new OperationTypeMissmatchError(addition.getLine(), "Add"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Subtraction subtraction) {
		Type leftType = subtraction.getLeftExpression().accept(this);
		Type rightType = subtraction.getRightExpression().accept(this);
		Type expressionType = getTypeForNumericExpression(leftType, rightType);
		if (expressionType.isUnknownType()) {
			errorHandler.addError(new OperationTypeMissmatchError(subtraction.getLine(), "Subtraction"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Multiplication multiplication) {
		Type leftType = multiplication.getLeftExpression().accept(this);
		Type rightType = multiplication.getRightExpression().accept(this);
		Type expressionType = getTypeForNumericExpression(leftType, rightType);
		if (expressionType.isUnknownType()) {
			errorHandler.addError(new OperationTypeMissmatchError(multiplication.getLine(), "Multiply"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Division division) {
		Type leftType = division.getLeftExpression().accept(this);
		Type rightType = division.getRightExpression().accept(this);
		Type expressionType = getTypeForNumericExpression(leftType, rightType);
		if (expressionType.isUnknownType()) {
			errorHandler.addError(new OperationTypeMissmatchError(division.getLine(), "Divide"));
		}
		return expressionType;
	}

	@Override
	public Type visit(Conjunction conjunction) {
		Type leftType = conjunction.getLeftExpression().accept(this);
		Type rightType = conjunction.getRightExpression().accept(this);
		if(leftType.isBooleanCompatible(rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(conjunction.getLine(), "And"));
		return new UnknownType();
	}

	@Override
	public Type visit(Disjunction disjunction) {
		Type leftType = disjunction.getLeftExpression().accept(this);
		Type rightType = disjunction.getRightExpression().accept(this);
		if(leftType.isBooleanCompatible(rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(disjunction.getLine(), "Or"));
		return new UnknownType();
	}

	@Override
	public Type visit(Equal equal) {
		Type leftType = equal.getLeftExpression().accept(this);
		Type rightType = equal.getRightExpression().accept(this);
		if(leftType.isStringCompatible(rightType) ||
				leftType.isBooleanCompatible(rightType) ||
					leftType.isNumericCompatible(rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(equal.getLine(), "Equal"));
		return new UnknownType();
	}

	@Override
	public Type visit(NotEqual notEqual) {
		Type leftType = notEqual.getLeftExpression().accept(this);
		Type rightType = notEqual.getRightExpression().accept(this);
		if(leftType.isStringCompatible(rightType) ||
				leftType.isBooleanCompatible(rightType) ||
					leftType.isNumericCompatible(rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(notEqual.getLine(), "NotEqual"));
		return new UnknownType();
	}

	@Override
	public Type visit(GreaterThan greaterThan) {
		Type leftType = greaterThan.getLeftExpression().accept(this);
		Type rightType = greaterThan.getRightExpression().accept(this);
		if(leftType.isNumericCompatible(rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(greaterThan.getLine(), "Greater than"));
		return new UnknownType();
	}

	@Override
	public Type visit(GreaterThanEqual greaterThanEqual) {
		Type leftType = greaterThanEqual.getLeftExpression().accept(this);
		Type rightType = greaterThanEqual.getRightExpression().accept(this);
		if(leftType.isNumericCompatible(rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(greaterThanEqual.getLine(), "Greater than equal"));
		return new UnknownType();
	}

	@Override
	public Type visit(LessThan lessThan) {
		Type leftType = lessThan.getLeftExpression().accept(this);
		Type rightType = lessThan.getRightExpression().accept(this);
		if(leftType.isNumericCompatible(rightType)){
			return new BooleanType();
		}
		errorHandler.addError(new OperationTypeMissmatchError(lessThan.getLine(), "Less than"));
		return new UnknownType();
	}

	@Override
	public Type visit(LessThanEqual lessThanEqual) {
		Type leftType = lessThanEqual.getLeftExpression().accept(this);
		Type rightType = lessThanEqual.getRightExpression().accept(this);
		if(leftType.isNumericCompatible(rightType)){
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
	public Type visit(MoneyLiteral moneyLiteral) {
		return new MoneyType();
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
	
	private void checkForRedeclaration(Question question) {
		Identifier identifier = question.getIdentifier();
		String identifierName = identifier.getName();
		Type type = question.getType();
		if(identifierInfoMap.containsKey(identifierName)){
			IdentifierInfo identifierInfo = identifierInfoMap.get(identifierName);
			if(!identifierInfo.getType().equals(type)){
				errorHandler.addError(new DuplicateDeclarationError(question.getLine(),
						identifierName, identifierInfoMap.get(identifierName).getType().getName()));
			}
		}
		else {
			IdentifierInfo identifierInfo = new IdentifierInfo();
			identifierInfo.setType(type);
			identifierInfoMap.put(identifierName, identifierInfo);
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
	
	private void checkConditionType(IfStatement ifStatement) {
		Expression condition = ifStatement.getExpression();
		Type conditionType = condition.accept(this);
		if(!conditionType.isBooleanCompatible(new BooleanType())){
			errorHandler.addError(new TypeMissmatchError(ifStatement.getLine(), "If condition", "Boolean"));
		}
	}
	
	private void checkCyclicDependecy(ComputedQuestion computedQuestion) {
		Expression expression = computedQuestion.getExpression();
		Identifier identifier = computedQuestion.getIdentifier();
		IdentifierVisitor identifierVisitor = new IdentifierVisitor();
		Set<String> directDependencies = expression.accept(identifierVisitor);
		Set<String> dependencySet = new HashSet<>(directDependencies);
		List<DependencyPair> dependencyPairs = new LinkedList<>();
		for (String directDependency: directDependencies) {
			addIndirectDependencies(identifier, dependencySet, dependencyPairs, directDependency);
		}
		identifierInfoMap.get(identifier.getName()).setDependencies(dependencySet);
		for(DependencyPair dependencyPair: dependencyPairs){
			errorHandler.addError(new CyclicDependencyError(
					dependencyPair.getFirstIdentifier(), dependencyPair.getSecondIdentifier()));
		}
	}
	
	private void addIndirectDependencies(Identifier identifier, Set<String> dependencySet,
			List<DependencyPair> dependencyPairs, String dependency) {
		if (identifierInfoMap.containsKey(dependency)) {
			Set<String> indirectDependencies = identifierInfoMap.get(dependency).getDependencies();
			dependencySet.addAll(indirectDependencies);
			
			for (String indirectDependency: indirectDependencies) {
				if (indirectDependency.equals(identifier.getName())) {
					dependencyPairs.add(new DependencyPair(identifier.getName(), dependency));
				} 
				else {
					addIndirectDependencies(identifier, dependencySet, dependencyPairs, indirectDependency);
				}
			}
		}
	}
	
	private Type getTypeForNumericExpression(Type leftType, Type rightType) {
		Type priorityType = new MoneyType();
		Type baseType = new IntegerType();
		
		if(leftType.isNumericCompatible(rightType)){
			if (leftType.equals(priorityType) || rightType.equals(priorityType)) {
				return priorityType;
			} else {
				return baseType;
			}
		}
		return new UnknownType();
	}
}
