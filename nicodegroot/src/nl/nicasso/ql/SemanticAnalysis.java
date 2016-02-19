package nl.nicasso.ql;

import java.util.ArrayList;
import java.util.Stack;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Expression;
import nl.nicasso.ql.ast.expression.Parenthesis;
import nl.nicasso.ql.ast.expression.additive.Addition;
import nl.nicasso.ql.ast.expression.additive.Subtraction;
import nl.nicasso.ql.ast.expression.conditional.And;
import nl.nicasso.ql.ast.expression.conditional.Not;
import nl.nicasso.ql.ast.expression.conditional.Or;
import nl.nicasso.ql.ast.expression.equality.Equal;
import nl.nicasso.ql.ast.expression.equality.NotEqual;
import nl.nicasso.ql.ast.expression.multiplicative.Division;
import nl.nicasso.ql.ast.expression.multiplicative.Multiplication;
import nl.nicasso.ql.ast.expression.relational.Greater;
import nl.nicasso.ql.ast.expression.relational.GreaterEqual;
import nl.nicasso.ql.ast.expression.relational.Less;
import nl.nicasso.ql.ast.expression.relational.LessEqual;
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

public class SemanticAnalysis implements Visitor<Void> {

	private boolean debug = false;
	
	private ArrayList<Question> questions;


	private ArrayList<String> warnings;
	private ArrayList<String> errors;

	SemanticAnalysis(ArrayList<Question> questions) {
		warnings = new ArrayList<String>();
		errors = new ArrayList<String>();
		this.questions = questions;
	}

	@Override
	public Void visit(Form value) {
		if (debug) {
			System.out.println("Form");
		}

		value.getBlock().accept(this);
		
		return null;
	}

	@Override
	public Void visit(Block value) {
		if (debug) {
			System.out.println("Block");
		}

		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}
	
		return null;
	}

	@Override
	public Void visit(Question value) {
		if (debug) {
			System.out.println("Question");
		}
				
		return null;
	}

	@Override
	public Void visit(ComputedQuestion value) {
		if (debug) {
			System.out.println("ComputedQuestion");
		}
		
		value.getExpr().accept(this);

		return null;
	}

	@Override
	public Void visit(IdentifierLit value) {
		if (debug) {
			System.out.println("IdentifierLit: " + value.getValue());
		}

		return null;
	}

	@Override
	public Void visit(ASTNode node) {
		return null;
	}

	@Override
	public Void visit(Statement value) {
		return null;
	}

	@Override
	public Void visit(IfStatement value) {
		return null;
	}

	@Override
	public Void visit(IfElseStatement value) {
		return null;
	}

	@Override
	public Void visit(Expression value) {
		return null;
	}

	@Override
	public Void visit(Addition value) {
		return null;
	}

	@Override
	public Void visit(Subtraction value) {
		return null;
	}

	@Override
	public Void visit(And value) {
		return null;
	}

	@Override
	public Void visit(Or value) {
		return null;
	}

	@Override
	public Void visit(Not value) {
		return null;
	}

	@Override
	public Void visit(Parenthesis value) {
		return null;
	}

	@Override
	public Void visit(Equal value) {
		return null;
	}

	@Override
	public Void visit(NotEqual value) {
		return null;
	}

	@Override
	public Void visit(Division value) {
		return null;
	}

	@Override
	public Void visit(Multiplication value) {
		return null;
	}

	@Override
	public Void visit(Greater value) {
		return null;
	}

	@Override
	public Void visit(GreaterEqual value) {
		return null;
	}

	@Override
	public Void visit(Less value) {
		return null;
	}

	@Override
	public Void visit(LessEqual value) {
		return null;
	}

	@Override
	public Void visit(Literal value) {
		return null;
	}

	@Override
	public Void visit(BooleanLit value) {
		return null;
	}

	@Override
	public Void visit(IntegerLit value) {
		return null;
	}

	@Override
	public Void visit(StringLit value) {
		return null;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}
	
	public ArrayList<String> getWarnings() {
		return warnings;
	}

}