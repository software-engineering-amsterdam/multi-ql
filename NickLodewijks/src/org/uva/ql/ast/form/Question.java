package org.uva.ql.ast.form;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.VariableIdentifier;
import org.uva.ql.ui.QLQuestion;
import org.uva.ql.ui.WidgetFactory;

public abstract class Question extends ASTNode {

	private final VariableIdentifier variableIdentifier;
	private final String label;

	public Question(VariableIdentifier variableIdentifier, String label) {
		this.variableIdentifier = variableIdentifier;
		this.label = label;
	}

	public VariableIdentifier getVariableId() {
		return variableIdentifier;
	}

	public String getLabel() {
		return label;
	}

	public abstract QLQuestion getUIComponent(WidgetFactory factory);
}
