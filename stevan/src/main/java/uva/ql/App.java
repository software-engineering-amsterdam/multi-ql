package uva.ql;

import uva.TaxForm.GUI.GUI;
import uva.ql.ast.Form;
import uva.ql.deprecated.ASTVisitorToGUI;
import uva.ql.deprecated.ASTVisitorToGUIListeners;

public class App {
	
	public static void main(String[] args) {
		String filePath;
		boolean internal = true;
		QL ql = null;
		Form root = null;
		GUI gui = null;

		if (args.length == 0) {
			filePath = "resources/default.ql";
		} else {
			filePath = args[0];
			internal = false;
		}
		
		ql = new QL(filePath, internal);
		
		try {
			root = (Form) ql.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		//Visit AST and build GUI
		gui = new GUI(root);
		ASTVisitorToGUI astToGUI = new ASTVisitorToGUI(gui);
		astToGUI.visit(root);
		
		// Add Action/DocumentListeners to update computed fields.
		ASTVisitorToGUIListeners astToGUIListeners = new ASTVisitorToGUIListeners(gui);
		astToGUIListeners.visit(root);
		*/
	}

}