package org.uva.sea.ql.ast.domain;

import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.expr.type.Type;
import org.uva.sea.ql.ast.visitors.QLNodeVisitor;

public class NormalQuestion extends Question {
	
	public NormalQuestion(VarDeclaration var, String text) {
		super(var, text);
	}
	
	@Override
	public Type accept(QLNodeVisitor qlPartVisitor) {
		return qlPartVisitor.visit(this);
	}

}
