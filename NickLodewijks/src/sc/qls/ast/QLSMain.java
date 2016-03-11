package sc.qls.ast;

import java.io.File;
import java.io.IOException;

import sc.ql.SemanticAnalyser;
import sc.ql.ast.form.QLForm;
import sc.ql.ui.UIFactory;
import sc.ql.ui.UIQuestionnaire;
import sc.qls.ast.page.QLSStyleSheet;
import sc.qls.ui.QLSUIFactory;

public class QLSMain {

	public static void main(String[] args) throws IOException {
		QLSStyleSheet styleSheet;
		QLForm form;
		File inputFile;

		// inputFile = new File(args[0]);
		inputFile = new File("resources/Questionnaire.ql");

		form = QLForm.create(inputFile);
		new SemanticAnalyser().validateTypes(form);

		styleSheet = QLSStyleSheet.create(new File("resources/Stylesheet.qls"));

		createUI(form, styleSheet);
	}

	private static void createUI(QLForm form, QLSStyleSheet styleSheet) {
		UIQuestionnaire uiForm;

		UIFactory.set(new QLSUIFactory(styleSheet));

		uiForm = UIFactory.get().create(form);
		uiForm.show();
	}
}
