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
import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.expr.math.Add;
import org.uva.ql.ast.expr.math.Div;
import org.uva.ql.ast.expr.math.Mul;
import org.uva.ql.ast.expr.math.Sub;
import org.uva.ql.ast.expr.rel.And;
import org.uva.ql.ast.expr.rel.Equals;
import org.uva.ql.ast.expr.rel.EqualsNot;
import org.uva.ql.ast.expr.rel.GreaterThan;
import org.uva.ql.ast.expr.rel.GreaterThanOrEquals;
import org.uva.ql.ast.expr.rel.LessThan;
import org.uva.ql.ast.expr.rel.LessThanOrEquals;
import org.uva.ql.ast.expr.rel.Or;
import org.uva.ql.ast.form.QLBlock;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.type.QLType;

public class QLSemanticAnalyser {

	public QLSemanticAnalyser() {

	}

	/**
	 * Validate the types in the {@code questionnaire}.
	 * <p>
	 * This will check for:
	 * <li>Reference to undefined variables (=questions)
	 * <li>Duplicate declaration of variables (=questions)
	 * <li>Conditions that are not of the type boolean
	 * <li>Operands of invalid type to operators</br>
	 * 
	 * @param questionnaire
	 * @return a {@link Result} containing errors and warnings.
	 */
	public Result validateTypes(QLQuestionnaire questionnaire) {
		return new TypeCheckVisitor().visit(questionnaire);
	}

	/**
	 * Validate the the questions of the supplied {@code questionnaire}.
	 * <p>
	 * This will check for:
	 * <li>duplicate question labels</li></br>
	 * 
	 * @param questionnaire
	 * @return a {@link Result} containing errors and warnings.
	 */
	public Result validateQuestions(QLQuestionnaire questionnaire) {
		return new DuplicateQuestionLabelVisitor().visit(questionnaire);
	}

	/**
	 * Validate the that there are no cyclic dependencies between questions of
	 * the supplied {@code questionnaire}.
	 * 
	 * @param questionnaire
	 * @return a {@link Result} containing errors and warnings.
	 */
	public Result validateCyclicReferences(QLQuestionnaire questionnaire) {
		return new CyclicReferenceVisitor().visit(questionnaire);
	}

	private static class TypeCheckVisitor extends ASTNodeVisitorAdapter<QLType, SymbolTable> {

		private Result result;

		public TypeCheckVisitor() {
		}

		public Result visit(QLQuestionnaire q) {
			result = new Result();

			q.accept(this, new SymbolTable());

			return result;
		}

		@Override
		public QLType visit(QLQuestionnaire node, SymbolTable st) {
			for (QLForm form : node.getForms()) {
				form.accept(this, st);
			}

			return null;
		}

		@Override
		public QLType visit(QLForm node, SymbolTable st) {
			// The body of every form has its own SymbolTable
			node.getBody().accept(this, new SymbolTable());
			return null;
		}

		@Override
		public QLType visit(QLBlock node, SymbolTable st) {
			// Copy the SymbolTable for scoping of variables
			st = new SymbolTable(st);

			// First traverse the questions, because they
			// declare variables that can be used in the if statements.
			for (QLQuestion question : node.getQuestions()) {
				question.accept(this, st);
			}

			for (QLIFStatement statement : node.getIfStatements()) {
				statement.accept(this, st);
			}

			return null;
		}

		@Override
		public QLType visit(QLIFStatement node, SymbolTable st) {
			checkType(node.getExpr(), st, QLType.BOOLEAN);
			node.getBody().accept(this, st);
			return null;
		}

		@Override
		public QLType visit(QLQuestion node, SymbolTable st) {
			String variableName;
			QLType questionType;

			variableName = node.getId();
			questionType = node.getType();

			if (st.contains(variableName)) {
				result.addError("Duplicate question id %s", node);
			} else {
				st.add(variableName, questionType);
			}

			return questionType;
		}

