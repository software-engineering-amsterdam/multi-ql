/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.form;

import ql.ASTNode;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class Form extends ASTNode{
    private final String formname;
    private final Block result;
    
    public Form(String formname, Block result){
        this.formname = formname;
        this.result = result;
    }
    
    public String getID(){
        return this.formname;
    }
    
    public Block getBlockresult(){
        return this.result;
    }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
     return visitor.visit(this);
    }
}
