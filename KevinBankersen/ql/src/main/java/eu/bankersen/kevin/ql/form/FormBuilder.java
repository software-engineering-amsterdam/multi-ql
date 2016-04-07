package eu.bankersen.kevin.ql.form;

import java.io.File;
import java.io.IOException;

import eu.bankersen.kevin.ql.form.analyzer.Analyzer;
import eu.bankersen.kevin.ql.form.analyzer.InvalidForm;
import eu.bankersen.kevin.ql.form.ast.Form;
import eu.bankersen.kevin.ql.form.parser.FormParser;
import eu.bankersen.kevin.ql.form.parser.ParseException;
import eu.bankersen.kevin.ql.gui.dialog.ErrorMessage;
import eu.bankersen.kevin.ql.gui.dialog.WarningMessage;

public class FormBuilder {

	public Form createForm(File file) {
		Form form = parseForm(file);
		checkForm(form);
		return form;
	}

	private Form parseForm(File resource) {
		try {
			return new FormParser(resource).getForm();
		} catch (ParseException e) {
			new ErrorMessage("Form Parsing Error", e.getErrors());
			return null;
		} catch (IOException e) {
			new ErrorMessage("File Reader Error", "Can't read the file " + resource);
			return null;
		}
	}

	private void checkForm(Form form) {
		try {
			new Analyzer(form);
		} catch (InvalidForm e) {
			if (e.getWarnings().hasNext()) {
				new WarningMessage(e.getWarnings());
			}
			if (e.getErrors().hasNext()) {
				new ErrorMessage("Invalid Form", e.getErrors());
			}
		}
	}

}
