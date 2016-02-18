package org.uva.sea.ql.ast.TaxForm;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNODE;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class Block extends ASTNODE {
	//private List<VarDeclaration> variables;
	private List<Question> questions;
	private List<IFblock> statements;

	public Block() {
		//variables = new ArrayList<VarDeclaration>();
		questions = new ArrayList<Question>();
		statements = new ArrayList<IFblock>();
	}


	public void add(Question question) {
		questions.add(question);
	}

	public void add(IFblock statement) {
		statements.add(statement);
	}

	public List<Question> getQuestions() {
		return questions;
	}
	
	public List<IFblock> getStatements() {
		return statements;
	}

	@Override
	public void accept(QLNodeVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);


		for (Question q : questions) {
			q.accept(qlPartVisitor);
			q.getVariableId().accept(qlPartVisitor);
		}

		for (IFblock statement : statements) {
			statement.accept(qlPartVisitor);
		}
		
	}
}
