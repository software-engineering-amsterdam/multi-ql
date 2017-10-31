package ql2.ui;

import java.awt.TrayIcon.MessageType;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ql2.Context;
import ql2.conflict.Conflict;

public class ErrorDialog extends JFrame {

	private Context context;
	private JOptionPane jpane;
	
	public ErrorDialog(Context ctx) {
		this.context = ctx;
		this.jpane = new JOptionPane();
		render();
	}
	
	private void render() {
		jpane.showMessageDialog(this, getErrors(), "Errors", JOptionPane.ERROR_MESSAGE);
		
		System.exit(EXIT_ON_CLOSE);
	}
	
	private String getErrors() {
		StringBuilder build = new StringBuilder();
		build.append("Errors were detected: \n");
		
		for (Conflict c : context.getProblems()) {
			build.append(c.getErrorMsg() + "\n");
		}
		build.append("\n");
	
		if (context.getWarnings().size() > 0) {
			build.append("Warnings were detected: \n");
			for (Conflict c : context.getWarnings()) {
				build.append(c.getErrorMsg() + "\n");
			}
		}
		return build.toString();
	}
}
