package org.uva.ql.ast.form;

import java.util.List;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;

public class Questionnaire extends ASTNode {
	private final List<Form> forms;

	public Questionnaire(List<Form> forms) {
		this.forms = forms;
	}

	public void add(Form form) {
		forms.add(form);
	}

	public List<Form> getForms() {
		return forms;
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

}
