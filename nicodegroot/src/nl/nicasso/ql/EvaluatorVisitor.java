package nl.nicasso.ql;

import nl.nicasso.ql.ast.Visitor;
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
import nl.nicasso.ql.ast.literal.Literal;
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

public class EvaluatorVisitor implements Visitor<Object> {
	
	private boolean debug = false;
	
	private SymbolTable symbolTable;
	
	EvaluatorVisitor(SymbolTable symbolTable) {
		this.symbolTable = symbolTable;
	}

	@Override
	public Object visit(And value) {	
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
				
		if (debug) {
			System.out.println("And");
		}
		
		Boolean result = new Boolean((Boolean) left && (Boolean) right);
		
		return result;
	}
	
	@Override
	public Object visit(Addition value) {		
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Addition");
		}
		
		Integer result = new Integer((Integer) left + (Integer) right);
		return result;
	}

	@Override
	public Object visit(Subtraction value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Subtraction");
		}
		
		Integer result = new Integer((Integer) left - (Integer) right);
		return result;
	}

	@Override
	public Object visit(Or value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Or");
		}
		
		Boolean result = new Boolean((Boolean) left || (Boolean) right);
		
		return result;
	}

	@Override
	public Object visit(Not value) {
		Object exprValue = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Not");
		}
		
		if ((Boolean) exprValue == true) {
			return new Boolean(false);
		} else {
			return new Boolean(true);
		}
	}

	@Override
	public Object visit(Parenthesis value) {		
		Object exprValue = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Parenthesis");
		}
		
		return exprValue;
	}

	@Override
	public Object visit(Equal value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Equal");
		}
		
		Boolean result = new Boolean((Boolean) left == (Boolean) right);
		return result;
	}

	@Override
	public Object visit(NotEqual value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);	
		
		if (debug) {
			System.out.println("NotEqual");
		}
		
		Boolean result = new Boolean((Boolean) left != (Boolean) right);
		
		return result;
	}

	@Override
	public Object visit(Division value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Division");
		}
		
		Integer result = new Integer((Integer) left / (Integer) right);
		
		return result;
	}

	@Override
	public Object visit(Multiplication value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Multiplication");
		}
		
		Integer result = new Integer((Integer) left * (Integer) right);
		
		return result;
	}

	@Override
	public Object visit(Greater value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Greater");
		}
		
		Boolean result = new Boolean((Integer) left > (Integer) right);
		return result;
	}

	@Override
	public Object visit(GreaterEqual value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("GreaterEqual");
		}
		
		Boolean result = new Boolean((Integer) left >= (Integer) right);
		return result;
	}

	@Override
	public Object visit(Less value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);

		if (debug) {
			System.out.println("Less");
		}
		
		Boolean result = new Boolean((Integer) left < (Integer) right);
		return result;
	}

	@Override
	public Object visit(LessEqual value) {
		Object left = value.getLeft().accept(this);

		Object right = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("LessEqual");
		}
		
		Boolean result = new Boolean((Integer) left <= (Integer) right);
		return result;
	}

	@Override
	public Object visit(Form value) {
		value.getBlock().accept(this);
		
		if (debug) {
			System.out.println("Form");
		}
		
		return null;
	}

	@Override
	public Object visit(Block value) {
		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}
		
		if (debug) {
			System.out.println("Block");
		}
		
		return null;
	}

	@Override
	public Object visit(Question value) {
		if (debug) {
			System.out.println("Question");
		}
		
		// WATCH OUT!
		//symbolTable.addSymbol(value.getId(), null);
		
		//SymbolTableEntry ste = symbolTable.getEntry(value.getId());
		//ste.setValue(exprValue);
		//symbolTable.addSymbol(value.getId(), ste);
		
		return null;
	}

	@Override
	public Object visit(ComputedQuestion value) {
		Object exprValue = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("ComputedQuestion");
		}
		
		SymbolTableEntry ste = symbolTable.getEntry(value.getId());
		ste.setValue(exprValue);
		symbolTable.addSymbol(value.getId(), ste);

		return exprValue;
	}

	@Override
	public Object visit(IfStatement value) {
		Object exprValue = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		
		if (debug) {
			System.out.println("ifStatement");
		}
		
		return new BooleanLit(true);
	}

	@Override
	public Object visit(IfElseStatement value) {
		Object exprValue = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		value.getBlock_else().accept(this);
		
		if (debug) {
			System.out.println("IfElseStatement");
		}
		return null;
	}

	@Override
	public Object visit(BooleanLit value) {
		if (debug) {
			System.out.println("BooleanLit: "+value.getValue());
		}
		
		return new Boolean(value.getValue());
	}

	@Override
	public Object visit(Identifier value) {
		if (debug) {
			System.out.println("IdentifierLit: "+value.getValue());
		}
		
		SymbolTableEntry entry = symbolTable.getEntry(value);
				
		return entry.getValue();
	}

	@Override
	public Object visit(IntegerLit value) {
		if (debug) {
			System.out.println("IntegerLit: "+value.getValue());
		}
		return new Integer((Integer) value.getValue());
	}

	@Override
	public Object visit(StringLit value) {
		if (debug) {
			System.out.println("StringLit: "+value.getValue());
		}
		return new String(value.getValue());
	}

}
