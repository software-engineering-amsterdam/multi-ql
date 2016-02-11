package org.uva.ql;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;

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
import org.uva.ql.ast.expr.Mul;
import org.uva.ql.ast.expr.NEq;
import org.uva.ql.ast.expr.Or;
import org.uva.ql.ast.expr.Sub;
import org.uva.ql.ast.form.ComputedQuestion;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.InputQuestion;
import org.uva.ql.ast.form.Question;
import org.uva.ql.ast.stat.IFStat;
import org.uva.ql.ui.QLQuestionaire;

public class TypeChecker {

	public TypeChecker(Form form) {
		QLQuestionaire questionaire;
		QLInterpreter interpreter;

		// Initialize symbol table.
		form.accept(new VariabelChecker());

		// Validate operand and condition types.
		form.accept(new TypeCheckValidator());

		// Look for duplicate question declarations.
		form.accept(new DuplicateFinder());

		interpreter = new QLInterpreter();
		form.accept(interpreter);

		questionaire = interpreter.getQuestionaire();
		questionaire.show();

	}

	private class VariabelChecker extends ASTNodeVisitorAdapter {

		private Map<String, VariableDecl> symbols = new HashMap<String, VariableDecl>();

		@Override
		public void visit(VariableDecl node) {
			String variableName;

			variableName = node.getId().getName();
			if (symbols.containsKey(variableName)) {
				error("Duplicate variable declaration " + node);
				return;
			}

			symbols.put(variableName, node);
		}

		@Override
		public void visit(VariableIdentifier node) {
			VariableDecl variableDecl;
			String variableName;

			variableName = node.getName();

			variableDecl = symbols.get(variableName);
			if (variableDecl == null) {
				error("Reference to undefined question " + node + " " + variableName);
				return;
			}

			node.setType(variableDecl.getType().getType());
		}
	}

	private class TypeCheckValidator extends ASTNodeVisitorAdapter {

		@Override
		public void visit(IFStat node) {
			checkType(node.getExpression(), ValueType.BOOLEAN);
			super.visit(node);
		}

		@Override
		public void visit(Add node) {
			checkOperands(node, ValueType.INTEGER);
			super.visit(node);
		}

		@Override
		public void visit(Div node) {
			checkOperands(node, ValueType.INTEGER);
			super.visit(node);
		}

		@Override
		public void visit(Mul node) {
			checkOperands(node, ValueType.INTEGER);
			super.visit(node);
		}

		@Override
		public void visit(Sub node) {
			checkOperands(node, ValueType.INTEGER);
			super.visit(node);
		}

		@Override
		public void visit(Eq node) {
			Expr lhs;
			Expr rhs;

			lhs = node.left();
			rhs = node.right();
			if (lhs.type() != rhs.type()) {
				String msg;

				msg = String.format(
						"[%s: %s] Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
						node.getLineIndex(), node.getCharIndex(), lhs.type().getName(), rhs.type().getName());

				error(msg);
			}

			super.visit(node);
		}

		@Override
		public void visit(ComputedQuestion node) {
			checkType(node.getExpression(), node.getVariableId().getType());

			super.visit(node);
		}

		@Override
		public void visit(GEq node) {
			checkOperands(node, ValueType.INTEGER);
			super.visit(node);
		}

		@Override
		public void visit(GT node) {
			checkOperands(node, ValueType.INTEGER);
			super.visit(node);
		}

		@Override
		public void visit(LEq node) {
			checkOperands(node, ValueType.INTEGER);
			super.visit(node);
		}

		@Override
		public void visit(LT node) {
			checkOperands(node, ValueType.INTEGER);
			super.visit(node);
		}

		@Override
		public void visit(NEq node) {
			checkOperands(node, ValueType.INTEGER);
			super.visit(node);
		}

		@Override
		public void visit(And node) {
			checkOperands(node, ValueType.BOOLEAN);
			super.visit(node);
		}

		@Override
		public void visit(Or node) {
			checkOperands(node, ValueType.BOOLEAN);
			super.visit(node);
		}

		private void checkOperands(BinaryExpr expr, ValueType expectedType) {
			checkType(expr.left(), expectedType);
			checkType(expr.right(), expectedType);
		}

		private void checkType(Expr expr, ValueType expectedType) {
			ValueType actual;

			actual = expr.type();
			if (actual != expectedType) {
				String msg;

				msg = String.format("[%s: %s] Type mismatch: '%s' should be of type '%s' but is of type '%s'. ",
						expr.getLineIndex(), expr.getCharIndex(), expr.getText(), expectedType.getName(),
						actual.getName());

				error(msg);
			}
		}
	}

	private class DuplicateFinder extends ASTNodeVisitorAdapter {

		private Set<String> questions = new HashSet<String>();

		private void addQuestion(Question node) {
			String label;

			label = node.getLabel();
			if (!questions.add(label)) {
				warn("Duplicate label:" + label);
			}
		}

		@Override
		public void visit(ComputedQuestion node) {
			addQuestion(node);
			super.visit(node);
		}

		@Override
		public void visit(InputQuestion node) {
			addQuestion(node);
			super.visit(node);
		}

	}

	private void warn(String msg) {
		System.out.println(String.format("WARNING: %s", msg));
	}

	private void error(String msg) {
		System.err.println(String.format("ERROR: %s", msg));
	}
}
