package sc.ql.ast;

public abstract class Statement extends ASTNode {

	public abstract <T, U> T accept(StatementVisitor<T, U> visitor, U context);

	public static final class IfThen extends Statement {

		private final Expression condition;
		private final Block body;

		public IfThen(Expression condition, Block body) {
			this.condition = condition;
			this.body = body;
		}

		public Expression getCondition() {
			return condition;
		}

		public Block getBody() {
			return body;
		}

		@Override
		public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static abstract class Question extends Statement {

		private final String id;
		private final ValueType type;
		private final String label;

		public Question(ValueType type, String id, String label) {
			this.id = id;
			this.type = type;
			this.label = label;
		}

		public String getId() {
			return id;
		}

		public ValueType getType() {
			return type;
		}

		public String getLabel() {
			return label;
		}
	}

	public static final class ComputedQuestion extends Question {

		private final Expression computation;

		public ComputedQuestion(ValueType type, String id, String label, Expression computation) {
			super(type, id, label);

			this.computation = computation;
		}

		public Expression getComputation() {
			return computation;
		}

		@Override
		public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static final class NormalQuestion extends Question {

		public NormalQuestion(ValueType type, String id, String label) {
			super(type, id, label);
		}

		@Override
		public <T, U> T accept(StatementVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}
}
