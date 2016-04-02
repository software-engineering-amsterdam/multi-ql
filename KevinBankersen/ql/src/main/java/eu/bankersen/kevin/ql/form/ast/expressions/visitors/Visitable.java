package eu.bankersen.kevin.ql.form.ast.expressions.visitors;

public interface Visitable {

	<T, U> T accept(Visitor<T, U> visitor, U context);

}
