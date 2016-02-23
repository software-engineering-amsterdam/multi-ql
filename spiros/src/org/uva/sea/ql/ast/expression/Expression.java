package org.uva.sea.ql.ast.expression;

import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.Type;


public abstract class Expression extends ASTNode {
	
	public Expression(CodeFragment fragment) {
		super(fragment);
	}
	
	public abstract <T> T accept(ExpressionVisitor<T> visitor);
	
	public Type getTypeOfExpression() { System.out.println("getTypeOfExpression"); return new BoolType();}

	public Type getTypeOfExpression(Form form) {
		// TODO Auto-generated method stub
		return null;
	};
};