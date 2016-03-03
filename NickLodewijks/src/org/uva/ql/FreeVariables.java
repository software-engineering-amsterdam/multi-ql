package org.uva.ql;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.VariableExpr;

public class FreeVariables implements Iterable<String> {

	private final Set<String> freeVariableIds;

	private FreeVariables() {
		freeVariableIds = new HashSet<>();
	}

	/**
	 * Collect all free variables in specified expressions.
	 * 
	 * @param expr
	 *            the expression to collect all free variables from.
	 * @return the free variables used in the expression.
	 */
	public static FreeVariables collect(Expr expr) {
		FreeVariables variables;

		variables = new FreeVariables();

		expr.accept(new FreeVariableCollector(), variables);
		return variables;
	}

	private void add(String id) {
		freeVariableIds.add(id);
	}

	@Override
	public Iterator<String> iterator() {
		return Collections.unmodifiableSet(freeVariableIds).iterator();
	}

	private static class FreeVariableCollector extends ASTNodeVisitorAdapter<Void, FreeVariables> {

		@Override
		public Void visit(VariableExpr node, FreeVariables variables) {
			variables.add(node.getVariableId());

			return null;
		}
	}
}
