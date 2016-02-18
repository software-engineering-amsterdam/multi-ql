package org.uva.ql;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.BooleanType;
import org.uva.ql.ast.IntegerType;
import org.uva.ql.ast.StringType;
import org.uva.ql.ast.ValueType;
import org.uva.ql.ast.VariableType;
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

	public Result validateTypes(Questionnaire questionnaire) {
		result = new Result();

		// Validate the following:
		// - Reference to undefined variables (=questions)
		// - Conditions that are not of the type boolean
		// - Operands of invalid type to operators
		// - Duplicate variable (=question) declaration
		new TypeCheckVisitor().visit(questionnaire);

		return result;
	}

	public Result validateQuestions(Questionnaire questionnaire) {
		result = new Result();

		// Validate:
		// - duplicate question labels
		new DuplicateQuestionLabelVisitor().visit(questionnaire);

		return result;
	}

	public Result validateCyclicReferences(Questionnaire questionnaire) {
		result = new Result();

		new CyclicReferenceVisitor().visit(questionnaire);

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

	private class TypeCheckVisitor extends ASTNodeVisitorAdapter<ValueType, SymbolTable> {

		public TypeCheckVisitor() {
		}

		public void visit(Questionnaire q) {
			q.accept(this, new SymbolTable());
		}

		@Override
		public ValueType visit(Questionnaire node, SymbolTable st) {
			for (Form form : node.getForms()) {
				form.accept(this, st);
			}

			return null;
		}

		@Override
		public ValueType visit(Form node, SymbolTable st) {
			// The body of every form has its own SymbolTable
			node.getBody().accept(this, new SymbolTable());
			return null;
		}

		@Override
		public ValueType visit(Block node, SymbolTable st) {
			// Copy the SymbolTable for scoping of variables
			st = new SymbolTable(st);

			// First traverse the questions.
			for (Question question : node.getQuestions()) {
				question.accept(this, st);
			}

			for (IFStat statement : node.getIfStatements()) {
				statement.accept(this, st);
			}

			return null;
		}

		@Override
		public ValueType visit(IFStat node, SymbolTable st) {
			checkType(node.getExpr(), st, ValueType.BOOLEAN);
			node.getBody().accept(this, st);
			return null;
		}

		@Override
		public ValueType visit(Question node, SymbolTable st) {
			String variableName;
			ValueType type;
			VariableType questionType;

			variableName = node.getId();
			questionType = node.getType();

			if (questionType instanceof BooleanType) {
				type = ValueType.BOOLEAN;
			} else if (questionType instanceof IntegerType) {
				type = ValueType.INTEGER;
			} else if (questionType instanceof StringType) {
				type = ValueType.STRING;
			} else {
				throw new IllegalStateException("Undefined question type '" + questionType.getClass() + "'");
			}

			if (st.contains(variableName)) {
				error("Duplicate question id " + node);
			} else {
				st.add(variableName, type);
			}

			return type;
		}

		@Override
		public ValueType visit(LiteralExpr node, SymbolTable st) {
			return node.getLiteral().accept(this, st);
		}

		@Override
		public ValueType visit(VariableExpr node, SymbolTable st) {
			ValueType type;

			type = st.getType(node.getVariableId());
			if (type == null) {
				error("Undeclared variable " + node + node.getVariableId());
			}

			return type;
		}

		// Literals
		@Override
		public ValueType visit(BooleanLiteral node, SymbolTable st) {
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(IntegerLiteral node, SymbolTable st) {
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(StringLiteral node, SymbolTable st) {
			return ValueType.STRING;
		}

		// Arithmetic operations
		@Override
		public ValueType visit(Add node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Div node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Mul node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Sub node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		// Equality relations
		@Override
		public ValueType visit(Eq node, SymbolTable st) {
			Expr lhs;
			ValueType lhsType;
			ValueType rhsType;
			Expr rhs;

			lhs = node.left();
			lhsType = lhs.accept(this, st);
			rhs = node.right();
			rhsType = rhs.accept(this, st);
			if (lhsType != rhsType) {
				String msg;

				msg = String.format("%s: Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
						node.getSourceLocation(), lhsType.getName(), rhsType.getName());

				error(msg);
			}

			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(NEq node, SymbolTable st) {
			Expr lhs;
			ValueType lhsType;
			ValueType rhsType;
			Expr rhs;

			lhs = node.left();
			lhsType = lhs.accept(this, st);
			rhs = node.right();
			rhsType = rhs.accept(this, st);
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
		public ValueType visit(GEq node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(GT node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(LEq node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(LT node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		// Boolean relations
		@Override
		public ValueType visit(And node, SymbolTable st) {
			checkOperands(node, st, ValueType.BOOLEAN);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(Or node, SymbolTable st) {
			checkOperands(node, st, ValueType.BOOLEAN);
			return ValueType.BOOLEAN;
		}

		private void checkOperands(BinaryExpr expr, SymbolTable st, ValueType expectedType) {
			checkType(expr.left(), st, expectedType);
			checkType(expr.right(), st, expectedType);
		}

		private void checkType(Expr expr, SymbolTable st, ValueType expectedType) {
			ValueType actual;

			actual = expr.accept(this, st);

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

	private class DuplicateQuestionLabelVisitor extends ASTNodeVisitorAdapter<Void, QuestionTable> {

		public DuplicateQuestionLabelVisitor() {

		}

		public void visit(Questionnaire q) {
			q.accept(this, new QuestionTable());
		}

		@Override
		public Void visit(Question node, QuestionTable qt) {
			String label;
			VariableType knownType;
			VariableType nodeType;

			label = node.getLabel();
			nodeType = node.getType();
			knownType = qt.add(label, nodeType);
			if (knownType != null) {
				warn("Duplicate label: %s", label);

				if (!Objects.equals(nodeType, knownType)) {
					error("Question with '%s' has been declared twice, but with different types: %s and %s", label,
							nodeType, knownType);
				}
			}

			return null;
		}
	}

	public static class Result {

		private final List<String> warnings = new ArrayList<>();
		private final List<String> errors = new ArrayList<>();

		private Result() {

		}

		private void addWarning(String msg) {
			warnings.add(String.format("WARNING: %s", msg));
		}

		private void addError(String msg) {
			errors.add(String.format("ERROR  : %s", msg));
		}

		public boolean hasErrors() {
			return !errors.isEmpty();
		}

		public List<String> getErrors() {
			return Collections.unmodifiableList(errors);
		}

		public boolean hasWarnings() {
			return !warnings.isEmpty();
		}

		public List<String> getWarnings() {
			return Collections.unmodifiableList(warnings);
		}

		public boolean hasMessages() {
			return hasErrors() || hasWarnings();
		}

		public List<String> getAllMessages() {
			List<String> allMessages;

			allMessages = new ArrayList<>();
			allMessages.addAll(getErrors());
			allMessages.addAll(getWarnings());

			return Collections.unmodifiableList(allMessages);
		}

		public void print() {
			for (String msg : getErrors()) {
				System.err.println(msg);
			}

			for (String msg : getWarnings()) {
				System.out.println(msg);
			}
		}
	}

	private void warn(String msg, Object... args) {
		result.addWarning(String.format(msg, args));
	}

	private void error(String msg, Object... args) {
		result.addError(String.format(msg, args));
	}

	private static class QuestionTable {

		private final Map<String, VariableType> questionLabelToType;

		public QuestionTable() {
			questionLabelToType = new HashMap<>();
		}

		public VariableType add(String label, VariableType type) {
			return questionLabelToType.put(label, type);
		}
	}

	private static class ReferenceTable {

		private final Map<String, Reference> referenceMapById;

		public ReferenceTable() {
			referenceMapById = new HashMap<>();
		}

		public Result findCyclicReferences() {
			Result result;

			result = new Result();

			for (Reference r : referenceMapById.values()) {
				if (r.getReferents().isEmpty()) {
					continue;
				}
				for (String referencePath : getReferencePath(r.id)) {
					System.out.println(referencePath);
				}
			}

			return result;
		}

		private List<String> getReferencePath(String referer) {
			return getReferencePath(referer, "");
		}

		private List<String> getReferencePath(String referer, String parentPath) {
			List<String> referencePathList;

			Reference r = getReference(referer);

			referencePathList = new ArrayList<>();

			// If the parentPath is not empty, we are extending the chain.
			if (!parentPath.trim().isEmpty()) {
				parentPath += " -> ";
			}

			// Add the referrer itself to the path
			parentPath += referer;

			// This is the end of the reference path.
			if (r.getReferents().isEmpty()) {
				referencePathList.add(parentPath);
				return referencePathList;
			}

			for (Reference referent : r.getReferents()) {

				// Found a cycle, no need to continue with this referent.
				if (parentPath.contains(referent.id)) {
					referencePathList.add(parentPath + " -> " + referent.id + " (cycle)");
					continue;
				}

				// Add the reference paths of the referents
				for (String subReferencePath : getReferencePath(referent.id, parentPath)) {
					referencePathList.add(subReferencePath);
				}
			}

			return referencePathList;
		}

		private Reference getReference(String id) {
			Reference r;

			r = referenceMapById.get(id);
			if (r == null) {
				r = new Reference(id);
				referenceMapById.put(id, r);
			}

			return r;
		}

		private class Reference {

			private final String id;
			private List<Reference> referents = new ArrayList<>();

			public Reference(String id) {
				this.id = id;
			}

			public void addReferent(String id) {
				referents.add(getReference(id));
			}

			public List<Reference> getReferents() {
				return Collections.unmodifiableList(referents);
			}
		}
	}

	private class CyclicReferenceVisitor extends ASTNodeVisitorAdapter<Void, ReferenceTable> {

		private ComputedQuestion currentQuestion;

		public CyclicReferenceVisitor() {

		}

		public Result visit(Questionnaire q) {
			ReferenceTable rt;

			rt = new ReferenceTable();

			q.accept(this, rt);

			return rt.findCyclicReferences();
		}

		@Override
		public Void visit(ComputedQuestion node, ReferenceTable rt) {
			currentQuestion = node;

			node.getExpr().accept(this, rt);

			return null;
		}

		@Override
		public Void visit(VariableExpr node, ReferenceTable context) {
			context.getReference(currentQuestion.getId()).addReferent(node.getVariableId());

			return null;
		}
	}

	public static void main(String[] args) throws IOException {
		Questionnaire questionnaire;
		SemanticAnalyser sa;
		File inputFile;

		// inputFile = new File("resources/Questionaire.ql");
		inputFile = new File("test/resources/org/uva/ql/CyclicReferences.ql");
		questionnaire = Questionnaire.create(inputFile);

		sa = new SemanticAnalyser();

		sa.validateTypes(questionnaire).print();
		sa.validateQuestions(questionnaire).print();
		sa.validateCyclicReferences(questionnaire);
	}

}
