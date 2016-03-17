package eu.bankersen.kevin.ql.interperter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import eu.bankersen.kevin.ql.ast.TopDownQuestionVisitor;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.ast.expr.Identifier;
import eu.bankersen.kevin.ql.ast.expr.Literal;
import eu.bankersen.kevin.ql.ast.expr.logic.And;
import eu.bankersen.kevin.ql.ast.expr.logic.Eq;
import eu.bankersen.kevin.ql.ast.expr.logic.GEq;
import eu.bankersen.kevin.ql.ast.expr.logic.GT;
import eu.bankersen.kevin.ql.ast.expr.logic.LEq;
import eu.bankersen.kevin.ql.ast.expr.logic.LT;
import eu.bankersen.kevin.ql.ast.expr.logic.NEq;
import eu.bankersen.kevin.ql.ast.expr.logic.Not;
import eu.bankersen.kevin.ql.ast.expr.logic.Or;
import eu.bankersen.kevin.ql.ast.expr.math.Add;
import eu.bankersen.kevin.ql.ast.expr.math.Div;
import eu.bankersen.kevin.ql.ast.expr.math.Mul;
import eu.bankersen.kevin.ql.ast.expr.math.Neg;
import eu.bankersen.kevin.ql.ast.expr.math.Pos;
import eu.bankersen.kevin.ql.ast.expr.math.Sub;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.ViewListener;

public class Evaluator implements ViewListener {

    private final Form form;
    private final Map<String, QLValue> environment;
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
    public void viewUpdate(String name, QLValue value) {
	environment.put(name, value);
	evaluate();
	dataUpdate();
    }

    private void evaluate() {
	Map<String, QLValue> oldEnvironment;

	do {
	    oldEnvironment = new HashMap<>(environment);
	    environment.clear();

	    form.accept(new TopDownQuestionVisitor<Map<String, QLValue>>() {

		private boolean ifCondition(Expr condition, Map<String, QLValue> context) {

		    QLValue result = condition.accept(new ExprEvaluator(), context);

		    if (result.equals(new UndifinedValue())) {
			return false;
		    } else {
			return (Boolean) result.value();
		    }
		}

		@Override
		public void visit(IFStatement o, Map<String, QLValue> context) {
		    if (ifCondition(o.condition(), context)) {
			o.body().accept(this, context);
		    }
		}

		@Override
		public void visit(ElseStatement o, Map<String, QLValue> context) {
		    if (ifCondition(o.condition(), context)) {
			o.body().accept(this, context);
		    } else {
			o.elseBody().accept(this, context);
		    }
		}

		@Override
		public void visit(NormalQuestion o, Map<String, QLValue> context) {
		    QLValue result = context.containsKey(o.name()) ? context.get(o.name()) : new UndifinedValue();

		    environment.put(o.name(), result);
		}

		@Override
		public void visit(ComputedQuestion o, Map<String, QLValue> context) {
		    QLValue result = o.computation().accept(new ExprEvaluator(), context);

		    environment.put(o.name(), result);
		}

	    }, oldEnvironment);
	} while (wasEnvironmentUpdated(oldEnvironment));
	System.out.println(environment);
    }

    private boolean wasEnvironmentUpdated(Map<String, QLValue> oldEnvironment) {
	if (environment.size() == oldEnvironment.size()) {
	    try {
		Iterator<Entry<String, QLValue>> i = environment.entrySet().iterator();
		while (i.hasNext()) {
		    Entry<String, QLValue> e = i.next();
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

    private class ExprEvaluator implements ExprVisitor<QLValue, Map<String, QLValue>> {

	@Override
	public QLValue visit(Sub o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.subtract(right);
	}

	@Override
	public QLValue visit(Add o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.add(right);
	}

	@Override
	public QLValue visit(Div o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.divide(right);
	}

	@Override
	public QLValue visit(Mul o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.multiply(right);
	}

	@Override
	public QLValue visit(Pos o, Map<String, QLValue> context) {
	    QLValue expr = o.expr().accept(this, context);

	    return expr.absolute();
	}

	@Override
	public QLValue visit(Neg o, Map<String, QLValue> context) {
	    QLValue expr = o.expr().accept(this, context);

	    return expr.negate();
	}

	@Override
	public QLValue visit(Or o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.or(right);
	}

	@Override
	public QLValue visit(And o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.and(right);
	}

	@Override
	public QLValue visit(Eq o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.equal(right);
	}

	@Override
	public QLValue visit(GEq o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.greaterOrEqual(right);
	}

	@Override
	public QLValue visit(GT o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.greater(right);
	}

	@Override
	public QLValue visit(LEq o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.lowerOrEqual(right);
	}

	@Override
	public QLValue visit(LT o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.lower(right);
	}

	@Override
	public QLValue visit(NEq o, Map<String, QLValue> context) {
	    QLValue left = o.lhs().accept(this, context);
	    QLValue right = o.rhs().accept(this, context);

	    return left.notEqual(right);
	}

	@Override
	public QLValue visit(Not o, Map<String, QLValue> context) {
	    QLValue expr = o.expr().accept(this, context);
	    return expr.not();
	}

	@Override
	public QLValue visit(Literal o, Map<String, QLValue> context) {
	    System.out.println(o.value());
	    return o.value();
	}

	@Override
	public QLValue visit(Identifier o, Map<String, QLValue> context) {
	    return context.containsKey(o.name()) ? context.get(o.name()) : new UndifinedValue();
	}

    }

}
