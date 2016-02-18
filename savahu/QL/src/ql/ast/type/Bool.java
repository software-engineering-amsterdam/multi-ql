package ql.ast.type;

import ql.ast.expression.Expr;

/**
 *
 * @author sander
 */
public class Bool extends Expr {
    private final Boolean value;
    
    public Bool(Boolean b)
    {
        this.value = b;
    }
    
    public Boolean getValue()
    {
        return value;
    }
}
