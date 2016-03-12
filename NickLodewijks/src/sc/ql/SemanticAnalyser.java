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
import sc.ql.ast.ExpressionVisitor;
import sc.ql.ast.Form;
import sc.ql.ast.FormVisitor;
import sc.ql.ast.Statement;
import sc.ql.ast.Statement.Block;
import sc.ql.ast.Statement.ComputedQuestion;
import sc.ql.ast.Statement.IfThen;
import sc.ql.ast.Statement.NormalQuestion;
import sc.ql.ast.Statement.Question;
import sc.ql.ast.StatementVisitor;
import sc.ql.ast.TopDown;
import sc.ql.ast.ValueType;

public class SemanticAnalyser {

	public SemanticAnalyser() {

	}

	public SemanticErrors validate(Form form) {
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
	public SemanticErrors validateTypes(Form form) {
		return new TypeCheckVisitor().visit(form);
	}

	public SemanticErrors validateQuestions(Form form) {
		SemanticErrors result;
		QuestionTable qt;

		qt = new QuestionTable();

		form.accept(new TopDown<Void, Void>() {

			@Override
			public Void visit(ComputedQuestion node, Void context) {
				qt.add(node);
				return null;
			}

			@Override
			public Void visit(NormalQuestion node, Void context) {
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
			Question question;

			if (qt.getByName(name).size() == 1) {
				continue;
			}

			question = qt.getByName(name).get(0);
			for (Question other : qt.getByName(name)) {

				if (other.type().equals(question.type())) {
					continue;
				}

				result.add(new DuplicateQuestionName(name, qt.getByName(name)));
				break;
			}
		}

		return result;
	}

	private static class QuestionTable {

		private final Map<String, List<Question>> nameToQuestion = new HashMap<>();
		private final Map<String, List<Question>> labelToQuestion = new HashMap<>();

		public QuestionTable() {
			// TODO Auto-generated constructor stub
		}

		public void add(Question q) {
			List<Question> nameToQuestionsList;
			List<Question> labelToQuestionList;

			labelToQuestionList = labelToQuestion.computeIfAbsent(q.label(), f -> new ArrayList<>());
			nameToQuestionsList = nameToQuestion.computeIfAbsent(q.name(), f -> new ArrayList<>());

			labelToQuestionList.add(q);
			nameToQuestionsList.add(q);
		}

		public Set<String> getNames() {
			return Collections.unmodifiableSet(nameToQuestion.keySet());
		}

		public List<Question> getByName(String name) {
			return Collections.unmodifiableList(nameToQuestion.get(name));
		}

		public Set<String> getLabels() {
			return Collections.unmodifiableSet(labelToQuestion.keySet());
		}

		public List<Question> getByLabel(String label) {
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
	public SemanticErrors validateCyclicReferences(Form form) {
		SemanticErrors result;

		result = new SemanticErrors();

		CyclicReferences.collect(form).forEach(cr -> {
			result.add(new CyclicDependency(cr));
		});

		return result;
	}

	private static class TypeCheckVisitor implements ExpressionVisitor<ValueType, SymbolTable>,
			FormVisitor<Void, SymbolTable>, StatementVisitor<Void, SymbolTable> {

		private SemanticErrors result;

		private TypeCheckVisitor() {
		}

		public SemanticErrors visit(Form form) {
			SymbolTable table;

			result = new SemanticErrors();

			table = new SymbolTable();
			form.accept(this, table);

			return result;
		}

		@Override
		public Void visit(Form node, SymbolTable st) {
			node.getBody().accept(this, st);
			return null;
		}

		@Override
		public Void visit(Block node, SymbolTable st) {

			collectSymbols(node, st);
			checkExpressions(node, st);

			return null;
		}

		private void collectSymbols(Block block, SymbolTable st) {
			for (Statement statement : block.statements()) {
				statement.accept(new StatementVisitor<Void, Void>() {

					@Override
					public Void visit(ComputedQuestion question, Void unused) {
						question.accept(TypeCheckVisitor.this, st);
						return null;
					};

					@Override
					public Void visit(NormalQuestion question, Void unused) {
						question.accept(TypeCheckVisitor.this, st);
						return null;
					}

					@Override
					public Void visit(Block block, Void unused) {
						block.accept(TypeCheckVisitor.this, st);
						return null;
					}

					@Override
					public Void visit(IfThen node, Void context) {
						return null;
					}
				}, null);
			}
		}

		private void checkExpressions(Block block, SymbolTable st) {
			block.statements().forEach(statement -> {
				statement.accept(new StatementVisitor<Void, Void>() {

					@Override
					public Void visit(ComputedQuestion question, Void unused) {
						ValueType type;

						type = st.typeOf(question.name());
						assert type != null;

						checkType(question.computation(), st, type);
						return null;
					};

					@Override
					public Void visit(NormalQuestion question, Void unused) {
						return null;
					}

					@Override
					public Void visit(Block block, Void unused) {
						return null;
					}

					@Override
					public Void visit(IfThen ifThen, Void unused) {
						checkType(ifThen.condition(), st, ValueType.BOOLEAN);
						ifThen.then().accept(TypeCheckVisitor.this, st);
						return null;
					}
				}, null);
			});
		}

		@Override
		public Void visit(IfThen node, SymbolTable st) {
			assert false : "Should have visited this type of node in checkExpressions()";
			return null;
		}

		@Override
		public Void visit(NormalQuestion node, SymbolTable st) {
			st.add(node.name(), node.type());
			return null;
		}

		@Override
		public Void visit(ComputedQuestion node, SymbolTable st) {
			st.add(node.name(), node.type());
			return null;
		}

		@Override
		public ValueType visit(VariableExpr node, SymbolTable st) {
			ValueType type;

			type = st.typeOf(node.getVariableName());
			if (type == null) {
				result.add(new UndeclaredVariable(node));
				return null;
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
		public ValueType visit(Negative node, SymbolTable st) {
			checkType(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Positive node, SymbolTable st) {
			checkType(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Add node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Divide node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Multiply node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		@Override
		public ValueType visit(Subtract node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.INTEGER;
		}

		// Equality relations
		@Override
		public ValueType visit(Equals node, SymbolTable st) {
			ValueType lhsType;
			ValueType rhsType;

			lhsType = node.left().accept(this, st);
			rhsType = node.right().accept(this, st);
			if (!lhsType.equals(rhsType)) {
				result.add(new OperandTypeMismatch(node, lhsType, rhsType));
			}

			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(EqualsNot node, SymbolTable st) {
			ValueType lhsType;
			ValueType rhsType;

			lhsType = node.left().accept(this, st);
			rhsType = node.right().accept(this, st);
			if (!lhsType.equals(rhsType)) {
				result.add(new OperandTypeMismatch(node, lhsType, rhsType));
			}

			return ValueType.BOOLEAN;
		}

		// Number relations
		@Override
		public ValueType visit(GreaterThanOrEqual node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(GreaterThan node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(LessThanOrEqual node, SymbolTable st) {
			checkOperands(node, st, ValueType.INTEGER);
			return ValueType.BOOLEAN;
		}

		@Override
		public ValueType visit(LessThan node, SymbolTable st) {
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

		@Override
		public ValueType visit(Not node, SymbolTable st) {
			checkType(node, st, ValueType.BOOLEAN);
			return ValueType.BOOLEAN;
		}

		private void checkOperands(BinaryExpr expr, SymbolTable st, ValueType expectedType) {
			checkType(expr.left(), st, expectedType);
			checkType(expr.right(), st, expectedType);
		}

		private void checkType(Expression expr, SymbolTable st, ValueType expectedType) {
			ValueType actualType;

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

		private Map<String, ValueType> nameToType = new HashMap<>();

		public SymbolTable() {

		}

		public void add(String name, ValueType type) {
			nameToType.put(name, type);
		}

		public ValueType typeOf(String name) {
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

		public OperandTypeMismatch(BinaryExpr expr, ValueType lhsType, ValueType rhsType) {
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

		public TypeMismatch(Expression expr, ValueType expected, ValueType actual) {
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
			msg = String.format(MESSAGE, node, node.getVariableName());
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

		public DuplicateQuestionLabels(String label, List<Question> questions) {
			super(String.format(MESSAGE, label), questions);
		}

		@Override
		public Level getLevel() {
			return Level.WARNING;
		}
	}

	private static class DuplicateQuestionName extends SemanticQuestionMessage {

		private static final String MESSAGE = "Question '%s' has been declared multiple times, but with different types:";

		public DuplicateQuestionName(String name, List<Question> questions) {
			super(String.format(MESSAGE, name), questions);
		}

		@Override
		public Level getLevel() {
			return Level.ERROR;
		}
	}

	private static abstract class SemanticQuestionMessage extends SemanticMessage {

		private final String message;

		public SemanticQuestionMessage(String msg, List<Question> questions) {
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
