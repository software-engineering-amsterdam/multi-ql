package eu.bankersen.kevin.ql.form.ast.visitors;

import eu.bankersen.kevin.ql.form.ast.Body;
import eu.bankersen.kevin.ql.form.ast.Form;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.statements.UserQuestion;

public interface Visitor<T> {

	void visit(Form form, T context);

	void visit(Body body, T context);

	void visit(IFStatement statement, T context);

	void visit(ElseStatement statement, T context);

	void visit(UserQuestion question, T context);

	void visit(ComputedQuestion question, T context);

}
