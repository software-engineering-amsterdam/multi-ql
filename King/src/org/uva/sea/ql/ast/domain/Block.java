package org.uva.sea.ql.ast.domain;

import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.visitors.QLDomainVisitor;

public class Block extends ASTNode {
	private List<Question> questions;
	private List<IFblock> ifstatementblock;

	public Block(List<Question> questions, List<IFblock> statements) {
		this.questions = questions;
		this.ifstatementblock = statements;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public List<IFblock> getStatements() {
		return ifstatementblock;
	}

	public void accept(QLDomainVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);
	}
}
