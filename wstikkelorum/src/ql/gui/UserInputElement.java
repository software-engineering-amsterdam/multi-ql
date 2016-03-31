package ql.gui;

import ql.ast.value.Value;

public interface UserInputElement extends DrawableElement{
	Value getInput();
	void onAction();
}
