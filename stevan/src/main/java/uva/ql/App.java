package uva.ql;

import uva.ql.ast.Form;
import uva.ql.gui.GUI;
import uva.ql.visitors.VisitorActionListenersToGUI;
import uva.ql.visitors.VisitorASTToGUI;

public class App {
	
	public static void main(String[] args) {
		String filePath;
		boolean internal = true;
		QL ql = null;
		Form form = null;
		GUI gui = null;

		if (args.length == 0) {
			filePath = "resources/default.ql";
		} else {
			filePath = args[0];
			internal = false;
		}
		
		ql = new QL(filePath, internal);
		
		try {
			form = (Form) ql.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Visit AST and build GUI
		gui = new GUI(form);
		VisitorASTToGUI astToGUI = new VisitorASTToGUI();
		astToGUI.visitForm(form, gui.getPanel());
		
		// Add Action/DocumentListeners to update computed fields.
		/*VisitorToActionListeners astToGUIListeners = new VisitorToActionListeners(gui);
		astToGUIListeners.visitForm(form);*/
	}

}