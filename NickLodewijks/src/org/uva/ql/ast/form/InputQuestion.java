package org.uva.ql.ast.form;

import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.VariableIdentifier;
import org.uva.ql.ui.QLQuestion;
import org.uva.ql.ui.WidgetFactory;

public class InputQuestion extends Question {

	public InputQuestion(VariableIdentifier variableIdentifier, String label) {
		super(variableIdentifier, label);
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public QLQuestion getUIComponent(WidgetFactory factory) {
		return factory.create(this);
	}
}
