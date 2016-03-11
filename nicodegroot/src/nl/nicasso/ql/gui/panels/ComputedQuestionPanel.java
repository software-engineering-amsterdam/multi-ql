package nl.nicasso.ql.gui.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

import nl.nicasso.ql.Evaluator;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.statements.ComputedQuestion;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.values.Value;

public class ComputedQuestionPanel extends Panel {
	
	private ComputedQuestion question;
	
	// Is 4 params too long? Should I use the ParamObject here?
	public ComputedQuestionPanel(ComputedQuestion q, QuestionField field, Value value, Expression condition, SymbolTable symbolTable) {
		 panel = new JPanel(new GridLayout(2,2));
		 this.condition = condition;
		 this.symbolTable = symbolTable;
		 this.question = q;
		 
		 addQuestionLabel(q);
		 panel.add(new JPanel());		 
		 addQuestionField(q, field, value);
	}
	
	@Override
	public void updatePanel() {
		// Visibility
		Evaluator evaluator = new Evaluator(symbolTable);
		Value visibility = condition.accept(evaluator);
		System.out.println("VALUE: "+visibility.getValue());
		setVisible((Boolean) visibility.getValue());
		
		// Expression
		Value questionValue = question.getExpr().accept(evaluator);
		field.setValue(questionValue.getValue());
	}
	
}