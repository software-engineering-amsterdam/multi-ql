package uva.TaxForm.GUI.ActionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import uva.TaxForm.GUI.GUI;
import uva.ql.QL;
import uva.ql.Visitors.ASTVisitorToGUI;
import uva.ql.ast.ASTForm;

public class LoadMenu implements ActionListener {

	JFileChooser fc;
	JFrame frame;
	GUI gui;
	
	public LoadMenu(JFileChooser fc, JFrame frame, GUI gui) {
		this.fc = fc;
		this.frame = frame;
		this.gui = gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		int returnVal = fc.showOpenDialog(frame);
		
		if ( returnVal == JFileChooser.APPROVE_OPTION ) {
			File file = fc.getSelectedFile();
			ASTForm root = null;
			QL taxForm = null;
			ASTVisitorToGUI astVisitor = null;
			
			taxForm = new QL(file.toPath().toString(), false);
			
			try {
				root = (ASTForm) taxForm.start();
				astVisitor = new ASTVisitorToGUI(gui.resetFrame());
				astVisitor.visit(root);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		} 
		else {
			//System.out.println("Cancelled");
		}
	}
}
