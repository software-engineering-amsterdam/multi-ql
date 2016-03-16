package sc.qls.ast.page;

import javafx.scene.paint.Color;
import sc.qls.ast.ASTNode;
import sc.qls.ast.literal.IntegerLiteral;
import sc.qls.ast.literal.StringLiteral;

public abstract class Property extends ASTNode {

	public abstract <T, U> T accept(PropertyVisitor<T, U> visitor, U context);

	public static class ColorProperty extends Property {

		private final StringLiteral value;

		public ColorProperty(StringLiteral value) {
			this.value = value;
		}

		public StringLiteral value() {
			return value;
		}

		public Color color() {
			return Color.valueOf(value.getValue().toString());
		}

		@Override
		public <T, U> T accept(PropertyVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class HeightProperty extends Property {

		private final IntegerLiteral value;

		public HeightProperty(IntegerLiteral value) {
			this.value = value;
		}

		public IntegerLiteral value() {
			return value;
		}

		@Override
		public <T, U> T accept(PropertyVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class WidthProperty extends Property {

		private final IntegerLiteral value;

		public WidthProperty(IntegerLiteral value) {
			this.value = value;
		}

		public IntegerLiteral value() {
			return value;
		}

		@Override
		public <T, U> T accept(PropertyVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

}
