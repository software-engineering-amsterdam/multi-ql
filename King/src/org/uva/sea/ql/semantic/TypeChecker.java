package org.uva.sea.ql.semantic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.uva.sea.ql.ast.domain.*;
import org.uva.sea.ql.ast.expr.VarExpr;
import org.uva.sea.ql.ast.expr.binary.*;
import org.uva.sea.ql.ast.expr.literal.*;
import org.uva.sea.ql.ast.expr.math.*;
import org.uva.sea.ql.ast.expr.type.*;
import org.uva.sea.ql.ast.expr.unary.*;
import org.uva.sea.ql.ast.visitors.QLDomainVisitor;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class TypeChecker implements QLNodeVisitor<Type>, QLDomainVisitor {
	private SymbolTable symTable;
	private Message messages;
	private Set<String> lableNames = new HashSet<String>();
	private QuestionCyclicDependencyManager questionCyclicDependencyManager;
	private List<ReadOnlyQuestion> readOnlyQuestion;

	public TypeChecker(Form form) {
		readOnlyQuestion = new ArrayList<>();
		messages = new Message();
		symTable = new SymbolTable();
		questionCyclicDependencyManager = new QuestionCyclicDependencyManager();
		form.accept(this);
	}

	@Override
	public void visit(Form form) {
		form.getBody().accept(this);
	}

	@Override
	public void visit(Block block) {

		for (Question q : block.getQuestions()) {
			q.accept(this);
		}

		for (IFblock ib : block.getStatements()) {
			ib.accept(this);
		}
	}

	@Override
	public void visit(IFblock statement) {
		for (Question ibq : statement.getBody().getQuestions()) {
			ibq.accept(this);
		}

		if (!isBlockConditionBoolean(statement)) {
			String msg = statement + " condition is not of the type boolean";
			messages.addError("condition",msg);
		}

	}

	@Override
	public void visit(Question question) {

		if (hasDuplicateVarDeclaration(question)) {
			String msg = "The question '"+question.getVarIdentifierName()+"' has been declared multiple time with different type";
			messages.addError("duplicateQuestion",msg);
		} else {
			symTable.add(question.getVarIdentifierName(), question.getVarType());
		}

		if (hasDuplicateLablesWarning(question)) {
			String msg = "The question lable '" + question.getText() + "' has been used more than once";
			messages.addWarning(question.getText(),msg);
		}
		lableNames.add(question.getText());

	}

	@Override
	public void visit(ReadOnlyQuestion computedQuestion) {
		if (hasDuplicateVarDeclaration(computedQuestion)) {
			String msg = "The question '" + computedQuestion.getVarIdentifierName()
					+ "' has been declared multiple time with different type";
			messages.addError("duplicateQuestion",msg);
		} else {
			symTable.add(computedQuestion.getVarIdentifierName(),computedQuestion.getVarType());
		}

		if (hasDuplicateLablesWarning(computedQuestion)) {
			String msg = "The question lable '" + computedQuestion.getText() + "' has been used more than once";
			messages.addWarning(computedQuestion.getText(),msg);
		}
		lableNames.add(computedQuestion.getText());
		//context ->false indicates that we will only visit to collect but not check for errors
		computedQuestion.getExpression().accept(this,false);
		readOnlyQuestion.add(computedQuestion);
		IdentifierDependency identifiers = new IdentifierDependency();
		Set<String> identifierDependencies = computedQuestion.getExpression().accept(identifiers,false);
		questionCyclicDependencyManager.addQuestionDependency(computedQuestion, identifierDependencies);

	}

	@Override
	public Type visit(Equal eq, boolean context) {
		return checkBinaryExpression(eq,context);
	}

	@Override
	public Type visit(OR or, boolean context) {
		return checkBinaryExpression(or,context);
	}

	@Override
	public Type visit(GreaterOrEqual geq, boolean context) {
		return checkBinaryExpression(geq,context);
	}

	@Override
	public Type visit(GreaterThan gt, boolean context) {
		return checkBinaryExpression(gt,context);

	}

	@Override
	public Type visit(SmallerOrEqual leq, boolean context) {
		return checkBinaryExpression(leq,context);

	}

	@Override
	public Type visit(SmallerThan lt, boolean context) {
		return checkBinaryExpression(lt,context);

	}

	@Override
	public Type visit(AND and, boolean context) {
		return checkBinaryExpression(and,context);

	}

	@Override
	public Type visit(NotEqual neq, boolean context) {
		return checkBinaryExpression(neq,context);

	}

	@Override
	public Type visit(Negative neg, boolean context) {
		return checkUnaryExpression(neg,context);

	}

	@Override
	public Type visit(NOT not, boolean context) {
		return checkUnaryExpression(not,context);
	}

	@Override
	public Type visit(Positive pos, boolean context) {
		return checkUnaryExpression(pos,context);
	}

	@Override
	public Type visit(Div div, boolean context) {
		Type type = checkBinaryExpression(div,context);
		if (!mathExprHasExpectedType(type)) {
			String msg = "Integer or Money was expected for division. {money / money} or {integer / integer}";
			messages.addError("mathType",msg);
		}
		return type;
	}

	@Override
	public Type visit(Mul mul, boolean context) {
		Type type = checkBinaryExpression(mul,context);
		if (!mathExprHasExpectedType(type)) {
			String msg = "Integer or Money was expected for multiplication. {money * money} or {integer * integer}";
			messages.addError("mathType",msg);
		}
		return type;
	}

	@Override
	public Type visit(Add add, boolean context) {
		Type type = checkBinaryExpression(add,context);
		//System.out.println(type);
		if (!mathExprHasExpectedType(type) && context) {
			String msg = "Integer or Money was expected for addition. {money + money} or {integer + integer}";
			messages.addError("mathType",msg);
		}
		return type;
	}

	@Override
	public Type visit(Sub sub, boolean context) {
		Type type = checkBinaryExpression(sub,context);
		if (!mathExprHasExpectedType(type)) {
			String msg = "Integer or Money was expected for subtitution. {money - money} or {integer - integer}";
			messages.addError("mathType",msg);
		}
		return type;
	}

	@Override
	public Type visit(IntegerLiteral intLiteral, boolean context) {
		return new IntegerType();
	}

	@Override
	public Type visit(BooleanLiteral boolLiteral, boolean context) {
		return new BooleanType();
	}

	@Override
	public Type visit(StringLiteral stringLiteral, boolean context) {
		return new StringType();
	}

	@Override
	public Type visit(MoneyLiteral moneyLiteral, boolean context) {
		return new MoneyType();
	}

	@Override
	public Type visit(VarExpr varExpr, boolean context) {
		Type typeToReturn = getVarExpressionType(varExpr);
		if (checkExprEquality(typeToReturn, new UnknownType()) && context) {
			String msg = varExpr.getIdentifierName() + " reference to undefined question";
			messages.addError("undefined",msg);
		}
		return typeToReturn;

	}

	public boolean isBlockConditionBoolean(IFblock statement) {
		boolean isBoolean = false;
		Type exprType = statement.getCondition().accept(this,true);
		if (checkExprEquality(exprType, new BooleanType())) {
			isBoolean = true;
		}
		return isBoolean;
	}

	private boolean hasExpectedType(ReadOnlyQuestion question) {
		Type expected = question.getVarType();
		Type expr = question.getExpression().accept(this,true);
		return checkExprEquality(expr, expected);
	}

	private boolean mathExprHasExpectedType(Type type) {
		boolean isAllowed = false;
		if (checkExprEquality(type, new IntegerType()) || checkExprEquality(type, new MoneyType())) {
			isAllowed = true;
		}
		return isAllowed;
	}

	private boolean hasDuplicateVarDeclaration(Question question) {
		boolean isDuplicateVariable = false;
		String variableName = question.getVarIdentifierName();
		Type type = question.getVarType();
		if (symTable.contains(variableName)) {
			if (type.equals(symTable.lookupType(variableName)) == false) {
				isDuplicateVariable = true;
			}

		}
		return isDuplicateVariable;
	}

	private boolean hasDuplicateLablesWarning(Question question) {
		boolean isDuplicatelableName = false;

		if (lableNames.contains(question.getText())) {
			isDuplicatelableName = true;

		}
		return isDuplicatelableName;
	}

	private Boolean checkExprEquality(Type e1, Type e2) {
		boolean isExprEqual = false;
		if (e1.equals(e2)) {
			isExprEqual = true;
		}
		return isExprEqual;
	}

	private Type checkBinaryExpression(BinaryExpression e, boolean context) {
		Type e1 = e.getFirstExpression().accept(this,context);
		Type e2 = e.getSecondExpression().accept(this,context);
		Type typeToReturn = e1;
		if (checkExprEquality(e1, new UnknownType()) && context) {
			String msg = e.toString() + " reference to undefined question";
			messages.addError("undefined",msg);
		}

		if (!checkExprEquality(e1, e2)&& context) {
			typeToReturn = new UnknownType();
			String msg = e.toString() + " Binary expression must be of the same type";
			messages.addError("binaryType",msg);
		}
		return typeToReturn;
	}

	private Type checkUnaryExpression(UnaryExpression ue, boolean context) {
		Type expectedType = new BooleanType();
		Type e = ue.getExpression().accept(this,context);
		if (!checkExprEquality(expectedType, e)&& context) {
			expectedType = new UnknownType();
			String msg = "The unary expression is not of the type boolean";
			messages.addError("unaryType",msg);
		}
		return expectedType;
	}

	private Type getVarExpressionType(VarExpr varExpr) {
		Type typeToReturn = new UnknownType();
		if (symTable.lookupType(varExpr.getIdentifierName()) != null) {
			typeToReturn = symTable.lookupType(varExpr.getIdentifierName());
			varExpr.getIdentifier().setType(typeToReturn);
		}
		return typeToReturn;
	}
	
	public Message getQLAllSemanticMessages() {
		return messages;
	}
	
	public void printSemanticMessages() {
		getQLAllSemanticMessages().print();
	}

	private void addCyclicSymanticErrors() {
		for (String cyclicMessage : questionCyclicDependencyManager.findCyclicDepency()) {
			messages.addError("cyclic",cyclicMessage);
		}
	}
	
	public void addOtherSymanticErrors() {
		addCyclicSymanticErrors();
		addReadOnlyQuestionSemantic();
	}

	private void addReadOnlyQuestionSemantic() {
		for (ReadOnlyQuestion question : readOnlyQuestion) {
			if (!hasExpectedType(question)) {
				String msg = "The question variable '" + question.getVariableId()
						+ "' in the computed question references to different type.";
				messages.addError("correctReferences",msg);
			}	
		}
	}
	
	public Boolean hasErrorMessages() {
		return messages.hasErrors();
	}
	
	public SymbolTable getQLSymbolsTbale() {
		return symTable;
	}

}
