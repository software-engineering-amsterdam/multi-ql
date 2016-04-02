package ql.gui.questionWidget;

import ql.ast.literal.Variable;
import ql.gui.DrawableElement;

public abstract class QuestionWidget implements DrawableElement{
	public abstract Variable getVariable();

}
