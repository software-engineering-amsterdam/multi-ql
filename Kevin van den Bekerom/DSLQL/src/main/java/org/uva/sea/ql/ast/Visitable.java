package org.uva.sea.ql.ast;

public interface Visitable<U> {
	public void accept(Visitor visitor, U context);
}
