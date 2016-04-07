package sc.ql.check;

import sc.ql.ast.Expression;
import sc.ql.ast.Expression.BinaryExpr;
import sc.ql.ast.Expression.VariableExpr;
import sc.ql.ast.Statement.Question;
import sc.ql.ast.ValueType;
import sc.ql.check.CyclicReferences.CyclicReference;

public abstract class SemanticMessage {

	private final String description;
	private final Level level;
	private final String location;

	SemanticMessage(String message, Level level) {
		this(message, level, null);
	}

	SemanticMessage(String description, Level level, String location) {
		this.description = description;
		this.level = level;
		this.location = location == null ? "" : location;
	}

	public String location() {
		return location;
	}

	public String description() {
		return description;
	}

	public Level level() {
		return level;
	}

	@Override
	public String toString() {
		String msg;

		msg = level.getText() + " " + location() + " " + description();
		return msg;
	}

	static class TypeMismatch extends SemanticMessage {

		TypeMismatch(Level level, Expression expr, ValueType expected, ValueType actual) {
			super(
					String.format(
							"Type mismatch: '%s' should be of type '%s' but is of type '%s'. ",
							expr.getSourceText(), expected, actual), level,
					expr.getSourceLocation());
		}

	}

	static class OperandTypeMismatch extends SemanticMessage {

		OperandTypeMismatch(Level level, BinaryExpr expr, ValueType lhsType,
				ValueType rhsType) {
			super(
					String.format(
							"Type mismatch: operands of %s should be of same type. (lhs='%s', rhs='%s')",
							expr.getSourceText(), lhsType, rhsType), level,
					expr.getSourceLocation());
		}
	}

	static class UnknownType extends SemanticMessage {

		UnknownType(Level level, Expression expr) {
			super(String.format("Unknown type for %s", expr), level, expr
					.getSourceLocation());
		}
	}

	static class CyclicDependency extends SemanticMessage {

		CyclicDependency(Level level, CyclicReference cr) {
			super(String.format("Cyclic dependency for question %s: (%s)",
					cr.getReference(), cr.getPath()), level);
		}
	}

	static class UndeclaredVariable extends SemanticMessage {

		UndeclaredVariable(VariableExpr node, Level level) {
			super(String.format("Undeclared variable %s, %s", node,
					node.getVariableName()), level, node.getSourceLocation());
		}
	}

	static class DuplicateQuestionLabels extends SemanticMessage {

		DuplicateQuestionLabels(Level level, Question question) {
			super(String.format("Duplicate label: %s", question.label()),
					level, question.getSourceLocation());
		}
	}

	static class DuplicateQuestionName extends SemanticMessage {

		DuplicateQuestionName(Level level, Question question) {
			super(
					String.format(
							"Question '%s' has been declared multiple times, but with different types:",
							question.name()), level, question
							.getSourceLocation());
		}
	}

	public static enum Level {
		ERROR("ERROR  "), WARNING("WARNING");

		private final String text;

		private Level(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
}