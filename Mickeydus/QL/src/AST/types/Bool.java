/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.types;

/**
 *
 * @author Dominique
 */
public class Bool extends Type {

	private final Boolean value;

	public Bool(Boolean bool) {
		this.value = bool;
	}

	public Boolean getValue() {
		return value;
	}
    
}
