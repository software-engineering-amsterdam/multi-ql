package uva.ql.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import uva.ql.QL;
import uva.ql.gui.GUI;

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
						
			try {
				gui.disposeFrame();
				ql.start();
			} 
			catch (Exception e) {
				System.out.println("An inexplicable error occured... " + e.getMessage());
			}
		}
	}
}
