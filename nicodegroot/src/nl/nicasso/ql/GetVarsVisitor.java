package nl.nicasso.ql;

import java.util.ArrayList;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.Visitor;
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

public class GetVarsVisitor implements Visitor {
	
	private ArrayList<ASTNode> identifiers;
	
	GetVarsVisitor() {
		identifiers = new ArrayList<ASTNode>();
	}

	@Override
	public void visit(Form value) {

		System.out.println("Form");
		
		value.getBlock().accept(this);
	}

	@Override
	public void visit(Block value) {
		System.out.println("Block");

		for (Statement cur : value.getStatements()) {
			System.out.println("Block i");
			
			cur.accept(this);
		}
	}

	@Override
	public void visit(Question value) {
		System.out.println("Question");

		
	}

	@Override
	public void visit(ComputedQuestion value) {
		System.out.println("ComputedQuestion");

		
	}

	@Override
	public void visit(IfStatement value) {
		System.out.println("IfStatement");

		for (Statement cur : value.getBlock_if().getStatements()) {
			System.out.println("If Statement i");
			cur.accept(this);
		}
	}

	@Override
	public void visit(IfElseStatement value) {
		System.out.println("IfElseStatement");
		
		for (Statement cur : value.getBlock_if().getStatements()) {
			System.out.println("IfElse If Statement i");
			cur.accept(this);
		}
		
		for (Statement cur : value.getBlock_else().getStatements()) {
			System.out.println("IfElse Else Statement i");
			cur.accept(this);
		}		
	}

	@Override
	public void visit(BooleanExpr value) {
		System.out.println("BooleanExpr");

		
	}

	@Override
	public void visit(MathHighExpr value) {
		System.out.println("MathHighExpr");

		
	}

	@Override
	public void visit(MathLowExpr value) {
		System.out.println("MathLowExpr");

		
	}

	@Override
	public void visit(NotExpr value) {
		System.out.println("NotExpr");

		
	}

	@Override
	public void visit(ParenthesisExpr value) {
		System.out.println("ParenthesisExpr");

		
	}

	@Override
	public void visit(RelationExpr value) {
		System.out.println("RelationExpr");

		
	}

	@Override
	public void visit(BooleanLit value) {
		System.out.println("BooleanLit");

		
	}

	@Override
	public void visit(IdentifierLit value) {
		System.out.println("IdentifierLit");

		
	}

	@Override
	public void visit(IntegerLit value) {
		System.out.println("IntegerLit");

		
	}

	@Override
	public void visit(StringLit value) {
		System.out.println("StringLit");

		
	}

	@Override
	public void visit(ASTNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Statement value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Expression value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Literal value) {
		// TODO Auto-generated method stub
		
	}


}
