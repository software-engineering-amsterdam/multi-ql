package uva.ql.gui.fields.focuslisteners;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.SwingUtilities;

import uva.ql.ast.variables.VarInt;
import uva.ql.gui.fields.IntTextField;

public class IntFocusListener extends FocusAdapter {

	private IntTextField mtf;
	private VarInt var;
	
	public IntFocusListener(IntTextField mtf, VarInt var) {
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
				var.setValue(Integer.parseInt(mtf.getText()));
				System.out.println("IntTextField: " + var.getValue());
			}
		});
	}
}
