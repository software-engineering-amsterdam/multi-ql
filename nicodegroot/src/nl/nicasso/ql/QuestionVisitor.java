package nl.nicasso.ql;

import java.util.ArrayList;
import java.util.List;

import nl.nicasso.ql.ast.expression.Identifier;
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
import nl.nicasso.ql.ast.literal.IntegerLit;
import nl.nicasso.ql.ast.literal.MoneyLit;
import nl.nicasso.ql.ast.literal.StringLit;
import nl.nicasso.ql.ast.statement.ComputedQuestion;
import nl.nicasso.ql.ast.statement.IfElseStatement;
import nl.nicasso.ql.ast.statement.IfStatement;
import nl.nicasso.ql.ast.statement.Question;
import nl.nicasso.ql.ast.statement.Statement;
import nl.nicasso.ql.ast.structure.Block;
import nl.nicasso.ql.ast.structure.Form;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.visitor.ExpressionVisitor;
import nl.nicasso.ql.visitor.StatementVisitor;
import nl.nicasso.ql.visitor.StructureVisitor;

public class QuestionVisitor implements StructureVisitor<Identifier>, StatementVisitor<Identifier>, ExpressionVisitor<Identifier> {

	private boolean debug = false;

	private List<Question> questions;
	private List<Identifier> identifiers;
	
	private List<String> warnings;
	private List<String> errors;
	
	private SymbolTable symbolTable;

	QuestionVisitor(SymbolTable symbolTable) {
		questions = new ArrayList<Question>();
		identifiers = new ArrayList<Identifier>();
		warnings = new ArrayList<String>();
		errors = new ArrayList<String>();
		
		this.symbolTable = symbolTable;
	}

	@Override
	public Identifier visit(Form value) {
		if (debug) {
			System.out.println("Form");
		}

		value.getBlock().accept(this);
		
		checkNullPointers();
		
		return null;
	}

	@Override
	public Identifier visit(Block value) {
		if (debug) {
			System.out.println("Block");
		}

		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}

		return null;
	}

	@Override
	public Identifier visit(Question value) {
		if (debug) {
			System.out.println("Question");
		}
		
		symbolTable.addSymbol(value.getId(), new SymbolTableEntry(value.getType()));
		
		addQuestion(value);
		
		return null;
	}

	@Override
	public Identifier visit(ComputedQuestion value) {
		if (debug) {
			System.out.println("ComputedQuestion");
		}
		
		symbolTable.addSymbol(value.getId(), new SymbolTableEntry(value.getType()));
				
		addQuestion(value);
		
		value.getExpr().accept(this);
				
		return null;
	}

	@Override
	public Identifier visit(Identifier value) {
		if (debug) {
			System.out.println("IdentifierLit: " + value.getValue());
		}
		
		identifiers.add(value);

		return null;
	}

	@Override
	public Identifier visit(IfStatement value) {
		return null;
	}

	@Override
	public Identifier visit(IfElseStatement value) {
		return null;
	}

	public List<Question> getQuestions() {
		return questions;
	}
	
	public List<Identifier> getIdentifiers() {
		return identifiers;
	}
	
	public void checkNullPointers() {
		for (Identifier id : identifiers) {
			if (checkExistanceIdentifier(id) == null) {
				errors.add("The identifier " + id.getValue() + " does not exist.");
			}
		}
	}

	public boolean addQuestion(Question q) {
		
		boolean insert = true;
		
		Question cur = checkExistanceIdentifier(q.getId());
		
		if (cur != null) {
			if (cur.getType().getType().equals(q.getType().getType())) {
				warnings.add("The identifier " + q.getId().getValue() + " already exist.");
				insert = false;
			} else {
				errors.add("The identifier " + q.getId().getValue() + " already exist.");
			}
		}
		
		checkExistanceLabel(q.getLabel());
		
		if (insert == true) {
			questions.add(q);
			return true;
		}
		
		return false;
	}
	
	private Question checkExistanceIdentifier(Identifier value) {
		for (Question cur : questions) {
			if (cur.getId().getValue().equals(value.getValue())) {
				return cur;
			}
		}

		return null;
	}
	
	private boolean checkExistanceLabel(String value) {
		for (Question cur : questions) {
			if (cur.getLabel().equals(value)) {
				warnings.add("Warning: The label " + value + " already exist.");
				return true;
			}
		}

		return false;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public List<String> getWarnings() {
		return warnings;
	}

	@Override
	public Identifier visit(Addition value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(Subtraction value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(And value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(Or value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(Not value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(Parenthesis value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(Equal value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(NotEqual value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(Division value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(Multiplication value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(Greater value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(GreaterEqual value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(Less value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(LessEqual value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(BooleanLit value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(IntegerLit value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(StringLit value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Identifier visit(MoneyLit value) {
		// TODO Auto-generated method stub
		return null;
	}
		
}