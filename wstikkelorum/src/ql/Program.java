package ql;

import java.io.IOException;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.issue.Issue;

public class Program {

	// TODO: exception handling
	public static void main(String[] args) throws IOException {
		Form form = parseForm();
		SemanticAnalyser semanticAnalyser = analyseForm(form);

		if (semanticAnalyser.noIssues()) {
			FormEvaluation formEval = evaluateForm(form, semanticAnalyser);
			drawForm(form, formEval);
		} else {
			printIssues(semanticAnalyser);
		}
		System.out.println("Done");
	}

	private static void printIssues(SemanticAnalyser semanticAnalyser) {
		for (Issue issue : semanticAnalyser.getContext().getIssues()) {
			issue.print();
		}
	}

	private static Form parseForm() throws IOException {
		FormParser formParser = new FormParser();
//		Form form = formParser.parseForm("resources/CyclicExampleC.ql", false);
//		Form form = formParser.parseForm("resources/SmallQuestionaire.ql", false);
		Form form = formParser.parseForm("resources/Questionaire.ql", false);
		return form;
	}

	private static SemanticAnalyser analyseForm(Form form) {
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		semanticAnalyser.printData();
		return semanticAnalyser;
	}

	private static FormEvaluation evaluateForm(Form form,
			SemanticAnalyser semanticAnalyser) {
		FormEvaluation formEval = new FormEvaluation(
				semanticAnalyser.getContext());
		formEval.evaluateForm(form);
		formEval.printData();
		return formEval;
	}

	private static void drawForm(Form form, FormEvaluation formEval) {
		Context currentContext = formEval.getContext();
		QLdrawer qld = new QLdrawer(form, currentContext);
		qld.drawForm();
	}
}
