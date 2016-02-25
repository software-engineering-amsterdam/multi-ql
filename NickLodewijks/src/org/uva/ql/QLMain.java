package org.uva.ql;

import java.io.File;
import java.io.IOException;

import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.domain.QLQuestionaire;
import org.uva.ql.ui.QLASTToUIVisitor;
import org.uva.ql.ui.swing.SwingUIFactory;

public class QLMain {

	public static void main(String[] args) throws IOException {
		QLQuestionaire qlQuestionnaire;
		Questionnaire questionnaire;
		File inputFile;

		// resources/Questionaire.ql
		inputFile = new File(args[0]);
		questionnaire = Questionnaire.create(inputFile);

		new QLSemanticAnalyser().validateTypes(questionnaire);

		qlQuestionnaire = QLASTToUIVisitor.create(questionnaire);

		new SwingUIFactory().create(qlQuestionnaire).show();
	}
}
