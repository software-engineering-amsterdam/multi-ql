package org.uva.sea.ql.ast.expr;

public class OrderedComparisonExpr extends ComparisonExpr {
    
    public OrderedComparisonExpr(Expr firstExpr, Expr secondExpr) {
        super(firstExpr, secondExpr);
        if (!(firstExpr.canBeNumeric() && secondExpr.canBeNumeric())) {
            throw new IllegalArgumentException("The arguments of an ordered comparison should both be numeric");
        }
    }
    
}
