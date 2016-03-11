package org.uva.qls.ast;

import java.io.File;
import java.io.IOException;

import org.uva.ql.QLSemanticAnalyser;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ui.UIFactory;
import org.uva.ql.ui.UIQuestionnaire;
import org.uva.qls.ast.page.QLSStyleSheet;
import org.uva.qls.ui.QLSUIFactory;

public class QLSMain {

	public static void main(String[] args) throws IOException {
		QLSStyleSheet styleSheet;
		QLForm form;
		File inputFile;

		// inputFile = new File(args[0]);
		inputFile = new File("resources/Questionnaire.ql");

		form = QLForm.create(inputFile);
		new QLSemanticAnalyser().validateTypes(form);

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
