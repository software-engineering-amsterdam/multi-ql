package eu.bankersen.kevin.ql.interpreter;

import eu.bankersen.kevin.ql.context.SymbolTable;

public interface DataListener {

    void dataUpdate(SymbolTable symbolTable);

}
