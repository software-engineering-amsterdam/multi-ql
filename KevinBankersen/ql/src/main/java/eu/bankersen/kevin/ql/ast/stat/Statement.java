package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;

public abstract class Statement {
    
    // Turn this into an interface
    public abstract SymbolTable visible(SymbolTable symbolTable, Boolean visible);
    public abstract Context visible(Context context, Boolean visible);

    public abstract SymbolTable evalStatement(SymbolTable symbolTable);

    public abstract Context checkType(Context context);

}
