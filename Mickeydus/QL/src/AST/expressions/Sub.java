/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.expressions;

import AST.types.Type;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class Sub extends BinaryExpr {

    private Type type;
    
    public Sub(Expr left, Expr right) {
        super(left, right);
    }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Type getType() {
        return this.type;
    }
}
