package org.uva.ql;

import java.io.File;
import java.io.IOException;

import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.domain.Questionnaire;
import org.uva.ql.domain.QuestionnaireFactory;
import org.uva.ql.ui.swing.UISwingFactory;

public class QLMain {

	public static void main(String[] args) throws IOException {
		Questionnaire qlQuestionnaire;
		QLQuestionnaire questionnaire;
		File inputFile;

		// resources/Questionaire.ql
		inputFile = new File(args[0]);
		questionnaire = QLQuestionnaire.create(inputFile);

		new QLSemanticAnalyser().validateTypes(questionnaire);

		qlQuestionnaire = QuestionnaireFactory.create(questionnaire);

		new UISwingFactory().create(qlQuestionnaire).show();
	}
}
