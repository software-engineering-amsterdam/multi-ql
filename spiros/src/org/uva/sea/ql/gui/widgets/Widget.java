package org.uva.sea.ql.gui.widgets;

public abstract class Widget<T> {
	
	private final T widget;
	
	public Widget(T value) {
		this.widget = value;
	}
	
	public T getWidget() {
		return this.widget;
	}

}