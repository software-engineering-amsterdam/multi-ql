package org.uva.sea.ql.gui.widget;

import javax.swing.Box;

import org.uva.sea.ql.gui.FormObserver;

public abstract class Widget extends FormObserver {
	private boolean visible;
	protected Box box;
	
	public void setVisible(boolean visible) {
		this.visible = visible;
		box = new Box(0);
	}
	
	public boolean IsVisible() {
		return this.visible;
	}
	
	public Box getBox() {
		return this.box;
	}
}
