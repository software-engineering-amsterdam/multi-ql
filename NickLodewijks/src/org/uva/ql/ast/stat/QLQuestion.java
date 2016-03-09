package org.uva.ql.ast.stat;

import org.uva.ql.QLInterpreter;
import org.uva.ql.QLInterpreterContext;
import org.uva.ql.ast.ASTSourceInfo;
import org.uva.ql.ast.BooleanValue;
import org.uva.ql.ast.expr.BooleanLiteral;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.type.QLType;

public abstract class QLQuestion extends QLStatement {

	private final String id;
	private final QLType type;
	private final String label;

	private final Expr condition;
	private final Expr calculation;

	public QLQuestion(ASTSourceInfo context, QLType type, String id, String label) {
		super(context);
		this.id = id;
		this.type = type;
		this.label = label.replace("\"", "");
		this.condition = new BooleanLiteral(null, true);
		this.calculation = null;
	}

	public QLQuestion(ASTSourceInfo context, QLType type, String id, String label, Expr expr, Expr calculation) {
		super(context);
		this.id = id;
		this.type = type;
		this.label = label.replace("\"", "");
		this.condition = expr;
		this.calculation = calculation;
	}

	public Expr getCondition() {
		return condition;
	}

	public boolean isEnabled(QLInterpreterContext context) {
		return QLInterpreter.interpret(condition, context).equals(BooleanValue.TRUE);
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
