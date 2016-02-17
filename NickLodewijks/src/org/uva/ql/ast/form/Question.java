package org.uva.ql.ast.form;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ValueType;
import org.uva.ql.ast.VariableDecl;

public abstract class Question extends ASTNode {

	private final VariableDecl variableDecl;
	private final String label;

	public Question(ParserRuleContext context, VariableDecl variableDecl, String label) {
		super(context);
		this.variableDecl = variableDecl;
		this.label = label;
	}

	public VariableDecl getVariableDecl() {
		return variableDecl;
	}

	public ValueType getType() {
		return variableDecl.getType().getType();
	}

	public String getName() {
		return variableDecl.getId().getName();
	}

	public String getLabel() {
		return label;
	}
}
