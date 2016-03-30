/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.types;

import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class Label extends Type {

	private final String labelText;

	public Label(String labeltext) {
		this.labelText = labeltext;
	}

	public String getText() {
		return labelText;
	}

    public <T> T accept(TypecheckInterface<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean isString() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isBoolean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isMoney() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isInt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}