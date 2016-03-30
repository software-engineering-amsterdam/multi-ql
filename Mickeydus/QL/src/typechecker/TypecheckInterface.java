/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typechecker;

import AST.expressions.*;
import AST.form.*;
import AST.literals.*;
import AST.types.*;
import ql.ASTNode;

/**
 *
 * @author Dominique
 * @param <Type>
 */
public interface TypecheckInterface <Type> {
    
    public Type visit(ASTNode node);
    
    public Type visit(Form node);
    
    public Type visit(Block node);
    
    public Type visit(IfStatement node);
    
    public Type visit(IfElseStatement node);
    
    public Type visit(NormalQuestion node);
    
    public Type visit(ComputedQuestion node);
    
    public Type visit(OneExpr node);
    
    public Type visit(Add node);
    
    public Type visit(And node);
    
    public Type visit(Div node);
    
    public Type visit(Eq node);
    
    public Type visit(GEq node);
    
    public Type visit(GT node);
    
    public Type visit(LEq node);
    
    public Type visit(LT node);
    
    public Type visit(Mul node);
    
    public Type visit(NEq node);
    
    public Type visit(Neg node);
    
    public Type visit(Not node);
    
    public Type visit(Or node);
    
    public Type visit(Pos node);
    
    public Type visit(Sub node);
    
    public Type visit(Literal node);
    
    public Type visit(BoolLiteral node);
    
    public Type visit(IntLiteral node);
    
    public Type visit(MoneyLiteral node);
    
    public Type visit(StrLiteral node);
    
    public Type visit(Bool node);
    
    public Type visit(Ident node);
    
    public Type visit(Int node);
    
    public Type visit(Label node);
    
    public Type visit(Money node);
    
    public Type visit(Str node);
    
    public Type visit(Type node);
}
