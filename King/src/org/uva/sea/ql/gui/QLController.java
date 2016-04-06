package org.uva.sea.ql.gui;

import java.util.HashSet;
import java.util.Set;
import org.joda.money.Money;
import org.uva.sea.ql.ast.domain.Block;
import org.uva.sea.ql.ast.domain.Form;
import org.uva.sea.ql.ast.domain.IFblock;
import org.uva.sea.ql.ast.domain.Question;
import org.uva.sea.ql.ast.domain.ReadOnlyQuestion;
import org.uva.sea.ql.ast.expr.VarExpr;
import org.uva.sea.ql.ast.expr.binary.AND;
import org.uva.sea.ql.ast.expr.binary.Equal;
import org.uva.sea.ql.ast.expr.binary.GreaterOrEqual;
import org.uva.sea.ql.ast.expr.binary.GreaterThan;
import org.uva.sea.ql.ast.expr.binary.NotEqual;
import org.uva.sea.ql.ast.expr.binary.OR;
import org.uva.sea.ql.ast.expr.binary.SmallerOrEqual;
import org.uva.sea.ql.ast.expr.binary.SmallerThan;
import org.uva.sea.ql.ast.expr.literal.BooleanLiteral;
import org.uva.sea.ql.ast.expr.literal.IntegerLiteral;
import org.uva.sea.ql.ast.expr.literal.MoneyLiteral;
import org.uva.sea.ql.ast.expr.literal.StringLiteral;
import org.uva.sea.ql.ast.expr.math.Add;
import org.uva.sea.ql.ast.expr.math.Div;
import org.uva.sea.ql.ast.expr.math.Mul;
import org.uva.sea.ql.ast.expr.math.Sub;
import org.uva.sea.ql.ast.expr.type.BooleanType;
import org.uva.sea.ql.ast.expr.type.IntegerType;
import org.uva.sea.ql.ast.expr.type.MoneyType;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.expr.unary.NOT;
import org.uva.sea.ql.ast.expr.unary.Negative;
import org.uva.sea.ql.ast.expr.unary.Positive;
import org.uva.sea.ql.ast.visitors.QLDomainVisitor;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;
import org.uva.sea.ql.evalutor.BooleanValue;
import org.uva.sea.ql.evalutor.IntegerValue;
import org.uva.sea.ql.evalutor.MoneyValue;
import org.uva.sea.ql.evalutor.StringValue;
import org.uva.sea.ql.evalutor.Value;
import org.uva.sea.ql.gui.widget.QLQuestionText;
import org.uva.sea.ql.gui.widget.QLQuestionTextFeild;
import org.uva.sea.ql.gui.widget.QLRadioButton;
import org.uva.sea.ql.semantic.Message;
import org.uva.sea.ql.semantic.SymbolTable;

