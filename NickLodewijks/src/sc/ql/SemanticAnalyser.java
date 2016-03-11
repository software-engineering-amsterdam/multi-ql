package sc.ql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import sc.ql.CyclicReferences.CyclicReference;
import sc.ql.SemanticAnalyser.SemanticMessage.Level;
import sc.ql.ast.Expression;
import sc.ql.ast.ExpressionVisitor;
import sc.ql.ast.TopDown;
import sc.ql.ast.Expression.Add;
import sc.ql.ast.Expression.And;
import sc.ql.ast.Expression.BinaryExpr;
import sc.ql.ast.Expression.BooleanLiteral;
import sc.ql.ast.Expression.Divide;
import sc.ql.ast.Expression.Equals;
import sc.ql.ast.Expression.EqualsNot;
import sc.ql.ast.Expression.GreaterThan;
import sc.ql.ast.Expression.GreaterThanOrEqual;
import sc.ql.ast.Expression.IntegerLiteral;
import sc.ql.ast.Expression.LessThan;
import sc.ql.ast.Expression.LessThanOrEqual;
import sc.ql.ast.Expression.Multiply;
import sc.ql.ast.Expression.Negative;
import sc.ql.ast.Expression.Not;
import sc.ql.ast.Expression.Or;
import sc.ql.ast.Expression.Positive;
import sc.ql.ast.Expression.StringLiteral;
import sc.ql.ast.Expression.Subtract;
import sc.ql.ast.Expression.VariableExpr;
import sc.ql.ast.form.QLBlock;
import sc.ql.ast.form.QLForm;
import sc.ql.ast.form.QLFormVisitor;
import sc.ql.ast.stat.QLIFStatement;
import sc.ql.ast.stat.QLQuestion;
import sc.ql.ast.stat.QLQuestionComputed;
import sc.ql.ast.stat.QLQuestionInput;
import sc.ql.ast.stat.QLStatementVisitor;
import sc.ql.ast.type.QLType;

public class SemanticAnalyser {

	public SemanticAnalyser() {

	}

	public SemanticErrors validate(QLForm form) {
		SemanticErrors result;

		result = new SemanticErrors();

		result.addAll(validateQuestions(form));
		result.addAll(validateTypes(form));
		result.addAll(validateCyclicReferences(form));

		return result;
	}

	/**
	 * Validate the types in the {@code form}.
	 * <p>
	 * This will check for:
	 * <li>Reference to undefined questions
	 * <li>Duplicate question (name) declaration with different types
	 * <li>duplicate question labels
	 * <li>Conditions that are not of the type boolean
	 * <li>Operands of invalid type to operators</br>
	 * 
	 * @param form
	 * @return a {@link SemanticErrors} containing errors and warnings.
	 */
	public SemanticErrors validateTypes(QLForm form) {
		return new TypeCheckVisitor().visit(form);
	}

	public SemanticErrors validateQuestions(QLForm form) {
		SemanticErrors result;
		QuestionTable qt;

		qt = new QuestionTable();

		form.accept(new TopDown<Void, Void>() {

			@Override
			public Void visit(QLQuestionComputed node, Void context) {
				qt.add(node);
				return null;
			}

			@Override
			public Void visit(QLQuestionInput node, Void context) {
				qt.add(node);
				return null;
			}
		}, null);

		result = new SemanticErrors();

		for (String label : qt.getLabels()) {
			if (qt.getByLabel(label).size() > 1) {
				result.add(new DuplicateQuestionLabels(label, qt.getByLabel(label)));
			}
		}

		for (String name : qt.getNames()) {
			QLQuestion question;

			if (qt.getByName(name).size() == 1) {
				continue;
			}

			question = qt.getByName(name).get(0);
			for (QLQuestion other : qt.getByName(name)) {

				if (other.getType().equals(question.getType())) {
					continue;
				}

				result.add(new DuplicateQuestionName(name, qt.getByName(name)));
				break;
			}
		}

		return result;
	}

