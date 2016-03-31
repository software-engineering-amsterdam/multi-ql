package qlTest;

import java.io.IOException;

import org.junit.Test;

import ql.FormParser;
import ql.SemanticAnalyser;
import ql.ast.form.Form;
import ql.ast.visitor.Context;

public class DuplicateQuestionWithDifferentType {

	@Test
	public void test() throws IOException {
		Form form = FormParser.parseForm("testResources/DuplicateQuestionWithDifferentType.ql");
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		Context context = semanticAnalyser.getContext();
		assert(context.getIssueIterator().next() instanceof ql.issue.problem.DuplicateQuestionWithDifferentType);
	}
}
