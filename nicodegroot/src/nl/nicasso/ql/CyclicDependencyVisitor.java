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

	private boolean debug = false;
	
	private ArrayList<String> warnings;
	private ArrayList<String> errors;
	
	private ArrayList<Pair> dependencies;
	private IdentifierLit currentIdentifier;

	CyclicDependencyVisitor(ArrayList<Question> questions) {
		warnings = new ArrayList<String>();
		errors = new ArrayList<String>();
		
		this.dependencies = new ArrayList<Pair>();
	}

	@Override
	public IdentifierLit visit(And value) {
		if (debug) {
			System.out.println("And");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
		
		return null;
	}
	
	@Override
	public IdentifierLit visit(Addition value) {
		if (debug) {
			System.out.println("Addition");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(Subtraction value) {
		if (debug) {
			System.out.println("Subtraction");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(Or value) {
		if (debug) {
			System.out.println("Or");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
			
		return null;
	}

	@Override
	public IdentifierLit visit(Not value) {
		if (debug) {
			System.out.println("Not");
		}
		
		value.getExpr().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(Parenthesis value) {
		if (debug) {
			System.out.println("Parenthesis");
		}
		
		value.getExpr().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(Equal value) {
		if (debug) {
			System.out.println("Equal");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
				
		return null;
	}

	@Override
	public IdentifierLit visit(NotEqual value) {
		if (debug) {
			System.out.println("NotEqual");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);	
		
		return null;
	}

	@Override
	public IdentifierLit visit(Division value) {
		if (debug) {
			System.out.println("Division");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(Multiplication value) {
		if (debug) {
			System.out.println("Multiplication");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(Greater value) {
		if (debug) {
			System.out.println("Greater");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(GreaterEqual value) {
		if (debug) {
			System.out.println("GreaterEqual");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(Less value) {
		if (debug) {
			System.out.println("Less");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(LessEqual value) {
		if (debug) {
			System.out.println("LessEqual");
		}
		
		value.getLeft().accept(this);
		value.getRight().accept(this);
				
		return null;
	}

	@Override
	public IdentifierLit visit(ASTNode node) {
		return null;
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
		if (debug) {
			System.out.println("ifStatement");
		}
		
		value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		
		return null;
	}

	@Override
	public IdentifierLit visit(IfElseStatement value) {
		if (debug) {
			System.out.println("IfElseStatement");
		}
		
		value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		value.getBlock_else().accept(this);
		
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
	
	public boolean detectCyclicDependencies() {
		
		makePairsTransitive(dependencies);
		
		int a = dependencies.size();
		for (int i = 0; i < a; i++) {
			Pair tmp = new Pair(dependencies.get(i).getRight(), dependencies.get(i).getLeft());
			if (checkPairExistance(tmp)) {
				errors.add("A cyclic dependency has been detected!");
				return true;
			}
		}
		return false;
	}
	
	private ArrayList<Pair> makePairsTransitive(ArrayList<Pair> tmpDependencies) {	
		boolean keepRunning = true;
		
		while (keepRunning) {
			int size = dependencies.size();
			
			for (int i = 0; i < size; i++) {
				Pair transitivePair = checkPairDependencyExistance(dependencies.get(i));
				if (transitivePair != null) {
					if (!checkPairExistance(transitivePair)) {
						dependencies.add(transitivePair);
					}
				}
			}

			if (tmpDependencies.size() == dependencies.size()) {
				keepRunning = false;
			} else {
				tmpDependencies = dependencies;
			}
		}
		
		return tmpDependencies;
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