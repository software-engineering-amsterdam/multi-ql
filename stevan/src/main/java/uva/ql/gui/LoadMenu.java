package uva.ql.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import uva.ql.QL;
import uva.ql.ast.Form;
import uva.ql.visitors.VisitorASTToGUI;

public class LoadMenu implements ActionListener {

	private JFileChooser fc;
	private JFrame frame;
	private GUI gui;
	
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
			QL ql = new QL(file.toPath().toString(), false);
			Form form = null;
						
			try {
				form = (Form) ql.start();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			
			VisitorASTToGUI astToGUI = new VisitorASTToGUI(this.frame);
			gui.resetFrame();
			astToGUI.visitForm(form, gui.getPanel());
		} 
		else {
			//System.out.println("Cancelled");
		}
	}
}
