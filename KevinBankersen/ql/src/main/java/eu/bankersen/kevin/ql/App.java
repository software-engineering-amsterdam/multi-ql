package eu.bankersen.kevin.ql;

import java.io.IOException;

import com.esotericsoftware.minlog.Log;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.context.TypeChecker;
import eu.bankersen.kevin.ql.context.errors.ContextBuildException;
import eu.bankersen.kevin.ql.parser.ANTLRParseException;
import eu.bankersen.kevin.ql.parser.FormParser;
import eu.bankersen.kevin.ql.util.CustomLogger;
import eu.bankersen.kevin.ql.util.FileReader;

/**
 * Hello world!
 *
 */
public final class App {

    private App() {
    }

    public static void main(String[] args) {
	Log.INFO(); // Set log level
	Log.setLogger(new CustomLogger()); // Our custom logger.

	System.out.println("Welcome to the Questionnaire Language (QL)!\n");

	String form;

	String resource;
	
	//resource = "resources/Tax.form";
	resource = "resources/Tax2.form";
	//resource = "resources/Tax3.form";



	try { // Currently the top level so here we catch exceptions.
	    form = new FileReader().read(resource);
	} catch (IOException e) {
	    Log.error("Unable to read file, Terminating");
	    System.exit(1);
	    form = null;
	}

	FormParser parser;
	Form parsedForm;

	try {
	    parser = new FormParser(form);
	    parsedForm = parser.getForm();
	    Log.info("File Parsed");
	} catch (ANTLRParseException e) {
	    parsedForm = null;
	    e.getErrors().forEach(error -> Log.error("Line " + error[0] + ": " + error[1]));
	    Log.error("Parse Errors, Terminating!");
	    System.exit(1);
	}

	// Trying to type check.
	Log.info("Trying to build context");
	SymbolTable symbolTable;
	try {
	    symbolTable = new TypeChecker(parsedForm).getSymbolTable();
	} catch (ContextBuildException e) {
	    symbolTable = null;
	    e.getErrors().forEach(error -> Log.error(error.toString()));
	    Log.error("Parse Errors, Terminating!");
	    System.exit(1);
	}


	Log.info("\n\n***********************\n"
		+ "* Doing random  stuff *\n"
		+ "***********************\n");

	if (resource.equals("resources/Tax2.form")) {

	    Log.info("Starting State");
	    symbolTable = parsedForm.evalForm(symbolTable); // Initialize the form
	    Log.info(parsedForm.toString());

	    Log.info("Answer european: yes");
	    symbolTable.updateSymbol("European", new Boolean(true));
	    symbolTable = parsedForm.evalForm(symbolTable);
	    Log.info(parsedForm.toString());

	    Log.info("Answer name: Kevin");
	    symbolTable.updateSymbol("name", new String("Kevin"));
	    symbolTable = parsedForm.evalForm(symbolTable);
	    Log.info(parsedForm.toString());

	    Log.info("Answer age: 26");
	    symbolTable.updateSymbol("age", new Integer(26));
	    symbolTable = parsedForm.evalForm(symbolTable);
	    Log.info(parsedForm.toString());

	    Integer age = 4;
	    Log.info("Answer startSchool: " + age.toString());
	    symbolTable.updateSymbol("startSchool", age);
	    symbolTable = parsedForm.evalForm(symbolTable);
	    Log.info(parsedForm.toString());

	    Log.info(parsedForm.toString());
	}

    }
}
