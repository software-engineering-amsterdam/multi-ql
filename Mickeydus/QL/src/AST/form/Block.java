/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.form;

import java.util.List;
import ql.ASTNode;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class Block extends ASTNode {
    
    private final List<Statement> statementlist;
    private final List<Question> questionlist;
    
    public Block(List<Statement> statements, List<Question> questions){
        this.statementlist = statements;
        this.questionlist = questions;
    }

    public void add(IfStatement ifStatement) {
      
    }
    
    public List<Question> getQuestionResult(){
        return this.questionlist;
    }
    
    public List<Statement> getStatementResult(){
        return this.statementlist;
    }
    
     public String getStatements(){
        return this.statementlist.toString();
    }
    
    public String getQuestions(){
        return this.questionlist.toString();
    }
    
    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
     return visitor.visit(this);
    }
}
