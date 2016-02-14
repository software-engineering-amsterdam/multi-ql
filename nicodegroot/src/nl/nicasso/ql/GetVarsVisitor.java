package nl.nicasso.ql;

import java.util.ArrayList;
import java.util.Stack;

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
	
	private boolean debug = true;
	
	private Stack<ArrayList<IdentifierLit>> scopes;
	private ArrayList<IdentifierLit> currentScope;
	
	private ArrayList<String> errors;
	
	GetVarsVisitor() {
		scopes = new Stack<ArrayList<IdentifierLit>>();
		currentScope = null;
		errors = new ArrayList<String>();
	}

	@Override
	public void visit(Form value) {

		if (debug) 
			System.out.println("Form");
		
		value.getBlock().accept(this);
	}

	@Override
	public void visit(Block value) {
		if (debug)
			System.out.println("Block");
		
		if (currentScope != null) {
			
			for (IdentifierLit cur : currentScope) {
				if (debug)
					System.out.println("GO THROUGH CURRENT SCOPE: "+cur.getValue());
			}
			
			scopes.push(currentScope);
		} else {
			currentScope = new ArrayList<IdentifierLit>();
		}

		for (Statement cur : value.getStatements()) {
			if (debug)
				System.out.println("Block i");
			
			cur.accept(this);
		}
		
		if (!scopes.isEmpty()) {
			System.out.println("REALLLLLLLYYYY CLOSING A BLOCK!");
			System.out.println("SIZE A: "+scopes.size());
			currentScope = scopes.pop();
			System.out.println("SIZE B: "+scopes.size());
		}
	}

	@Override
	public void visit(Question value) {
		if (debug)
			System.out.println("Question");

		currentScope.add(value.getId());
	}

	@Override
	public void visit(ComputedQuestion value) {
		if (debug)
			System.out.println("ComputedQuestion");
		
		value.getExpr().accept(this);
		
		currentScope.add(value.getId());
	}

	@Override
	public void visit(IfStatement value) {
		if (debug)
			System.out.println("IfStatement");
		
		value.getExpr().accept(this);

		value.getBlock_if().accept(this);
	}

	@Override
	public void visit(IfElseStatement value) {
		if (debug)
			System.out.println("IfElseStatement");
		
		value.getExpr().accept(this);
		
		value.getBlock_if().accept(this);
		
		value.getBlock_else().accept(this);
	}

	@Override
	public void visit(BooleanExpr value) {
		if (debug)
			System.out.println("BooleanExpr");
		
		
	}

	@Override
	public void visit(MathHighExpr value) {
		if (debug)
			System.out.println("MathHighExpr");
		
	}

	@Override
	public void visit(MathLowExpr value) {
		if (debug)
			System.out.println("MathLowExpr");
		
	}

	@Override
	public void visit(NotExpr value) {
		if (debug)
			System.out.println("NotExpr");
		
	}

	@Override
	public void visit(ParenthesisExpr value) {
		if (debug)
			System.out.println("ParenthesisExpr");
		
	}

	@Override
	public void visit(RelationExpr value) {
		if (debug)
			System.out.println("RelationExpr");
		
	}

	@Override
	public void visit(BooleanLit value) {
		if (debug)
			System.out.println("BooleanLit");
		
	}

	@Override
	public void visit(IdentifierLit value) {
		if (debug)
			System.out.println("IdentifierLit: "+value.getValue());
		
		if (!checkExistanceIdentifier(value)) {
			errors.add("Error: The identifier '"+value.getValue()+"' does not exist.");
		}
	}

	@Override
	public void visit(IntegerLit value) {
		if (debug)
			System.out.println("IntegerLit");

	}

	@Override
	public void visit(StringLit value) {
		if (debug)
			System.out.println("StringLit");
		
	}

	@Override
	public void visit(ASTNode node) {
		if (debug)
			System.out.println("ASTNode");
	}

	@Override
	public void visit(Statement value) {
		if (debug)
			System.out.println("Statement");
	}

	@Override
	public void visit(Expression value) {
		if (debug)
			System.out.println("Expression");
	}

	@Override
	public void visit(Literal value) {
		if (debug)
			System.out.println("Literal");
	}
	
	public boolean checkExistanceIdentifier(IdentifierLit value) {
		
		for (IdentifierLit cur : currentScope) {
			if (debug)
				System.out.println("checkExistanceIdentifier: "+cur.getValue());
			
			if (cur.getValue().equals(value.getValue())) {
				return true;
			}
		}
		
		return false;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}

}
