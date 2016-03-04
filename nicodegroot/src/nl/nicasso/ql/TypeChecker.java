package nl.nicasso.ql;

import java.util.ArrayList;
import java.util.List;

import nl.nicasso.ql.ast.expressions.Binary;
import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.ast.expressions.Parenthesis;
import nl.nicasso.ql.ast.expressions.Unary;
import nl.nicasso.ql.ast.expressions.additive.Addition;
import nl.nicasso.ql.ast.expressions.additive.Subtraction;
import nl.nicasso.ql.ast.expressions.conditional.And;
import nl.nicasso.ql.ast.expressions.conditional.Not;
import nl.nicasso.ql.ast.expressions.conditional.Or;
import nl.nicasso.ql.ast.expressions.equality.Equal;
import nl.nicasso.ql.ast.expressions.equality.NotEqual;
import nl.nicasso.ql.ast.expressions.multiplicative.Division;
import nl.nicasso.ql.ast.expressions.multiplicative.Multiplication;
import nl.nicasso.ql.ast.expressions.relational.Greater;
import nl.nicasso.ql.ast.expressions.relational.GreaterEqual;
import nl.nicasso.ql.ast.expressions.relational.Less;
import nl.nicasso.ql.ast.expressions.relational.LessEqual;
import nl.nicasso.ql.ast.literals.BooleanLit;
import nl.nicasso.ql.ast.literals.IntegerLit;
import nl.nicasso.ql.ast.literals.MoneyLit;
import nl.nicasso.ql.ast.literals.StringLit;
import nl.nicasso.ql.ast.statements.ComputedQuestion;
import nl.nicasso.ql.ast.statements.IfElseStatement;
import nl.nicasso.ql.ast.statements.IfStatement;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.ast.statements.Statement;
import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.structures.Form;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.IntegerType;
import nl.nicasso.ql.ast.types.MoneyType;
import nl.nicasso.ql.ast.types.StringType;
import nl.nicasso.ql.ast.types.Type;
import nl.nicasso.ql.ast.types.UnknownType;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.utils.Pair;
import nl.nicasso.ql.visitors.ExpressionVisitor;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;

public class TypeChecker implements StructureVisitor<Void>, StatementVisitor<Void, Identifier>, ExpressionVisitor<Type> {
		
	private List<String> errors;
	private List<String> warnings;
	
	private List<Pair<Identifier>> dependencies;
	private Identifier currentIdentifier;

	private SymbolTable symbolTable;
	
	public TypeChecker(SymbolTable symbolTable) {
		errors = new ArrayList<String>();
		warnings = new ArrayList<String>();
		
		this.symbolTable = symbolTable;
		this.dependencies = new ArrayList<Pair<Identifier>>();
	}
	
	private Type binaryExpressionTraversal(Binary expr) {
		Type leftType = expr.getLeft().accept(this);
		Type rightType = expr.getRight().accept(this);
		
		Type type = expr.inferType(leftType, rightType);
		
		if (type.equals(new UnknownType())) {
			errors.add("Error: Incompatible types detected. "+expr.toString());
		}
		
		return type;
	}
	
	private Type unaryExpressionTraversal(Unary expr) {
		Type exprType = expr.getExpr().accept(this);
		
		Type type = expr.inferType(exprType);
		
		if (type.equals(new UnknownType())) {
			errors.add("Error: Incompatible types detected. "+expr.toString());
		}
		
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
	public Void visit(Form value) {
		value.getBlock().accept(this);
		
		// @TODO This here?
		detectCyclicDependencies();
		
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
	public Void visit(Question value, Identifier context) {
		return null;
	}

	@Override
	public Void visit(ComputedQuestion value, Identifier context) {
		// @TODO: Temp variable smell! Pass it on as a parameter!
		currentIdentifier = value.getId();
		
		Type expr = value.getExpr().accept(this);
		
		if (!expr.equals(value.getType())) {
			errors.add("Error: Incompatible types detected (ComputedQuestion): "+value.getId().getValue());
		}
		
		return null;
	}

	@Override
	public Void visit(IfStatement value, Identifier context) {
		Type expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		
		Type type = value.checkAllowedTypes(expr);
		
		if (type.equals(new UnknownType())) {
			errors.add("Error: Incompatible types detected (IfStatement)");
		}
		
		return null;
	}

	@Override
	public Void visit(IfElseStatement value, Identifier context) {
		Type expr = value.getExpr().accept(this);
		value.getBlock_if().accept(this);
		value.getBlock_else().accept(this);
		
		Type type = value.checkAllowedTypes(expr);
		
		if (type.equals(new UnknownType())) {
			errors.add("Error: Incompatible types detected (IfElseStatement)");
		}
		
		return null;
	}

	@Override
	public Type visit(BooleanLit value) {
		return new BooleanType();
	}

	@Override
	public Type visit(Identifier value) {
		SymbolTableEntry entry = symbolTable.getEntry(value);
		
		addQuestionDependency(value);
		
		return entry.getType();
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
	
	public List<String> getErrors() {
		return errors;
	}
	
	public List<String> getWarnings() {
		return warnings;
	}
	
	private void addQuestionDependency(Identifier currentId) {
		dependencies.add(new Pair<Identifier>(currentIdentifier, currentId));
	}
	
	public boolean detectCyclicDependencies() {
		
		makePairsTransitive(dependencies);
		
		int size = dependencies.size();
		for (int i = 0; i < size; i++) {
			Pair<Identifier> tmp = new Pair<Identifier>(dependencies.get(i).getRight(), dependencies.get(i).getLeft());
			if (checkPairExistance(tmp)) {
				errors.add("A cyclic dependency has been detected!");
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
