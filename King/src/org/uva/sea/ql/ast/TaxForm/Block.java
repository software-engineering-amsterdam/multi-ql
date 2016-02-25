package org.uva.sea.ql.ast.TaxForm;

import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

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
	public void accept(QLNodeVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);
		
	}
}
