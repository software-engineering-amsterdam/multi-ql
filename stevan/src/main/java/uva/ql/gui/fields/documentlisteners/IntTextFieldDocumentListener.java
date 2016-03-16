package uva.ql.gui.fields.documentlisteners;

import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;

import uva.ql.gui.fields.IntTextField;

public class IntTextFieldDocumentListener extends AbstractDocumentListener {
	
	private IntTextField itf;
	private final JPanel panel;
	
	public IntTextFieldDocumentListener(final IntTextField itf, final JPanel panel) {
		this.itf = itf;
		this.panel = panel;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {}

	@Override
	public void insertUpdate(DocumentEvent e) {
		if (Integer.parseInt(itf.getText()) > 0) {
			enablePanel( panel, true);
		} else {
			enablePanel( panel, false);
			resetPanel(panel);
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {}

}
