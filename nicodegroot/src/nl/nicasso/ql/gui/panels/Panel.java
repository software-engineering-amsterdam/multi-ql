package nl.nicasso.ql.gui.panels;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javax.swing.JPanel;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.statements.Question;
import nl.nicasso.ql.gui.evaluator.Evaluator;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.widgets.LabelWidget;
import nl.nicasso.ql.gui.widgets.Widget;

public abstract class Panel {

	protected JPanel panel;
	protected StateTable stateTable;
	protected List<Expression> conditions;
	protected QuestionField field;

	protected void addQuestionLabel(Question question) {
		Widget questionLabel = new LabelWidget(question.getLabel());
		questionLabel.addSelfToPanel(panel);
	}

	protected void indexVisiblityConditions(Stack<Expression> conditions) {
		Iterator<Expression> it = conditions.iterator();
		while (it.hasNext()) {
			this.conditions.add(it.next());
		}
	}

	public JPanel getPanel() {
		return this.panel;
	}
	
	protected boolean checkPanelVisibilityConditions() {
		boolean visible = true;
		
		Iterator<Expression> it = conditions.iterator();
		while (it.hasNext()) {
			Value visibility = it.next().accept(new Evaluator(), stateTable);
			if (!((Boolean) visibility.getValue())) {
				visible = false;
				break;
			}
		}

		panel.setVisible(visible);
		panel.updateUI();
		
		return visible;
	}

	protected void addQuestionField(Question q, QuestionField field, Value value) {
		this.field = field;

		field.updateValueAndTextfield(value);
		
		addWidgetToPanel(field.getField());
	}
	
	private void addWidgetToPanel(Widget widget) {
		widget.addSelfToPanel(panel);
	}

	abstract public boolean update();

}