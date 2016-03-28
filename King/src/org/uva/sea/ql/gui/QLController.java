package org.uva.sea.ql.gui;

import java.util.HashSet;
import java.util.Set;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
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

public class QLController
		implements QLNodeVisitor<Value>, QLDomainVisitor, QLSelectedQuesionListener, QLTextFeildQuesionListener {
	private QLView qLView;
	private ValuesReferenceTable identifierValues;
	private final Form qlAst;
	private Set<String> conditionId = new HashSet<String>();
	private boolean isInCondition = false;
	private final Message qlSemanticErrors;

	public QLController(Form qlAst, QLView qLView, Message qlSemanticErrors) {
		this.qlSemanticErrors = qlSemanticErrors;
		this.qlAst = qlAst;
		this.identifierValues = new ValuesReferenceTable();
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
		Value condition = statement.getCondition().accept(this);
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
		String qIdentifier = question.getVariableId().getIdentifierName();
		Type qType = question.getVarType();
		String qLable = question.getText();
		if (!this.identifierValues.questionAlreadyInReferenceTable(qIdentifier)) {
			if (qType.equals(new BooleanType())) {
				addBooleanQuestionToQLView(qLable, qIdentifier);
			}
			if (qType.equals(new MoneyType())) {
				addMoneyQuestionToQLView(qLable, qIdentifier);
			}
		}

	}

	@Override
	public void visit(ReadOnlyQuestion readOnlyQuestion) {
		String identifier = readOnlyQuestion.getVariableId().getIdentifierName();
		String text = readOnlyQuestion.getText();
		if (!this.identifierValues.questionAlreadyInReferenceTable(identifier)) {
			Value computed = readOnlyQuestion.getExpression().accept(this);
			addReadOnlyQuestionToQLView(identifier, text, computed);
		}

	}

	private void addReadOnlyQuestionToQLView(String identifier, String qLable, Value computed) {
		identifierValues.addQLIdentifier(identifier, computed);
		QLViewInputTextQuestion qLViewInputTextQuestion = new QLViewInputTextQuestion(new QLQuestionText(qLable, qlSemanticErrors.hasQuestion(qLable)),
				new QLQuestionTextFeild(identifier, computed.getMoneyValue().toString()), false);
		qLView.addQuestionView(qLViewInputTextQuestion);
	}
	
	private void addBooleanQuestionToQLView(String qLable, String qIdentifier) {
		boolean btnState = getQLSelectedState(qIdentifier);
		QLViewSelectQuestion qLViewSelectQuestion = new QLViewSelectQuestion(new QLQuestionText(qLable,qlSemanticErrors.hasQuestion(qLable)),
				new QLRadioButton(qIdentifier, btnState));
		qLView.addQuestionView(qLViewSelectQuestion);
		identifierValues.addQLIdentifier(qIdentifier, new BooleanValue(btnState));
	}

	private void addMoneyQuestionToQLView(String qLable, String qIdentifier) {
		Value currentVal = getQLMoneyValue(qIdentifier);
		QLViewInputTextQuestion qLViewInputTextQuestion = new QLViewInputTextQuestion(new QLQuestionText(qLable,qlSemanticErrors.hasQuestion(qLable)),
				new QLQuestionTextFeild(qIdentifier, currentVal.getMoneyValue().toString()), true);
		qLView.addQuestionView(qLViewInputTextQuestion);
		identifierValues.addQLIdentifier(qIdentifier, currentVal);
	}

	private Value getQLMoneyValue(String identifier) {
		Value currentVal = new MoneyValue(Money.parse("USD 0.00"));
		if (identifierValues.questionAlreadyInReferenceTableClone(identifier)) {
			currentVal = identifierValues.getQLValueClone(identifier);
		}
		return currentVal;
	}

	private boolean getQLSelectedState(String identifier) {
		boolean btnState = false;
		if (identifierValues.questionAlreadyInReferenceTableClone(identifier)) {
			btnState = identifierValues.getQLValueClone(identifier).getBooleanValue();
		}
		return btnState;
	}

	@Override
	public Value visit(Add add) {
		Value left = add.getFirstExpression().accept(this);
		Value right = add.getSecondExpression().accept(this);
		return left.add(right);
	}

	@Override
	public Value visit(AND and) {
		Value left = and.getFirstExpression().accept(this);
		Value right = and.getSecondExpression().accept(this);
		return left.and(right);
	}

	@Override
	public Value visit(Div div) {
		Value left = div.getFirstExpression().accept(this);
		Value right = div.getSecondExpression().accept(this);
		return left.div(right);
	}

	@Override
	public Value visit(Equal eq) {
		Value left = eq.getFirstExpression().accept(this);
		Value right = eq.getSecondExpression().accept(this);
		return left.equal(right);
	}

	@Override
	public Value visit(GreaterOrEqual geq) {
		Value left = geq.getFirstExpression().accept(this);
		Value right = geq.getSecondExpression().accept(this);
		return left.greaterOrEqual(right);
	}

	@Override
	public Value visit(GreaterThan gt) {
		Value left = gt.getFirstExpression().accept(this);
		Value right = gt.getSecondExpression().accept(this);
		return left.greaterThan(right);
	}

	@Override
	public Value visit(SmallerOrEqual leq) {
		Value left = leq.getFirstExpression().accept(this);
		Value right = leq.getSecondExpression().accept(this);
		return left.lessOrEqual(right);
	}

	@Override
	public Value visit(SmallerThan lt) {
		Value left = lt.getFirstExpression().accept(this);
		Value right = lt.getSecondExpression().accept(this);
		return left.lessThan(right);
	}

	@Override
	public Value visit(Mul mul) {
		Value left = mul.getFirstExpression().accept(this);
		Value right = mul.getSecondExpression().accept(this);
		return left.mul(right);
	}

	@Override
	public Value visit(NotEqual neq) {
		Value left = neq.getFirstExpression().accept(this);
		Value right = neq.getSecondExpression().accept(this);
		return left.notEqual(right);
	}

	@Override
	public Value visit(Negative neg) {
		Value e = neg.getExpression().accept(this);
		return e.negative();
	}

	@Override
	public Value visit(NOT not) {
		Value e = not.getExpression().accept(this);
		return e.not();
	}

	@Override
	public Value visit(OR or) {
		Value left = or.getFirstExpression().accept(this);
		Value right = or.getSecondExpression().accept(this);
		return left.or(right);
	}

	@Override
	public Value visit(Positive pos) {
		Value e = pos.getExpression().accept(this);
		return e.positive();
	}

	@Override
	public Value visit(Sub sub) {
		Value left = sub.getFirstExpression().accept(this);
		Value right = sub.getSecondExpression().accept(this);
		return left.sub(right);
	}

	@Override
	public Value visit(IntegerLiteral intLiteral) {
		return new IntegerValue(intLiteral.getValue());
	}

	@Override
	public Value visit(BooleanLiteral boolLiteral) {
		return new BooleanValue(boolLiteral.getValue());
	}

	@Override
	public Value visit(StringLiteral stringLiteral) {
		return new StringValue(stringLiteral.getValue());
	}

	@Override
	public Value visit(MoneyLiteral moneyLiteral) {
		return new MoneyValue(moneyLiteral.getValue());
	}

	@Override
	public Value visit(VarExpr varExpr) {
		if (isInCondition) {
			conditionId.add(varExpr.getIdentifier().getName());
		}
		return identifierValues.getQLValue(varExpr.getIdentifierName());
	}

	@Override
	public void QLQuesionSelected(JRadioButton btn, boolean isSelected) {
		identifierValues.addQLIdentifier(btn.getName(), new BooleanValue(isSelected));
		if (conditionId.contains(btn.getName())) {
			updateQLView();
		}

	}

	@Override
	public void QLQuesionTextFeildInput(JTextField textInput) {
		Value inputVal = new MoneyValue(Money.parse(textInput.getText()));
		identifierValues.addQLIdentifier(textInput.getName(), inputVal);
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


}
