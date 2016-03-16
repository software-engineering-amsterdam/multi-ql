package nl.nicasso.ql.gui.evaluator;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.expressions.Parenthesis;
import nl.nicasso.ql.ast.nodes.expressions.additive.Addition;
import nl.nicasso.ql.ast.nodes.expressions.additive.Subtraction;
import nl.nicasso.ql.ast.nodes.expressions.conditional.And;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Not;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Or;
import nl.nicasso.ql.ast.nodes.expressions.equality.Equal;
import nl.nicasso.ql.ast.nodes.expressions.equality.NotEqual;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Division;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Multiplication;
import nl.nicasso.ql.ast.nodes.expressions.relational.Greater;
import nl.nicasso.ql.ast.nodes.expressions.relational.GreaterEqual;
import nl.nicasso.ql.ast.nodes.expressions.relational.Less;
import nl.nicasso.ql.ast.nodes.expressions.relational.LessEqual;
import nl.nicasso.ql.ast.nodes.literals.BooleanLit;
import nl.nicasso.ql.ast.nodes.literals.IntegerLit;
import nl.nicasso.ql.ast.nodes.literals.MoneyLit;
import nl.nicasso.ql.ast.nodes.literals.StringLit;
import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.ast.nodes.statements.IfElseStatement;
import nl.nicasso.ql.ast.nodes.statements.IfStatement;
import nl.nicasso.ql.ast.nodes.statements.Question;
import nl.nicasso.ql.ast.nodes.statements.Statement;
import nl.nicasso.ql.ast.nodes.structures.Block;
import nl.nicasso.ql.ast.nodes.structures.Form;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTableEntry;
import nl.nicasso.ql.gui.evaluator.values.BooleanValue;
import nl.nicasso.ql.gui.evaluator.values.IntegerValue;
import nl.nicasso.ql.gui.evaluator.values.MoneyValue;
import nl.nicasso.ql.gui.evaluator.values.StringValue;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.visitors.ExpressionVisitor;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;

public class Evaluator implements StructureVisitor<Value, Void>, StatementVisitor<Value, Void>, ExpressionVisitor<Value> {
	
	private boolean debug = false;
	
	private StateTable stateTable;
	
	public Evaluator(StateTable stateTable) {
		this.stateTable = stateTable;
	}

	@Override
	public Value visit(And value) {	
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
				
		if (debug) {
			System.out.println("And");
		}
		
		return left.equal(right);
	}
	
	@Override
	public Value visit(Addition value) {		
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Addition");
		}
		
		return left.addition(right);
	}

	@Override
	public Value visit(Subtraction value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Subtraction");
		}
		
		return left.subtraction(right);
	}

	@Override
	public Value visit(Or value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Or");
		}
		
		return left.or(right);
	}

	@Override
	public Value visit(Not value) {
		Value exprValue = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Not");
		}
		
		return exprValue.not();
	}

	@Override
	public Value visit(Parenthesis value) {		
		Value exprValue = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Parenthesis");
		}
		
		return exprValue;
	}

	@Override
	public Value visit(Equal value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Equal");
		}
		
		return left.equal(right);
	}

	@Override
	public Value visit(NotEqual value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);	
		
		if (debug) {
			System.out.println("NotEqual");
		}
		
		return left.notEqual(right);
	}

	@Override
	public Value visit(Division value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Division");
		}
		
		return left.division(right);
	}

	@Override
	public Value visit(Multiplication value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Multiplication: "+left.getValue()+"*"+right.getValue());
		}
		
		return left.multiplication(right);
	}

	@Override
	public Value visit(Greater value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Greater");
		}
		
		return left.greater(right);
	}

	@Override
	public Value visit(GreaterEqual value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("GreaterEqual");
		}
		
		return left.greaterEqual(right);
	}

	@Override
	public Value visit(Less value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);

		if (debug) {
			System.out.println("Less");
		}
		
		return left.less(right);
	}

	@Override
	public Value visit(LessEqual value) {
		Value left = value.getLeft().accept(this);
		Value right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("LessEqual");
		}
		
		return left.lessEqual(right);
	}

	@Override
	public Value visit(Form value, Void ignore) {
		value.getBlock().accept(this, null);
		
		if (debug) {
			System.out.println("Form");
		}
		
		return null;
	}

	@Override
	public Value visit(Block value, Void ignore) {
		for (Statement cur : value.getStatements()) {
			cur.accept(this, ignore);
		}
		
		if (debug) {
			System.out.println("Block");
		}
		
		return null;
	}

	@Override
	public Value visit(Question value, Void context) {
		if (debug) {
			System.out.println("Question");
		}
		
		//SymbolTableEntry ste = symbolTable.getEntry(value.getId());
		//ste.setValue(exprValue);
		//symbolTable.addSymbol(value.getId(), ste);
		
		// getDefaultValue? Is this ugly?!
		StateTableEntry ste = new StateTableEntry(value.getType().getDefaultValue());
		stateTable.addState(value.getId(), ste);
		
		return null;
	}

	@Override
	public Value visit(ComputedQuestion value, Void context) {
		Value exprValue = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("ComputedQuestion");
		}
		
		//StateTableEntry ste = stateTable.getEntry(value.getId());
		//ste.setValue(exprValue);
		StateTableEntry ste = new StateTableEntry(exprValue);
		stateTable.addState(value.getId(), ste);                                                             

		return null;
	}

	@Override
	public Value visit(IfStatement value, Void context) {
		value.getExpr().accept(this);
		value.getBlock_if().accept(this, null);
		
		if (debug) {
			System.out.println("ifStatement");
		}
		
		return null;
	}

	@Override
	public Value visit(IfElseStatement value, Void context) {
		value.getExpr().accept(this);
		value.getBlock_if().accept(this, null);
		value.getBlock_else().accept(this, null);
		
		if (debug) {
			System.out.println("IfElseStatement");
		}
		return null;
	}

	@Override
	public Value visit(BooleanLit value) {
		if (debug) {
			System.out.println("BooleanLit: "+value.getValue());
		}
		
		return new BooleanValue(value.getValue());
	}

	@Override
	public Value visit(Identifier value) {		
		StateTableEntry entry = stateTable.getEntry(value);
		
		if (debug) {
			System.out.println("IdentifierLit: "+value.getValue() + " = " + entry.getValue().getValue());
		}
						
		return entry.getValue();
	}

	@Override
	public Value visit(IntegerLit value) {
		if (debug) {
			System.out.println("IntegerLit: "+value.getValue());
		}
		return new IntegerValue((Integer) value.getValue());
	}

	@Override
	public Value visit(StringLit value) {
		if (debug) {
			System.out.println("StringLit: "+value.getValue());
		}
		return new StringValue(value.getValue());
	}

	@Override
	public Value visit(MoneyLit value) {
		if (debug) {
			System.out.println("MoneyLit: "+value.getValue());
		}
		return new MoneyValue(value.getValue());
	}

}
