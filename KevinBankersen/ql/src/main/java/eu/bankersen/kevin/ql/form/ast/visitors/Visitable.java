package eu.bankersen.kevin.ql.form.ast.visitors;

public interface Visitable {

	<T> void accept(Visitor<T> visitor, T context);

}