		@Override
		public QLType visit(LiteralExpr node, SymbolTable st) {
			return node.getLiteral().accept(this, st);
		}

		@Override
		public QLType visit(VariableExpr node, SymbolTable st) {
			QLType type;

			type = st.getType(node.getVariableId());
			if (type == null) {
				result.addError("Undeclared variable %s, %s", node, node.getVariableId());
			}

			return type;
		}

		// Literals
		@Override
		public QLType visit(BooleanLiteral node, SymbolTable st) {
			return QLType.BOOLEAN;
		}

		@Override
		public QLType visit(IntegerLiteral node, SymbolTable st) {
			return QLType.INTEGER;
		}

		@Override
		public QLType visit(StringLiteral node, SymbolTable st) {
			return QLType.STRING;
		}

		// Arithmetic operations
		@Override
		public QLType visit(Add node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.INTEGER;
		}

		@Override
		public QLType visit(Div node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.INTEGER;
		}

		@Override
		public QLType visit(Mul node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.INTEGER;
		}

		@Override
		public QLType visit(Sub node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.INTEGER;
		}

		// Equality relations
		@Override
		public QLType visit(Equals node, SymbolTable st) {
			Expr lhs;
			QLType lhsType;
			QLType rhsType;
			Expr rhs;

			lhs = node.left();
			lhsType = lhs.accept(this, st);
			rhs = node.right();
			rhsType = rhs.accept(this, st);
			if (!lhsType.equals(rhsType)) {
				result.addError("%s: Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
						node.getSourceLocation(), lhsType.getName(), rhsType.getName());
			}

			return QLType.BOOLEAN;
		}

		@Override
		public QLType visit(EqualsNot node, SymbolTable st) {
			Expr lhs;
			QLType lhsType;
			QLType rhsType;
			Expr rhs;

			lhs = node.left();
			lhsType = lhs.accept(this, st);
			rhs = node.right();
			rhsType = rhs.accept(this, st);
			if (!lhsType.equals(rhsType)) {
				result.addError("%s: Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
						node.getSourceLocation(), lhsType.getName(), rhsType.getName());
			}

			return QLType.BOOLEAN;
		}

		// Number relations
		@Override
		public QLType visit(GreaterThanOrEquals node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.BOOLEAN;
		}

		@Override
		public QLType visit(GreaterThan node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.BOOLEAN;
		}

		@Override
		public QLType visit(LessThanOrEquals node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.BOOLEAN;
		}

		@Override
		public QLType visit(LessThan node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.BOOLEAN;
		}

		// Boolean relations
		@Override
		public QLType visit(And node, SymbolTable st) {
			checkOperands(node, st, QLType.BOOLEAN);
			return QLType.BOOLEAN;
		}

		@Override
		public QLType visit(Or node, SymbolTable st) {
			checkOperands(node, st, QLType.BOOLEAN);
			return QLType.BOOLEAN;
		}

		private void checkOperands(BinaryExpr expr, SymbolTable st, QLType expectedType) {
			checkType(expr.left(), st, expectedType);
			checkType(expr.right(), st, expectedType);
		}

		private void checkType(Expr expr, SymbolTable st, QLType expectedType) {
			QLType actual;

			actual = expr.accept(this, st);

			if (actual == null) {
				result.addError("Unknown type for %s", expr);
			} else if (!actual.equals(expectedType)) {
				result.addError("%s: Type mismatch: '%s' should be of type '%s' but is of type '%s'. ",
						expr.getSourceLocation(), expr.getSourceText(), expectedType.getName(), actual.getName());
			}
		}
	}

	public static class Result {

		private final List<String> warnings = new ArrayList<>();
		private final List<String> errors = new ArrayList<>();

		private Result() {

		}

		void addWarning(String msg, Object... args) {
			warnings.add(String.format("WARNING: %s", String.format(msg, args)));
		}

