package org.uva.ql;

import java.io.File;
import java.io.IOException;

import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ui.UIFactory;
import org.uva.ql.ui.UIQuestionnaire;

public class QLMain {

	public static void main(String[] args) throws IOException {
		QLForm form;
		File inputFile;

		// inputFile = new File(args[0]);
		inputFile = new File("resources/Questionnaire.ql");

		form = QLForm.create(inputFile);
		new QLSemanticAnalyser().validateTypes(form);

		createUI(form);
	}

	private static void createUI(QLForm form) {
		UIQuestionnaire uiForm;

		uiForm = UIFactory.get().create(form);
		uiForm.show();
	}
}
