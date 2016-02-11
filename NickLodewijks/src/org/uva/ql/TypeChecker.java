package org.uva.ql;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.IFStat;
import org.uva.ql.ui.QLQuestionaire;

public class TypeChecker {

	public TypeChecker(Form form) {
		QLQuestionaire questionaire;
		QLInterpreter interpreter;

		// Validate operand and condition types.
		new TypeCheckValidator(form);

		// Look for duplicate question declarations.
		new DuplicateFinder(form);

		interpreter = new QLInterpreter(form);

		questionaire = interpreter.getQuestionaire();
		questionaire.show();

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

	private class TypeCheckValidator extends ASTNodeVisitorAdapter<ValueType, SymbolTable> {

		public TypeCheckValidator(Form form) {
			form.accept(this, new SymbolTable());
		}

		@Override
		public ValueType visit(Form node, SymbolTable context) {
			node.getBody().accept(this, new SymbolTable());
			return null;
		}

		@Override
		public ValueType visit(Block node, SymbolTable context) {
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
			node.getVariableDecl().accept(this, context);

			checkType(node.getExpression(), context, node.getType());
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

		@Override
		public ValueType visit(Add node, SymbolTable context) {
			checkOperands(node, context, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Div node, SymbolTable context) {
			checkOperands(node, context, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Mul node, SymbolTable context) {
			checkOperands(node, context, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Sub node, SymbolTable context) {
			checkOperands(node, context, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

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

				msg = String.format(
						"[%s: %s] Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
						node.getLineIndex(), node.getCharIndex(), lhsType.getName(), rhsType.getName());

				error(msg);
			}

			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(GEq node, SymbolTable context) {
			checkOperands(node, context, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(GT node, SymbolTable context) {
			checkOperands(node, context, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(LEq node, SymbolTable context) {
			checkOperands(node, context, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(LT node, SymbolTable context) {
			checkOperands(node, context, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(NEq node, SymbolTable context) {
			checkOperands(node, context, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(And node, SymbolTable context) {
			checkOperands(node, context, ValueType.BOOLEAN);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(Or node, SymbolTable context) {
			checkOperands(node, context, ValueType.BOOLEAN);
			return ValueType.BOOLEAN;
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

				msg = String.format("[%s: %s] Type mismatch: '%s' should be of type '%s' but is of type '%s'. ",
						expr.getLineIndex(), expr.getCharIndex(), expr.getText(), expectedType.getName(),
						actual.getName());

				error(msg);
			}
		}
	}

	private class DuplicateFinder extends ASTNodeVisitorAdapter<Void, Void> {

		private Set<String> questions = new HashSet<String>();

		public DuplicateFinder(Form form) {
			form.accept(this, null);
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

	private void warn(String msg) {
		System.out.println(String.format("WARNING: %s", msg));
	}

	private void error(String msg) {
		System.err.println(String.format("ERROR: %s", msg));
	}
}
