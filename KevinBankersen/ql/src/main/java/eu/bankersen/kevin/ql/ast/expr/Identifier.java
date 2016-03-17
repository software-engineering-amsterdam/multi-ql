package eu.bankersen.kevin.ql.ast.expr;

public class Identifier extends Expr {

    private final String name;

    public Identifier(String name, int line) {
	super(line);
	this.name = name;
    }

    public String name() {
	return name;
    }

    @Override
    public <T, U> T accept(ExprVisitor<T, U> v, U context) {
	return v.visit(this, context);
    }

}
