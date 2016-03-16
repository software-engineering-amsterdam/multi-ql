package nl.nicasso.ql.gui.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.evaluator.Evaluator;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.questionFields.QuestionField;

public class ComputedQuestionPanel extends Panel {
	
	private ComputedQuestion question;
	private Observer main;
	
	// Is 4 params too long? Should I use the ParamObject here?
	public ComputedQuestionPanel(ComputedQuestion q, QuestionField field, Value value, Expression condition, StateTable stateTable, Observer main) {
		 panel = new JPanel(new GridLayout(2,2));
		 this.main = main;
		 this.condition = condition;
		 this.stateTable = stateTable;
		 this.question = q;
		 
		 addQuestionLabel(q);
		 panel.add(new JPanel());		 
		 addQuestionField(q, field, value);
	}
	
	public boolean update() {
		boolean updated = false;
		
		// Visibility
		Evaluator evaluator = new Evaluator(stateTable);
		Value visibility = condition.accept(evaluator);
		//System.out.println("VISIBLITY: "+visibility.getValue());
		setVisible((Boolean) visibility.getValue());
		
		if (panel.isVisible() != (Boolean) visibility.getValue()) {
			//updated = true;
		}
		
		// Expression
		Value questionValue = question.getExpr().accept(evaluator);
		
		if (!field.equalValues(questionValue.getValue())) {
			//updateAllPanels();
			main.fieldValueChanged(question.getId(), questionValue);
			updated = true;
		}
		
		field.setValue(questionValue.getValue());
		
		// SET VALUE IN STATETABLE
		//stateTable.addState();
		
		return updated;
	}
	
}