package nl.nicasso.ql.gui;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;

public class QuestionFieldParameter {

	private Identifier identifier;
	private Observer main;
	private boolean enabled;
	
	public QuestionFieldParameter(Identifier identifier, Observer main, boolean enabled) {
		this.identifier = identifier;
		this.main = main;
		this.enabled = enabled;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public void setMain(Observer main) {
		this.main = main;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public Observer getMain() {
		return main;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
}
