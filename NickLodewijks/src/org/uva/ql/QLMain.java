package org.uva.ql;

import java.io.File;
import java.io.IOException;

import org.uva.ql.ast.check.SemanticAnalyser;
import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.ui.QLASTToUIVisitor;
import org.uva.ql.ui.QLQuestionaire;
import org.uva.ql.ui.swing.SwingUIFactory;

public class QLMain {

	public static void main(String[] args) throws IOException {
		QLQuestionaire qlQuestionnaire;
		Questionnaire questionnaire;
		QLASTToUIVisitor qlInterpreter;
		File inputFile;

		inputFile = new File("resources/Questionaire.ql");
		questionnaire = Questionnaire.create(inputFile);

		new SemanticAnalyser().validateTypes(questionnaire);

		qlInterpreter = new QLASTToUIVisitor();

		qlQuestionnaire = qlInterpreter.interpret(questionnaire);

		new SwingUIFactory().create(qlQuestionnaire).show();
	}
}
