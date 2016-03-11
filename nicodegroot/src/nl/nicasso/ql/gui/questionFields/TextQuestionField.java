package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldParameter;
import nl.nicasso.ql.gui.widgets.Label;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.values.StringValue;

public class TextQuestionField extends QuestionField {

	private Identifier identifier;
	private JTextField field;
	private SymbolTable symboltable;
	private Label label;
	private Observer main;

	public TextQuestionField(QuestionFieldParameter params) {
		this.identifier = params.getIdentifier();
		this.symboltable = params.getSymboltable();
		this.main = params.getMain();
		
		setupField(params.isEnabled());
	}
	
	private void setupField(boolean enabled) {
		field = new JTextField();
		field.setColumns(20);
		field.setEnabled(enabled);
		
		addListenerToField();
	}
	
	private void addListenerToField() {
		field.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				SymbolTableEntry entry = symboltable.getEntry(identifier);
				entry.setValue(new StringValue(field.getText()));
				main.updatePanel();
			}
			
		});
	}
	
	public void setValue(Object value) {
		field.setText((String) value);
	}
	
	public void setFeedbackLabel(Label label) {
		this.label = label;
	}
	
	public JTextField getField() {
		return this.field;
	}
	
}
