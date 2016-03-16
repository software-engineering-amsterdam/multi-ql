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
	private MessageHandler messages;
	
	public TypeChecker(SymbolTable symbolTable, MessageHandler messages) {
		this.symbolTable = symbolTable;
		this.messages = messages;
		
		this.dependencies = new ArrayList<Pair<Identifier>>();
	}
	
	private Type binaryExpressionTraversal(Binary expr) {
		Type leftType = expr.getLeft().accept(this);
		Type rightType = expr.getRight().accept(this);
		
		Type type = expr.inferType(leftType, rightType);
		
		return type;
	}
	
	private Type unaryExpressionTraversal(Unary expr) {
		Type exprType = expr.getExpr().accept(this);
		
		Type type = expr.inferType(exprType);
		
		return type;
	}
		
	@Override
	public Type visit(And expr) {	
		return binaryExpressionTraversal(expr);
	}
	
	@Override
	public Type visit(Addition expr) {		
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(Subtraction expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(Or expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(Not expr) {
		return unaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(Parenthesis expr) {		
		return unaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(Equal expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(NotEqual expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(Division expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(Multiplication expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(Greater expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(GreaterEqual expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(Less expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Type visit(LessEqual expr) {
		return binaryExpressionTraversal(expr);
	}

	@Override
	public Void visit(Form value, Void ignore) {
		value.getBlock().accept(this, null);
		
		// @TODO This here?
		detectCyclicDependencies();
		
		return null;
	}

	@Override
	public Void visit(Block value, Void ignore) {
		for (Statement cur : value.getStatements()) {
			cur.accept(this, null);
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
		currentIdentifier = value.getId();
		
		Type expr = value.getExpr().accept(this);
		
		currentIdentifier = null;
		
		if (!expr.equals(value.getType())) {
			messages.addMessage(new IncompatibleTypes(value.getLocation()));
		}
		
		return null;
	}

	@Override
	public Void visit(IfStatement value, Identifier context) {
		Type expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this, null);
		
		Type type = value.checkAllowedTypes(expr);
		
		if (type.equals(new UnknownType())) {
			messages.addMessage(new IncompatibleTypes(value.getLocation()));
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
			messages.addMessage(new IncompatibleTypes(value.getLocation()));
		}
		
		return null;
	}

	@Override
	public Type visit(BooleanLit value) {
		return new BooleanType();
	}

	@Override
	public Type visit(Identifier value) {		
		Type entryType = symbolTable.getEntryType(value);
		
		addQuestionDependency(value);
		
		return entryType;
	}

	@Override
	public Type visit(IntegerLit value) {
		return new IntegerType();
	}

	@Override
	public Type visit(StringLit value) {
		return new StringType();
	}
	
	@Override
	public Type visit(MoneyLit value) {
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
			Pair<Identifier> tmp = new Pair<Identifier>(dependencies.get(i).getRight(), dependencies.get(i).getLeft());
			if (checkPairExistance(tmp)) {
				messages.addMessage(new CyclomaticDependency(tmp.getLeft().getLocation()));
				return true;
			}
		}
		return false;
	}
	
	private void makePairsTransitive(List<Pair<Identifier>> tmpDependencies) {	
		boolean keepRunning = true;
		
		while (keepRunning) {
			int size = dependencies.size();
			
			for (int i = 0; i < size; i++) {
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
	
	private Pair<Identifier> checkPairDependencyExistance(Pair<Identifier> p) {
		for (Pair<Identifier> tmp : dependencies) {
			if (tmp.getLeft().getValue().equals(p.getRight().getValue())) {
				return new Pair<Identifier>(p.getLeft(), tmp.getRight());
			}
		}
		return null;
	}
	
	private boolean checkPairExistance(Pair<Identifier> p) {
		for (Pair<Identifier> tmp : dependencies) {
			if (tmp.equals(p)) {
				return true;
			}
		}
		return false;
	}
	
}