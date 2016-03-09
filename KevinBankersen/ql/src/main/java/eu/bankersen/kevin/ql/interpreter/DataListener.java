package eu.bankersen.kevin.ql.interpreter;

import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public interface DataListener {

    void dataUpdate(SymbolTable symbolTable);

}
