package eu.bankersen.kevin.ql.ast.expr;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.type.IntType;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.context.SymbolTableBuilder;

public abstract class NumberExpr extends Expr {

    public NumberExpr(Expr lhs, Expr rhs, int line) {
	super(new IntType(), lhs, rhs, line);
    }

    public abstract BigDecimal eval(SymbolTable symbolTable) throws EvaluateExeption;
    


}
