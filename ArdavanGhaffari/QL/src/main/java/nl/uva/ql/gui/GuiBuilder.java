package nl.uva.ql.gui;

import java.util.LinkedList;
import java.util.List;

import nl.uva.ql.ast.Box;
import nl.uva.ql.ast.Form;
import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.ast.statement.ComputedQuestion;
import nl.uva.ql.ast.statement.IfElseStatement;
import nl.uva.ql.ast.statement.IfStatement;
import nl.uva.ql.ast.statement.Question;
import nl.uva.ql.ast.statement.Statement;
import nl.uva.ql.ast.type.BooleanType;
import nl.uva.ql.ast.type.IntegerType;
import nl.uva.ql.ast.type.MoneyType;
import nl.uva.ql.ast.type.StringType;
import nl.uva.ql.ast.type.UnknownType;
import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.gui.panel.BoxPanel;
import nl.uva.ql.gui.panel.ComputedQuestionPanel;
import nl.uva.ql.gui.panel.IfElsePanel;
import nl.uva.ql.gui.panel.IfPanel;
import nl.uva.ql.gui.panel.Panel;
import nl.uva.ql.gui.panel.QuestionPanel;
import nl.uva.ql.gui.widget.CheckBox;
import nl.uva.ql.gui.widget.NumericTextField;
import nl.uva.ql.gui.widget.StringTextField;
import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.FormVisitor;
import nl.uva.ql.visitors.StatementVisitor;
import nl.uva.ql.visitors.TypeVisitor;

public class GuiBuilder implements StatementVisitor<Panel>, TypeVisitor, FormVisitor{
	
	private Evaluator evaluator;
	private QLFrame frame;
	public GuiBuilder(){
		this.evaluator = new Evaluator();
		this.frame = new QLFrame();
	}

	@Override
	public void visit(Form form) {
		Panel panel = form.getBox().accept(this);
		frame.addToFrame(panel);
		frame.update(evaluator, null);
		frame.setVisible(true);
	}

	@Override
	public Panel visit(Box box) {
		List<Panel> panels = new LinkedList<Panel>();
		for(Statement statement: box.getStatements()){
			panels.add(statement.accept(this));
		}
		return new BoxPanel(panels);
	}

	@Override
	public Panel visit(Question question) {
		Widget widget = question.getType().accept(this);
		widget.setIdentifier(question.getIdentifier());
		String labelText = question.getLabel();
		return new QuestionPanel(widget, labelText);
	}

	@Override
	public Panel visit(ComputedQuestion computedQuestion) {
		Expression expression = computedQuestion.getExpression();
		Widget widget = computedQuestion.getType().accept(this);
		widget.setIdentifier(computedQuestion.getIdentifier());
		String labelText = computedQuestion.getLabel();
		return new ComputedQuestionPanel(widget, labelText, expression);
	}

	@Override
	public Panel visit(IfStatement ifStatement) {
		Expression expression = ifStatement.getExpression();
		Panel ifBoxPanel = ifStatement.getIfBox().accept(this);
		return new IfPanel(expression, ifBoxPanel);
	}

	@Override
	public Panel visit(IfElseStatement ifElseStatement) {
		Expression expression = ifElseStatement.getExpression();
		Panel ifBoxPanel = ifElseStatement.getIfBox().accept(this);
		Panel elseBoxPanel = ifElseStatement.getElseBox().accept(this);
		return new IfElsePanel(expression, ifBoxPanel, elseBoxPanel);
	}

	@Override
	public Widget visit(BooleanType booleanType) {
		return new CheckBox(evaluator, frame);
	}

	@Override
	public Widget visit(IntegerType integerType) {
		return new NumericTextField(evaluator, frame);
	}

	@Override
	public Widget visit(MoneyType moneyType) {
		return new NumericTextField(evaluator, frame);
	}

	@Override
	public Widget visit(StringType stringType) {
		return new StringTextField(evaluator, frame);
	}

	@Override
	public Widget visit(UnknownType unknownType) {
		return null;
	}

}
