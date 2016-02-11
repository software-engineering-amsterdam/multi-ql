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

public class QLInterpreter extends ASTNodeVisitorAdapter<Void, Void> {

	private WidgetFactory widgetFactory;

	private QLForm currentForm;
	private Expr currentCondition;
	private QLQuestionaire questionaire;

	public QLInterpreter(Form form) {
		widgetFactory = new DefaultWidgetFactory();
		questionaire = new QLQuestionaire();

		form.accept(this, null);
	}

	public QLQuestionaire getQuestionaire() {
		return questionaire;
	}

	@Override
	public Void visit(Form node, Void context) {
		currentForm = widgetFactory.create(node);
		currentCondition = null;

		questionaire.addForm(currentForm);

		visit(node.getBody(), context);

		return null;
	}

	@Override
	public Void visit(IFStat node, Void context) {
		currentCondition = node.getExpression();

		// All questions in the body will use currentCondition
		visit(node.getBody(), context);

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
		currentForm.addQuestion(widgetFactory.create(node), currentCondition);

		return null;
	}

	@Override
	public Void visit(InputQuestion node, Void context) {
		currentForm.addQuestion(widgetFactory.create(node), currentCondition);

		return null;
	}
}
