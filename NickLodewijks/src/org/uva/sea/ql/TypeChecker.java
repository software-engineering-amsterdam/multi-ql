package org.uva.sea.ql;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.ASTNodeVisitorAdapter;
import org.uva.sea.ql.ast.Result;
import org.uva.sea.ql.ast.ValueType;
import org.uva.sea.ql.ast.VariableDecl;
import org.uva.sea.ql.ast.VariableIdentifier;
import org.uva.sea.ql.ast.expr.Expr;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.Question;

public class TypeChecker {

	public TypeChecker(Form form) {

		// Initialize symbol table.
		form.accept(new VariabelChecker());

		// Validate operand and condition types.
		form.accept(new TypeValidator());

		// Look for duplicate question declarations.
		form.accept(new DuplicateFinder());

	}

	private class VariabelChecker extends ASTNodeVisitorAdapter {

		private Map<String, VariableDecl> symbols = new HashMap<String, VariableDecl>();

		@Override
		public void visit(VariableDecl node) {
			String variableName;

			variableName = node.getIdentifier().getName();
			if (symbols.containsKey(variableName)) {
				error("Duplicate variable declaration " + node);
				return;
			}

			symbols.put(variableName, node);
		}

		@Override
		public void visit(VariableIdentifier node) {
			VariableDecl variableDecl;
			String variableName;

			variableName = node.getName();

			variableDecl = symbols.get(variableName);
			if (variableDecl == null) {
				error("Reference to undefined question " + node);
				return;
			}

			node.setType(variableDecl.getType().getType());
		}
	}

	private class TypeValidator extends ASTNodeVisitorAdapter {
		@Override
		public void visit(ASTNode node) {
			Result result;

			result = node.validate();
			if (result.isFalse()) {
				error(result.getMessage());
			}
		}
	}

	private class DuplicateFinder extends ASTNodeVisitorAdapter {

		private Set<String> questions = new HashSet<String>();

		@Override
		public void visit(Question question) {
			String text;

			text = question.getText();
			if (!questions.add(text)) {
				warn("Duplicate question:" + text);
			}
		}
	}

	private void warn(String msg) {
		System.out.println(String.format("WARNING: %s", msg));
	}

	private void error(String msg) {
		System.err.println(String.format("ERROR: %s", msg));
	}

	public static Result checkType(Expr expr, ValueType expected) {
		ValueType actual;

		actual = expr.type();
		if (actual != expected) {
			return Result.FALSE(String.format("Expression %s should be of type %s but is of type %s. ", expr,
					expected.getName(), actual.getName()));
		}

		return Result.TRUE();
	}
}
