package org.uva.ql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.uva.ql.SemanticAnalyser.Result;
import org.uva.ql.ast.form.Questionnaire;

public class SemanticAnalyserTest {

	@Test
	public void testDuplicateQuestions() throws IOException {
		Result result;
		InputStream is;

		Questionnaire questionnaire;

		is = SemanticAnalyserTest.class.getResourceAsStream("DuplicateQuestions.ql");

		questionnaire = Questionnaire.create(is);

		result = new SemanticAnalyser().validateQuestions(questionnaire);

		assertNumberOfWarnings(result, 3);
		assertNumberOfErrors(result, 1);
	}

	@Test
	public void testDuplicateQuestionsNested() throws IOException {
		Result result;
		InputStream is;

		Questionnaire questionnaire;

		is = SemanticAnalyserTest.class.getResourceAsStream("DuplicateQuestionsNested.ql");

		questionnaire = Questionnaire.create(is);

		result = new SemanticAnalyser().validateQuestions(questionnaire);

		assertNumberOfWarnings(result, 4);
		assertNumberOfErrors(result, 2);
	}

	@Test
	public void testCyclicReferences() throws IOException {
		Result result;
		InputStream is;

		Questionnaire questionnaire;

		is = SemanticAnalyserTest.class.getResourceAsStream("CyclicReferences.ql");

		questionnaire = Questionnaire.create(is);

		result = new SemanticAnalyser().validateCyclicReferences(questionnaire);
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
