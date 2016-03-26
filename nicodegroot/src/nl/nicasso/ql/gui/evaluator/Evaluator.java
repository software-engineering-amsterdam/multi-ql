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
import nl.nicasso.ql.ast.nodes.literals.BooleanLiteral;
import nl.nicasso.ql.ast.nodes.literals.IntegerLiteral;
import nl.nicasso.ql.ast.nodes.literals.MoneyLiteral;
import nl.nicasso.ql.ast.nodes.literals.StringLiteral;
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

public class Evaluator implements StructureVisitor<Value, Void>, StatementVisitor<Value, Void>, ExpressionVisitor<Value, Void> {
	
	private StateTable stateTable;
	
	public Evaluator(StateTable stateTable) {
		this.stateTable = stateTable;
	}

	@Override
	public Value visit(And expression, Void context) {	
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
				
		return left.equal(right);
	}
	
	@Override
	public Value visit(Addition expression, Void context) {		
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.addition(right);
	}

	@Override
	public Value visit(Subtraction expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.subtraction(right);
	}

	@Override
	public Value visit(Or expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.or(right);
	}

	@Override
	public Value visit(Not expression, Void context) {
		Value exprValue = expression.getExpr().accept(this, null);
		
		return exprValue.not();
	}

	@Override
	public Value visit(Parenthesis expression, Void context) {		
		Value exprValue = expression.getExpr().accept(this, null);
		
		return exprValue;
	}

	@Override
	public Value visit(Equal expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.equal(right);
	}

	@Override
	public Value visit(NotEqual expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);	
		
		return left.notEqual(right);
	}

	@Override
	public Value visit(Division expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.division(right);
	}

	@Override
	public Value visit(Multiplication expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.multiplication(right);
	}

	@Override
	public Value visit(Greater expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.greater(right);
	}

	@Override
	public Value visit(GreaterEqual expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.greaterEqual(right);
	}

	@Override
	public Value visit(Less expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.less(right);
	}

	@Override
	public Value visit(LessEqual expression, Void context) {
		Value left = expression.getLeft().accept(this, null);
		Value right = expression.getRight().accept(this, null);
		
		return left.lessEqual(right);
	}

	@Override
	public Value visit(Form structure, Void ignore) {
		structure.getBlock().accept(this, null);
		
		return null;
	}

	@Override
	public Value visit(Block structure, Void ignore) {
		for (Statement cur : structure.getStatements()) {
			cur.accept(this, ignore);
		}
		
		return null;
	}

	@Override
	public Value visit(Question statement, Void context) {
		// getDefaultValue? Is this ugly?!
		StateTableEntry ste = new StateTableEntry(statement.getType().getDefaultValue());
		stateTable.add(statement.getIdentifier(), ste);
		
		return null;
	}

	@Override
	public Value visit(ComputedQuestion statement, Void context) {
		Value exprValue = statement.getExpr().accept(this, null);

		StateTableEntry ste = new StateTableEntry(exprValue);
		stateTable.add(statement.getIdentifier(), ste);                                                             

		return null;
	}

	@Override
	public Value visit(IfStatement statement, Void context) {
		statement.getExpr().accept(this, null);
		statement.getBlock_if().accept(this, null);
		
		return null;
	}

	@Override
	public Value visit(IfElseStatement statement, Void context) {
		statement.getExpr().accept(this, null);
		statement.getBlock_if().accept(this, null);
		statement.getBlock_else().accept(this, null);
		
		return null;
	}

	@Override
	public Value visit(BooleanLiteral expression, Void context) {
		return new BooleanValue(expression.getValue());
	}

	@Override
	public Value visit(Identifier expression, Void context) {		
		StateTableEntry entry = stateTable.getEntry(expression);
						
		return entry.getValue();
	}

	@Override
	public Value visit(IntegerLiteral expression, Void context) {
		return new IntegerValue((Integer) expression.getValue());
	}

	@Override
	public Value visit(StringLiteral expression, Void context) {
		return new StringValue(expression.getValue());
	}

	@Override
	public Value visit(MoneyLiteral expression, Void context) {
		return new MoneyValue(expression.getValue());
	}

}