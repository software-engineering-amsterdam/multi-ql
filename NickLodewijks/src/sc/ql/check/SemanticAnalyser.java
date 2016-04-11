package sc.ql.check;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sc.ql.ast.Expression;
import sc.ql.ast.Expression.Add;
import sc.ql.ast.Expression.And;
import sc.ql.ast.Expression.BinaryExpr;
import sc.ql.ast.Expression.Divide;
import sc.ql.ast.Expression.Equal;
import sc.ql.ast.Expression.GreaterThan;
import sc.ql.ast.Expression.GreaterThanOrEqual;
import sc.ql.ast.Expression.LessThan;
import sc.ql.ast.Expression.LessThanOrEqual;
import sc.ql.ast.Expression.LiteralExpr;
import sc.ql.ast.Expression.Multiply;
import sc.ql.ast.Expression.Negative;
import sc.ql.ast.Expression.Not;
import sc.ql.ast.Expression.NotEqual;
import sc.ql.ast.Expression.Or;
import sc.ql.ast.Expression.Positive;
import sc.ql.ast.Expression.Subtract;
import sc.ql.ast.Expression.VariableExpr;
import sc.ql.ast.ExpressionVisitor;
import sc.ql.ast.Form;
import sc.ql.ast.Literal.BooleanLiteral;
import sc.ql.ast.Literal.IntegerLiteral;
import sc.ql.ast.Literal.StringLiteral;
import sc.ql.ast.LiteralVisitor;
import sc.ql.ast.Statement.Block;
import sc.ql.ast.Statement.ComputedQuestion;
import sc.ql.ast.Statement.IfThen;
import sc.ql.ast.Statement.NormalQuestion;
import sc.ql.ast.Statement.Question;
import sc.ql.ast.StatementVisitor;
import sc.ql.ast.TopDown;
import sc.ql.ast.ValueType;
import sc.ql.ast.ValueType.UnknownType;
import sc.ql.check.ReferenceTable.Reference;
import sc.ql.check.ReferenceTable.ReferencePath;
import sc.ql.check.SemanticMessage.CyclicDependency;
import sc.ql.check.SemanticMessage.DuplicateQuestionLabels;
import sc.ql.check.SemanticMessage.DuplicateQuestionName;
import sc.ql.check.SemanticMessage.Level;
import sc.ql.check.SemanticMessage.OperandTypeMismatch;
import sc.ql.check.SemanticMessage.TypeMismatch;
import sc.ql.check.SemanticMessage.UndeclaredVariable;

public class SemanticAnalyser
{

  private SemanticAnalyser()
  {

  }

  public static SemanticResult validate(Form form)
  {
    SemanticResult result;

    result = new SemanticResult();

    result.addAll(validateQuestions(form).messages());
    result.addAll(validateTypes(form).messages());
    result.addAll(validateCyclicReferences(form).messages());

    return result;
  }

  /**
   * Validate the questions in the {@code form}.
   * <p>
   * This will check for:
   * <li>Reference to undefined questions
   * <li>Duplicate question (name) declaration with different types
   * <li>duplicate question labels</br>
   * 
   * @param form
   * @return a {@link SemanticResult} containing errors and warnings.
   */
  private static SemanticResult validateQuestions(Form form)
  {
    SemanticResult result;
    QuestionTable qt;

    qt = new QuestionTable();

    form.body().accept(new TopDown<Void, Void>()
                       {
                         @Override
                         public Void visit(ComputedQuestion question, Void unused)
                         {
                           qt.add(question);
                           return null;
                         }

                         @Override
                         public Void visit(NormalQuestion question, Void unused)
                         {
                           qt.add(question);
                           return null;
                         }
                       },
                       null);

    result = new SemanticResult();

    for (List<Question> questions : qt.getByLabel().values())
    {
      if (questions.size() > 1)
      {
        questions.forEach(q -> result.add(new DuplicateQuestionLabels(Level.WARNING,
                                                                      q)));
      }
    }

    for (List<Question> questions : qt.getByName().values())
    {
      ValueType type;

      if (questions.size() == 1)
      {
        continue;
      }

      type = questions.get(0).type();
      for (Question other : questions)
      {
        if (other.type().equals(type))
        {
          continue;
        }

        questions.forEach(q -> result.add(new DuplicateQuestionName(Level.ERROR,
                                                                    q)));
        break;
      }
    }

    return result;
  }

  /**
   * Validate the types in the {@code form}.
   * <p>
   * This will check for:
   * <li>Conditions that are not of the type boolean
   * <li>Operands of invalid type to operators</br>
   * 
   * @param form
   * @return a {@link SemanticResult} containing errors and warnings.
   */
  private static SemanticResult validateTypes(Form form)
  {
    return new TypeCheckVisitor().visit(form);
  }

