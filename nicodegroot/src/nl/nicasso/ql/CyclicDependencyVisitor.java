package nl.nicasso.ql;

import java.util.ArrayList;
import java.util.Stack;

import org.antlr.v4.runtime.misc.MultiMap;

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

public class CyclicDependencyVisitor implements Visitor<IdentifierLit> {

	private boolean debug = true;
	
	private ArrayList<Question> questions;
	private ArrayList<String> warnings;
	private ArrayList<String> errors;
	
	private MultiMap<IdentifierLit, IdentifierLit> dependencies;

	CyclicDependencyVisitor(ArrayList<Question> questions) {
		warnings = new ArrayList<String>();
		errors = new ArrayList<String>();
		
		this.questions = questions;
		this.dependencies = new MultiMap<IdentifierLit, IdentifierLit>();  
	}

	@Override
	public IdentifierLit visit(Form value) {
		if (debug) {
			System.out.println("Form");
		}

		value.getBlock().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(Block value) {
		if (debug) {
			System.out.println("Block");
		}

		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}
	
		return null;
	}

	@Override
	public IdentifierLit visit(Question value) {
		if (debug) {
			System.out.println("Question");
		}
				
		return null;
	}

	@Override
	public IdentifierLit visit(ComputedQuestion value) {
		if (debug) {
			System.out.println("ComputedQuestion");
		}
		
		System.out.println("CQ: "+value.getId());
		
		addComputedQuestion(value);
		
		value.getExpr().accept(this);

		return null;
	}

	@Override
	public IdentifierLit visit(IdentifierLit value) {
		if (debug) {
			System.out.println("IdentifierLit: " + value.getValue());
		}

		return null;
	}

	@Override
	public IdentifierLit visit(ASTNode node) {
		return null;
	}

	@Override
	public IdentifierLit visit(Statement value) {
		return null;
	}

	@Override
	public IdentifierLit visit(IfStatement value) {
		return null;
	}

	@Override
	public IdentifierLit visit(IfElseStatement value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Expression value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Addition value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Subtraction value) {
		return null;
	}

	@Override
	public IdentifierLit visit(And value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Or value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Not value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Parenthesis value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Equal value) {
		return null;
	}

	@Override
	public IdentifierLit visit(NotEqual value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Division value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Multiplication value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Greater value) {
		return null;
	}

	@Override
	public IdentifierLit visit(GreaterEqual value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Less value) {
		return null;
	}

	@Override
	public IdentifierLit visit(LessEqual value) {
		return null;
	}

	@Override
	public IdentifierLit visit(Literal value) {
		return null;
	}

	@Override
	public IdentifierLit visit(BooleanLit value) {
		return null;
	}

	@Override
	public IdentifierLit visit(IntegerLit value) {
		return null;
	}

	@Override
	public IdentifierLit visit(StringLit value) {
		return null;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}
	
	public ArrayList<String> getWarnings() {
		return warnings;
	}
	
	private void addComputedQuestion(ComputedQuestion key) {
		//dependencies.put(key.getId(), key.getExpr().accept(this));
	}
	
	private void addComputedQuestionDependency(ComputedQuestion key, ComputedQuestion value) {
		
	}

}