package nl.nicasso.ql.gui.questionFields;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.JTextField;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.values.MoneyValue;

public class MoneyQuestionField extends QuestionField {

	private Identifier identifier;
	private JTextField field;
	private SymbolTable symboltable;

	public MoneyQuestionField(Identifier identifier, SymbolTable symboltable) {
		this.identifier = identifier;
		this.symboltable = symboltable;

		setupField();	
	}
	
	private void setupField() {
		field = new JTextField();
		field.setColumns(20);
		
		addListenerToField();
	}
	
	private void addListenerToField() {
		field.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent e) {
				SymbolTableEntry entry = symboltable.getEntry(identifier);
				entry.setValue(new MoneyValue(BigDecimal.valueOf(Double.parseDouble(field.getText()))));
			}
			
		});
	}
	
	public void setValue(Object value) {
		field.setText(value.toString());
	}
	
	public JTextField getField() {
		return this.field;
	}
	
}