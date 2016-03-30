package eu.bankersen.kevin.ql.form.typechecker.errors;

import eu.bankersen.kevin.ql.form.ast.expr.Identifier;

public class UndefinedQuestionError extends TypeCheckError {

    public UndefinedQuestionError(Identifier o) {
	super(o.line(), String.format("Question \"%s\" is not declared!", o.name()));
    }
}
