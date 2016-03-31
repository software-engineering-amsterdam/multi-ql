package ql.ast.visitor;

public interface Visitable {
	public <T> T accept(Visitor<T> visitor);
}
