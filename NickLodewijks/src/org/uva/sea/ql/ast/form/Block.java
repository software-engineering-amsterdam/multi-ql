package org.uva.sea.ql.ast.form;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.ASTNodeVisitor;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.VariableDecl;
import org.uva.sea.ql.ast.stat.IFStat;

public class Block extends ASTNode {
	private List<VariableDecl> variables;
	private List<Question> questions;
	private List<IFStat> statements;

	public Block() {
		variables = new ArrayList<VariableDecl>();
		questions = new ArrayList<Question>();
		statements = new ArrayList<IFStat>();
	}

	public void add(VariableDecl variableDecl) {
		variables.add(variableDecl);
	}

	public void add(Question question) {
		questions.add(question);
	}

	public void add(IFStat statement) {
		statements.add(statement);
	}

	public List<Question> getQuestions() {
		return questions;
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);

		for (VariableDecl variable : variables) {
			variable.accept(visitor);
		}

		for (Question q : questions) {
			q.accept(visitor);
		}

		for (IFStat statement : statements) {
			statement.accept(visitor);
		}
	}

	@Override
	public Result validate() {
		return Result.TRUE();
	}

}
