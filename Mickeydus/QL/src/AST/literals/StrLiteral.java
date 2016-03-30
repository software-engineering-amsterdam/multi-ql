/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.literals;

import AST.types.Str;
import AST.types.Type;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class StrLiteral extends Literal {
    private final String value;

    public StrLiteral(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

        @Override
    public Type getType() {
        return new Str();
    }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
