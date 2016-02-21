package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.Type;

public class Question extends Statement {
	Identifier id;
	String label;
	Type type;

	public Question(Identifier id, String label, Type type, CodeFragment fragment) {
		super(fragment);
		this.id = id;
		this.label = label;
		this.type = type;
	} 
	
	public Identifier getId() {
		return this.id;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public Type getType() {
		return this.type;
	}
	

	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visitQuestion(this);
	}

}
