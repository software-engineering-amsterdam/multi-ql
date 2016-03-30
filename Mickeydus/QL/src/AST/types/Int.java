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
//public class Int {
//    
//    public Int (Int integer){
//    }
//    

public class Int extends Type {

	private final int value;

	public Int(int n) {
		this.value = n;
	}

	public int getValue() {
		return value;
	}
	
}
