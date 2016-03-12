package nl.nicasso.ql.gui.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import nl.nicasso.ql.Evaluator;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.ast.statements.ComputedQuestion;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.stateTable.StateTable;
import nl.nicasso.ql.values.Value;

public class ComputedQuestionPanel extends Panel {
	
	private ComputedQuestion question;
	
	// Is 4 params too long? Should I use the ParamObject here?
	public ComputedQuestionPanel(ComputedQuestion q, QuestionField field, Value value, Expression condition, StateTable stateTable) {
		 panel = new JPanel(new GridLayout(2,2));
		 this.condition = condition;
		 this.stateTable = stateTable;
		 this.question = q;
		 
		 addQuestionLabel(q);
		 panel.add(new JPanel());		 
		 addQuestionField(q, field, value);
	}
	
	@Override
	public boolean fieldValueChanged() {
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
			//System.out.println("THIS CQ IS UPDATED!");
			updated = true;
		}
		
		field.setValue(questionValue.getValue());
		
		// SET VALUE IN STATETABLE
		//stateTable.addState();
		
		return updated;
	}
	
}