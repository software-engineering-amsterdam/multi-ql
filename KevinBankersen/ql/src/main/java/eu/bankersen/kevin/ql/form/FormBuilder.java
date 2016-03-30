package eu.bankersen.kevin.ql.form;

import java.io.IOException;

import eu.bankersen.kevin.ql.form.ast.form.Form;
import eu.bankersen.kevin.ql.form.parser.ANTLRParseException;
import eu.bankersen.kevin.ql.form.parser.FormParser;
import eu.bankersen.kevin.ql.form.typechecker.FormChecker;
import eu.bankersen.kevin.ql.form.typechecker.FormCheckerException;
import eu.bankersen.kevin.ql.gui.dialog.ErrorDialog;
import eu.bankersen.kevin.ql.gui.dialog.WarningDialog;

public class FormBuilder {

    public Form createForm(String resource) {
	Form form = parseForm(resource);
	checkForm(form);
	return form;
    }

    private Form parseForm(String resource) {
	try {
	    return new FormParser(resource).getForm();
	} catch (ANTLRParseException e) {
	    new ErrorDialog(e.getErrors());
	    return null;
	} catch (IOException e) {
	    new ErrorDialog("File Reader Error", "Can't read the file " + resource);
	    return null;
	}
    }

    private void checkForm(Form form) {
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
    }

}
