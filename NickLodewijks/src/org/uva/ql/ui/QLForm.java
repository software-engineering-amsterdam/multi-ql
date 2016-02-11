package org.uva.ql.ui;

import javax.swing.JComponent;

import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Expr;

public interface QLForm {

	public void setContext(Context context);

	public void addQuestion(QLQuestion question);

	public void addQuestion(QLQuestion question, Expr condition);

	public JComponent getComponent();
}
