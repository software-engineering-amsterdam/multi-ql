package eu.bankersen.kevin.ql.typechecker.warnings;

import eu.bankersen.kevin.ql.typechecker.symboltable.Symbol;

public class AllreadyDeclaredWarning extends TypeCheckWarning {

    public AllreadyDeclaredWarning(int line, Symbol symbol1,  Symbol symbol2) {
	super(line, String.format("Question %s has different labels associated\n\"%s\"\n\"%s\"",
		symbol1.getName(), symbol1.getQuestion(), symbol2.getQuestion()));
    }

}