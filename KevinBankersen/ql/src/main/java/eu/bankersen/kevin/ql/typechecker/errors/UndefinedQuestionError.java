package eu.bankersen.kevin.ql.typechecker.errors;

import eu.bankersen.kevin.ql.ast.Identifier;

public class UndefinedQuestionError extends TypeCheckError {

    public UndefinedQuestionError(Identifier o) {
	super(o.line(), String.format("Question \"%s\" is not declared!", o.name()));
    }
}
