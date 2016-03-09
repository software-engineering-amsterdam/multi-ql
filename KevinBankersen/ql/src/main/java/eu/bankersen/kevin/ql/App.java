package eu.bankersen.kevin.ql;

import java.io.IOException;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.gui.QLgui;
import eu.bankersen.kevin.ql.gui.dialog.ErrorDialog;
import eu.bankersen.kevin.ql.gui.dialog.WarningDialog;
import eu.bankersen.kevin.ql.interpreter.Interpreter;
import eu.bankersen.kevin.ql.parser.ANTLRParseException;
import eu.bankersen.kevin.ql.parser.FormParser;
import eu.bankersen.kevin.ql.typechecker.TypeCheckException;
import eu.bankersen.kevin.ql.typechecker.TypeChecker;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;
import eu.bankersen.kevin.ql.util.CustomLogger;
import eu.bankersen.kevin.ql.util.FileReader;


public final class App {

    private App() {
    }

    public static void main(String[] args) {
	Log.INFO(); // Set log level
	Log.setLogger(new CustomLogger()); // Our custom logger.

	System.out.println("Welcome to the Questionnaire Language (QL)!\n");

	String resource;

	//resource = "resources/Tax.form";
	//resource = "resources/Tax2.form";
	resource = "resources/Tax3.form";

	// Read the file.
	String file;
	try { // Currently the top level so here we catch exceptions.
	    file = new FileReader().read(resource);
	} catch (IOException e) {
	    file = null;
	    new ErrorDialog("File Reader Error", "Can't read the file " + resource);
	}

	// Parse the form to an AST
	Form form;
	try {
	    FormParser parser = new FormParser(file);
	    form = parser.getForm();
	    Log.info("File Parsed");
	} catch (ANTLRParseException e) {
	    form = null;
	    new ErrorDialog(e.getErrors());
	}

	// Build the context object (type-checking and symbol table)
	SymbolTable symbolTable;
	try {
	    symbolTable = new TypeChecker(form).getSymbolTable();
	    Log.info("Build Context");
	} catch (TypeCheckException e) {
	    symbolTable = e.getSymbolTable();
	    if (!e.getWarnings().isEmpty()) {
		new WarningDialog(e.getWarnings());
	    }
	    if (!e.getErrors().isEmpty()) {
		new ErrorDialog(e.getErrors());
	    }
	}

	// Create the GUI
	Interpreter interp = new Interpreter(form, symbolTable);
	QLgui ui = new QLgui(form, symbolTable);

	// Link Listeners
	ui.addViewListener(interp);
	interp.addDataListener(ui);

	ui.setVisible(true);
    }
}
