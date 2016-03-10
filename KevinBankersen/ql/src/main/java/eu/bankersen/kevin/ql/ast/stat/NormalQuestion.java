package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.BasicVisitor;
import eu.bankersen.kevin.ql.ast.type.Type;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class NormalQuestion extends AbstractQuestion {

    public NormalQuestion(String name, String text, Type type, int line) {
	super(name, text, type, null, line);
    }

    @Override
    public SymbolTable evalStatement(SymbolTable symbolTable) {
	return symbolTable;
    }
    
    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }
}