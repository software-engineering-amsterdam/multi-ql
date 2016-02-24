/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.form;

import java.util.ArrayList;
import java.util.List;
import ql.ast.statement.Statement;

/**
 *
 * @author sander
 */
public class Block {
    
    private final List<Statement> _Statements;
    
    public Block(List<Statement> statements)
    {
        this._Statements = statements;
    }
   
    
}
