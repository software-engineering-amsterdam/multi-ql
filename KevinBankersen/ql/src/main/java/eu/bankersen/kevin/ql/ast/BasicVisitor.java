package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.AbstractQuestion;
import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;

public interface BasicVisitor<T, U> {

    T visit(Form o, U context);

    void visit(Body o);
    
    void visit(AbstractStatement o);

    void visit(IFStatement o);
    
    void visit(ElseStatement o);
    
    void visit(AbstractQuestion o);

    void visit(NormalQuestion o);

    void visit(ComputedQuestion o);
    
    void visit(Expr o);

    void visit(Identifier o);

    void visit(Literal o);







}
