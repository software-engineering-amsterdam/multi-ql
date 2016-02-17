package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.Type;

public class Question extends Statement {
	Identifier id;
	StringLiteral label;
	Type type;

	public Question(Identifier id, StringLiteral label, Type type, CodeFragment fragment) {
		super(fragment);
		this.id = id;
		this.label = label;
		this.type = type;
	} 
	
	public Identifier getId() {
		return this.id;
	}
	
	public StringLiteral getLabel() {
		return this.label;
	}
	
	public Type getType() {
		return this.type;
	}
	
	@Override
	public ASTNode accept(StatementVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}

}
