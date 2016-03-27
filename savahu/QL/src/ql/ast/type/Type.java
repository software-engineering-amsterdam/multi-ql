/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.type;

import ql.ast.ASTNode;
import ql.ast.IVisitor;

/**
 *
 * @author sander
 */
public abstract class Type extends ASTNode {

    public Type() {

    }

    public abstract Boolean isCompatibleToString();

    public abstract Boolean isCompatibleToInteger();

    public abstract Boolean isCompatibleToBoolean();

    @Override
    public void visitChildren(IVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
