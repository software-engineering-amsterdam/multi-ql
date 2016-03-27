/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql.ast;

import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BinaryExpr;
import ql.ast.expression.Div;
import ql.ast.expression.Eq;
import ql.ast.expression.Expr;
import ql.ast.expression.GEq;
import ql.ast.expression.GT;
import ql.ast.expression.Ident;
import ql.ast.expression.LEq;
import ql.ast.expression.LT;
import ql.ast.expression.Mul;
import ql.ast.expression.NEq;
import ql.ast.expression.Neg;
import ql.ast.expression.Not;
import ql.ast.expression.Or;
import ql.ast.expression.Pos;
import ql.ast.expression.Sub;
import ql.ast.form.Block;
import ql.ast.form.Form;
import ql.ast.literal.BoolLiteral;
import ql.ast.literal.IntegerLiteral;
import ql.ast.literal.Literal;
import ql.ast.literal.StringLiteral;
import ql.ast.question.ComputedQuestion;
import ql.ast.question.Label;
import ql.ast.question.SimpleQuestion;
import ql.ast.statement.IfElseStatement;
import ql.ast.statement.IfStatement;
import ql.ast.type.BoolType;
import ql.ast.type.IntType;
import ql.ast.type.Type;
import ql.ast.type.StringType;

public interface IVisitor {

    public void visit(ASTNode node);

    public void visit(Block node);

    public void visit(Form node);

    public void visit(SimpleQuestion node);

    public void visit(ComputedQuestion node);

    public void visit(Label node);

    public void visit(IfStatement node);

    public void visit(IfElseStatement node);

    public void visit(Expr node);

    public void visit(Add node);

    public void visit(And node);

    public void visit(Div node);

    public void visit(Eq node);

    public void visit(GEq node);

    public void visit(GT node);

    public void visit(LEq node);

    public void visit(LT node);

    public void visit(Mul node);

    public void visit(Neg node);

    public void visit(NEq node);

    public void visit(Not node);

    public void visit(Or node);

    public void visit(Pos node);

    public void visit(Sub node);

    public void visit(Ident node);

    public void visit(Literal node);

    public void visit(BoolLiteral node);

    public void visit(IntegerLiteral node);

    public void visit(StringLiteral node);

    public void visit(Type node);

    public void visit(BoolType node);

    public void visit(IntType node);

    public void visit(StringType node);

}
