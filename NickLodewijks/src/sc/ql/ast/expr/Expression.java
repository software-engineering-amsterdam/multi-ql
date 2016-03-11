package sc.ql.ast.expr;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import sc.ql.ast.ASTNode;
import sc.ql.ast.value.BooleanValue;
import sc.ql.ast.value.NumberValue;
import sc.ql.ast.value.StringValue;
import sc.ql.parser.QLLexer;
import sc.ql.parser.QLParser;

public abstract class Expression extends ASTNode {

	public abstract <T, U> T accept(ExprVisitor<T, U> visitor, U context);

	public static Expression create(String text) throws IOException {
		TokenStream tokenStream;
		QLParser parser;

		tokenStream = new CommonTokenStream(new QLLexer(new ANTLRInputStream(text)));
		parser = new QLParser(tokenStream);

		return parser.expr().result;
	}

	public static abstract class BinaryExpr extends Expression {

		private final Expression lhs;
		private final Expression rhs;

		public BinaryExpr(Expression lhs, Expression rhs) {
			this.lhs = lhs;
			this.rhs = rhs;
		}

		public final Expression left() {
			return lhs;
		}

		public final Expression right() {
			return rhs;
		}
	}

	public static class Add extends BinaryExpr {

		public Add(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class And extends BinaryExpr {

		public And(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class Divide extends BinaryExpr {

		public Divide(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class Equals extends BinaryExpr {

		public Equals(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class EqualsNot extends BinaryExpr {

		public EqualsNot(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class GreaterThan extends BinaryExpr {

		public GreaterThan(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class GreaterThanOrEqual extends BinaryExpr {

		public GreaterThanOrEqual(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class LessThan extends BinaryExpr {

		public LessThan(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class LessThanOrEqual extends BinaryExpr {

		public LessThanOrEqual(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class Multiply extends BinaryExpr {

		public Multiply(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class Negative extends UnaryExpr {

		public Negative(Expression expr) {
			super(expr);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class Not extends UnaryExpr {

		public Not(Expression expr) {
			super(expr);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static abstract class UnaryExpr extends Expression {

		private final Expression expr;

		public UnaryExpr(Expression expr) {
			this.expr = expr;
		}

		public final Expression expr() {
			return expr;
		}
	}

	public static class Or extends BinaryExpr {

		public Or(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class Positive extends UnaryExpr {

		public Positive(Expression expr) {
			super(expr);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static class Subtract extends BinaryExpr {

		public Subtract(Expression lhs, Expression rhs) {
			super(lhs, rhs);
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static final class VariableExpr extends Expression {

		private final String variableId;

		public VariableExpr(String variableId) {
			this.variableId = variableId;
		}

		public String getVariableId() {
			return variableId;
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static final class IntegerLiteral extends Expression {

		private final NumberValue value;

		public IntegerLiteral(Integer value) {
			this.value = new NumberValue(value);
		}

		public NumberValue getValue() {
			return value;
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static final class StringLiteral extends Expression {

		private final StringValue value;

		public StringLiteral(String value) {
			this.value = new StringValue(value);
		}

		public StringValue getValue() {
			return value;
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

	public static final class BooleanLiteral extends Expression {

		public static final BooleanLiteral TRUE = new BooleanLiteral(true);
		public static final BooleanLiteral FALSE = new BooleanLiteral(false);

		private final BooleanValue value;

		public BooleanLiteral(boolean value) {
			this.value = new BooleanValue(value);
		}

		public BooleanValue getValue() {
			return value;
		}

		@Override
		public <T, U> T accept(ExprVisitor<T, U> visitor, U context) {
			return visitor.visit(this, context);
		}
	}

}
