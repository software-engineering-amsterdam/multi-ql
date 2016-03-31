package nl.nicasso.ql.gui.questionFields;

import java.awt.Component;

import javax.swing.JLabel;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.Observer;
import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.Value;

public abstract class QuestionField {

	private final Identifier identifier;
	private final Observer main;

	public QuestionField(QuestionFieldArguments params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
	}

	public abstract void setValue(Value value);

	public abstract Component getField();

	public abstract boolean equalValues(Value value);

	public abstract void setFeedbackField(JLabel feedback);

	public abstract Value getValue();

	protected Observer getMain() {
		return main;
	}

	protected Identifier getIdentifier() {
		return identifier;
	}

}