public class QLController
		implements QLNodeVisitor<Value>, QLDomainVisitor, QLSelectedQuesionListener, QLTextFeildQuesionListener {
	private QLView qLView;
	private ValuesReferenceTable identifierValues;
	private final Form qlAst;
	private Set<String> conditionId = new HashSet<String>();
	private boolean isInCondition = false;
	private final Message qlSemanticErrors;
	private final SymbolTable symbolTable;
	private final String currency = "USD";

	public QLController(Form qlAst, QLView qLView, Message qlSemanticErrors, SymbolTable symbolTable) {
		this.qlSemanticErrors = qlSemanticErrors;
		this.qlAst = qlAst;
		this.identifierValues = new ValuesReferenceTable();
		this.symbolTable = symbolTable;
		this.qLView = qLView;
		qlAst.accept(this);
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
		isInCondition = true;
		Value condition = statement.getCondition().accept(this,false);
		isInCondition = false;
		Value trueCondition = new BooleanValue(true);
		if (condition.equal(trueCondition).getBooleanValue()) {
			for (Question ibq : statement.getBody().getQuestions()) {

				ibq.accept(this);
			}

		}

	}

	@Override
	public void visit(Question question) {
		String qIdentifier = question.getVarIdentifierName();
		Type qType = question.getVarType();
		String qLable = question.getText();
		if (!this.identifierValues.questionAlreadyInReferenceTable(qIdentifier)) {
			addQuestionToQLView(qIdentifier, qType, qLable);
		}

	}

	@Override
	public void visit(ReadOnlyQuestion readOnlyQuestion) {
		String identifier = readOnlyQuestion.getVarIdentifierName();
		String text = readOnlyQuestion.getText();
		Type qType = readOnlyQuestion.getVarType();
		if (!this.identifierValues.questionAlreadyInReferenceTable(identifier)) {
			addReadOnlyQuestionToQLView(readOnlyQuestion, identifier, text, qType);
		}

	}
	
	private void addQuestionToQLView(String qIdentifier, Type qType, String qLable) {
		if (qType.equals(new BooleanType())) {
			Value btnState = getQLSelectedState(qIdentifier);
			identifierValues.addQLIdentifier(qIdentifier, btnState);
			addBooleanQuestionToQLView(qLable, qIdentifier, btnState.getBooleanValue());
		}
		if (qType.equals(new MoneyType())) {
			Value currentVal = getQLMoneyValue(qIdentifier);
			identifierValues.addQLIdentifier(qIdentifier, currentVal);
			addTextQuestionToQLView(qLable, qIdentifier, currentVal.toString(),true);
		}
		
		if (qType.equals(new IntegerType())) {
			Value intValue = getQLIntegerValue(qIdentifier);
			identifierValues.addQLIdentifier(qIdentifier, intValue);
			addTextQuestionToQLView(qLable, qIdentifier, intValue.getIntegerValue().toString(),true);
		}
	}

	private void addReadOnlyQuestionToQLView(ReadOnlyQuestion readOnlyQuestion, String identifier, String text,
			Type qType) {
		Value computed = readOnlyQuestion.getExpression().accept(this,false);
		identifierValues.addQLIdentifier(identifier, computed);
		if (qType.equals(new MoneyType())) {
			addTextQuestionToQLView(identifier, text, computed.getMoneyValue().toString(),false);
		}
		
		if (qType.equals(new IntegerType())) {
			addTextQuestionToQLView(identifier, text, computed.getIntegerValue().toString(),false);
		}
	}
	
	private void addTextQuestionToQLView(String qLable, String qIdentifier, String value, boolean isReadOnly) {
		QLQuestionText qLQuestionText = new QLQuestionText(qLable, qlSemanticErrors.hasQuestion(qLable));
		QLQuestionTextFeild qLQuestionTextFeild = new QLQuestionTextFeild(qIdentifier, value);
		qLQuestionTextFeild.addQLTextFeildQuesionListener(this);
		QLViewInputTextQuestion qLViewInputTextQuestion = new QLViewInputTextQuestion(qLQuestionText,qLQuestionTextFeild, isReadOnly);
		qLView.addQuestionView(qLViewInputTextQuestion);
	}
	

	private void addBooleanQuestionToQLView(String qLable, String qIdentifier, boolean btnState) {
		QLQuestionText qLQuestionText = new QLQuestionText(qLable,qlSemanticErrors.hasQuestion(qLable));
		QLRadioButton qLRadioButton = new QLRadioButton(qIdentifier, btnState);
		qLRadioButton.addQLSelectedQuesionListener(this);
		QLViewSelectQuestion qLViewSelectQuestion = new QLViewSelectQuestion(qLQuestionText,qLRadioButton);
		qLView.addQuestionView(qLViewSelectQuestion);
	}

	private Value getQLIntegerValue(String qIdentifier) {
		Value intValue = new IntegerValue(0);
		if (identifierValues.questionAlreadyInReferenceTableClone(qIdentifier)) {
			intValue = identifierValues.getQLValueClone(qIdentifier);
		}
		return intValue;
	}

	private Value getQLMoneyValue(String identifier) {
		Value currentVal = new MoneyValue(Money.parse(currency+" 0.00"));
		if (identifierValues.questionAlreadyInReferenceTableClone(identifier)) {
			currentVal = identifierValues.getQLValueClone(identifier);
		}
		return currentVal;
	}

	private Value getQLSelectedState(String identifier) {
		Value btnStateValue = new BooleanValue(false);
		if (identifierValues.questionAlreadyInReferenceTableClone(identifier)) {
			btnStateValue = identifierValues.getQLValueClone(identifier);
		}
		return btnStateValue;
	}

	@Override
	public Value visit(Add add, boolean context) {
		Value left = add.getFirstExpression().accept(this,context);
		Value right = add.getSecondExpression().accept(this,context);
		return left.add(right);
	}

	@Override
	public Value visit(AND and, boolean context) {
		Value left = and.getFirstExpression().accept(this,context);
		Value right = and.getSecondExpression().accept(this,context);
		return left.and(right);
	}

	@Override
	public Value visit(Div div, boolean context) {
		Value left = div.getFirstExpression().accept(this,context);
		Value right = div.getSecondExpression().accept(this,context);
		return left.div(right);
	}

	@Override
	public Value visit(Equal eq, boolean context) {
		Value left = eq.getFirstExpression().accept(this,context);
		Value right = eq.getSecondExpression().accept(this,context);
		return left.equal(right);
	}

	@Override
	public Value visit(GreaterOrEqual geq, boolean context) {
		Value left = geq.getFirstExpression().accept(this,context);
		Value right = geq.getSecondExpression().accept(this,context);
		return left.greaterOrEqual(right);
	}

	@Override
	public Value visit(GreaterThan gt, boolean context) {
		Value left = gt.getFirstExpression().accept(this,context);
		Value right = gt.getSecondExpression().accept(this,context);
		return left.greaterThan(right);
	}

	@Override
	public Value visit(SmallerOrEqual leq, boolean context) {
		Value left = leq.getFirstExpression().accept(this,context);
		Value right = leq.getSecondExpression().accept(this,context);
		return left.lessOrEqual(right);
	}

	@Override
	public Value visit(SmallerThan lt, boolean context) {
		Value left = lt.getFirstExpression().accept(this,context);
		Value right = lt.getSecondExpression().accept(this,context);
		return left.lessThan(right);
	}

	@Override
	public Value visit(Mul mul, boolean context) {
		Value left = mul.getFirstExpression().accept(this,context);
		Value right = mul.getSecondExpression().accept(this,context);
		return left.mul(right);
	}

	@Override
	public Value visit(NotEqual neq, boolean context) {
		Value left = neq.getFirstExpression().accept(this,context);
		Value right = neq.getSecondExpression().accept(this,context);
		return left.notEqual(right);
	}

	@Override
	public Value visit(Negative neg, boolean context) {
		Value e = neg.getExpression().accept(this,context);
		return e.negative();
	}

	@Override
	public Value visit(NOT not, boolean context) {
		Value e = not.getExpression().accept(this,context);
		return e.not();
	}

	@Override
	public Value visit(OR or, boolean context) {
		Value left = or.getFirstExpression().accept(this,context);
		Value right = or.getSecondExpression().accept(this,context);
		return left.or(right);
	}

	@Override
	public Value visit(Positive pos, boolean context) {
		Value e = pos.getExpression().accept(this,context);
		return e.positive();
	}

	@Override
	public Value visit(Sub sub, boolean context) {
		Value left = sub.getFirstExpression().accept(this,context);
		Value right = sub.getSecondExpression().accept(this,context);
		return left.sub(right);
	}

	@Override
	public Value visit(IntegerLiteral intLiteral, boolean context) {
		return new IntegerValue(intLiteral.getValue());
	}

	@Override
	public Value visit(BooleanLiteral boolLiteral, boolean context) {
		return new BooleanValue(boolLiteral.getValue());
	}

	@Override
	public Value visit(StringLiteral stringLiteral, boolean context) {
		return new StringValue(stringLiteral.getValue());
	}

	@Override
	public Value visit(MoneyLiteral moneyLiteral, boolean context) {
		return new MoneyValue(moneyLiteral.getValue());
	}

	@Override
	public Value visit(VarExpr varExpr, boolean context) {
		if (isInCondition) {
			conditionId.add(varExpr.getIdentifierName());
		}
		return identifierValues.getQLValue(varExpr.getIdentifierName());
	}

	@Override
	public void QLQuesionSelected(QLRadioButton qLRadioButton) {
		identifierValues.addQLIdentifier(qLRadioButton.getQlComponentName(), new BooleanValue(qLRadioButton.getQlComponentState()));
		if (conditionId.contains(qLRadioButton.getQlComponentName())) {
			updateQLView();
		}

	}

	@Override
	public void QLQuesionTextFeildInput(QLQuestionTextFeild qLQuestionTextFeild) {
		assert symbolTable.lookupType(qLQuestionTextFeild.getQlComponentName()) != null : "They symbol table should contains all the question type by now.";
		if(symbolTable.lookupType(qLQuestionTextFeild.getQlComponentName()).equals(new MoneyType())){
			Value inputVal = new MoneyValue(Money.parse(currency+" "+qLQuestionTextFeild.getQlComponentText()));
			identifierValues.addQLIdentifier(qLQuestionTextFeild.getQlComponentName(), inputVal);
		}
		if(symbolTable.lookupType(qLQuestionTextFeild.getQlComponentName()).equals(new IntegerType())){
			Value inputVal = new IntegerValue(Integer.parseInt(qLQuestionTextFeild.getQlComponentText()));
			identifierValues.addQLIdentifier(qLQuestionTextFeild.getQlComponentName(), inputVal);
		}
		
		updateQLView();

	}

	private void updateQLView() {
		identifierValues.identifierValuesClone();
		removeDataFromQLView();
		redrawQLview();
	}

	private void redrawQLview() {
		qlAst.accept(this);
		qLView.showQL();
	}

	private void removeDataFromQLView() {
		qLView.cleanQLView();
		identifierValues.clearIdentifierValues();
	}
	
	public void showQLview() {
		qLView.showQL();
	}


}