	private static class QuestionTable {

		private final Map<String, List<QLQuestion>> nameToQuestion = new HashMap<>();
		private final Map<String, List<QLQuestion>> labelToQuestion = new HashMap<>();

		public QuestionTable() {
			// TODO Auto-generated constructor stub
		}

		public void add(QLQuestion q) {
			List<QLQuestion> nameToQuestionsList;
			List<QLQuestion> labelToQuestionList;

			labelToQuestionList = labelToQuestion.computeIfAbsent(q.getLabel(), f -> new ArrayList<>());
			nameToQuestionsList = nameToQuestion.computeIfAbsent(q.getId(), f -> new ArrayList<>());

			labelToQuestionList.add(q);
			nameToQuestionsList.add(q);
		}

		public Set<String> getNames() {
			return Collections.unmodifiableSet(nameToQuestion.keySet());
		}

		public List<QLQuestion> getByName(String name) {
			return Collections.unmodifiableList(nameToQuestion.get(name));
		}

		public Set<String> getLabels() {
			return Collections.unmodifiableSet(labelToQuestion.keySet());
		}

		public List<QLQuestion> getByLabel(String label) {
			return Collections.unmodifiableList(labelToQuestion.get(label));
		}
	}

	/**
	 * Validate the that there are no cyclic dependencies between questions of
	 * the supplied {@code questionnaire}.
	 * 
	 * @param questionnaire
	 * @return a {@link SemanticErrors} containing errors and warnings.
	 */
	public SemanticErrors validateCyclicReferences(QLForm form) {
		SemanticErrors result;

		result = new SemanticErrors();

		CyclicReferences.collect(form).forEach(cr -> {
			result.add(new CyclicDependency(cr));
		});

		return result;
	}

