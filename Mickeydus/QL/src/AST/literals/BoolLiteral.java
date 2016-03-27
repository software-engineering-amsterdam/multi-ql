/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.literals;

import AST.types.Bool;
import AST.types.Type;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class BoolLiteral extends Literal {
        private final Boolean value;
    public BoolLiteral(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new Bool();
    }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
        return visitor.visit(this);
    }
}
