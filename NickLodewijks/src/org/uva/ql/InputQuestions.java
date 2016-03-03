package org.uva.ql;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.ast.stat.QLQuestionInput;

public class InputQuestions implements Iterable<QLQuestionInput> {

	private final Set<QLQuestionInput> inputQuestions;

	private InputQuestions() {
		inputQuestions = new HashSet<>();
	}

	/**
	 * Collect all computed questions in the questionnaire.
	 * 
	 * @param questionnaire
	 *            the questionnaire to collect all computed questions from.
	 * @return the computed questions used in the questionnaire.
	 */
	public static InputQuestions collect(QLQuestionnaire questionnaire) {
		InputQuestions computedQuestions;

		computedQuestions = new InputQuestions();

		questionnaire.accept(new InputQuestionCollector(), computedQuestions);

		return computedQuestions;
	}

	private void add(QLQuestionInput question) {
		inputQuestions.add(question);
	}

	@Override
	public Iterator<QLQuestionInput> iterator() {
		return Collections.unmodifiableSet(inputQuestions).iterator();
	}

	private static class InputQuestionCollector extends ASTNodeVisitorAdapter<Void, InputQuestions> {

		@Override
		public Void visit(QLQuestionInput node, InputQuestions questions) {
			questions.add(node);

			return null;
		}
	}
}
