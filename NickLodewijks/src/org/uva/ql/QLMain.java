package org.uva.ql;

import java.io.File;
import java.io.IOException;

import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ui.UIFactory;
import org.uva.ql.ui.UIQuestionnaire;
import org.uva.ql.ui.swing.DefaultUISwingFactory;

public class QLMain {

	public static void main(String[] args) throws IOException {
		QLForm questionnaire;
		File inputFile;

		// resources/Questionnaire.ql
		inputFile = new File(args[0]);
		questionnaire = QLForm.create(inputFile);

		new QLSemanticAnalyser().validateTypes(questionnaire);

		createUI(questionnaire);
	}

	private static void createUI(QLForm form) {
		UIQuestionnaire uiQuestionnaire;
		UIFactory factory;

		factory = new DefaultUISwingFactory();
		uiQuestionnaire = factory.create(form);

		uiQuestionnaire.show();
	}
}
