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
//public class Int {
//    
//    public Int (Int integer){
//    }
//    

public class Int extends Type {

//	private final int value;

	public Int() {
            super();
	}

        public Boolean isInt(){
            return true;
        }
        
        @Override
        public Boolean isString(){
            return false;
        }
        
        @Override
        public Boolean isBoolean(){
            return false;
        }
        
        @Override
        public Boolean isMoney(){
            return false;
        }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
        return visitor.visit(this);
    }
	
}
