package org.uva.ql.ast.form;

import java.util.ArrayList;
import java.util.List;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.ast.Result;
import org.uva.ql.ast.VariableDecl;
import org.uva.ql.ast.stat.IFStat;

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

	public List<VariableDecl> getVariables() {
		return variables;
	}

	public List<IFStat> getIfStatements() {
		return statements;
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}
