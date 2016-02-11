package org.uva.ql;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.Block;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.InputQuestion;
import org.uva.ql.ast.form.Question;
import org.uva.ql.ast.stat.IFStat;
import org.uva.ql.ui.DefaultWidgetFactory;
import org.uva.ql.ui.QLForm;
import org.uva.ql.ui.QLQuestionaire;
import org.uva.ql.ui.WidgetFactory;

public class QLInterpreter extends ASTNodeVisitorAdapter {

	private WidgetFactory widgetFactory;

	private QLForm currentForm;
	private Expr currentCondition;
	private QLQuestionaire questionaire;

	public QLInterpreter() {
		widgetFactory = new DefaultWidgetFactory();
		questionaire = new QLQuestionaire();
	}

	public QLQuestionaire getQuestionaire() {
		return questionaire;
	}

	@Override
	public void visit(Form node) {

		currentForm = widgetFactory.create(node);
		currentCondition = null;

		questionaire.addForm(currentForm);

		visit(node.getBody());
	}

	@Override
	public void visit(IFStat node) {
		currentCondition = node.getExpression();

		// All questions in the body will use currentCondition
		visit(node.getBody());
	}

	@Override
	public void visit(Block node) {
		// First traverse the questions.
		for (Question q : node.getQuestions()) {
			q.accept(this);
		}

		for (IFStat statement : node.getIfStatements()) {
			statement.accept(this);
		}
	}

	@Override
	public void visit(ComputedQuestion node) {
		currentForm.addQuestion(widgetFactory.create(node), currentCondition);
	}

	@Override
	public void visit(InputQuestion node) {
		currentForm.addQuestion(widgetFactory.create(node), currentCondition);
	}
}
