package sc.qls;

import java.io.File;
import java.io.IOException;

import sc.ql.SemanticAnalyser;
import sc.ql.ast.Form;
import sc.ql.ui.UIFactory;
import sc.ql.ui.UIQuestionnaire;
import sc.qls.ast.StyleSheet;
import sc.qls.ui.QLSUIFactory;

public class Main {

	public static void main(String[] args) throws IOException {
		StyleSheet styleSheet;
		Form form;
		File inputFile;

		// inputFile = new File(args[0]);
		inputFile = new File("resources/Questionnaire.ql");

		form = Form.create(inputFile);
		new SemanticAnalyser().validateTypes(form);

		styleSheet = StyleSheet.create(new File("resources/Stylesheet.qls"));

		createUI(form, styleSheet);
	}

	private static void createUI(Form form, StyleSheet styleSheet) {
		UIQuestionnaire uiForm;

		UIFactory.set(new QLSUIFactory(styleSheet));

		uiForm = UIFactory.get().create(form);
		uiForm.show();
	}
}
