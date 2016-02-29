/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.form;

/**
 *
 * @author sander
 */
public class Label {
    
    private final String _LabelText;
    
    public Label(String labelText)
    {
        this._LabelText = labelText;
    }
    
    public String getLabelText() {
        return this._LabelText;
    }
}
