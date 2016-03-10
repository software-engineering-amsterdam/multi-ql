package nl.nicasso.ql.gui.questionFields;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.values.BooleanValue;

public class BooleanQuestionField extends QuestionField {

	private Identifier identifier;
	private JCheckBox field;
	private SymbolTable symboltable;

	public BooleanQuestionField(Identifier identifier, SymbolTable symboltable) {
		this.identifier = identifier;
		this.symboltable = symboltable;
		setupField();
	}
	
	private void setupField() {
		field = new JCheckBox();
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
			}
		});
	}
	
	public void setValue(Object value) {
		field.setSelected((Boolean) value);
	}
	
	public JCheckBox getField() {
		return this.field;
	}	
}