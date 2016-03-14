package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;

public interface BaseVisitor<T> {

    T visit(Form o, T context);

    T visit(Body o, T context);

    T visit(IFStatement o, T context);

    T visit(ElseStatement o, T context);

    T visit(NormalQuestion o, T context);

    T visit(ComputedQuestion o, T context);

}
