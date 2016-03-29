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

public class Evaluator implements StructureVisitor<Void, StateTable>, StatementVisitor<Void, StateTable>, ExpressionVisitor<Value, StateTable> {
	
	@Override
	public Value visit(And expression, StateTable stateTable) {	
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);

		return left.equal(right);
	}
	
	@Override
	public Value visit(Addition expression, StateTable stateTable) {		
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.addition(right);
	}

	@Override
	public Value visit(Subtraction expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.subtraction(right);
	}

	@Override
	public Value visit(Or expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.or(right);
	}

	@Override
	public Value visit(Not expression, StateTable stateTable) {
		Value exprValue = expression.getExpr().accept(this, stateTable);
		
		return exprValue.not();
	}

	@Override
	public Value visit(Parenthesis expression, StateTable stateTable) {		
		Value exprValue = expression.getExpr().accept(this, stateTable);
		
		return exprValue;
	}

	@Override
	public Value visit(Equal expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.equal(right);
	}

	@Override
	public Value visit(NotEqual expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);	
		
		return left.notEqual(right);
	}

	@Override
	public Value visit(Division expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.division(right);
	}

	@Override
	public Value visit(Multiplication expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.multiplication(right);
	}

	@Override
	public Value visit(Greater expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.greater(right);
	}

	@Override
	public Value visit(GreaterEqual expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.greaterEqual(right);
	}

	@Override
	public Value visit(Less expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.less(right);
	}

	@Override
	public Value visit(LessEqual expression, StateTable stateTable) {
		Value left = expression.getLeft().accept(this, stateTable);
		Value right = expression.getRight().accept(this, stateTable);
		
		return left.lessEqual(right);
	}

	@Override
	public Void visit(Form structure, StateTable stateTable) {
		structure.getBlock().accept(this, stateTable);
		
		return null;
	}

	@Override
	public Void visit(Block structure, StateTable stateTable) {
		for (Statement cur : structure.getStatements()) {
			cur.accept(this, stateTable);
		}
		
		return null;
	}

	@Override
	public Void visit(Question statement, StateTable stateTable) {
		StateTableEntry ste = new StateTableEntry(statement.getDefaultValue());
		stateTable.add(statement.getIdentifier(), ste);
		
		return null;
	}

	@Override
	public Void visit(ComputedQuestion statement, StateTable stateTable) {
		Value exprValue = statement.getExpr().accept(this, stateTable);

		StateTableEntry ste = new StateTableEntry(exprValue);
		stateTable.add(statement.getIdentifier(), ste);                                                             

		return null;
	}

	@Override
	public Void visit(IfStatement statement, StateTable stateTable) {
		statement.getExpr().accept(this, stateTable);
		statement.getBlock_if().accept(this, stateTable);
		
		return null;
	}

	@Override
	public Void visit(IfElseStatement statement, StateTable stateTable) {
		statement.getExpr().accept(this, stateTable);
		statement.getBlock_if().accept(this, stateTable);
		statement.getBlock_else().accept(this, stateTable);
		
		return null;
	}

	@Override
	public Value visit(BooleanLiteral expression, StateTable stateTable) {
		return new BooleanValue(expression.getValue());
	}

	@Override
	public Value visit(Identifier expression, StateTable stateTable) {		
		StateTableEntry entry = stateTable.getEntry(expression);
						
		return entry.getValue();
	}

	@Override
	public Value visit(IntegerLiteral expression, StateTable stateTable) {
		return new IntegerValue((Integer) expression.getValue());
	}

	@Override
	public Value visit(StringLiteral expression, StateTable stateTable) {
		return new StringValue(expression.getValue());
	}

	@Override
	public Value visit(MoneyLiteral expression, StateTable stateTable) {
		return new MoneyValue(expression.getValue());
	}

}