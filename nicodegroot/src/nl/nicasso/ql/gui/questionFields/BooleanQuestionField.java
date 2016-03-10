package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldParameter;
import nl.nicasso.ql.gui.widgets.Label;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.values.BooleanValue;

public class BooleanQuestionField extends QuestionField {

	private Identifier identifier;
	private JCheckBox field;
	private SymbolTable symboltable;
	private Label label;
	private Observer main;

	public BooleanQuestionField(QuestionFieldParameter params) {
		this.identifier = params.getIdentifier();
		this.symboltable = params.getSymboltable();
		this.main = params.getMain();
		
		setupField(params.isEnabled());
	}
	
	private void setupField(boolean enabled) {
		field = new JCheckBox();
		field.setEnabled(enabled);
		
		addListenerToField();
	}
	
	private void addListenerToField() {
		field.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				SymbolTableEntry entry = symboltable.getEntry(identifier);
				if (e.getStateChange() == ItemEvent.SELECTED) {
					entry.setValue(new BooleanValue(true));
				} else {
					entry.setValue(new BooleanValue(false));
				}
				main.updatePanel();
			}
		});
	}
	
	public void setValue(Object value) {
		field.setSelected((Boolean) value);
	}
	
	public void setFeedbackLabel(Label label) {
		this.label = label;
	}
	
	public JCheckBox getField() {
		return this.field;
	}	
}