package eu.bankersen.kevin.ql.interperter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import eu.bankersen.kevin.ql.form.ast.expressions.Expression;
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
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.form.ast.visitors.TopDownVisitor;
import eu.bankersen.kevin.ql.gui.ViewListener;

public class Evaluator implements ViewListener {

	private final Form form;
	private final Map<String, Value> environment;
	private final List<DataListener> dataListeners;

	public Evaluator(Form form) {
		this.form = form;
		this.environment = new HashMap<>();
		this.dataListeners = new ArrayList<>();
		evaluate();
	}

	@Override
	public String toString() {
		return form.name() + environment.toString();
	}

	public void addDataListener(DataListener listener) {
		dataListeners.add(listener);
		dataUpdate();
	}

	private void dataUpdate() {
		dataListeners.forEach(listener -> listener.dataUpdate(environment));
	}

	@Override
	public void viewUpdate(String name, Value value) {
		environment.put(name, value);
		evaluate();
		dataUpdate();
	}

	private void evaluate() {
		Map<String, Value> currentEnvironment;

		do {
			currentEnvironment = new HashMap(environment);
			environment.clear();

			form.accept(new TopDownVisitor<Map<String, Value>>() {

				private boolean ifCondition(Expression condition, Map<String, Value> context) {

					Value result = condition.accept(new ExprEvaluator(), context);

					if (result.equals(new EmptyValue())) {
						return false;
					} else {
						return (Boolean) result.value();
					}
				}

				@Override
				public void visit(IFStatement o, Map<String, Value> context) {
					if (ifCondition(o.condition(), context)) {
						o.body().accept(this, context);
					}
				}

				@Override
				public void visit(ElseStatement o, Map<String, Value> context) {
					if (ifCondition(o.condition(), context)) {
						o.body().accept(this, context);
					} else {
						o.elseBody().accept(this, context);
					}
				}

				@Override
				public void visit(UserQuestion o, Map<String, Value> context) {
					Value result = context.containsKey(o.name()) ? context.get(o.name()) : new EmptyValue();

					environment.put(o.name(), result);
				}

				@Override
				public void visit(ComputedQuestion o, Map<String, Value> context) {
					Value result = o.computation().accept(new ExprEvaluator(), context);

					environment.put(o.name(), result);
				}

			}, currentEnvironment);
		} while (environmentIsUpdated(currentEnvironment));
		System.out.println(toString());
	}

	private boolean environmentIsUpdated(Map<String, Value> oldEnvironment) {
		if (environment.size() == oldEnvironment.size()) {
			try {
				Iterator<Entry<String, Value>> i = environment.entrySet().iterator();
				while (i.hasNext()) {
					Entry<String, Value> e = i.next();
					if (!environment.get(e.getKey()).equals(oldEnvironment.get(e.getKey()))) {
						return true;
					}
				}
				return false;
			} catch (NullPointerException unused) {
				return false;
			}
		} else {
			return true;
		}
	}

	private class ExprEvaluator implements Visitor<Value, Map<String, Value>> {

		@Override
		public Value visit(Sub expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.subtract(right);
		}

		@Override
		public Value visit(Add expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.add(right);
		}

		@Override
		public Value visit(Div expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.divide(right);
		}

		@Override
		public Value visit(Mul expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.multiply(right);
		}

		@Override
		public Value visit(Pos expression, Map<String, Value> context) {
			Value expr = expression.expr().accept(this, context);

			return expr.absolute();
		}

		@Override
		public Value visit(Neg expression, Map<String, Value> context) {
			Value expr = expression.expr().accept(this, context);

			return expr.negate();
		}

		@Override
		public Value visit(Or expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.or(right);
		}

		@Override
		public Value visit(And expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.and(right);
		}

		@Override
		public Value visit(Eq expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.equal(right);
		}

		@Override
		public Value visit(GEq expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.greaterOrEqual(right);
		}

		@Override
		public Value visit(GT expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.greater(right);
		}

		@Override
		public Value visit(LEq expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.lowerOrEqual(right);
		}

		@Override
		public Value visit(LT expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.lower(right);
		}

		@Override
		public Value visit(NEq expression, Map<String, Value> context) {
			Value left = expression.lhs().accept(this, context);
			Value right = expression.rhs().accept(this, context);

			return left.notEqual(right);
		}

		@Override
		public Value visit(Not expression, Map<String, Value> context) {
			Value expr = expression.expr().accept(this, context);
			return expr.not();
		}

		@Override
		public Value visit(Literal expression, Map<String, Value> context) {
			return expression.value();
		}

		@Override
		public Value visit(Identifier expression, Map<String, Value> context) {
			return context.containsKey(expression.name()) ? context.get(expression.name()) : new EmptyValue();
		}

	}

}
