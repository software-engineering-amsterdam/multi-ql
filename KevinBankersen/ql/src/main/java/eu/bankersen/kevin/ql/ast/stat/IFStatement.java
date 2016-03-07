package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.BasicVisitor;
import eu.bankersen.kevin.ql.ast.expr.EvaluateExeption;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.type.BooleanType;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class IFStatement extends AbstractStatement  {

    private final Body body;

    public IFStatement(Expr expr, Body body, int line) {
	super(new BooleanType(), expr, line);
	this.body = body;
    }
    
    public Body body() {
	return body;
    }
    
    private boolean evalIFexpr(SymbolTable symbolTable) {
	boolean exprValue;
	
	try {
	    exprValue = (Boolean) expr().evalExpr(symbolTable);
	} catch (EvaluateExeption e) {
	    exprValue = false;
	}
	
	return exprValue;
    }
    
    @Override
    public SymbolTable evalStatement(SymbolTable symbolTable) {	
	return visible(body.evalBody(symbolTable), true); 
    }

    @Override
    public SymbolTable visible(SymbolTable symbolTable, Boolean visible) {
	return body.visible(symbolTable, evalIFexpr(symbolTable));
    }
    
    @Override
    public <T> void accept(BasicVisitor v, T context) {
	v.visit(this);
    }
}
