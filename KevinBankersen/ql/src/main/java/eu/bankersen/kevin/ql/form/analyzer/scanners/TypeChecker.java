package eu.bankersen.kevin.ql.form.analyzer.scanners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.InvalidExpression;
import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.InvalidQuestion;
import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.ScannerError;
import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.UndefinedQuestion;
import eu.bankersen.kevin.ql.form.analyzer.scanners.warnings.DuplicateQuestion;
import eu.bankersen.kevin.ql.form.analyzer.scanners.warnings.ScanWarning;
import eu.bankersen.kevin.ql.form.ast.Form;
import eu.bankersen.kevin.ql.form.ast.expressions.Identifier;
import eu.bankersen.kevin.ql.form.ast.expressions.Literal;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.And;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.Eq;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.GEq;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.GT;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.LEq;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.LT;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.NEq;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.Not;
import eu.bankersen.kevin.ql.form.ast.expressions.logic.Or;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Add;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Div;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Mul;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Neg;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Pos;
import eu.bankersen.kevin.ql.form.ast.expressions.math.Sub;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.Visitor;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.statements.UserQuestion;
import eu.bankersen.kevin.ql.form.ast.types.BooleanType;
import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.form.ast.types.UndefinedType;
import eu.bankersen.kevin.ql.form.ast.visitors.TopDownVisitor;

public class TypeChecker {

	private final List<ScannerError> errorList;
	private final List<ScanWarning> warningList;

	public List<ScannerError> getErrors() {
		return errorList;
	}

	public List<ScanWarning> getWarnings() {
		return warningList;
	}

	public TypeChecker(Form form) {
		this.errorList = new ArrayList<>();
		this.warningList = new ArrayList<>();
		Map<String, Type> symbolTable = createSymbolTable(form);

		typeCheckForm(form, symbolTable);
	}

	private Map<String, Type> createSymbolTable(Form form) {
		Map<String, Type> symbolTable = new HashMap<>();

		form.accept(new TopDownVisitor<Void>() {

			private void analyze(Question question) {
				if (question.type().equals(symbolTable.get(question.name()))) {
					warningList.add(new DuplicateQuestion(question));
				} else {
					errorList.add(new InvalidQuestion(question));
				}
			}

			@Override
			public void visit(UserQuestion question, Void empty) {
				if (symbolTable.containsKey(question.name())) {
					analyze(question);
				} else {
					symbolTable.put(question.name(), question.type());
				}
			}

			@Override
			public void visit(ComputedQuestion question, Void empty) {
				if (symbolTable.containsKey(question.name())) {
					analyze(question);
				} else {
					symbolTable.put(question.name(), question.type());
				}
			}
		}, null);
		return symbolTable;
	}

	private void typeCheckForm(Form form, Map<String, Type> symbolTable) {

		form.accept(new TopDownVisitor<Map<String, Type>>() {

			@Override
			public void visit(IFStatement statement, Map<String, Type> context) {

				statement.body().accept(this, context);

				Type expr = statement.condition().accept(new TypeCheckVisitor(), context);

				if (!expr.equals(new BooleanType())) {
					errorList.add(new InvalidExpression(statement, expr));
				}
			}

			@Override
			public void visit(ElseStatement statement, Map<String, Type> context) {

				statement.body().accept(this, context);
				statement.elseBody().accept(this, context);

				Type expr = statement.condition().accept(new TypeCheckVisitor(), context);

				if (!expr.equals(new BooleanType())) {
					errorList.add(new InvalidExpression(statement, expr));
				}
			}

			@Override
			public void visit(ComputedQuestion question, Map<String, Type> context) {

				Type expr = question.computation().accept(new TypeCheckVisitor(), context);

				if (!question.type().equals(expr) && !expr.equals(new UndefinedType())) {
					errorList.add(new InvalidExpression(question, expr));
				}
			}

			@Override
			public void visit(UserQuestion question, Map<String, Type> context) {
			}
		}, symbolTable);

	}

	private class TypeCheckVisitor implements Visitor<Type, Map<String, Type>> {

		private Boolean isError(Type result, Type expr) {
			return result.equals(new UndefinedType()) && !expr.equals(result);
		}

		private Boolean isError(Type result, Type lhs, Type rhs) {
			return result.equals(new UndefinedType()) && !lhs.equals(result) && !rhs.equals(result);
		}

		@Override
		public Type visit(Sub expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.subtract(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(Add expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.add(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(Div expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.divide(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(Mul expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.multiply(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(Pos expression, Map<String, Type> context) {

			Type expr = expression.expr().accept(this, context);
			Type result = expr.absolute();

			if (isError(result, expr)) {
				errorList.add(new InvalidExpression(expression, expr));
			}
			return result;
		}

		@Override
		public Type visit(Neg expression, Map<String, Type> context) {

			Type expr = expression.expr().accept(this, context);
			Type result = expr.negate();

			if (isError(result, expr)) {
				errorList.add(new InvalidExpression(expression, expr));
			}
			return result;
		}

		@Override
		public Type visit(Or expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.or(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(And expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.and(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(Eq expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.equal(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(GEq expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.greaterOrEqual(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(GT expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.greater(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(LEq expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.lowerOrEqual(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(LT expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.lower(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(NEq expression, Map<String, Type> context) {

			Type left = expression.lhs().accept(this, context);
			Type right = expression.rhs().accept(this, context);
			Type result = left.notEqual(right);

			if (isError(result, left, right)) {
				errorList.add(new InvalidExpression(expression, left, right));
			}
			return result;
		}

		@Override
		public Type visit(Not expression, Map<String, Type> context) {

			Type expr = expression.expr().accept(this, context);
			Type result = expr.not();

			if (isError(expr, result)) {
				errorList.add(new InvalidExpression(expression, expr));
			}
			return result;
		}

		@Override
		public Type visit(Literal expression, Map<String, Type> context) {
			return expression.type();
		}

		@Override
		public Type visit(Identifier expression, Map<String, Type> context) {
			if (context.containsKey(expression.name())) {
				return context.get(expression.name());
			} else {
				errorList.add(new UndefinedQuestion(expression));
				return new UndefinedType();
			}
		}
	}
}
