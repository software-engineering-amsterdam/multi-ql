package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.ast.expression.*;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;

public class ComputedQuestion extends Question {
	
	private final Expression expression;
	
	
	public ComputedQuestion(CodeFragment fragment,Identifier id, StringLiteral label, Type type, Expression expression ) {
		super(id, label, type, fragment);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}

	@Override
	public ASTNode accept(StatementVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}
	
}