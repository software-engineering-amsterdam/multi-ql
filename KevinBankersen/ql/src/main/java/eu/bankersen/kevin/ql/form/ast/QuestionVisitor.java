package eu.bankersen.kevin.ql.form.ast;

import eu.bankersen.kevin.ql.form.ast.form.Body;
import eu.bankersen.kevin.ql.form.ast.form.Form;
import eu.bankersen.kevin.ql.form.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.form.ast.stat.NormalQuestion;

public interface QuestionVisitor<T> {

    void visit(Form o, T context);

    void visit(Body o, T context);

    void visit(IFStatement o, T context);

    void visit(ElseStatement o, T context);

    void visit(NormalQuestion o, T context);

    void visit(ComputedQuestion o, T context);

}