  /**
   * Validate the that there are no cyclic dependencies between questions of the supplied {@code questionnaire}.
   * 
   * @param questionnaire
   * @return a {@link SemanticResult} containing errors and warnings.
   */
  private static SemanticResult validateCyclicReferences(Form form)
  {
    ReferenceTable referenceTable;
    SemanticResult result;

    referenceTable = new ReferenceTable();
    form.body().accept(new TopDown<Void, Void>()
                       {
                         @Override
                         public Void visit(ComputedQuestion question, Void unused)
                         {
                           referenceTable.add(question);
                           return null;
                         }
                       },
                       null);

    result = new SemanticResult();
    for (Reference reference : referenceTable.getReferences())
    {
      for (ReferencePath cyclicDependency : reference.getCyclicPaths())
      {
        result.add(new CyclicDependency(Level.ERROR,
                                        reference,
                                        cyclicDependency));
      }
    }

    return result;
  }

  private static class TypeCheckVisitor
      implements ExpressionVisitor<ValueType, SymbolTable>, StatementVisitor<Void, SymbolTable>,
      LiteralVisitor<ValueType, SymbolTable>
  {
    private SemanticResult result;

    private TypeCheckVisitor()
    {
    }

    public SemanticResult visit(Form form)
    {
      SymbolTable st;

      result = new SemanticResult();

      st = new SymbolTable();

      form.body().accept(new TopDown<Void, Void>()
                         {
                           @Override
                           public Void visit(ComputedQuestion question, Void unused)
                           {
                             st.add(question.name(),
                                    question.type());
                             return null;
                           };

                           @Override
                           public Void visit(NormalQuestion question, Void unused)
                           {
                             st.add(question.name(),
                                    question.type());
                             return null;
                           }
                         },
                         null);

      form.body().accept(this,
                         st);

      return result;
    }

    @Override
    public Void visit(Block node, SymbolTable st)
    {
      node.statements().forEach(statement -> statement.accept(this,
                                                              st));

      return null;
    }

    @Override
    public Void visit(IfThen node, SymbolTable st)
    {
      checkType(node.condition(),
                st,
                ValueType.BOOLEAN);

      node.then().accept(TypeCheckVisitor.this,
                         st);
      return null;
    }

    @Override
    public Void visit(NormalQuestion node, SymbolTable st)
    {
      return null;
    }

    @Override
    public Void visit(ComputedQuestion node, SymbolTable st)
    {
      ValueType type;

      type = st.typeOf(node.name());

      checkType(node.computation(),
                st,
                type);
      return null;
    }

    @Override
    public ValueType visit(VariableExpr node, SymbolTable st)
    {
      ValueType type;

      type = st.typeOf(node.getVariableName());
      if (isUnknownType(type))
      {
        result.add(new UndeclaredVariable(node,
                                          Level.ERROR));
      }

      return type;
    }

    @Override
    public ValueType visit(LiteralExpr node, SymbolTable st)
    {
      return node.literal().accept(this,
                                   st);
    }

    // Literals
    @Override
    public ValueType visit(BooleanLiteral node, SymbolTable st)
    {
      return ValueType.BOOLEAN;
    }

    @Override
    public ValueType visit(IntegerLiteral node, SymbolTable st)
    {
      return ValueType.INTEGER;
    }

    @Override
    public ValueType visit(StringLiteral node, SymbolTable st)
    {
      return ValueType.STRING;
    }

    // Arithmetic operations
    @Override
    public ValueType visit(Negative node, SymbolTable st)
    {
      checkType(node,
                st,
                ValueType.INTEGER);
      return ValueType.INTEGER;
    }

    @Override
    public ValueType visit(Positive node, SymbolTable st)
    {
      checkType(node,
                st,
                ValueType.INTEGER);
      return ValueType.INTEGER;
    }

    @Override
    public ValueType visit(Add node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.INTEGER);
      return ValueType.INTEGER;
    }

