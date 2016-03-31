/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.form;

import AST.expressions.Expr;
import AST.types.Type;
import java.util.ArrayList;
import java.util.List;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class IfElseStatement extends Statement{
    
    private Block elsestatement;
    
    public IfElseStatement(Expr ifstatement, Block thenstatement){
        super(ifstatement, thenstatement);
    }

    public IfElseStatement(Expr ifstatement, Block thenstatement, Block elsestatement){
        super(ifstatement, thenstatement);
        this.elsestatement = elsestatement;
    }
    
    public Block getElseStatement(){
       return this.elsestatement;
    }
    
    public <T> T accept(TypecheckInterface<T> visitor) {
     return visitor.visit(this);
    }

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
