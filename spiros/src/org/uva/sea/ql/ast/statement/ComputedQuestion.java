package org.uva.sea.ql.ast.statement;

import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.ast.expression.*;
import org.uva.sea.ql.ast.expression.Literal.Identifier;

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
	public void accept(StatementVisitor visitor) {
		visitor.visitComputedQuestion(this);
	}
	
}