package org.uva.sea.ql.ast.visit;

public interface Visitable {
	public <U> void accept(Visitor<U> visitor, U context);
}
