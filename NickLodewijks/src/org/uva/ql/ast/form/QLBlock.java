package org.uva.ql.ast.form;

import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.QLBlockVisitor;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;

public class QLBlock extends ASTNode {

	private final List<QLQuestion> questions;
	private final List<QLIFStatement> statements;

	public QLBlock(ParserRuleContext context, List<QLQuestion> questions, List<QLIFStatement> statements) {
		super(context);
		this.questions = questions;
		this.statements = statements;
	}

	public List<QLQuestion> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	public List<QLIFStatement> getIfStatements() {
		return Collections.unmodifiableList(statements);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	public <T, U> T accept(QLBlockVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}
