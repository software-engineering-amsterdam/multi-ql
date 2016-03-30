/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.types;

import AST.expressions.Expr;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class Ident extends Expr {

    private final String name;
    private Type blablabla;
    private Boolean isLiteral = false;

    public Ident(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setInt() {
        this.blablabla = new Int();
    }

    public void setString() {
        this.blablabla = new Str();
    }

    public void setBoolean() {
        this.blablabla = new Bool();
    }

    public void setMoney() {
        this.blablabla = new Money();
    }

    @Override
    public Type getType() {
        return this.blablabla;
    }

    @Override
    public void setLiteral() {
        this.isLiteral = true;
    }

    @Override
    public Boolean isLiteral() {
        return this.isLiteral;
    }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
        return visitor.visit(this);
    }
}
