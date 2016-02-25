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
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.ast.literal.BooleanLiteral;
import org.uva.ql.ast.literal.IntegerLiteral;
import org.uva.ql.ast.literal.StringLiteral;
import org.uva.ql.ast.stat.ComputedQuestion;
import org.uva.ql.ast.stat.IFStat;
import org.uva.ql.ast.stat.Question;

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
	public Result validateTypes(Questionnaire questionnaire) {
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
	public Result validateQuestions(Questionnaire questionnaire) {
		return new DuplicateQuestionLabelVisitor().visit(questionnaire);
	}

	/**
	 * Validate the that there are no cyclic dependencies between questions of
	 * the supplied {@code questionnaire}.
	 * 
	 * @param questionnaire
	 * @return a {@link Result} containing errors and warnings.
	 */
	public Result validateCyclicReferences(Questionnaire questionnaire) {
		return new CyclicReferenceVisitor().visit(questionnaire);
	}

	private static class TypeCheckVisitor extends ASTNodeVisitorAdapter<VariableType, SymbolTable> {

		private Result result;

		public TypeCheckVisitor() {
		}

		public Result visit(Questionnaire q) {
			result = new Result();

			q.accept(this, new SymbolTable());

			return result;
		}

		@Override
		public VariableType visit(Questionnaire node, SymbolTable st) {
			for (Form form : node.getForms()) {
				form.accept(this, st);
			}

			return null;
		}

		@Override
		public VariableType visit(Form node, SymbolTable st) {
			// The body of every form has its own SymbolTable
			node.getBody().accept(this, new SymbolTable());
			return null;
		}

		@Override
		public VariableType visit(Block node, SymbolTable st) {
			// Copy the SymbolTable for scoping of variables
			st = new SymbolTable(st);

			// First traverse the questions, because they
			// declare variables that can be used in the if statements.
			for (Question question : node.getQuestions()) {
				question.accept(this, st);
			}

			for (IFStat statement : node.getIfStatements()) {
				statement.accept(this, st);
			}

			return null;
		}

		@Override
		public VariableType visit(IFStat node, SymbolTable st) {
			checkType(node.getExpr(), st, VariableType.BOOLEAN);
			node.getBody().accept(this, st);
			return null;
		}

		@Override
		public VariableType visit(Question node, SymbolTable st) {
			String variableName;
			VariableType type;
			VariableType questionType;

			variableName = node.getId();
			questionType = node.getType();

			if (questionType instanceof BooleanType) {
				type = VariableType.BOOLEAN;
			} else if (questionType instanceof IntegerType) {
				type = VariableType.INTEGER;
			} else if (questionType instanceof StringType) {
				type = VariableType.STRING;
			} else {
				throw new IllegalStateException("Undefined question type '" + questionType.getClass() + "'");
			}

			if (st.contains(variableName)) {
				result.addError("Duplicate question id %s", node);
			} else {
				st.add(variableName, type);
			}

			return type;
		}

		@Override
		public VariableType visit(LiteralExpr node, SymbolTable st) {
			return node.getLiteral().accept(this, st);
		}

		@Override
		public VariableType visit(VariableExpr node, SymbolTable st) {
			VariableType type;

			type = st.getType(node.getVariableId());
			if (type == null) {
				result.addError("Undeclared variable %s, %s", node, node.getVariableId());
			}

			return type;
		}

		// Literals
		@Override
		public VariableType visit(BooleanLiteral node, SymbolTable st) {
			return VariableType.BOOLEAN;
		}

		@Override
		public VariableType visit(IntegerLiteral node, SymbolTable st) {
			return VariableType.INTEGER;
		}

		@Override
		public VariableType visit(StringLiteral node, SymbolTable st) {
			return VariableType.STRING;
		}

		// Arithmetic operations
		@Override
		public VariableType visit(Add node, SymbolTable st) {
			checkOperands(node, st, VariableType.INTEGER);
			return VariableType.INTEGER;
		}

		@Override
		public VariableType visit(Div node, SymbolTable st) {
			checkOperands(node, st, VariableType.INTEGER);
			return VariableType.INTEGER;
		}

		@Override
		public VariableType visit(Mul node, SymbolTable st) {
			checkOperands(node, st, VariableType.INTEGER);
			return VariableType.INTEGER;
		}

		@Override
		public VariableType visit(Sub node, SymbolTable st) {
			checkOperands(node, st, VariableType.INTEGER);
			return VariableType.INTEGER;
		}

		// Equality relations
		@Override
		public VariableType visit(Eq node, SymbolTable st) {
			Expr lhs;
			VariableType lhsType;
			VariableType rhsType;
			Expr rhs;

			lhs = node.left();
			lhsType = lhs.accept(this, st);
			rhs = node.right();
			rhsType = rhs.accept(this, st);
			if (lhsType != rhsType) {
				result.addError("%s: Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
						node.getSourceLocation(), lhsType.getName(), rhsType.getName());
			}

			return VariableType.BOOLEAN;
		}

		@Override
		public VariableType visit(NEq node, SymbolTable st) {
			Expr lhs;
			VariableType lhsType;
			VariableType rhsType;
			Expr rhs;

			lhs = node.left();
			lhsType = lhs.accept(this, st);
			rhs = node.right();
			rhsType = rhs.accept(this, st);
			if (lhsType != rhsType) {
				result.addError("%s: Type mismatch: operands of == should be of same type. (lhs='%s', rhs='%s')",
						node.getSourceLocation(), lhsType.getName(), rhsType.getName());
			}

			return VariableType.BOOLEAN;
		}

		// Number relations
		@Override
		public VariableType visit(GEq node, SymbolTable st) {
			checkOperands(node, st, VariableType.INTEGER);
			return VariableType.BOOLEAN;
		}

		@Override
		public VariableType visit(GT node, SymbolTable st) {
			checkOperands(node, st, VariableType.INTEGER);
			return VariableType.BOOLEAN;
		}

		@Override
		public VariableType visit(LEq node, SymbolTable st) {
			checkOperands(node, st, VariableType.INTEGER);
			return VariableType.BOOLEAN;
		}

		@Override
		public VariableType visit(LT node, SymbolTable st) {
			checkOperands(node, st, VariableType.INTEGER);
			return VariableType.BOOLEAN;
		}

		// Boolean relations
		@Override
		public VariableType visit(And node, SymbolTable st) {
			checkOperands(node, st, VariableType.BOOLEAN);
			return VariableType.BOOLEAN;
		}

		@Override
		public VariableType visit(Or node, SymbolTable st) {
			checkOperands(node, st, VariableType.BOOLEAN);
			return VariableType.BOOLEAN;
		}

		private void checkOperands(BinaryExpr expr, SymbolTable st, VariableType expectedType) {
			checkType(expr.left(), st, expectedType);
			checkType(expr.right(), st, expectedType);
		}

		private void checkType(Expr expr, SymbolTable st, VariableType expectedType) {
			VariableType actual;

			actual = expr.accept(this, st);

			if (actual == null) {
				result.addError("Unknown type for %s", expr);
			} else if (actual != expectedType) {
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

		public Result visit(Questionnaire q) {
			result = new Result();

			q.accept(this, new QuestionTable());

			return result;
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

		private Map<String, VariableType> nameToType = new HashMap<>();

		public SymbolTable() {
			nameToType = new HashMap<>();
		}

		public SymbolTable(SymbolTable table) {
			nameToType = new HashMap<>(table.nameToType);
		}

		public boolean contains(String name) {
			return nameToType.containsKey(name);
		}

		public void add(String name, VariableType type) {
			nameToType.put(name, type);
		}

		public VariableType getType(String name) {
			return nameToType.get(name);
		}
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
		Questionnaire questionnaire;
		QLSemanticAnalyser sa;
		File inputFile;

		// inputFile = new File("resources/Questionaire.ql");
		inputFile = new File("test/resources/org/uva/ql/CyclicReferences.ql");
		questionnaire = Questionnaire.create(inputFile);

		sa = new QLSemanticAnalyser();

		sa.validateTypes(questionnaire).print();
		sa.validateQuestions(questionnaire).print();
		sa.validateCyclicReferences(questionnaire).print();
	}

}
