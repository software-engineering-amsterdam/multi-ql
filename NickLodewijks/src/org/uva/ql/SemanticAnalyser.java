package org.uva.ql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.ValueType;
import org.uva.ql.ast.VariableDecl;
import org.uva.ql.ast.VariableIdentifier;
import org.uva.ql.ast.expr.Add;
import org.uva.ql.ast.expr.And;
import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.Div;
import org.uva.ql.ast.expr.Eq;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.GEq;
import org.uva.ql.ast.expr.GT;
import org.uva.ql.ast.expr.LEq;
import org.uva.ql.ast.expr.LT;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.Mul;
import org.uva.ql.ast.expr.NEq;
import org.uva.ql.ast.expr.Or;
import org.uva.ql.ast.expr.Sub;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.form.Block;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.InputQuestion;
import org.uva.ql.ast.form.Question;
import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.IFStat;

public class SemanticAnalyser {

	private Result result = new Result();

	public SemanticAnalyser() {

	}

	public Result analyse(Questionnaire questionnaire) {
		result = new Result();

		// Validate the following:
		// - Reference to undefined variables (=questions)
		// - Conditions that are not of the type boolean
		// - Operands of invalid type to operators
		// - Duplicate variable (=question) declaration
		new TypeCheckVisitor().visit(questionnaire);

		// Validate:
		// - duplicate question labels
		new DuplicateQuestionLabelVisitor(questionnaire);

		return result;
	}

	private static class SymbolTable {
		private Map<String, ValueType> nameToType = new HashMap<>();

		public SymbolTable() {
			nameToType = new HashMap<>();
		}

		public SymbolTable(SymbolTable table) {
			nameToType = new HashMap<>(table.nameToType);
		}

		public boolean contains(String name) {
			return nameToType.containsKey(name);
		}

		public void add(String name, ValueType type) {
			nameToType.put(name, type);
		}

		public ValueType getType(String name) {
			return nameToType.get(name);
		}
	}

	@FunctionalInterface
	private static interface TypeChecker<T extends ASTNode> {
		public ValueType check(T node, SymbolTable symbols);
	}

	private class TypeCheckVisitor extends ASTNodeVisitorAdapter<ValueType, SymbolTable> {

		private final TypeChecker<BinaryExpr> numberRelation;
		private final TypeChecker<BinaryExpr> booleanRelation;
		private final TypeChecker<BinaryExpr> arithmeticOperation;

		public TypeCheckVisitor() {
			numberRelation = (BinaryExpr a, SymbolTable b) -> {
				checkOperands(a, b, ValueType.INTEGER);
				return ValueType.BOOLEAN;
			};

			booleanRelation = (BinaryExpr a, SymbolTable b) -> {
				checkOperands(a, b, ValueType.BOOLEAN);
				return ValueType.BOOLEAN;
			};

			arithmeticOperation = (BinaryExpr a, SymbolTable b) -> {
				checkOperands(a, b, ValueType.INTEGER);
				return ValueType.INTEGER;
			};
		}

		public void visit(Questionnaire q) {
			q.accept(this, new SymbolTable());
		}

		@Override
		public ValueType visit(Questionnaire node, SymbolTable context) {
			for (Form form : node.getForms()) {
				form.accept(this, context);
			}

			return null;
		}

		@Override
		public ValueType visit(Form node, SymbolTable context) {
			// The body of every form has its own SymbolTable
			node.getBody().accept(this, new SymbolTable());
			return null;
		}

		@Override
		public ValueType visit(Block node, SymbolTable context) {
			// Copy the SymbolTable for scoping of variables
			context = new SymbolTable(context);

			// First traverse the questions.
			for (Question question : node.getQuestions()) {
				question.accept(this, context);
			}

			for (IFStat statement : node.getIfStatements()) {
				statement.accept(this, context);
			}

			return null;
		}

		@Override
		public ValueType visit(IFStat node, SymbolTable context) {
			checkType(node.getExpression(), context, ValueType.BOOLEAN);
			node.getBody().accept(this, context);
			return null;
		}

		@Override
		public ValueType visit(ComputedQuestion node, SymbolTable context) {
			checkType(node.getExpression(), context, node.getType());

			node.getVariableDecl().accept(this, context);
			return null;
		}

		@Override
		public ValueType visit(InputQuestion node, SymbolTable context) {
			node.getVariableDecl().accept(this, context);
			return null;
		}

		@Override
		public ValueType visit(VariableDecl node, SymbolTable context) {
			String variableName;
			ValueType type;

			variableName = node.getId().getName();
			type = node.getType().getType();
			if (context.contains(variableName)) {
				error("Duplicate variable declaration " + node);
			} else {
				context.add(variableName, type);
			}

			return type;
		}

		@Override
		public ValueType visit(LiteralExpr node, SymbolTable context) {
			return node.getLiteral().accept(this, context);
		}

