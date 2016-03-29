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
	
	public ComputedQuestionPanel(ComputedQuestion q, QuestionField field, Value value, Expression condition, StateTable stateTable, Observer main) {
		 panel = new JPanel(new GridLayout(2,2));
		 this.main = main;
		 this.condition = condition;
		 this.stateTable = stateTable;
		 this.question = q;
		 
		 addQuestionLabel(q);		 
		 addQuestionField(q, field, value);
	}
	
	public boolean update() {
		boolean updated = false;
		
		Evaluator evaluator = new Evaluator();
		Value visibility = condition.accept(evaluator, stateTable);
		setVisible((Boolean) visibility.getValue());
		
		if ((Boolean) visibility.getValue() == true) {
			Value questionValue = question.getExpr().accept(evaluator, stateTable);
		
			if (!field.equalValues(questionValue)) {
				field.setValue(questionValue);
				updated = true;				
			}
			
			main.updateValueInStateTable(question.getIdentifier(), questionValue);
		}
		
		return updated;
	}
	
}