package nl.nicasso.ql;

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

public class EvaluatorVisitor implements Visitor<Literal> {
	
	private boolean debug = false;

	@Override
	public Literal visit(And value) {	
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
				
		if (debug) {
			System.out.println("And");
		}
		
		BooleanLit result = new BooleanLit((Boolean) leftLiteral.getValue() && (Boolean) rightLiteral.getValue());
		
		return result;
	}
	
	@Override
	public Literal visit(Addition value) {		
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Addition");
		}
		
		IntegerLit result = new IntegerLit((Integer) leftLiteral.getValue() + (Integer) rightLiteral.getValue());
		System.out.println("Addition: "+leftLiteral.getValue()+" + "+ rightLiteral.getValue());
		return result;
	}

	@Override
	public Literal visit(Subtraction value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Subtraction");
		}
		
		IntegerLit result = new IntegerLit((Integer) leftLiteral.getValue() - (Integer) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(Or value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Or");
		}
		
		BooleanLit result = new BooleanLit((Boolean) leftLiteral.getValue() || (Boolean) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(Not value) {
		Literal exprLiteral = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Not");
		}
		
		if ((Boolean) exprLiteral.getValue() == true) {
			return new BooleanLit(false);
		} else {
			return new BooleanLit(true);
		}
	}

	@Override
	public Literal visit(Parenthesis value) {		
		Literal exprLiteral = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Parenthesis");
		}
		
		return exprLiteral;
	}

	@Override
	public Literal visit(Equal value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Equal");
		}
		
		BooleanLit result = new BooleanLit((Boolean) leftLiteral.getValue() == (Boolean) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(NotEqual value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);	
		
		if (debug) {
			System.out.println("NotEqual");
		}
		
		BooleanLit result = new BooleanLit((Boolean) leftLiteral.getValue() != (Boolean) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(Division value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Division");
		}
		
		IntegerLit result = new IntegerLit((Integer) leftLiteral.getValue() / (Integer) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(Multiplication value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Multiplication");
		}
		
		IntegerLit result = new IntegerLit((Integer) leftLiteral.getValue() * (Integer) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(Greater value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Greater");
		}
		
		BooleanLit result = new BooleanLit((Integer) leftLiteral.getValue() > (Integer) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(GreaterEqual value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("GreaterEqual");
		}
		
		BooleanLit result = new BooleanLit((Integer) leftLiteral.getValue() >= (Integer) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(Less value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);

		if (debug) {
			System.out.println("Less");
		}
		
		BooleanLit result = new BooleanLit((Integer) leftLiteral.getValue() < (Integer) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(LessEqual value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("LessEqual");
		}
		
		BooleanLit result = new BooleanLit((Integer) leftLiteral.getValue() <= (Integer) rightLiteral.getValue());
		
		return result;
	}

	@Override
	public Literal visit(ASTNode node) {
		return null;
	}

	@Override
	public Literal visit(Form value) {
		value.getBlock().accept(this);
		
		if (debug) {
			System.out.println("Form");
		}
		
		return null;
	}

	@Override
	public Literal visit(Block value) {
		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}
		
		if (debug) {
			System.out.println("Block");
		}
		
		return null;
	}

	@Override
	public Literal visit(Statement value) {
		if (debug) {
			System.out.println("Statement");
		}
		return null;
	}

	@Override
	public Literal visit(Question value) {
		if (debug) {
			System.out.println("Question");
		}
		
		QL.symbolTable.addSymbol(value, null);
		
		return null;
	}

	@Override
	public Literal visit(ComputedQuestion value) {
		Literal expr = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("ComputedQuestion");
		}
		
		QL.symbolTable.addSymbol(value, expr);

		return expr;
	}

	@Override
	public Literal visit(IfStatement value) {
		Literal expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		
		if (debug) {
			System.out.println("ifStatement");
		}
		
		return new BooleanLit(true);
	}

	@Override
	public Literal visit(IfElseStatement value) {
		Literal expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		value.getBlock_else().accept(this);
		
		if (debug) {
			System.out.println("IfElseStatement");
		}
		return null;
	}

	@Override
	public Literal visit(Expression value) {
		if (debug) {
			System.out.println("Expression");
		}
		return null;
	}

	@Override
	public Literal visit(Literal value) {
		if (debug) {
			System.out.println("Literal");
		}
		return null;
	}

	@Override
	public Literal visit(BooleanLit value) {
		if (debug) {
			System.out.println("BooleanLit: "+value.getValue());
		}
		
		return new BooleanLit(value.getValue());
	}

	@Override
	public Literal visit(IdentifierLit value) {
		if (debug) {
			System.out.println("IdentifierLit: "+value.getValue());
		}
		
		Literal relatedLiteral = QL.symbolTable.getSymbolValueFromIdentifier(value);
				
		return relatedLiteral;
	}

	@Override
	public Literal visit(IntegerLit value) {
		if (debug) {
			System.out.println("IntegerLit: "+value.getValue());
		}
		return new IntegerLit(value.getValue());
	}

	@Override
	public Literal visit(StringLit value) {
		if (debug) {
			System.out.println("StringLit: "+value.getValue());
		}
		return new StringLit(value.getValue());
	}

}
