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
public class Str extends Type {

	private final String value;

	public Str(String str) {
		this.value = str;
	}

	public String getValue() {
		return value;
	}
    
}
