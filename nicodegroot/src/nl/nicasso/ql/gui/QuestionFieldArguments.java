package nl.nicasso.ql.gui;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class QuestionFieldArguments {

	private Identifier identifier;
	private NotifyAboutGuiUpdates main;
	private boolean enabled;
	private Value value;

	public QuestionFieldArguments(Identifier identifier, NotifyAboutGuiUpdates main, boolean enabled, Value value) {
		this.identifier = identifier;
		this.main = main;
		this.enabled = enabled;
		this.value = value;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public void setMain(NotifyAboutGuiUpdates main) {
		this.main = main;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public NotifyAboutGuiUpdates getMain() {
		return main;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

}
