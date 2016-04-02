package qlTest;

import java.io.IOException;

import org.junit.Test;

import ql.FormParser;
import ql.SemanticAnalyser;
import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.issue.Issue;
import ql.issue.problem.ReferenceToUndefinedQuestion;

public class UndefinedQuestion {

	@Test
	public void test() throws IOException {
		Form form = FormParser.parseForm("testResources/UndefinedQuestion.ql");
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		Context context = semanticAnalyser.getContext();
		Issue issue = context.getIssueIterator().next();
		assert(issue instanceof ReferenceToUndefinedQuestion);
	}

}
