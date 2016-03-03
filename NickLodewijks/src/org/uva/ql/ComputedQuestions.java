package org.uva.ql;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.ast.stat.QLQuestionComputed;

public class ComputedQuestions implements Iterable<QLQuestionComputed> {

	private final Set<QLQuestionComputed> computedQuestions;

	private ComputedQuestions() {
		computedQuestions = new HashSet<>();
	}

	/**
	 * Collect all computed questions in the questionnaire.
	 * 
	 * @param questionnaire
	 *            the questionnaire to collect all computed questions from.
	 * @return the computed questions used in the questionnaire.
	 */
	public static ComputedQuestions collect(QLQuestionnaire questionnaire) {
		ComputedQuestions computedQuestions;

		computedQuestions = new ComputedQuestions();

		questionnaire.accept(new ComputedQuestionCollector(), computedQuestions);

		return computedQuestions;
	}

	private void add(QLQuestionComputed question) {
		computedQuestions.add(question);
	}

	@Override
	public Iterator<QLQuestionComputed> iterator() {
		return Collections.unmodifiableSet(computedQuestions).iterator();
	}

	private static class ComputedQuestionCollector extends ASTNodeVisitorAdapter<Void, ComputedQuestions> {

		@Override
		public Void visit(QLQuestionComputed node, ComputedQuestions computedQuestions) {
			computedQuestions.add(node);

			return null;
		}
	}
}
