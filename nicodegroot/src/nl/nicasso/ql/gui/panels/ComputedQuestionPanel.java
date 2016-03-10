package nl.nicasso.ql.gui.panels;

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
import nl.nicasso.ql.values.Value;

public class ComputedQuestionPanel extends Panel implements Observer {

	private JPanel panel;
	private Expression condition;
	private SymbolTable symbolTable;
	
	// Is 4 params too long? Should I use the ParamObject here?
	public ComputedQuestionPanel(Question q, QuestionField field, Value value, Expression condition, SymbolTable symbolTable) {
		 panel = new JPanel(new GridLayout(2,2));
		 this.condition = condition;
		 this.symbolTable = symbolTable;
		 
		 addQuestionLabel(q);
		 panel.add(new JPanel());		 
		 addQuestionField(q, field, value);
	}
	
	public void addQuestionLabel(Question q) {
		Label questionLabel = new Label("CQ: "+q.getLabel());
		questionLabel.setFont(new Font("Arial", 0, 100));
		panel.add(questionLabel.getWidget());
	}
	
	public void addQuestionField(Question q, QuestionField field, Value value) {		
		field.setValue(value.getValue());
		panel.add(field.getField());
	}
	
	@Override
	public JPanel getPanel() {		
		return this.panel;
	}

	@Override
	public void updatePanel() {
		Evaluator evaluator = new Evaluator(symbolTable);
		Value value = condition.accept(evaluator);
		
	}
	
}