package org.uva.ql;

import java.io.IOException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.uva.ql.QLSemanticAnalyser.Result;
import org.uva.ql.ast.form.QLQuestionnaire;

public class SemanticAnalyserTest {

	@Test
	public void testDuplicateQuestions() throws IOException {
		Result result;

		result = new QLSemanticAnalyser().validate(createQuestionnaire("DuplicateQuestions.ql"));

		result.print();
		assertNumberOfWarnings(result, 4);
		assertNumberOfErrors(result, 2);
	}

	@Test
	public void testDuplicateQuestionsNested() throws IOException {
		Result result;

		result = new QLSemanticAnalyser().validate(createQuestionnaire("DuplicateQuestionsNested.ql"));

		result.print();
		assertNumberOfWarnings(result, 4);
		assertNumberOfErrors(result, 2);
	}

	@Test
	public void testCyclicReferences() throws IOException {
		Result result;

		result = new QLSemanticAnalyser().validateCyclicReferences(createQuestionnaire("CyclicReferences.ql"));

		result.print();
		assertNumberOfErrors(result, 6);
		assertNumberOfWarnings(result, 0);
	}

	@Test
	public void testValidQuestions() throws IOException {
		Result result;

		result = new QLSemanticAnalyser().validate(createQuestionnaire("ValidQuestions.ql"));

		result.print();
		assertNumberOfErrors(result, 0);
		assertNumberOfWarnings(result, 0);
	}

	private QLQuestionnaire createQuestionnaire(String fileName) throws IOException {
		return QLQuestionnaire.create(SemanticAnalyserTest.class.getResourceAsStream(fileName));
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