		@Override
		public ValueType visit(VariableExpr node, SymbolTable context) {
			return node.getVariableId().accept(this, context);
		}

		@Override
		public ValueType visit(VariableIdentifier node, SymbolTable context) {
			ValueType type;

			type = context.getType(node.getName());
			if (type == null) {
				error("Undeclared variable " + node + node.getName());
			}

			node.setType(type);

			return type;
		}

		// Literals
		@Override
		public ValueType visit(BooleanLiteral node, SymbolTable context) {
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(IntegerLiteral node, SymbolTable context) {
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(StringLiteral node, SymbolTable context) {
			return ValueType.STRING;
		}

		// Arithmetic operations
		@Override
		public ValueType visit(Add node, SymbolTable context) {
			return arithmeticOperation.check(node, context);
		}

		@Override
		public ValueType visit(Div node, SymbolTable context) {
			return arithmeticOperation.check(node, context);
		}

		@Override
		public ValueType visit(Mul node, SymbolTable context) {
			return arithmeticOperation.check(node, context);
		}

		@Override
		public ValueType visit(Sub node, SymbolTable context) {
			return arithmeticOperation.check(node, context);
		}

		// Equality relations
		@Override
		public ValueType visit(Eq node, SymbolTable context) {
			Expr lhs;
			ValueType lhsType;
			ValueType rhsType;
			Expr rhs;

			lhs = node.left();
			lhsType = lhs.accept(this, context);
			rhs = node.right();
			rhsType = rhs.accept(this, context);
			if (lhsType != rhsType) {
				String msg;

				msg = String.format("%s: Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
						node.getSourceLocation(), lhsType.getName(), rhsType.getName());

				error(msg);
			}

			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(NEq node, SymbolTable context) {
			Expr lhs;
			ValueType lhsType;
			ValueType rhsType;
			Expr rhs;

			lhs = node.left();
			lhsType = lhs.accept(this, context);
			rhs = node.right();
			rhsType = rhs.accept(this, context);
			if (lhsType != rhsType) {
				String msg;

				msg = String.format("%s: Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
						node.getSourceLocation(), lhsType.getName(), rhsType.getName());

				error(msg);
			}

			return ValueType.BOOLEAN;
		}

		// Number relations
		@Override
		public ValueType visit(GEq node, SymbolTable context) {
			return numberRelation.check(node, context);
		}

		@Override
		public ValueType visit(GT node, SymbolTable context) {
			return numberRelation.check(node, context);
		}

		@Override
		public ValueType visit(LEq node, SymbolTable context) {
			return numberRelation.check(node, context);
		}

		@Override
		public ValueType visit(LT node, SymbolTable context) {
			return numberRelation.check(node, context);
		}

		// Boolean relations
		@Override
		public ValueType visit(And node, SymbolTable context) {
			return booleanRelation.check(node, context);
		}

		@Override
		public ValueType visit(Or node, SymbolTable context) {
			return booleanRelation.check(node, context);
		}

		private void checkOperands(BinaryExpr expr, SymbolTable context, ValueType expectedType) {
			checkType(expr.left(), context, expectedType);
			checkType(expr.right(), context, expectedType);
		}

		private void checkType(Expr expr, SymbolTable context, ValueType expectedType) {
			ValueType actual;

			actual = expr.accept(this, context);

			if (actual == null) {
				error("Unknown type for " + expr);
			} else if (actual != expectedType) {
				String msg;

				msg = String.format("%s: Type mismatch: '%s' should be of type '%s' but is of type '%s'. ",
						expr.getSourceLocation(), expr.getSourceText(), expectedType.getName(), actual.getName());

				error(msg);
			}
		}
	}

	private class DuplicateQuestionLabelVisitor extends ASTNodeVisitorAdapter<Void, Void> {

		private Set<String> questions = new HashSet<String>();

		public DuplicateQuestionLabelVisitor(Questionnaire q) {
			q.accept(this, null);
		}

		private void addQuestion(Question node) {
			String label;

			label = node.getLabel();
			if (!questions.add(label)) {
				warn("Duplicate label:" + label);
			}
		}

		@Override
		public Void visit(ComputedQuestion node, Void context) {
			addQuestion(node);
			return super.visit(node, context);
		}

		@Override
		public Void visit(InputQuestion node, Void context) {
			addQuestion(node);
			return super.visit(node, context);
		}
	}

	public static class Result {
		private final List<String> messages = new ArrayList<>();

		private Result() {

		}

		private void add(String msg) {
			messages.add(msg);
		}

		public boolean hasMessages() {
			return messages.isEmpty();
		}

		public List<String> getMessages() {
			return Collections.unmodifiableList(messages);
		}

	}

	private void warn(String msg) {
		result.add(String.format("WARNING: %s", msg));
	}

	private void error(String msg) {
		result.add(String.format("ERROR: %s", msg));
	}
}
