package eu.bankersen.kevin.ql.form.analyzer.scanners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.ScannerError;
import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.InvalidExpression;
import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.UndefinedQuestion;
import eu.bankersen.kevin.ql.form.analyzer.scanners.warnings.AllreadyDeclared;
import eu.bankersen.kevin.ql.form.analyzer.scanners.warnings.TypeCheckWarning;
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
import eu.bankersen.kevin.ql.form.ast.statements.Form;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.statements.UserQuestion;
import eu.bankersen.kevin.ql.form.ast.types.BooleanType;
import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.form.ast.types.UndifinedType;
import eu.bankersen.kevin.ql.form.ast.visitors.TopDownVisitor;

public class TypeChecker {

	private final Map<String, Type> symbolTable;
	private final List<ScannerError> errorList;
	private final List<TypeCheckWarning> warningList;

	public List<ScannerError> getErrors() {
		return errorList;
	}

	public List<TypeCheckWarning> getWarnings() {
		return warningList;
	}

	public TypeChecker(Form form) {
		this.errorList = new ArrayList<>();
		this.warningList = new ArrayList<>();
		this.symbolTable = new HashMap<>();

		createSymbolTable(form);
		typeCheckForm(form);
	}

	private void createSymbolTable(Form form) {
		form.accept(new TopDownVisitor<Void>() {

			@Override
			public void visit(UserQuestion o, Void empty) {
				if (symbolTable.containsKey(o.name())) {
					warningList.add(new AllreadyDeclared(o.line(), o.name()));
				} else {
					symbolTable.put(o.name(), o.type());
				}
			}

			@Override
			public void visit(ComputedQuestion o, Void empty) {
				if (symbolTable.containsKey(o.name())) {
					warningList.add(new AllreadyDeclared(o.line(), o.name()));
				} else {
					symbolTable.put(o.name(), o.type());
				}
			}
		}, null);
	}

	private void typeCheckForm(Form form) {

		form.accept(new TopDownVisitor<Map<String, Type>>() {

			@Override
			public void visit(IFStatement o, Map<String, Type> context) {

				o.body().accept(this, context);

				Type expr = o.condition().accept(new TypeCheckVisitor(), context);

				if (!expr.equals(new BooleanType())) {
					errorList.add(new InvalidExpression(o, expr));
				}
			}

			@Override
			public void visit(ElseStatement o, Map<String, Type> context) {

				o.body().accept(this, context);
				o.elseBody().accept(this, context);

				Type expr = o.condition().accept(new TypeCheckVisitor(), context);

				if (!expr.equals(new BooleanType())) {
					errorList.add(new InvalidExpression(o, expr));
				}
			}

			@Override
			public void visit(ComputedQuestion o, Map<String, Type> context) {

				Type question = o.type();
				Type expr = o.computation().accept(new TypeCheckVisitor(), context);

				if (!question.equals(expr) && !expr.equals(new UndifinedType())) {
					errorList.add(new InvalidExpression(o, question, expr));
				}
			}

			@Override
			public void visit(UserQuestion o, Map<String, Type> context) {
			}
		}, symbolTable);

	}

	private class TypeCheckVisitor implements Visitor<Type, Map<String, Type>> {

		private Boolean isError(Type result, Type expr) {
			return result.equals(new UndifinedType()) && !expr.equals(result);
		}

		private Boolean isError(Type result, Type lhs, Type rhs) {
			return result.equals(new UndifinedType()) && !lhs.equals(result) && !rhs.equals(result);
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
				return new UndifinedType();
			}
		}
	}
}
