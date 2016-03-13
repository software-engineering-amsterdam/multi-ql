package eu.bankersen.kevin.ql;

import java.io.IOException;

import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.gui.QLgui;
import eu.bankersen.kevin.ql.gui.dialog.ErrorDialog;
import eu.bankersen.kevin.ql.gui.dialog.WarningDialog;
import eu.bankersen.kevin.ql.interpreter.Interpreter;
import eu.bankersen.kevin.ql.parser.ANTLRParseException;
import eu.bankersen.kevin.ql.parser.FormParser;
import eu.bankersen.kevin.ql.typechecker.FormChecker;
import eu.bankersen.kevin.ql.typechecker.FormCheckerException;

public final class App {

    private App() {
    }

    public static void main(String[] args) {
	String resource;

	// resource = "resources/Tax.form";
	resource = "resources/Tax2.form";
	// resource = "resources/Tax3.form";
	// resource = "resources/test.form";

	// Parse the form to an AST
	Form form;
	try {
	    FormParser parser = new FormParser(resource);
	    form = parser.getForm();
	} catch (ANTLRParseException e) {
	    form = null;
	    new ErrorDialog(e.getErrors());
	} catch (IOException e) {
	    form = null;
	    new ErrorDialog("File Reader Error", "Can't read the file " + resource);
	}

	// Build the context object (type-checking and symbol table)
	try {
	    new FormChecker(form);
	} catch (FormCheckerException e) {
	    if (!e.getWarnings().isEmpty()) {
		new WarningDialog(e.getWarnings());
	    }
	    if (!e.getErrors().isEmpty()) {
		new ErrorDialog(e.getErrors());
	    }
	}

	// Create the GUI
	Interpreter interp = new Interpreter(form);
	QLgui ui = new QLgui(form);

	// Link Listeners
	ui.addViewListener(interp);
	interp.addDataListener(ui);

	ui.setVisible(true);
    }
}
