package org.uva.sea.ql.ast.domain;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLDomainVisitor;

public class Question extends ASTNode {
	private final VarDeclaration varDeclaration;
	private final String text;

	public Question(VarDeclaration varDeclaration, String text) {
		this.varDeclaration = varDeclaration;
		this.text = text;
	}

	public VarDeclaration getVariableId() {
		return varDeclaration;
	}
	
	public VarIdentifier getVarIdentifier() {
		return varDeclaration.getIdentifier();
	}
	
	public String getVarIdentifierName() {
		return varDeclaration.getIdentifierName();
	}
	
	public Type getVarType() {
		return varDeclaration.getType();
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return "[ " + varDeclaration.toString() + " -> Lable " + text + " ]";
	}

	public void accept(QLDomainVisitor qlPartVisitor) {
		qlPartVisitor.visit(this);
	}
}
