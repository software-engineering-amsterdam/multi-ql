/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.literals;

import AST.types.Int;
import AST.types.Type;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class IntLiteral extends Literal {
        private final Integer value;

    public IntLiteral(Integer theValue) {
        this.value = theValue;
    }

    public Integer getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new Int();
    }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
