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
public class Str extends Type {

//	private final String string;

	public Str() {
		super();
	}

        @Override
        public Boolean isInt(){
            return false;
        }
        
        @Override
        public Boolean isString(){
            return true;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
