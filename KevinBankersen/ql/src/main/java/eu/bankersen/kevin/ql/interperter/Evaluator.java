package eu.bankersen.kevin.ql.interperter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import eu.bankersen.kevin.ql.form.ast.TopDownQuestionVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.Expr;
import eu.bankersen.kevin.ql.form.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.Identifier;
import eu.bankersen.kevin.ql.form.ast.expr.Literal;
import eu.bankersen.kevin.ql.form.ast.expr.logic.And;
import eu.bankersen.kevin.ql.form.ast.expr.logic.Eq;
import eu.bankersen.kevin.ql.form.ast.expr.logic.GEq;
import eu.bankersen.kevin.ql.form.ast.expr.logic.GT;
import eu.bankersen.kevin.ql.form.ast.expr.logic.LEq;
import eu.bankersen.kevin.ql.form.ast.expr.logic.LT;
import eu.bankersen.kevin.ql.form.ast.expr.logic.NEq;
import eu.bankersen.kevin.ql.form.ast.expr.logic.Not;
import eu.bankersen.kevin.ql.form.ast.expr.logic.Or;
import eu.bankersen.kevin.ql.form.ast.expr.math.Add;
import eu.bankersen.kevin.ql.form.ast.expr.math.Div;
import eu.bankersen.kevin.ql.form.ast.expr.math.Mul;
import eu.bankersen.kevin.ql.form.ast.expr.math.Neg;
import eu.bankersen.kevin.ql.form.ast.expr.math.Pos;
import eu.bankersen.kevin.ql.form.ast.expr.math.Sub;
import eu.bankersen.kevin.ql.form.ast.form.Form;
import eu.bankersen.kevin.ql.form.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.form.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
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
	Map<String, Value> oldEnvironment;

	do {
	    oldEnvironment = new HashMap<>(environment);
	    environment.clear();

	    form.accept(new TopDownQuestionVisitor<Map<String, Value>>() {

		private boolean ifCondition(Expr condition, Map<String, Value> context) {

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
		public void visit(NormalQuestion o, Map<String, Value> context) {
		    Value result = context.containsKey(o.name()) ? context.get(o.name()) : new EmptyValue();

		    environment.put(o.name(), result);
		}

		@Override
		public void visit(ComputedQuestion o, Map<String, Value> context) {
		    Value result = o.computation().accept(new ExprEvaluator(), context);

		    environment.put(o.name(), result);
		}

	    }, oldEnvironment);
	} while (wasEnvironmentUpdated(oldEnvironment));
	System.out.println(environment);
    }

    private boolean wasEnvironmentUpdated(Map<String, Value> oldEnvironment) {
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

    private class ExprEvaluator implements ExprVisitor<Value, Map<String, Value>> {

	@Override
	public Value visit(Sub o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.subtract(right);
	}

	@Override
	public Value visit(Add o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.add(right);
	}

	@Override
	public Value visit(Div o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.divide(right);
	}

	@Override
	public Value visit(Mul o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.multiply(right);
	}

	@Override
	public Value visit(Pos o, Map<String, Value> context) {
	    Value expr = o.expr().accept(this, context);

	    return expr.absolute();
	}

	@Override
	public Value visit(Neg o, Map<String, Value> context) {
	    Value expr = o.expr().accept(this, context);

	    return expr.negate();
	}

	@Override
	public Value visit(Or o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.or(right);
	}

	@Override
	public Value visit(And o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.and(right);
	}

	@Override
	public Value visit(Eq o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.equal(right);
	}

	@Override
	public Value visit(GEq o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.greaterOrEqual(right);
	}

	@Override
	public Value visit(GT o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.greater(right);
	}

	@Override
	public Value visit(LEq o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.lowerOrEqual(right);
	}

	@Override
	public Value visit(LT o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.lower(right);
	}

	@Override
	public Value visit(NEq o, Map<String, Value> context) {
	    Value left = o.lhs().accept(this, context);
	    Value right = o.rhs().accept(this, context);

	    return left.notEqual(right);
	}

	@Override
	public Value visit(Not o, Map<String, Value> context) {
	    Value expr = o.expr().accept(this, context);
	    return expr.not();
	}

	@Override
	public Value visit(Literal o, Map<String, Value> context) {
	    return o.value();
	}

	@Override
	public Value visit(Identifier o, Map<String, Value> context) {
	    return context.containsKey(o.name()) ? context.get(o.name()) : new EmptyValue();
	}

    }

}
