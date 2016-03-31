package nl.nicasso.ql.gui.questionFields;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.NotifyAboutGuiUpdates;
import nl.nicasso.ql.gui.QuestionFieldArguments;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.widgets.Widget;

public abstract class QuestionField {

	private final Identifier identifier;
	private final NotifyAboutGuiUpdates main;

	public QuestionField(QuestionFieldArguments params) {
		this.identifier = params.getIdentifier();
		this.main = params.getMain();
	}

	public abstract void updateValueAndTextfield(Value value);

	public abstract Widget getField();

	public abstract boolean equalValues(Value value);

	public abstract void setFeedbackField(Widget feedback);

	public abstract Value getValue();

	protected NotifyAboutGuiUpdates getMainWindow() {
		return main;
	}

	protected Identifier getIdentifier() {
		return identifier;
	}

}