package nl.nicasso.ql.ast;

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
import nl.nicasso.ql.ast.type.MoneyType;

public abstract class VisitorAbstraction implements Visitor<Void> {

	@Override
	public Void visit(ASTNode node) {
		return null;
	}

	@Override
	public Void visit(Form value) {
		value.getBlock().accept(this);
		return null;
	}

	@Override
	public Void visit(Block value) {
		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}
		return null;
	}

	@Override
	public Void visit(Statement value) {
		return null;
	}

	@Override
	public Void visit(Question value) {
		return null;
	}

	@Override
	public Void visit(ComputedQuestion value) {
		value.getExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(IfStatement value) {
		value.getExpr().accept(this);
		value.getBlock_if().accept(this);	
		return null;
	}

	@Override
	public Void visit(IfElseStatement value) {
		value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		value.getBlock_else().accept(this);
		return null;
	}

	@Override
	public Void visit(Expression value) {
		return null;
	}

	@Override
	public Void visit(Addition value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(Subtraction value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(And value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(Or value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(Not value) {
		value.getExpr().accept(this);	
		return null;
	}

	@Override
	public Void visit(Parenthesis value) {
		value.getExpr().accept(this);
		return null;
	}

	@Override
	public Void visit(Equal value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(NotEqual value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);	
		return null;
	}

	@Override
	public Void visit(Division value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(Multiplication value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(Greater value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(GreaterEqual value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(Less value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
		return null;
	}

	@Override
	public Void visit(LessEqual value) {
		value.getLeft().accept(this);
		value.getRight().accept(this);
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
	public Void visit(IdentifierLit value) {
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

}
