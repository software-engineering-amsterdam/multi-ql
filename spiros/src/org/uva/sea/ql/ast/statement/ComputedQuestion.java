package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.ast.expression.*;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;

public class ComputedQuestion extends Question {
	
	private final Expression expression;
	
	
	public ComputedQuestion(CodeFragment fragment,Identifier id, String label, Type type, Expression expression ) {
		super(id, label, type, fragment);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return this.expression;
	}

	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
	
}