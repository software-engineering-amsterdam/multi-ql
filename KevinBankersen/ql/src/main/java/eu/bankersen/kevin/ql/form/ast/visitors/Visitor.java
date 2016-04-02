package eu.bankersen.kevin.ql.form.ast.visitors;

import eu.bankersen.kevin.ql.form.ast.statements.Body;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Form;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.statements.UserQuestion;

public interface Visitor<T> {

	void visit(Form o, T context);

	void visit(Body o, T context);

	void visit(IFStatement o, T context);

	void visit(ElseStatement o, T context);

	void visit(UserQuestion o, T context);

	void visit(ComputedQuestion o, T context);

}
