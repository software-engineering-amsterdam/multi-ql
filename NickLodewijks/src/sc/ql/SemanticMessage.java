package sc.ql;

import java.util.List;

import sc.ql.CyclicReferences.CyclicReference;
import sc.ql.ast.Expression;
import sc.ql.ast.Expression.BinaryExpr;
import sc.ql.ast.Expression.VariableExpr;
import sc.ql.ast.Statement.Question;
import sc.ql.ast.ValueType;

public abstract class SemanticMessage {

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

	SemanticMessage() {

	}

	public abstract String getSourceLocation();

	public abstract String getMessage();

	public abstract Level getLevel();

	@Override
	public String toString() {
		return getLevel().getText() + " " + getSourceLocation() + " " + getMessage();
	}

	static class TypeMismatch extends SemanticMessage {

		private final String msg;
		private final Expression expr;

		TypeMismatch(Expression expr, ValueType expected, ValueType actual) {
			this.msg = String.format("Type mismatch: '%s' should be of type '%s' but is of type '%s'. ",
					expr.getSourceText(), expected, actual);
			this.expr = expr;
		}

		@Override
		public String getMessage() {
			return msg;
		}

		@Override
		public String getSourceLocation() {
			return expr.getSourceLocation();
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}
	}

	static class OperandTypeMismatch extends SemanticMessage {

		private final String msg;
		private final Expression expr;

		OperandTypeMismatch(BinaryExpr expr, ValueType lhsType, ValueType rhsType) {
			this.msg = String.format("Type mismatch: operands of %s should be of same type. (lhs='%s', rhs='%s'",
					expr.getSourceText(), lhsType, rhsType);
			this.expr = expr;
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}

		@Override
		public String getMessage() {
			return msg;
		}

		@Override
		public String getSourceLocation() {
			return expr.getSourceLocation();
		}
	}

	static class UnknownType extends SemanticMessage {

		private final String msg;
		private final Expression expr;

		UnknownType(Expression expr) {
			msg = String.format("Unknown type for %s", expr);
			this.expr = expr;
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}

		@Override
		public String getMessage() {
			return msg;
		}

		@Override
		public String getSourceLocation() {
			return expr.getSourceLocation();
		}
	}

	static class CyclicDependency extends SemanticMessage {

		private final String msg;

		CyclicDependency(CyclicReference cr) {
			msg = String.format("Cyclic dependency for question %s: (%s)", cr.getReference(), cr.getPath());
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}

		@Override
		public String getSourceLocation() {
			return "";
		}

		@Override
		public String getMessage() {
			return msg;
		}
	}

	static class UndeclaredVariable extends SemanticMessage {

		private final String msg;
		private final VariableExpr node;

		UndeclaredVariable(VariableExpr node) {
			msg = String.format("Undeclared variable %s, %s", node, node.getVariableName());
			this.node = node;
		}

		@Override
		public String getMessage() {
			return msg;
		}

		@Override
		public String getSourceLocation() {
			return node.getSourceLocation();
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}

		@Override
		public String toString() {
			return msg;
		}
	}

	static class DuplicateQuestionLabels extends SemanticQuestionMessage {

		DuplicateQuestionLabels(String label, List<Question> questions) {
			super(String.format("Duplicate labels: %s", label), questions);
		}

		@Override
		public Level getLevel() {
			return Level.WARNING;
		}
	}

	static class DuplicateQuestionName extends SemanticQuestionMessage {

		DuplicateQuestionName(String name, List<Question> questions) {
			super(String.format("Question '%s' has been declared multiple times, but with different types:", name),
					questions);
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}
	}

	private static abstract class SemanticQuestionMessage extends SemanticMessage {

		private final String message;

		SemanticQuestionMessage(String msg, List<Question> questions) {
			StringBuilder sb;

			assert !questions.isEmpty() : "Question list should not be empty";

			sb = new StringBuilder();
			sb.append(msg);
			sb.append(System.lineSeparator());
			questions.stream().forEach(c -> {
				sb.append("  ");
				sb.append(c.toString());
				sb.append(System.lineSeparator());
			});

			message = sb.toString();
		}

		@Override
		public String getSourceLocation() {
			return "";
		}

		@Override
		public String getMessage() {
			return message;
		}
	}
}