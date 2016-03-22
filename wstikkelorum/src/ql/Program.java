package ql;

import java.io.IOException;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.gui.UserInterface;
import ql.issue.Issue;

public class Program {

	// TODO: exception handling
	public static void main(String[] args) throws IOException {
//		String path = "resources/CyclicExampleC.ql";
//		String path = "resources/SmallQuestionaire.ql";
		String path = "resources/Questionaire.ql";
		
		Form form = parseForm(path);
		SemanticAnalyser semanticAnalyser = analyseForm(form);

		if (semanticAnalyser.noIssues()) {
			drawForm(form, semanticAnalyser.getContext());
		} else {
			printIssues(semanticAnalyser.getContext());
		}
		System.out.println("Done");
	}

	private static void printIssues(Context context) {
		for (Issue issue : context.getIssues()) {
			issue.print();
		}
	}

	//TODO: load form from GUI?
	public static Form parseForm(String path) throws IOException {
		Form form = FormParser.parseForm(path, false);
		return form;
	}

	public static SemanticAnalyser analyseForm(Form form) {
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		semanticAnalyser.printData();
		return semanticAnalyser;
	}

	//TODO: only used in test and should be moved to the GUI
	public static FormEvaluation evaluateForm(Form form, Context context) {
		FormEvaluation formEval = new FormEvaluation(context);
		formEval.evaluateForm(form);
		formEval.printData();
		return formEval;
	}

	public static void drawForm(Form form, Context context) {
		new UserInterface(form, context);
	}
}
