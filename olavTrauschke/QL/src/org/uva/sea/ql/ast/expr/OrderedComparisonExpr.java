package org.uva.sea.ql.ast.expr;

public class OrderedComparisonExpr extends ComparisonExpr {
    
    public static final String TYPE_ERROR_MESSAGE = "The arguments of an ordered comparison should both be numeric";
    
    public OrderedComparisonExpr(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
        if (!(firstExpr.canBeNumeric() && secondExpr.canBeNumeric())) {
            addError(TYPE_ERROR_MESSAGE);
        }
    }
    
}
