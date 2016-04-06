package eu.bankersen.kevin.ql.form.ast.expressions.visitors;

import java.util.Set;

import eu.bankersen.kevin.ql.form.ast.expressions.Binary;
import eu.bankersen.kevin.ql.form.ast.expressions.Identifier;
import eu.bankersen.kevin.ql.form.ast.expressions.Literal;

public class IdentifierGatherer extends LeafVisitor<Set<String>, Set<String>> {

	@Override
	public Set<String> visit(Literal expression, Set<String> context) {
		return context;
	}

	@Override
	public Set<String> visit(Identifier expression, Set<String> context) {
		context.add(expression.name());
		return context;
	}

	@Override
	public Set<String> visitBinary(Binary expression, Set<String> context) {
		context.addAll(expression.lhs().accept(this, context));
		context.addAll(expression.rhs().accept(this, context));
		return context;
	}
}