    @Override
    public ValueType visit(Divide node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.INTEGER);
      return ValueType.INTEGER;
    }

    @Override
    public ValueType visit(Multiply node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.INTEGER);
      return ValueType.INTEGER;
    }

    @Override
    public ValueType visit(Subtract node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.INTEGER);
      return ValueType.INTEGER;
    }

    // Equality relations
    @Override
    public ValueType visit(Equal node, SymbolTable st)
    {
      ValueType lhsType;
      ValueType rhsType;

      lhsType = node.left().accept(this,
                                   st);
      rhsType = node.right().accept(this,
                                    st);

      if (!isUnknownType(lhsType) && !isUnknownType(rhsType))
      {
        if (!lhsType.equals(rhsType))
        {
          result.add(new OperandTypeMismatch(Level.ERROR,
                                             node,
                                             lhsType,
                                             rhsType));
        }
      }

      return ValueType.BOOLEAN;
    }

    @Override
    public ValueType visit(NotEqual node, SymbolTable st)
    {
      ValueType lhsType;
      ValueType rhsType;

      lhsType = node.left().accept(this,
                                   st);
      rhsType = node.right().accept(this,
                                    st);

      if (!isUnknownType(lhsType) && !isUnknownType(rhsType))
      {
        if (!lhsType.equals(rhsType))
        {
          result.add(new OperandTypeMismatch(Level.ERROR,
                                             node,
                                             lhsType,
                                             rhsType));
        }
      }

      return ValueType.BOOLEAN;
    }

    // Number relations
    @Override
    public ValueType visit(GreaterThanOrEqual node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.INTEGER);
      return ValueType.BOOLEAN;
    }

    @Override
    public ValueType visit(GreaterThan node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.INTEGER);
      return ValueType.BOOLEAN;
    }

    @Override
    public ValueType visit(LessThanOrEqual node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.INTEGER);
      return ValueType.BOOLEAN;
    }

    @Override
    public ValueType visit(LessThan node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.INTEGER);
      return ValueType.BOOLEAN;
    }

    // Boolean relations
    @Override
    public ValueType visit(And node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.BOOLEAN);
      return ValueType.BOOLEAN;
    }

    @Override
    public ValueType visit(Or node, SymbolTable st)
    {
      checkOperands(node,
                    st,
                    ValueType.BOOLEAN);
      return ValueType.BOOLEAN;
    }

    @Override
    public ValueType visit(Not node, SymbolTable st)
    {
      checkType(node,
                st,
                ValueType.BOOLEAN);
      return ValueType.BOOLEAN;
    }

    private boolean isUnknownType(ValueType type)
    {
      return type.equals(new UnknownType());
    }

    private void checkOperands(BinaryExpr expr, SymbolTable st, ValueType expectedType)
    {
      checkType(expr.left(),
                st,
                expectedType);
      checkType(expr.right(),
                st,
                expectedType);
    }

    private void checkType(Expression expr, SymbolTable st, ValueType expectedType)
    {
      ValueType actualType;

      actualType = expr.accept(this,
                               st);

      // Prevent error cascading
      if (isUnknownType(actualType))
      {
        return;
      }

      if (!actualType.equals(expectedType))
      {
        result.add(new TypeMismatch(Level.ERROR,
                                    expr,
                                    expectedType,
                                    actualType));
      }
    }
  }

  private static class SymbolTable
  {
    private Map<String, ValueType> nameToType = new HashMap<>();

    public SymbolTable()
    {

    }

    public void add(String name, ValueType type)
    {
      nameToType.put(name,
                     type);
    }

    public ValueType typeOf(String name)
    {
      if (!nameToType.containsKey(name))
      {
        return new UnknownType();
      }

      return nameToType.get(name);
    }
  }

  private static class QuestionTable
  {
    private final Map<String, List<Question>> nameToQuestion = new HashMap<>();
    private final Map<String, List<Question>> labelToQuestion = new HashMap<>();

    public QuestionTable()
    {

    }

    public void add(Question q)
    {
      List<Question> nameToQuestionsList;
      List<Question> labelToQuestionList;

      labelToQuestionList = labelToQuestion.computeIfAbsent(q.label(),
                                                            f -> new ArrayList<>());
      nameToQuestionsList = nameToQuestion.computeIfAbsent(q.name(),
                                                           f -> new ArrayList<>());

      labelToQuestionList.add(q);
      nameToQuestionsList.add(q);
    }

    public Map<String, List<Question>> getByLabel()
    {
      Map<String, List<Question>> byLabel;

      byLabel = new HashMap<String, List<Question>>();
      labelToQuestion.forEach((key, value) -> byLabel.put(key,
                                                          Collections.unmodifiableList(value)));

      return Collections.unmodifiableMap(byLabel);
    }

    public Map<String, List<Question>> getByName()
    {
      Map<String, List<Question>> byName;

      byName = new HashMap<String, List<Question>>();
      nameToQuestion.forEach((key, value) -> byName.put(key,
                                                        Collections.unmodifiableList(value)));

      return Collections.unmodifiableMap(byName);
    }
  }
}
