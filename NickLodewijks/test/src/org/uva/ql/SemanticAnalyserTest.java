package org.uva.ql;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Test;
import org.uva.ql.SemanticAnalyser.Result;
import org.uva.ql.ast.form.Questionnaire;

import org.junit.Assert;

public class SemanticAnalyserTest {

	@Test
	public void testDuplicateQuestions() throws IOException {
		Result result;
		InputStream is;

		Questionnaire questionnaire;

		is = SemanticAnalyserTest.class.getResourceAsStream("DuplicateQuestions.ql");

		questionnaire = Questionnaire.create(is);

		result = new SemanticAnalyser().validateQuestions(questionnaire);

		Assert.assertEquals("Invalid number of warnings:" + Arrays.toString(result.getWarnings().toArray()), 3,
				result.getWarnings().size());
		Assert.assertEquals("Invalid number of errors: " + Arrays.toString(result.getErrors().toArray()), 1,
				result.getErrors().size());
	}

	@Test
	public void testDuplicateQuestionsNested() throws IOException {
		Result result;
		InputStream is;

		Questionnaire questionnaire;

		is = SemanticAnalyserTest.class.getResourceAsStream("DuplicateQuestionsNested.ql");

		questionnaire = Questionnaire.create(is);

		result = new SemanticAnalyser().validateQuestions(questionnaire);

		Assert.assertEquals("Invalid number of warnings: " + Arrays.toString(result.getWarnings().toArray()), 4,
				result.getWarnings().size());
		Assert.assertEquals("Invalid number of errors: " + Arrays.toString(result.getErrors().toArray()), 2,
				result.getErrors().size());
	}

}
