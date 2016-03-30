/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AST.form;

import AST.expressions.Expr;
import AST.types.*;
import typechecker.TypecheckInterface;

/**
 *
 * @author Dominique
 */
public class ComputedQuestion extends Question{
    
    private Expr computation;

    public ComputedQuestion(Ident ident, Label label, Type type) {
        super(ident, label, type);
    }
    
    public ComputedQuestion(Ident ident, Label label, Type type, Expr computation){
         super(ident, label, type);
        this.computation = computation;
    }
    
    public Expr getExpr()
    {
        return this.computation;
    }
    
//        public void addExprToList(Expr expression){
//        exprlist = new ArrayList();
//        exprlist.add(expression);
//    }
//    
//    public List<Expr> getExpressionList(){
//        return this.exprlist;
//    }

    @Override
    public <T> T accept(TypecheckInterface<T> visitor) {
      return visitor.visit(this);
    }
    
}