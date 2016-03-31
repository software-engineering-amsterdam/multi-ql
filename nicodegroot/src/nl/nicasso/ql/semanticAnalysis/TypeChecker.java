package nl.nicasso.ql.semanticAnalysis;

import nl.nicasso.ql.ast.nodes.expressions.Binary;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.expressions.Parenthesis;
import nl.nicasso.ql.ast.nodes.expressions.Unary;
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
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.StringType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.ast.nodes.types.UnknownType;
import nl.nicasso.ql.semanticAnalysis.messageHandling.MessageHandler;
import nl.nicasso.ql.semanticAnalysis.messageHandling.errors.IncompatibleTypes;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;
import nl.nicasso.ql.utils.Pair;
import nl.nicasso.ql.visitors.ExpressionVisitor;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;

public class TypeChecker implements StructureVisitor<Void, Void>, StatementVisitor<Void, Identifier>,
		ExpressionVisitor<Type, Identifier> {

	private SymbolTable symbolTable;
	private MessageHandler messageHandler;
	private QuestionDependencies dependencies;

	public TypeChecker(Form ast, SymbolTable symbolTable, MessageHandler messages) {
		this.symbolTable = symbolTable;
		this.messageHandler = messages;

		this.dependencies = new QuestionDependencies(messageHandler);

		ast.accept(this, null);

		dependencies.checkForCyclicDependencies();
	}

	public TypeChecker(SymbolTable symbolTable, MessageHandler messages) {
		this.symbolTable = symbolTable;
		this.messageHandler = messages;
	}

	private Type binaryExpressionTraversal(Binary expression, Identifier context) {
		Type leftType = expression.getLeft().accept(this, context);
		Type rightType = expression.getRight().accept(this, context);
		return expression.inferType(leftType, rightType);
	}

	private Type unaryExpressionTraversal(Unary expression, Identifier context) {
		Type exprType = expression.getExpression().accept(this, context);
		return expression.inferType(exprType);
	}

	@Override
	public Type visit(And expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Addition expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Subtraction expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Or expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Not expression, Identifier context) {
		return unaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Parenthesis expression, Identifier context) {
		return unaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Equal expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(NotEqual expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Division expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Multiplication expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Greater expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(GreaterEqual expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(Less expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Type visit(LessEqual expression, Identifier context) {
		return binaryExpressionTraversal(expression, context);
	}

	@Override
	public Void visit(Form structure, Void ignore) {
		structure.getBlock().accept(this, null);
		return null;
	}

	@Override
	public Void visit(Block structure, Void ignore) {
		for (Statement currentStatement : structure.getStatements()) {
			currentStatement.accept(this, null);
		}
		return null;
	}

	@Override
	public Void visit(Question statement, Identifier context) {
		return null;
	}

	@Override
	public Void visit(ComputedQuestion statement, Identifier context) {
		Type type = statement.getExpression().accept(this, statement.getIdentifier());

		if (!type.equals(statement.getType())) {
			messageHandler.addErrorMessage(new IncompatibleTypes(statement.getLocation(), statement.getType()));
		}

		return null;
	}

	@Override
	public Void visit(IfStatement statement, Identifier context) {
		Type expr = statement.getExpression().accept(this, context);
		statement.getBlockIf().accept(this, null);

		Type type = statement.inferType(expr);

		if (type.equals(new UnknownType())) {
			messageHandler.addErrorMessage(new IncompatibleTypes(statement.getLocation(), new BooleanType()));
		}

		return null;
	}

	@Override
	public Void visit(IfElseStatement statement, Identifier context) {
		Type expr = statement.getExpression().accept(this, context);
		statement.getBlockIf().accept(this, null);
		statement.getBlockElse().accept(this, null);

		Type type = statement.inferType(expr);

		if (type.equals(new UnknownType())) {
			messageHandler.addErrorMessage(new IncompatibleTypes(statement.getLocation(), new BooleanType()));
		}

		return null;
	}

	@Override
	public Type visit(BooleanLiteral literal, Identifier context) {
		return new BooleanType();
	}

	@Override
	public Type visit(Identifier identifier, Identifier context) {
		Type entryType = symbolTable.getEntryType(identifier);

		if (context != null) {
			addQuestionDependency(identifier, context);
		}

		return entryType;
	}

	@Override
	public Type visit(IntegerLiteral literal, Identifier context) {
		return new IntegerType();
	}

	@Override
	public Type visit(StringLiteral literal, Identifier context) {
		return new StringType();
	}

	@Override
	public Type visit(MoneyLiteral literal, Identifier context) {
		return new MoneyType();
	}

	private void addQuestionDependency(Identifier identifier, Identifier context) {
		dependencies.add(new Pair<Identifier>(context, identifier));
	}

}