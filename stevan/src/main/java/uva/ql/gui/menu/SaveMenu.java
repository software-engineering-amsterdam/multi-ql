package uva.ql.gui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import uva.ql.ast.Form;
import uva.ql.gui.GuiToJSON;

public class SaveMenu implements ActionListener {

	JFileChooser fc;
	JFrame frame;
	Form form;
	
	public SaveMenu(JFileChooser fc, JFrame frame, Form form) {
		this.fc = fc;
		this.frame = frame;
		this.form = form;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		int returnVal = fc.showSaveDialog(frame);
		
		if ( returnVal == JFileChooser.APPROVE_OPTION ) {
			String fileName = fc.getSelectedFile().toString();
			fileName = (fileName.toLowerCase().endsWith(".json"))? fileName : fileName + ".json";
						
			GuiToJSON guiToJSON = new GuiToJSON(frame);
			
			try {
				FileWriter fw = new FileWriter(fileName);
				fw.write(guiToJSON.getJsonString());
				fw.flush();
				fw.close();
			} 
			catch (IOException e) {
				System.out.println("That is very unfortunate... " + e.getMessage());
			}	
		}
	}

}
