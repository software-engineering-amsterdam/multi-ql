package sc.ql.ast;

import sc.ql.value.BooleanValue;
import sc.ql.value.NumberValue;
import sc.ql.value.StringValue;
import sc.ql.value.Value;

public abstract class Literal extends ASTNode {

	public abstract Value value();

	public abstract <T, U> T accept(LiteralVisitor<T, U> visitor, U context);

	public static final class BooleanLiteral extends Literal {

		public static final BooleanLiteral TRUE = new BooleanLiteral(true);
		public static final BooleanLiteral FALSE = new BooleanLiteral(false);

		private final BooleanValue value;

		public BooleanLiteral(boolean value) {
			this.value = new BooleanValue(value);
		}

		@Override
		public BooleanValue value() {
			return value;
		}

		@Override
		public <T, U> T accept(LiteralVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static final class IntegerLiteral extends Literal {

		private final NumberValue value;

		public IntegerLiteral(Integer value) {
			this.value = new NumberValue(value);
		}

		@Override
		public NumberValue value() {
			return value;
		}

		@Override
		public <T, U> T accept(LiteralVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static final class StringLiteral extends Literal {

		private final StringValue value;

		public StringLiteral(String value) {
			this.value = new StringValue(value);
		}

		@Override
		public StringValue value() {
			return value;
		}

		@Override
		public <T, U> T accept(LiteralVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}
}
