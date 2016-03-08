package eu.bankersen.kevin.ql.gui.dialog;

import java.util.List;

import javax.swing.JOptionPane;

public class ErrorDialog<T> {
    
    public ErrorDialog(String errorType, String message) {
	showError(errorType, message);
    }
    
    public ErrorDialog(List<T> list) {
	StringBuilder sb = new StringBuilder();
	list.forEach(error -> sb.append(error + "\n"));
	showError("Errors!", sb.toString());
    }


    private void showError(String errorType, String message) {
	JOptionPane.showMessageDialog(
		    null,
		    message,
		    errorType,
		    JOptionPane.ERROR_MESSAGE);
	System.exit(1);
    }
}
