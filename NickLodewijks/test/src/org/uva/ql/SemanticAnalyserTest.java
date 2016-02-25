package org.uva.ql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.uva.ql.QLSemanticAnalyser.Result;
import org.uva.ql.ast.form.QLQuestionnaire;

public class SemanticAnalyserTest {

	@Test
	public void testDuplicateQuestions() throws IOException {
		Result result;
		InputStream is;

		QLQuestionnaire questionnaire;

		is = SemanticAnalyserTest.class.getResourceAsStream("DuplicateQuestions.ql");

		questionnaire = QLQuestionnaire.create(is);

		result = new QLSemanticAnalyser().validateQuestions(questionnaire);

		assertNumberOfWarnings(result, 3);
		assertNumberOfErrors(result, 1);
	}

	@Test
	public void testDuplicateQuestionsNested() throws IOException {
		Result result;
		InputStream is;

		QLQuestionnaire questionnaire;

		is = SemanticAnalyserTest.class.getResourceAsStream("DuplicateQuestionsNested.ql");

		questionnaire = QLQuestionnaire.create(is);

		result = new QLSemanticAnalyser().validateQuestions(questionnaire);

		assertNumberOfWarnings(result, 4);
		assertNumberOfErrors(result, 2);
	}

	@Test
	public void testCyclicReferences() throws IOException {
		Result result;
		InputStream is;

		QLQuestionnaire questionnaire;

		is = SemanticAnalyserTest.class.getResourceAsStream("CyclicReferences.ql");

		questionnaire = QLQuestionnaire.create(is);

		result = new QLSemanticAnalyser().validateCyclicReferences(questionnaire);
		assertNumberOfErrors(result, 6);
	}

	private void assertNumberOfWarnings(Result result, int warnings) {
		Assert.assertEquals("Invalid number of warnings:\n" + Arrays.toString(result.getWarnings().toArray()) + "\n",
				warnings, result.getWarnings().size());
	}

	private void assertNumberOfErrors(Result result, int errors) {
		Assert.assertEquals("Invalid number of errors:\n" + Arrays.toString(result.getErrors().toArray()) + "\n",
				errors, result.getErrors().size());
	}
}
