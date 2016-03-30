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
public class Money extends Type {

	//private final String value;

	public Money() {
            super();
	}

        @Override
	  public Boolean isInt(){
            return false;
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
            return true;
        }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
        return visitor.visit(this);
    }
    
}
