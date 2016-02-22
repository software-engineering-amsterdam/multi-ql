package nl.nicasso.ql;

import java.util.ArrayList;

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
import nl.nicasso.ql.utils.Pair;

public class CyclicDependencyVisitor implements Visitor<IdentifierLit> {

	private boolean debug = true;
	
	private ArrayList<Question> questions;
	private ArrayList<String> warnings;
	private ArrayList<String> errors;
	
	private ArrayList<Pair> dependencies;
	private IdentifierLit currentIdentifier;

	CyclicDependencyVisitor(ArrayList<Question> questions) {
		warnings = new ArrayList<String>();
		errors = new ArrayList<String>();
		
		this.questions = questions;
		this.dependencies = new ArrayList<Pair>();
	}

	@Override
	public IdentifierLit visit(And value) {	
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("And");
		}
		
		
		
		return null;
	}
	
	@Override
	public IdentifierLit visit(Addition value) {		
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Addition");
		}
		
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(Subtraction value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Subtraction");
		}
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(Or value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Or");
		}
		
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(Not value) {
		value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Not");
		}
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(Parenthesis value) {		
		value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Parenthesis");
		}
		
		return null;
	}

	@Override
	public IdentifierLit visit(Equal value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Equal");
		}
		
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(NotEqual value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);	
		
		if (debug) {
			System.out.println("NotEqual");
		}
		
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(Division value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Division");
		}
		
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(Multiplication value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Multiplication");
		}
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(Greater value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Greater");
		}
		
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(GreaterEqual value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("GreaterEqual");
		}
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(Less value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);

		if (debug) {
			System.out.println("Less");
		}
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(LessEqual value) {
		value.getLeft().accept(this);

		value.getRight().accept(this);
		
		if (debug) {
			System.out.println("LessEqual");
		}
		
		
		
		return null;
	}

	@Override
	public IdentifierLit visit(ASTNode node) {
		return null;
	}

	@Override
	public IdentifierLit visit(Form value) {
		value.getBlock().accept(this);
		
		if (debug) {
			System.out.println("Form");
		}
		
		// @TODO MOVE THIS ELSEWHERE
		System.out.println("ORIGINAL");
		for (Pair currentPair : dependencies) {
			System.out.println(currentPair.getLeft().getValue()+"-"+currentPair.getRight().getValue());
		}
		
		makePairsTransitive();
		System.out.println("TRANSITIVE");
		for (Pair currentPair : dependencies) {
			System.out.println(currentPair.getLeft().getValue()+"-"+currentPair.getRight().getValue());
		}
		
		if (findLoops()) {
			System.out.println("IT HAS A CYCLIC DEPENDENCY!!!!");
		}
		
		return null;
	}

	@Override
	public IdentifierLit visit(Block value) {
		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}
		
		if (debug) {
			System.out.println("Block");
		}
		
		return null;
	}

	@Override
	public IdentifierLit visit(Statement value) {
		if (debug) {
			System.out.println("Statement");
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
			System.out.println("ComputedQuestion: "+value.getId().getValue());
		}
		
		currentIdentifier = value.getId();
		
		value.getExpr().accept(this);
				
		return null;
	}

	@Override
	public IdentifierLit visit(IfStatement value) {
		value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		
		if (debug) {
			System.out.println("ifStatement");
		}
		return null;
	}

	@Override
	public IdentifierLit visit(IfElseStatement value) {
		value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		value.getBlock_else().accept(this);
		
		
		if (debug) {
			System.out.println("IfElseStatement");
		}
		return null;
	}

	@Override
	public IdentifierLit visit(Expression value) {
		if (debug) {
			System.out.println("Expression");
		}
		return null;
	}

	@Override
	public IdentifierLit visit(Literal value) {
		if (debug) {
			System.out.println("Literal");
		}
		return null;
	}

	@Override
	public IdentifierLit visit(BooleanLit value) {
		if (debug) {
			System.out.println("BooleanLit: "+value.getValue());
		}
		return null;
	}

	@Override
	public IdentifierLit visit(IdentifierLit value) {
		if (debug) {
			System.out.println("IdentifierLit: "+value.getValue());
		}
		
		addComputedQuestion(value);
		
		return null;
	}

	@Override
	public IdentifierLit visit(IntegerLit value) {
		if (debug) {
			System.out.println("IntegerLit: "+value.getValue());
		}
		return null;
	}

	@Override
	public IdentifierLit visit(StringLit value) {
		if (debug) {
			System.out.println("StringLit: "+value.getValue());
		}
		return null;
	}

	public ArrayList<String> getErrors() {
		return errors;
	}
	
	public ArrayList<String> getWarnings() {
		return warnings;
	}
	
	private void addComputedQuestion(IdentifierLit currentId) {
		dependencies.add(new Pair(currentIdentifier, currentId));
	}
	
	private void makePairsReflexive() {
		int a = dependencies.size();
		for (int i = 0; i < a; i++) {
			Pair tmp = new Pair(dependencies.get(i).getLeft(), dependencies.get(i).getLeft());
			Pair tmp2 = new Pair(dependencies.get(i).getRight(), dependencies.get(i).getRight());
			if (!checkPairExistance(tmp)) {
				dependencies.add(tmp);
			}
			if (!checkPairExistance(tmp2)) {
				dependencies.add(tmp2);
			}
		}
	}
	
	private void makePairsSymmetric() {
		int a = dependencies.size();
		for (int i = 0; i < a; i++) {
			Pair tmp = new Pair(dependencies.get(i).getRight(), dependencies.get(i).getLeft());
			if (!checkPairExistance(tmp)) {
				dependencies.add(tmp);
			}
		}
	}
	
	private boolean findLoops() {
		int a = dependencies.size();
		for (int i = 0; i < a; i++) {
			Pair tmp = new Pair(dependencies.get(i).getRight(), dependencies.get(i).getLeft());
			if (checkPairExistance(tmp)) {
				return true;
			}
		}
		return false;
	}
	
	private void makePairsTransitive() {
		ArrayList<Pair> tmpDependencies = dependencies;
		
		boolean yay = true;
		
		while(yay) {
			int a = dependencies.size();
			for (int i = 0; i < a; i++) {
				Pair cool = checkPairDependencyExistance(dependencies.get(i));
				if (cool != null) {
					if (!checkPairExistance(cool)) {
						dependencies.add(cool);
					}
				}
			}
			System.out.println(tmpDependencies.size()+" - "+dependencies.size());
			if (tmpDependencies.size() == dependencies.size()) {
				yay = false;
			} else {
				tmpDependencies = dependencies;
			}
		}
	}
	
	private Pair checkPairDependencyExistance(Pair p) {
		for (Pair tmp : dependencies) {
			if (tmp.getLeft().getValue().equals(p.getRight().getValue())) {
				return new Pair(p.getLeft(), tmp.getRight());
			}
		}
		return null;
	}
	
	private boolean checkPairExistance(Pair p) {
		for (Pair tmp : dependencies) {
			if (tmp.getLeft().getValue().equals(p.getLeft().getValue()) && tmp.getRight().getValue().equals(p.getRight().getValue())) {
				return true;
			}
		}
		return false;
	}

}