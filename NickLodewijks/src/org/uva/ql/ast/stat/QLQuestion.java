package org.uva.ql.ast.stat;

import org.antlr.v4.runtime.ParserRuleContext;
import org.uva.ql.QLInterpreter;
import org.uva.ql.QLInterpreterContext;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.type.QLType;

public abstract class QLQuestion extends QLStatement {

	private final String id;
	private final QLType type;
	private final String label;

	private final Expr condition;
	private final Expr calculation;

	public QLQuestion(ParserRuleContext context, QLType type, String id, String label) {
		super(context);
		this.id = id;
		this.type = type;
		this.label = label;
		this.condition = new BooleanLiteral(null, true);
		this.calculation = null;
	}

	public QLQuestion(ParserRuleContext context, QLType type, String id, String label, Expr expr, Expr calculation) {
		super(context);
		this.id = id;
		this.type = type;
		this.label = label;
		this.condition = expr;
		this.calculation = calculation;
	}

	public Expr getCondition() {
		return condition;
	}

	public boolean isEnabled(QLInterpreterContext context) {
		return QLInterpreter.interpret(condition, context);
	}

	public Expr expr() {
		return calculation;
	}

	public String getId() {
		return id;
	}

	public QLType getType() {
		return type;
	}

	public String getLabel() {
		return label;
	}
}
