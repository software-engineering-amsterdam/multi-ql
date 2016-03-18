package nl.nicasso.ql.semanticAnalysis;

import java.util.ArrayList;
import java.util.List;

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
import nl.nicasso.ql.semanticAnalysis.messageHandling.errors.CyclomaticDependency;
import nl.nicasso.ql.semanticAnalysis.messageHandling.errors.IncompatibleTypes;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;
import nl.nicasso.ql.utils.Pair;
import nl.nicasso.ql.visitors.ExpressionVisitor;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;

public class TypeChecker implements StructureVisitor<Void, Void>, StatementVisitor<Void, Identifier>, ExpressionVisitor<Type> {
	
	private List<Pair<Identifier>> dependencies;
	private Identifier currentIdentifier;

	private SymbolTable symbolTable;
	private MessageHandler messageHandler;
	
	public TypeChecker(SymbolTable symbolTable, MessageHandler messages) {
		this.symbolTable = symbolTable;
		this.messageHandler = messages;
		
		this.dependencies = new ArrayList<Pair<Identifier>>();
	}
	
	private Type binaryExpressionTraversal(Binary expression) {
		Type leftType = expression.getLeft().accept(this);
		Type rightType = expression.getRight().accept(this);
		
		Type type = expression.inferType(leftType, rightType);
		
		return type;
	}
	
	private Type unaryExpressionTraversal(Unary expression) {
		Type exprType = expression.getExpr().accept(this);
		
		Type type = expression.inferType(exprType);
		
		return type;
	}
		
	@Override
	public Type visit(And expression) {	
		return binaryExpressionTraversal(expression);
	}
	
	@Override
	public Type visit(Addition expression) {		
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(Subtraction expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(Or expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(Not expression) {
		return unaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(Parenthesis expression) {		
		return unaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(Equal expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(NotEqual expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(Division expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(Multiplication expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(Greater expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(GreaterEqual expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(Less expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Type visit(LessEqual expression) {
		return binaryExpressionTraversal(expression);
	}

	@Override
	public Void visit(Form value, Void ignore) {
		value.getBlock().accept(this, null);
		
		// @TODO This here? (SEE COMMENT TOP QUESTIONINDEXER)
		detectCyclicDependencies();
		
		return null;
	}

	@Override
	public Void visit(Block value, Void ignore) {
		for (Statement currentStatement : value.getStatements()) {
			currentStatement.accept(this, null);
		}
		
		return null;
	}

	@Override
	public Void visit(Question value, Identifier context) {
		return null;
	}

	@Override
	public Void visit(ComputedQuestion value, Identifier context) {
		// @TODO: Temp variable smell! Pass it on as a parameter!
		currentIdentifier = value.getIdentifier();
		
		Type type = value.getExpr().accept(this);
		
		currentIdentifier = null;
		
		if (!type.equals(value.getType())) {
			messageHandler.addErrorMessage(new IncompatibleTypes(value.getLocation(), value.getType()));
		}
		
		return null;
	}

	@Override
	public Void visit(IfStatement value, Identifier context) {
		Type expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this, null);
		
		Type type = value.checkAllowedTypes(expr);
		
		if (type.equals(new UnknownType())) {
			messageHandler.addErrorMessage(new IncompatibleTypes(value.getLocation(), new BooleanType()));
		}
		
		return null;
	}

	@Override
	public Void visit(IfElseStatement value, Identifier context) {
		Type expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this, null);
		value.getBlock_else().accept(this, null);
		
		Type type = value.checkAllowedTypes(expr);
		
		if (type.equals(new UnknownType())) {
			messageHandler.addErrorMessage(new IncompatibleTypes(value.getLocation(), new BooleanType()));
		}
		
		return null;
	}

	@Override
	public Type visit(BooleanLiteral value) {
		return new BooleanType();
	}

	@Override
	public Type visit(Identifier value) {		
		Type entryType = symbolTable.getEntryType(value);
		
		addQuestionDependency(value);
		
		return entryType;
	}

	@Override
	public Type visit(IntegerLiteral value) {
		return new IntegerType();
	}

	@Override
	public Type visit(StringLiteral value) {
		return new StringType();
	}
	
	@Override
	public Type visit(MoneyLiteral value) {
		return new MoneyType();
	}
	
	private void addQuestionDependency(Identifier currentId) {
		// UGLY!
		if (currentIdentifier != null) {
			dependencies.add(new Pair<Identifier>(currentIdentifier, currentId));
		}
	}
	
	public boolean detectCyclicDependencies() {
		makePairsTransitive(dependencies);
		
		int size = dependencies.size();
		for (int i = 0; i < size; i++) {
			Pair<Identifier> currentPair = new Pair<Identifier>(dependencies.get(i).getRight(), dependencies.get(i).getLeft());
			if (checkPairExistance(currentPair)) {
				messageHandler.addErrorMessage(new CyclomaticDependency(currentPair.getLeft().getLocation()));
				return true;
			}
		}
		return false;
	}
	
	// @TODO Look again at this method, it does receive the dependencies, but does not return anything.
	// Seems a bit strange, not clear.
	private void makePairsTransitive(List<Pair<Identifier>> tmpDependencies) {	
		boolean keepRunning = true;
		
		while (keepRunning) {
			int amountOfDependencies = dependencies.size();
			
			for (int i = 0; i < amountOfDependencies; i++) {
				Pair<Identifier> transitivePair = checkPairDependencyExistance(dependencies.get(i));
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
	}
	
	private Pair<Identifier> checkPairDependencyExistance(Pair<Identifier> pair) {
		for (Pair<Identifier> currentPair : dependencies) {
			if (currentPair.getLeft().getIdentifier().equals(pair.getRight().getIdentifier())) {
				return new Pair<Identifier>(pair.getLeft(), currentPair.getRight());
			}
		}
		return null;
	}
	
	private boolean checkPairExistance(Pair<Identifier> pair) {
		for (Pair<Identifier> currentPair : dependencies) {
			if (currentPair.equals(pair)) {
				return true;
			}
		}
		return false;
	}
	
}