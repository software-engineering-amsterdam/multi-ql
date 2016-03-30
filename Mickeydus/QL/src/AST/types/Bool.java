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
public class Bool extends Type {

	public Bool() {
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
            return true;
        }
        
        @Override
        public Boolean isMoney(){
            return false;
        }
 
        public <T> T accept(TypecheckInterface<T> visitor) {
        return visitor.visit(this);
        }
}
