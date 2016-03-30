/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.form;

import AST.types.*;
import ql.ASTNode;

/**
 *
 * @author Dominique
 */
public abstract class Question extends ASTNode{
    
    private final Ident Ident;
    private final Label Label;
    private final Type Type;
    
    public Question(Ident ident, Label label, Type type) {
        ident.setMoney();
        this.Ident = ident;
        this.Label = label;
        this.Type = type;
    }

    public Ident getId() {
        return this.Ident;
    }

    public Label getLabel() {
        return this.Label;
    }
    
    public Type getType() {
        return this.Type;
    }
    
    private void setType(){
//        if(this.Type.isBoolean()){
//            this.Ident.setBoolean();
//        }
//        else if(this.Type.isInt()){
//            this.Ident.setInt();
//        }
//        else if(this.Type.isMoney()){
//            this.Ident.setMoney();
//        }
//        else {
//            this.Ident.setString();
//        }
        this.Ident.setMoney();
    }
}
