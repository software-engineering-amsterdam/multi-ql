package sc.ql;

import java.io.File;
import java.io.IOException;

import sc.ql.ast.form.QLForm;
import sc.ql.ui.UIFactory;
import sc.ql.ui.UIQuestionnaire;

public class Main {

	public static void main(String[] args) throws IOException {
		QLForm form;
		File inputFile;

		// inputFile = new File(args[0]);
		inputFile = new File("resources/Questionnaire.ql");

		form = QLForm.create(inputFile);
		new SemanticAnalyser().validateTypes(form);

		createUI(form);
	}

	private static void createUI(QLForm form) {
		UIQuestionnaire uiForm;

		uiForm = UIFactory.get().create(form);
		uiForm.show();
	}
}
