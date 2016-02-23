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
import nl.nicasso.ql.ast.type.Type;

public class EvaluatorVisitor implements Visitor<Literal> {
	
	private boolean debug = false;

	@Override
	public Literal visit(And value) {	
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("And");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new BooleanLiteral()) || !checkLiteral(rightLiteral, new BooleanLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new BooleanLiteral();
	}
	
	@Override
	public Literal visit(Addition value) {		
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Addition");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new NumericLiteral()) || !checkLiteral(rightLiteral, new NumericLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new NumericLiteral();
	}

	@Override
	public Literal visit(Subtraction value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Subtraction");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new NumericLiteral()) || !checkLiteral(rightLiteral, new NumericLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new NumericLiteral();
	}

	@Override
	public Literal visit(Or value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Or");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new BooleanLiteral()) || !checkLiteral(rightLiteral, new BooleanLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new BooleanLiteral();
	}

	@Override
	public Literal visit(Not value) {
		Literal exprLiteral = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Not");
		}
		
		if (!checkLiteral(exprLiteral, new BooleanLiteral())) {
			errors.add("Error: Incompatible Literal detected");
		}
		
		return new BooleanLiteral();
	}

	@Override
	public Literal visit(Parenthesis value) {		
		Literal exprLiteral = value.getExpr().accept(this);
		
		if (debug) {
			System.out.println("Parenthesis");
		}
		
		if (!checkLiteral(exprLiteral, new Literal())) {
			errors.add("Error: Incompatible Literal detected");
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
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new Literal()) || !checkLiteral(rightLiteral, new Literal())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new BooleanLiteral();
	}

	@Override
	public Literal visit(NotEqual value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);	
		
		if (debug) {
			System.out.println("NotEqual");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new Literal()) || !checkLiteral(rightLiteral, new Literal())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new BooleanLiteral();
	}

	@Override
	public Literal visit(Division value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Division");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new NumericLiteral()) || !checkLiteral(rightLiteral, new NumericLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new NumericLiteral();
	}

	@Override
	public Literal visit(Multiplication value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Multiplication");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new NumericLiteral()) || !checkLiteral(rightLiteral, new NumericLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new NumericLiteral();
	}

	@Override
	public Literal visit(Greater value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("Greater");
		}
		
		if (!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new NumericLiteral()) || !checkLiteral(rightLiteral, new NumericLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new BooleanLiteral();
	}

	@Override
	public Literal visit(GreaterEqual value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("GreaterEqual");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new NumericLiteral()) || !checkLiteral(rightLiteral, new NumericLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new BooleanLiteral();
	}

	@Override
	public Literal visit(Less value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);

		if (debug) {
			System.out.println("Less");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new NumericLiteral()) || !checkLiteral(rightLiteral, new NumericLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new BooleanLiteral();
	}

	@Override
	public Literal visit(LessEqual value) {
		Literal leftLiteral = value.getLeft().accept(this);

		Literal rightLiteral = value.getRight().accept(this);
		
		if (debug) {
			System.out.println("LessEqual");
		}
		
		if(!checkEqualLiterals(leftLiteral, rightLiteral)) {
			errors.add("Error: Literals are not equal.");
		}
		
		if (!checkLiteral(leftLiteral, new NumericLiteral()) || !checkLiteral(rightLiteral, new NumericLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		return new BooleanLiteral();
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
				
		return null;
	}

	@Override
	public Literal visit(ComputedQuestion value) {
		Literal expr = value.getExpr().accept(this);
		
		if (!checkEqualLiterals(expr, value.getLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		if (debug) {
			System.out.println("ComputedQuestion");
		}
		
		addComputedQuestion(value);
				
		return null;
	}

	@Override
	public Literal visit(IfStatement value) {
		Literal expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		
		if (!checkEqualLiterals(expr, new BooleanLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
		if (debug) {
			System.out.println("ifStatement");
		}
		return null;
	}

	@Override
	public Literal visit(IfElseStatement value) {
		Literal expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		value.getBlock_else().accept(this);
		
		if (!checkEqualLiterals(expr, new BooleanLiteral())) {
			errors.add("Error: Incompatible Literals detected");
		}
		
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
		return null;//new Literal();
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
		
		Literal relatedLiteral = null;//getIdentifierLiteral(value.getValue());
		
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
