package nl.nicasso.ql.gui.panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JPanel;

import nl.nicasso.ql.Evaluator;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.widgets.Label;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.values.UnknownValue;
import nl.nicasso.ql.values.Value;

public class QuestionPanel extends Panel {

	private Label feedback;
	
	public QuestionPanel(Question q, QuestionField field, Expression condition, SymbolTable symbolTable) {
		 panel = new JPanel(new GridLayout(2,2));
		 feedback = new Label("");
		 this.condition = condition;
		 this.symbolTable = symbolTable;
		 field.setFeedbackLabel(feedback);
		 
		 addQuestionLabel(q);
		 panel.add(new JPanel());		 
		 addQuestionField(q, field, q.getType().getDefaultValue());
		 addLabelForFeedback();
	}
	
	public void addLabelForFeedback() {
		feedback.setFont(new Font("Arial", 0, 100));
		feedback.setLabelColor(Color.RED);
		panel.add(feedback.getWidget());
	}	

}
