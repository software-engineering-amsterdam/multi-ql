package org.uva.sea.ql.ast.TaxForm;

import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;

public class Block extends ASTNode {
	//private List<VarDeclaration> variables;
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

	@Override
	public Type accept(QLNodeVisitor qlPartVisitor) {
		return qlPartVisitor.visit(this);
		
	}
}
