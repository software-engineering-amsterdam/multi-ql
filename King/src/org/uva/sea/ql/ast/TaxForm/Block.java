package org.uva.sea.ql.ast.TaxForm;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNODE;
import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.TaxForm.interfaces.QLPart;
import org.uva.sea.ql.ast.TaxForm.interfaces.QLPartVisitor;

public class Block extends ASTNODE implements QLPart {
	//private List<VarDeclaration> variables;
	private List<Question> questions;
	private List<IFblock> statements;

	public Block() {
		//variables = new ArrayList<VarDeclaration>();
		questions = new ArrayList<Question>();
		statements = new ArrayList<IFblock>();
	}
/**
	public void add(VarDeclaration var) {
		variables.add(var);
	}*/

	public void add(Question question) {
		questions.add(question);
	}

	public void add(IFblock statement) {
		statements.add(statement);
	}

	public List<Question> getQuestions() {
		return questions;
	}

	@Override
	public void accept(QLPartVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);
		
	}
}
