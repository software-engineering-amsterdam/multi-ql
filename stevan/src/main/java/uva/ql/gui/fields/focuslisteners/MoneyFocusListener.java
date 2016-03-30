package uva.ql.gui.fields.focuslisteners;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.math.BigDecimal;

import javax.swing.SwingUtilities;

import uva.ql.ast.variables.VarMoney;
import uva.ql.gui.fields.MoneyTextField;

public class MoneyFocusListener extends FocusAdapter {

	private MoneyTextField mtf;
	private VarMoney var;
	
	public MoneyFocusListener(MoneyTextField mtf, VarMoney var) {
		this.mtf = mtf;
		this.var = var;
	}
	
	@Override
	public void focusGained(FocusEvent evt) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				mtf.selectAll();
			}
		});
	}
	
	@Override
	public void focusLost(FocusEvent evt) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				BigDecimal val = new BigDecimal(mtf.getText());
				String temp = String.format("%.2f", val);
				temp = temp.replace(",", ".");
				mtf.setText(temp);
				
				val = val.multiply(new BigDecimal("100"));
				var.setValue(val.intValue());
			}
		});
	}
}
