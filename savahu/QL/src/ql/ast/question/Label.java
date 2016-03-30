/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.question;

import ql.ast.ASTNode;
import ql.ast.IVisitor;

/**
 *
 * @author sander
 */
public class Label extends ASTNode {

    private final String _LabelText;

    public Label(String labelText) {
        this._LabelText = labelText;
    }

    public String getLabelText() {
        return this._LabelText;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void visitChildren(IVisitor visitor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
