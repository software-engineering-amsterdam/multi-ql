package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.BasicVisitor;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.type.Type;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class ComputedQuestion extends AbstractQuestion {

    public ComputedQuestion(String name, String text, Type type, Expr expr, int line) {
	super(name, text, type, expr, line);
    }
    
    @Override
    public SymbolTable evalStatement(SymbolTable symbolTable) {
	
	Object value;
	try {
	    value = expr().evalExpr(symbolTable);
	} catch (EvaluateExeption  e) {
	    value = null;
	}
	symbolTable.updateSymbol(name(), value);
	
	return symbolTable;
    }
    
    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }
}
