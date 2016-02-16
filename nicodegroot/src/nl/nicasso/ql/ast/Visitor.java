package nl.nicasso.ql.ast;

import nl.nicasso.ql.ast.expression.BooleanExpr;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.expression.MathHighExpr;
import nl.nicasso.ql.ast.expression.MathLowExpr;
import nl.nicasso.ql.ast.expression.NotExpr;
import nl.nicasso.ql.ast.expression.ParenthesisExpr;
import nl.nicasso.ql.ast.expression.RelationExpr;
import nl.nicasso.ql.ast.literal.BooleanLit;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.literal.IntegerLit;
import nl.nicasso.ql.ast.literal.Literal;
import nl.nicasso.ql.ast.literal.StringLit;
import nl.nicasso.ql.ast.statement.ComputedQuestion;
import nl.nicasso.ql.ast.statement.IfElseStatement;
import nl.nicasso.ql.ast.statement.IfStatement;
import nl.nicasso.ql.ast.statement.Question;
import nl.nicasso.ql.ast.statement.Statement;
import nl.nicasso.ql.ast.structure.Block;
import nl.nicasso.ql.ast.structure.Form;

public interface Visitor {
	
	public void visit(ASTNode node);

	public void visit(Form value);
	public void visit(Block value);
	
	public void visit(Statement value);
	public void visit(Question value);
	public void visit(ComputedQuestion value);	
	public void visit(IfStatement value);
	public void visit(IfElseStatement value);
	
	public void visit(Expression value);
	public void visit(BooleanExpr value);
	public void visit(MathHighExpr value);
	public void visit(MathLowExpr value);
	public void visit(NotExpr value);
	public void visit(ParenthesisExpr value);
	public void visit(RelationExpr value);
	
	public void visit(Literal value);
	public void visit(BooleanLit value);
	public void visit(IdentifierLit value);
	public void visit(IntegerLit value);
	public void visit(StringLit value);

}