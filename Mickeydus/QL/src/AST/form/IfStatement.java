/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.form;

import AST.expressions.Expr;
import AST.types.Type;
import typechecker.TypecheckInterface;
/**
 *
 * @author Dominique
 */
public class IfStatement extends Statement {
    
    public IfStatement(Expr ifstatement, Block thenstatement){
        super(ifstatement, thenstatement);
    }

    public <T> T accept(TypecheckInterface<T> visitor) {
     return visitor.visit(this);
    }

    @Override
    public Type getType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
