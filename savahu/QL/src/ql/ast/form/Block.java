/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast.form;

import java.util.List;
import ql.ast.ASTNode;
import ql.ast.question.Question;
import ql.ast.statement.Statement;

/**
 *
 * @author sander
 */
public class Block implements ASTNode {
    
    private final List<Statement> _Statements;
    private final List<Question> _Questions;
    
    public Block(List<Statement> statements, List<Question> questions)
    {
        this._Statements = statements;
        this._Questions = questions;
    }
   
    
}
