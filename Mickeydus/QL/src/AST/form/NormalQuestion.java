/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.form;

import AST.types.*;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class NormalQuestion extends Question {

    public NormalQuestion(Ident ident, Label label, Type type) {
        super(ident, label, type);
    }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
        return visitor.visit(this);
    }
}
