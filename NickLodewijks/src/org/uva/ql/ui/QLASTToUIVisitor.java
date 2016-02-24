package org.uva.ql.ui;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.form.Block;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.InputQuestion;
import org.uva.ql.ast.form.Question;
import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.ast.stat.IFStat;

public class QLASTToUIVisitor extends ASTNodeVisitorAdapter<Void, Void> {

	private QLQuestionaire questionaire;
	private QLForm currentForm;
	private QLSection currentSection;

	public QLASTToUIVisitor() {

	}

	public QLQuestionaire interpret(Questionnaire q) {
		questionaire = new QLQuestionaire();
		currentForm = null;
		currentSection = null;

		q.accept(this, null);

		return questionaire;
	}

	@Override
	public Void visit(Form node, Void context) {
		currentForm = new QLForm(node.getName());
		currentSection = null;

		questionaire.addForm(currentForm);

		visit(node.getBody(), context);

		return null;
	}

	@Override
	public Void visit(IFStat node, Void context) {
		QLSection previousCurrentSection;

		previousCurrentSection = currentSection;

		// Add nested sections for nested if statements
		currentSection = new QLSection(node.getExpr());
		if (previousCurrentSection != null) {
			previousCurrentSection.addSubSection(currentSection);
		} else {
			currentForm.addSection(currentSection);
		}

		// All questions in the body will use currentCondition
		visit(node.getBody(), context);

		// All if statements in the same scope should be added to the same
		// section.
		currentSection = previousCurrentSection;

		return null;
	}

	@Override
	public Void visit(Block node, Void context) {
		// First traverse the questions.
		for (Question q : node.getQuestions()) {
			q.accept(this, context);
		}

		for (IFStat statement : node.getIfStatements()) {
			statement.accept(this, context);
		}

		return null;
	}

	@Override
	public Void visit(ComputedQuestion node, Void context) {
		QLQuestion question;

		question = new QLQuestion(node);
		if (currentSection == null) {
			currentForm.addQuestion(question);
		} else {
			currentSection.addQuestion(question);
		}
		return null;
	}

	@Override
	public Void visit(InputQuestion node, Void context) {
		QLQuestion question;

		question = new QLQuestion(node);
		if (currentSection == null) {
			currentForm.addQuestion(question);
		} else {
			currentSection.addQuestion(question);
		}
		return null;
	}
}
