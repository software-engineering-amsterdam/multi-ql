package org.uva.sea.ql.semantic;

import java.util.HashSet;
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

	public TypeChecker(Form form) {
		messages = new Message();
		symTable = new SymbolTable();
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
			messages.addError(msg);
		}

	}

	@Override
	public void visit(Question question) {

		if (hasDuplicateVarDeclaration(question)) {
			String msg = question.getVariableId()
					+ " The question has been declared multiple time with different type";
			messages.addError(msg);
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
			String msg = "The question '" + computedQuestion.getVariableId()
					+ "' has been declared multiple time with different type";
			messages.addError(msg);
		} else {
			symTable.add(computedQuestion.getVarIdentifierName(),computedQuestion.getVarType());
		}

		if (hasDuplicateLablesWarning(computedQuestion)) {
			String msg = "The question lable '" + computedQuestion.getText() + "' has been used more than once";
			messages.addWarning(computedQuestion.getText(),msg);
		}
		lableNames.add(computedQuestion.getText());

		if (!hasExpectedType(computedQuestion)) {
			String msg = "The question variable '" + computedQuestion.getVariableId()
					+ "' in the computed question references to different type.";
			messages.addError(msg);
		}

		if (hasCyclicDependency(computedQuestion)) {
			String msg = "The question variable '" + computedQuestion.getVariableId()
					+ "' in the computed question has a cyclic dependency.";
			messages.addError(msg);
		}
	}

	@Override
	public Type visit(Equal eq) {
		return checkBinaryExpression(eq);
	}

	@Override
	public Type visit(OR or) {
		return checkBinaryExpression(or);
	}

	@Override
	public Type visit(GreaterOrEqual geq) {
		return checkBinaryExpression(geq);
	}

	@Override
	public Type visit(GreaterThan gt) {
		return checkBinaryExpression(gt);

	}

	@Override
	public Type visit(SmallerOrEqual leq) {
		return checkBinaryExpression(leq);

	}

	@Override
	public Type visit(SmallerThan lt) {
		return checkBinaryExpression(lt);

	}

	@Override
	public Type visit(AND and) {
		return checkBinaryExpression(and);

	}

	@Override
	public Type visit(NotEqual neq) {
		return checkBinaryExpression(neq);

	}

	@Override
	public Type visit(Negative neg) {
		return checkUnaryExpression(neg);

	}

	@Override
	public Type visit(NOT not) {
		return checkUnaryExpression(not);
	}

	@Override
	public Type visit(Positive pos) {
		return checkUnaryExpression(pos);
	}

	@Override
	public Type visit(Div div) {
		Type type = checkBinaryExpression(div);
		if (!mathExprHasExpectedType(type)) {
			String msg = "Integer or Money was expected for division.";
			messages.addError(msg);
		}
		return type;
	}

	@Override
	public Type visit(Mul mul) {
		Type type = checkBinaryExpression(mul);
		if (!mathExprHasExpectedType(type)) {
			String msg = "Integer or Money was expected for multiplication.";
			messages.addError(msg);
		}
		return type;
	}

	@Override
	public Type visit(Add add) {
		Type type = checkBinaryExpression(add);
		if (!mathExprHasExpectedType(type)) {
			String msg = "Integer or Money was expected for addition.";
			messages.addError(msg);
		}
		return type;
	}

	@Override
	public Type visit(Sub sub) {
		Type type = checkBinaryExpression(sub);
		if (!mathExprHasExpectedType(type)) {
			String msg = "Integer or Money was expected for subtitution.";
			messages.addError(msg);
		}
		return type;
	}

	@Override
	public Type visit(IntegerLiteral intLiteral) {
		return new IntegerType();
	}

	@Override
	public Type visit(BooleanLiteral boolLiteral) {
		return new BooleanType();
	}

	@Override
	public Type visit(StringLiteral stringLiteral) {
		return new StringType();
	}

	@Override
	public Type visit(MoneyLiteral moneyLiteral) {
		return new MoneyType();
	}

	@Override
	public Type visit(VarExpr varExpr) {
		Type typeToReturn = getVarExpressionType(varExpr);
		if (checkExprEquality(typeToReturn, new UnknownType())) {
			String msg = varExpr.getIdentifierName() + " reference to undefined question";
			messages.addError(msg);
		}
		return typeToReturn;

	}

	public boolean isBlockConditionBoolean(IFblock statement) {
		boolean isBoolean = false;
		Type exprType = statement.getCondition().accept(this);
		if (!checkExprEquality(exprType, new UnknownType())) {
			isBoolean = true;
		}
		return isBoolean;
	}

	private boolean hasExpectedType(ReadOnlyQuestion question) {
		Type expected = question.getVarType();
		Type expr = question.getExpression().accept(this);
		return checkExprEquality(expr, expected);
	}

	private boolean hasCyclicDependency(ReadOnlyQuestion computedQuestion) {
		boolean isCyclic = false;
		IdentifierDependency identifiers = new IdentifierDependency();
		Set<String> identifierDependencies = computedQuestion.getExpression().accept(identifiers);
		QuestionCyclicDependencyManager questionCyclicDependencyManager = new QuestionCyclicDependencyManager();
		questionCyclicDependencyManager.addQuestionDependency(computedQuestion, identifierDependencies);
		if (questionCyclicDependencyManager.hasCyclicDepency()) {
			isCyclic = true;
		}
		return isCyclic;
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

	private Type checkBinaryExpression(BinaryExpression e) {
		Type e1 = e.getFirstExpression().accept(this);
		Type e2 = e.getSecondExpression().accept(this);
		Type typeToReturn = e1;
		if (checkExprEquality(e1, new UnknownType())) {
			String msg = e.toString() + " reference to undefined question";
			messages.addError(msg);
		}

		if (!checkExprEquality(e1, e2)) {
			typeToReturn = new UnknownType();
			String msg = e.toString() + " Binary expression must be of the same type";
			messages.addError(msg);
		}
		return typeToReturn;
	}

	private Type checkUnaryExpression(UnaryExpression ue) {
		Type expectedType = new BooleanType();
		Type e = ue.getExpression().accept(this);
		if (!checkExprEquality(expectedType, e)) {
			expectedType = new UnknownType();
			String msg = "The unary expression is not of the type boolean";
			messages.addError(msg);
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
	
	public Boolean hasErrorMessages() {
		return messages.hasErrors();
	}

}
