package org.uva.ql;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.expr.BinaryExpr;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.expr.LiteralExpr;
import org.uva.ql.ast.expr.VariableExpr;
import org.uva.ql.ast.expr.math.Add;
import org.uva.ql.ast.expr.math.Divide;
import org.uva.ql.ast.expr.math.Multiply;
import org.uva.ql.ast.expr.math.Subtract;
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

	public Result validate(QLQuestionnaire questionnaire) {
		Result result;

		result = new Result();

		result.addAll(validateTypes(questionnaire));
		result.addAll(validateCyclicReferences(questionnaire));

		return result;
	}

	/**
	 * Validate the types in the {@code questionnaire}.
	 * <p>
	 * This will check for:
	 * <li>Reference to undefined questions
	 * <li>Duplicate question (name) declaration with different types
	 * <li>duplicate question labels
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

		private TypeCheckVisitor() {
		}

		public Result visit(QLQuestionnaire q) {
			SymbolTable table;

			result = new Result();

			table = new SymbolTable();
			q.accept(this, table);

			result.duplicateNameQuestions.forEach(name -> {

				StringBuilder sb;
				String msg;

				msg = String.format("Question '%s' has been declared multiple times, but with different types:", name);

				sb = new StringBuilder();
				sb.append(msg);
				sb.append(System.lineSeparator());
				table.getByName(name).stream().forEach(c -> {
					sb.append("  ");
					sb.append(c.getSourceLocation());
					sb.append("  ");
					sb.append(c.getSourceText());
					sb.append(System.lineSeparator());
				});

				result.addError(sb.toString());
			});

			result.duplicateLabelQuestions.forEach(label -> {
				StringBuilder sb;
				String msg;

				msg = String.format("Duplicate labels: %s", label);

				sb = new StringBuilder();
				sb.append(msg);
				sb.append(System.lineSeparator());
				table.getByLabel(label).stream().forEach(c -> {
					sb.append("  ");
					sb.append(c.getSourceLocation());
					sb.append("  ");
					sb.append(c.getSourceText());
					sb.append(System.lineSeparator());
				});

				result.addWarning(sb.toString());
			});

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
			node.getBody().accept(this, st);
			return null;
		}

		@Override
		public QLType visit(QLBlock node, SymbolTable st) {
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

			if (!st.getByLabel(node.getLabel()).isEmpty()) {
				result.addDuplicateQuestionLabel(node);
			}

			for (QLQuestion other : st.getByName(node.getId())) {
				if (!other.getType().equals(node.getType())) {
					result.addDuplicateQuestionName(node);
					break;
				}
			}

			st.add(node);

			return node.getType();
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
		public QLType visit(Divide node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.INTEGER;
		}

		@Override
		public QLType visit(Multiply node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.INTEGER;
		}

		@Override
		public QLType visit(Subtract node, SymbolTable st) {
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
						node.getSourceLocation(), lhsType.toString(), rhsType.toString());
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
						node.getSourceLocation(), lhsType.toString(), rhsType.toString());
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
			QLType actualType;

			actualType = expr.accept(this, st);

			if (actualType == null) {
				result.addError("Unknown type for %s", expr);
			} else if (!actualType.equals(expectedType)) {
				result.addError("%s: Type mismatch: '%s' should be of type '%s' but is of type '%s'. ",
						expr.getSourceLocation(), expr.getSourceText(), expectedType.toString(), actualType.toString());
			}
		}
	}

	public static class Result {

		private final List<String> warnings = new ArrayList<>();
		private final List<String> errors = new ArrayList<>();

		private Set<String> duplicateNameQuestions = new HashSet<>();
		private Set<String> duplicateLabelQuestions = new HashSet<>();

		private Result() {

		}

		void addAll(Result result) {
			warnings.addAll(result.warnings);
			errors.addAll(result.errors);

			duplicateNameQuestions.addAll(result.duplicateNameQuestions);
			duplicateLabelQuestions.addAll(result.duplicateLabelQuestions);
		}

		void addDuplicateQuestionLabel(QLQuestion q) {
			duplicateLabelQuestions.add(q.getLabel());
		}

		void addDuplicateQuestionName(QLQuestion q) {
			duplicateNameQuestions.add(q.getId());
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

	private static class SymbolTable {

		private Map<String, List<QLQuestion>> nameToQuestion = new HashMap<>();
		private Map<String, List<QLQuestion>> labelToQuestion = new HashMap<>();

		public SymbolTable() {

		}

		public List<QLQuestion> getByName(String name) {
			return nameToQuestion.getOrDefault(name, Collections.emptyList());
		}

		public List<QLQuestion> getByLabel(String label) {
			return labelToQuestion.getOrDefault(label, Collections.emptyList());
		}

		public void add(QLQuestion node) {
			List<QLQuestion> questionListByName;
			List<QLQuestion> questionListByLabel;
			String name;
			String label;

			name = node.getId();
			label = node.getLabel();

			questionListByName = nameToQuestion.getOrDefault(name, new ArrayList<>());
			questionListByName.add(node);
			nameToQuestion.putIfAbsent(name, questionListByName);

			questionListByLabel = labelToQuestion.getOrDefault(label, new ArrayList<>());
			questionListByLabel.add(node);
			labelToQuestion.putIfAbsent(label, questionListByLabel);
		}

		public QLType getType(String name) {
			List<QLQuestion> questionList;

			questionList = getByName(name);
			if (questionList.isEmpty()) {
				return null;
			}

			return questionList.get(0).getType();
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

			private final List<Reference> referenceList;

			public ReferencePath() {
				referenceList = new ArrayList<>();
			}

			public ReferencePath(ReferencePath referencePath) {
				referenceList = new ArrayList<>(referencePath.referenceList);
			}

			/**
			 * Return a copy of this {@code ReferencePath} that is extended with
			 * the specified reference. Extending means that the reference is
			 * appended to the path.
			 * 
			 * @param r
			 *            the {@code Reference} to add.
			 * @return a copy of this {@code ReferencePath} with the the
			 *         supplied reference appended.
			 */
			public ReferencePath extend(Reference r) {
				ReferencePath copy;

				copy = new ReferencePath(this);
				copy.referenceList.add(r);

				return copy;
			}

			public List<Reference> getPath() {
				return Collections.unmodifiableList(referenceList);
			}

			/**
			 * Returns the path as a string in the form of 'a -> b -> c', where
			 * the letters are the id's of the references, and a refers to b,
			 * and b refers to c.
			 * 
			 * @return the path as a string.
			 */
			public String getPathString() {
				StringBuilder sb;

				sb = new StringBuilder();

				// Make a String in the form of 'a -> b -> c'
				referenceList.stream().forEachOrdered(ref -> {
					if (sb.length() > 0) {
						sb.append(" -> ");
					}
					sb.append(ref.id);
				});

				return sb.toString();
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
				return referenceList.contains(r);
			}

			/**
			 * Returns true if this path contains a cycle.
			 * 
			 * @return {@code true} if this path contains a cycle, {@code false}
			 *         otherwise.
			 */
			public boolean hasCycle() {
				for (Reference r : referenceList) {
					if (hasCycle(r)) {
						return true;
					}
				}

				return false;
			}

			/**
			 * Returns true if this path contains a cycle with specified
			 * reference.
			 * 
			 * @param r
			 *            the reference to check.
			 * @return {@code true} if this path contains a cycle with reference
			 *         {@code r}, {@code false} otherwise.
			 * 
			 * @throws NullPointerException
			 *             if {@code r} is {@code null}.
			 */
			public boolean hasCycle(Reference r) {
				return frequency(r) > 1;
			}

			/**
			 * Returns how many times the specified reference occurs in this
			 * ReferencePath.
			 *
			 * @param r
			 *            the reference whose frequency is to be determined
			 * @return the number of elements in this path equal to {@code r}
			 * @throws NullPointerException
			 *             if {@code r} is null
			 */
			private int frequency(Reference r) {
				return Collections.frequency(referenceList, r);
			}

			/**
			 * Calling toString on {@code ReferencePath} will return the same
			 * String that is returned by {@link #getPathString()}.
			 * 
			 * @see #getPathString()
			 */
			@Override
			public String toString() {
				return getPathString();
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

			/**
			 * Returns all the {@code ReferencePath}'s for this
			 * {@code References}.
			 * 
			 * @return the {@code ReferencesPath}'s. for this {@code Reference}.
			 */
			public List<ReferencePath> getPaths() {
				return getPaths(new ReferencePath());
			}

			/**
			 * Returns all the {@code ReferencePath}'s for this
			 * {@code References}, appended to the specified {@code parentPath}.
			 * 
			 * @param parentPath
			 *            the path to use as a root.
			 * 
			 * @return the {@code ReferencesPath}'s. for this {@code Reference}.
			 */
			public List<ReferencePath> getPaths(ReferencePath parentPath) {
				List<ReferencePath> paths;
				ReferencePath myPath;

				myPath = parentPath.extend(this);

				// We've arrived at the end of a path.
				if (isLeaf()) {
					return Collections.singletonList(myPath);
				}

				// Found a cycle. Continuing will cause endless recursion.
				if (myPath.hasCycle(this)) {
					return Collections.singletonList(myPath);
				}

				paths = new ArrayList<>();
				for (Reference referent : referents) {
					paths.addAll(referent.getPaths(myPath));
				}

				return Collections.unmodifiableList(paths);
			}

			/**
			 * Returns whether this reference is a leaf. A reference is a leaf
			 * iff the reference has no referents.
			 * 
			 * @return {@code true} if this reference is a leaf, {@code false}
			 *         otherwise.
			 */
			private boolean isLeaf() {
				return hasReferents();
			}

			/**
			 * Returns whether this reference has any referents.
			 * 
			 * @return {@code true} if this reference has referents,
			 *         {@code false} otherwise.
			 */
			private boolean hasReferents() {
				return referents.isEmpty();
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

		inputFile = new File("resources/Questionnaire.ql");
		// inputFile = new
		// File("test/resources/org/uva/ql/CyclicReferences.ql");
		questionnaire = QLQuestionnaire.create(inputFile);

		sa = new QLSemanticAnalyser();

		sa.validateTypes(questionnaire).print();
		sa.validateCyclicReferences(questionnaire).print();
	}

}
