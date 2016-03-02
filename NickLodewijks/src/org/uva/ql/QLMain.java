package org.uva.ql;

import java.io.File;
import java.io.IOException;

import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.domain.Questionnaire;
import org.uva.ql.domain.QuestionnaireFactory;
import org.uva.ql.ui.UIFactory;
import org.uva.ql.ui.UIQuestionnaire;
import org.uva.ql.ui.swing.DefaultUISwingFactory;

public class QLMain {

	public static void main(String[] args) throws IOException {
		Questionnaire qlQuestionnaire;
		QLQuestionnaire questionnaire;
		File inputFile;

		// resources/Questionnaire.ql
		inputFile = new File(args[0]);
		questionnaire = QLQuestionnaire.create(inputFile);

		new QLSemanticAnalyser().validateTypes(questionnaire);

		qlQuestionnaire = QuestionnaireFactory.create(questionnaire);

		createUI(qlQuestionnaire);
	}

	private static void createUI(Questionnaire questionnaire) {
		UIQuestionnaire uiQuestionnaire;
		UIFactory factory;

		factory = new DefaultUISwingFactory();
		uiQuestionnaire = factory.create(questionnaire);

		uiQuestionnaire.show();
	}
}