		void addError(String msg, Object... args) {
			errors.add(String.format("ERROR  : %s", String.format(msg, args)));
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

	private static class DuplicateQuestionLabelVisitor extends ASTNodeVisitorAdapter<Void, QuestionTable> {

		private Result result;

		public DuplicateQuestionLabelVisitor() {

		}

		public Result visit(QLQuestionnaire q) {
			result = new Result();

			q.accept(this, new QuestionTable());

			return result;
		}

		@Override
		public Void visit(QLQuestion node, QuestionTable qt) {
			String label;
			QLType knownType;
			QLType nodeType;

			label = node.getLabel();
			nodeType = node.getType();
			knownType = qt.add(label, nodeType);
			if (knownType != null) {
				result.addWarning("Duplicate label: %s", label);

				if (!Objects.equals(nodeType, knownType)) {
					result.addError("Question with '%s' has been declared twice, but with different types: %s and %s",
							label, nodeType, knownType);
				}
			}

			return null;
		}
	}

	private static class SymbolTable {

		private Map<String, QLType> nameToType = new HashMap<>();

		public SymbolTable() {
			nameToType = new HashMap<>();
		}

		public SymbolTable(SymbolTable table) {
			nameToType = new HashMap<>(table.nameToType);
		}

		public boolean contains(String name) {
			return nameToType.containsKey(name);
		}

		public void add(String name, QLType type) {
			nameToType.put(name, type);
		}

		public QLType getType(String name) {
			return nameToType.get(name);
		}
	}

	private static class QuestionTable {

		private final Map<String, QLType> questionLabelToType;

		public QuestionTable() {
			questionLabelToType = new HashMap<>();
		}

		public QLType add(String label, QLType type) {
			return questionLabelToType.put(label, type);
		}
	}

	public static class ReferenceTable {

		private final Map<String, Reference> referenceMapById;

		private ReferenceTable() {
			referenceMapById = new HashMap<>();
		}

		/**
		 * Find all the cyclic references
		 * 
		 * @return
		 */
		public Result findCyclicReferences() {
			Result result;

			result = new Result();

			for (Reference r : referenceMapById.values()) {
				if (r.getReferents().isEmpty()) {
					continue;
				}

				for (ReferencePath path : r.getPaths()) {
					if (path.hasCycle()) {
						result.addError("Cyclic dependency for question %s: (%s)", r.getId(), path.toString());
					}
				}
			}

			return result;
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

		public static class ReferencePath {

			private final List<Reference> readOnlyReferenceList;

			public ReferencePath() {
				readOnlyReferenceList = Collections.emptyList();
			}

			public ReferencePath(List<Reference> references) {
				List<Reference> copyOfReferences;

				copyOfReferences = new ArrayList<>(references);
				readOnlyReferenceList = Collections.unmodifiableList(copyOfReferences);
			}

			/**
			 * Make a copy of this {@code ReferencePath}, and add the supplied
			 * Reference {@code r} to the path.
			 * 
			 * @param r
			 *            the {@code Reference} to add.
			 * @return a copy of this {@code ReferencePath} with the the
			 *         supplied reference appended.
			 */
			public ReferencePath copyAndAdd(Reference r) {
				List<Reference> refs;

				refs = new ArrayList<>(readOnlyReferenceList);
				refs.add(r);

				return new ReferencePath(refs);
			}

			/**
			 * Find the root of this reference path.
			 * 
			 * @return the first {@code Reference} of this path, or {@code null}
			 *         if the path does not contain any references.
			 */
			public Reference getRoot() {
				if (readOnlyReferenceList.isEmpty()) {
					return null;
				}
				return readOnlyReferenceList.get(0);
			}

			public List<Reference> getPath() {
				return readOnlyReferenceList;
			}

			/**
			 * Returns true if this path contains the specified reference.
			 * 
			 * @param r
			 *            reference whose presence in this list is to be tested
			 * @return {@code true} if this path contains the specified
			 *         reference
			 */
			public boolean contains(Reference r) {
				return readOnlyReferenceList.contains(r);
			}

			/**
			 * Returns true if this path contains a cycle.
			 * 
			 * @return {@code true} if this path contains a cycle.
			 */
			public boolean hasCycle() {
				for (Reference r : readOnlyReferenceList) {
					if (Collections.frequency(readOnlyReferenceList, r) > 1) {
						return true;
					}
				}

				return false;
			}

			@Override
			public String toString() {
				StringBuilder sb;

				sb = new StringBuilder();

				// Make a String in the form of 'a -> b -> c'
				readOnlyReferenceList.stream().forEachOrdered(ref -> {
					if (sb.length() > 0) {
						sb.append(" -> ");
					}
					sb.append(ref.id);
				});

				return sb.toString();
			}
		}

		public class Reference {

			private final String id;
			private final List<Reference> referents = new ArrayList<>();

			private Reference(String id) {
				this.id = id;
			}

			public String getId() {
				return id;
			}

			public void addReferent(String id) {
				referents.add(getReference(id));
			}

			public List<Reference> getReferents() {
				return Collections.unmodifiableList(referents);
			}

			public List<ReferencePath> getPaths() {
				return getPaths(new ReferencePath());
			}

			public List<ReferencePath> getPaths(ReferencePath parentPath) {
				List<ReferencePath> paths;
				ReferencePath myPath;

				myPath = parentPath.copyAndAdd(this);

				// If this reference does not have any referents, this is the
				// end of the reference path.
				if (referents.isEmpty()) {
					return Collections.singletonList(myPath);
				}

				paths = new ArrayList<>();
				for (Reference referent : referents) {

					// Found a cycle.
					if (myPath.contains(referent)) {
						// Add the referent to the path, so the cycle
						// is actually present in the path.
						paths.add(myPath.copyAndAdd(referent));
						continue;
					}

					paths.addAll(referent.getPaths(myPath));
				}

				return Collections.unmodifiableList(paths);
			}

			@Override
			public final boolean equals(Object obj) {
				Reference other;

				if (!(obj instanceof Reference)) {
					return false;
				}

				other = (Reference) obj;

				return id.equals(other.id) && referents.equals(other.referents);
			}

			@Override
			public final int hashCode() {
				return id.hashCode();
			}
		}
	}

	private static class CyclicReferenceVisitor extends ASTNodeVisitorAdapter<Void, ReferenceTable> {

		private QLQuestionComputed currentQuestion;

		public CyclicReferenceVisitor() {

		}

		public Result visit(QLQuestionnaire q) {
			ReferenceTable rt;

			rt = new ReferenceTable();

			q.accept(this, rt);

			return rt.findCyclicReferences();
		}

		@Override
		public Void visit(QLQuestionComputed node, ReferenceTable rt) {
			currentQuestion = node;

			node.expr().accept(this, rt);

			currentQuestion = null;

			return null;
		}

		@Override
		public Void visit(VariableExpr node, ReferenceTable rt) {

			// This expression is not part of the computation of a question.
			if (currentQuestion == null) {
				return null;
			}

			// Add the variable id of the expression as a referent of the
			rt.getReference(currentQuestion.getId()).addReferent(node.getVariableId());

			return null;
		}
	}

	public static void main(String[] args) throws IOException {
		QLQuestionnaire questionnaire;
		QLSemanticAnalyser sa;
		File inputFile;

		inputFile = new File("resources/Questionaire.ql");
		// inputFile = new
		// File("test/resources/org/uva/ql/CyclicReferences.ql");
		questionnaire = QLQuestionnaire.create(inputFile);

		sa = new QLSemanticAnalyser();

		sa.validateTypes(questionnaire).print();
		sa.validateQuestions(questionnaire).print();
		sa.validateCyclicReferences(questionnaire).print();
	}

}