	private static class TypeCheckVisitor implements ExpressionVisitor<QLType, SymbolTable>, QLFormVisitor<Void, SymbolTable>,
			QLStatementVisitor<Void, SymbolTable> {

		private SemanticErrors result;

		private TypeCheckVisitor() {
		}

		public SemanticErrors visit(QLForm form) {
			SymbolTable table;

			result = new SemanticErrors();

			table = new SymbolTable();
			form.accept(this, table);

			return result;
		}

		@Override
		public Void visit(QLForm node, SymbolTable st) {
			node.getBody().accept(this, st);
			return null;
		}

		@Override
		public Void visit(QLBlock node, SymbolTable st) {
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
		public Void visit(QLIFStatement node, SymbolTable st) {
			checkType(node.getCondition(), st, QLType.BOOLEAN);
			node.getBody().accept(this, st);

			return null;
		}

		@Override
		public Void visit(QLQuestionInput node, SymbolTable st) {
			st.setType(node.getId(), node.getType());
			return null;
		}

		@Override
		public Void visit(QLQuestionComputed node, SymbolTable st) {
			st.setType(node.getId(), node.getType());
			return null;
		}

		@Override
		public QLType visit(VariableExpr node, SymbolTable st) {
			QLType type;

			type = st.getType(node.getVariableId());
			if (type == null) {
				result.add(new UndeclaredVariable(node));
				return null;
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
		public QLType visit(Negative node, SymbolTable st) {
			checkType(node, st, QLType.INTEGER);
			return QLType.INTEGER;
		}

		@Override
		public QLType visit(Positive node, SymbolTable st) {
			checkType(node, st, QLType.INTEGER);
			return QLType.INTEGER;
		}

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
			QLType lhsType;
			QLType rhsType;

			lhsType = node.left().accept(this, st);
			rhsType = node.right().accept(this, st);
			if (!lhsType.equals(rhsType)) {
				result.add(new OperandTypeMismatch(node, lhsType, rhsType));
			}

			return QLType.BOOLEAN;
		}

		@Override
		public QLType visit(EqualsNot node, SymbolTable st) {
			QLType lhsType;
			QLType rhsType;

			lhsType = node.left().accept(this, st);
			rhsType = node.right().accept(this, st);
			if (!lhsType.equals(rhsType)) {
				result.add(new OperandTypeMismatch(node, lhsType, rhsType));
			}

			return QLType.BOOLEAN;
		}

		// Number relations
		@Override
		public QLType visit(GreaterThanOrEqual node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.BOOLEAN;
		}

		@Override
		public QLType visit(GreaterThan node, SymbolTable st) {
			checkOperands(node, st, QLType.INTEGER);
			return QLType.BOOLEAN;
		}

		@Override
		public QLType visit(LessThanOrEqual node, SymbolTable st) {
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

		@Override
		public QLType visit(Not node, SymbolTable st) {
			checkType(node, st, QLType.BOOLEAN);
			return QLType.BOOLEAN;
		}

		private void checkOperands(BinaryExpr expr, SymbolTable st, QLType expectedType) {
			checkType(expr.left(), st, expectedType);
			checkType(expr.right(), st, expectedType);
		}

		private void checkType(Expression expr, SymbolTable st, QLType expectedType) {
			QLType actualType;

			actualType = expr.accept(this, st);

			if (actualType == null) {
				result.add(new UnknownType(expr));
				return;
			}

			if (!actualType.equals(expectedType)) {
				result.add(new TypeMismatch(expr, expectedType, actualType));
			}
		}
	}

	private static class SymbolTable {

		private Map<String, QLType> nameToType = new HashMap<>();

		public SymbolTable() {

		}

		public void setType(String name, QLType type) {
			nameToType.put(name, type);
		}

		public QLType getType(String name) {
			return nameToType.get(name);
		}
	}

	public static abstract class SemanticMessage {

		public static enum Level {
			ERROR("ERROR  "), WARNING("WARNING");

			private final String text;

			private Level(String text) {
				this.text = text;
			}

			public String getText() {
				return text;
			}
		}

		private SemanticMessage() {

		}

		public abstract String getSourceLocation();

		public abstract String getMessage();

		public abstract Level getLevel();

		@Override
		public String toString() {
			return getLevel().getText() + " " + getSourceLocation() + " " + getMessage();
		}
	}

	private static class OperandTypeMismatch extends SemanticMessage {

		private final String MESSAGE = "Type mismatch: operands of %s should be of same type. (lhs='%s', rhs='%s'";

		private final String msg;
		private final Expression expr;

		public OperandTypeMismatch(BinaryExpr expr, QLType lhsType, QLType rhsType) {
			msg = String.format(MESSAGE, expr.getSourceText(), lhsType, rhsType);
			this.expr = expr;
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}

		@Override
		public String getMessage() {
			return msg;
		}

		@Override
		public String getSourceLocation() {
			return expr.getSourceLocation();
		}
	}

	private static class TypeMismatch extends SemanticMessage {

		private final String MESSAGE = "Type mismatch: '%s' should be of type '%s' but is of type '%s'. ";

		private final String msg;
		private final Expression expr;

		public TypeMismatch(Expression expr, QLType expected, QLType actual) {
			msg = String.format(MESSAGE, expr.getSourceText(), expected, actual);
			this.expr = expr;
		}

		@Override
		public String getMessage() {
			return msg;
		}

		@Override
		public String getSourceLocation() {
			return expr.getSourceLocation();
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}
	}

	private static class UnknownType extends SemanticMessage {

		private final String MESSAGE = "Unknown type for %s";

		private final String msg;
		private final Expression expr;

		public UnknownType(Expression expr) {
			msg = String.format(MESSAGE, expr);
			this.expr = expr;
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}

		@Override
		public String getMessage() {
			return msg;
		}

		@Override
		public String getSourceLocation() {
			return expr.getSourceLocation();
		}
	}

	private static class CyclicDependency extends SemanticMessage {

		private final String MESSAGE = "Cyclic dependency for question %s: (%s)";

		private final String msg;

		public CyclicDependency(CyclicReference cr) {
			msg = String.format(MESSAGE, cr.getReference(), cr.getPath());
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}

		@Override
		public String getSourceLocation() {
			return "";
		}

		@Override
		public String getMessage() {
			return msg;
		}
	}

	private static class UndeclaredVariable extends SemanticMessage {

		private static final String MESSAGE = "Undeclared variable %s, %s";
		private final String msg;
		private final VariableExpr node;

		public UndeclaredVariable(VariableExpr node) {
			msg = String.format(MESSAGE, node, node.getVariableId());
			this.node = node;
		}

		@Override
		public String getMessage() {
			return msg;
		}

		@Override
		public String getSourceLocation() {
			return node.getSourceLocation();
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}

		@Override
		public String toString() {
			return msg;
		}
	}

	private static class DuplicateQuestionLabels extends SemanticQuestionMessage {

		private static final String MESSAGE = "Duplicate labels: %s";

		public DuplicateQuestionLabels(String label, List<QLQuestion> questions) {
			super(String.format(MESSAGE, label), questions);
		}

		@Override
		public Level getLevel() {
			return Level.WARNING;
		}
	}

	private static class DuplicateQuestionName extends SemanticQuestionMessage {

		private static final String MESSAGE = "Question '%s' has been declared multiple times, but with different types:";

		public DuplicateQuestionName(String name, List<QLQuestion> questions) {
			super(String.format(MESSAGE, name), questions);
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}
	}

	private static abstract class SemanticQuestionMessage extends SemanticMessage {

		private final String message;

		public SemanticQuestionMessage(String msg, List<QLQuestion> questions) {
			StringBuilder sb;

			assert !questions.isEmpty() : "Question list should not be empty";

			sb = new StringBuilder();
			sb.append(msg);
			sb.append(System.lineSeparator());
			questions.stream().forEach(c -> {
				sb.append("  ");
				sb.append(c.toString());
				sb.append(System.lineSeparator());
			});

			message = sb.toString();
		}

		@Override
		public String getSourceLocation() {
			return "";
		}

		@Override
		public String getMessage() {
			return message;
		}
	}

	public static class SemanticErrors {

		private final Map<Level, List<SemanticMessage>> levelToMessages = new HashMap<>();

		public SemanticErrors() {

		}

		void add(SemanticMessage error) {
			levelToMessages.computeIfAbsent(error.getLevel(), f -> new ArrayList<>()).add(error);
		}

		void addAll(SemanticErrors result) {
			result.getAllMessages().forEach(m -> add(m));
		}

		private boolean hasMessages(Level level) {
			return !getByLevel(level).isEmpty();
		}

		public boolean hasErrors() {
			return hasMessages(Level.ERROR);
		}

		private List<SemanticMessage> getByLevel(Level level) {
			return Collections.unmodifiableList(levelToMessages.getOrDefault(level, Collections.emptyList()));
		}

		public List<SemanticMessage> getErrors() {
			return Collections.unmodifiableList(getByLevel(Level.ERROR));
		}

		public boolean hasWarnings() {
			return hasMessages(Level.WARNING);
		}

		public List<SemanticMessage> getWarnings() {
			return Collections.unmodifiableList(getByLevel(Level.WARNING));
		}

		public boolean hasMessages() {
			return hasErrors() || hasWarnings();
		}

		public List<SemanticMessage> getAllMessages() {
			List<SemanticMessage> allMessages;

			allMessages = new ArrayList<>();
			allMessages.addAll(getErrors());
			allMessages.addAll(getWarnings());

			return Collections.unmodifiableList(allMessages);
		}

		public void print() {
			for (SemanticMessage msg : getErrors()) {
				System.err.println(msg.toString());
			}

			for (SemanticMessage msg : getWarnings()) {
				System.out.println(msg.toString());
			}
		}
	}
}
