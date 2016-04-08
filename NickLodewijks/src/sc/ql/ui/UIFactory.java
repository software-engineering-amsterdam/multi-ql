package sc.ql.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sc.ql.ast.Expression;
import sc.ql.ast.Expression.And;
import sc.ql.ast.Expression.LiteralExpr;
import sc.ql.ast.Form;
import sc.ql.ast.Literal.BooleanLiteral;
import sc.ql.ast.Statement.ComputedQuestion;
import sc.ql.ast.Statement.IfThen;
import sc.ql.ast.Statement.NormalQuestion;
import sc.ql.ast.Statement.Question;
import sc.ql.ast.TopDown;
import sc.ql.ast.ValueType.BooleanType;
import sc.ql.ast.ValueType.IntegerType;
import sc.ql.ast.ValueType.StringType;
import sc.ql.ast.ValueType.UnknownType;
import sc.ql.ast.ValueTypeVisitor;
import sc.ql.eval.Environment;
import sc.ql.ui.widget.UILabel;
import sc.ql.ui.widget.UIRadioButton;
import sc.ql.ui.widget.UITextField;
import sc.ql.ui.widget.UIWidget;
import sc.ql.ui.widget.UIWidgetChoice;
import sc.ql.ui.widget.UIWidgetChoices;
import sc.ql.value.BooleanValue;
import sc.ql.value.NumberValue;
import sc.ql.value.StringValue;

public class UIFactory
    implements QlAlg<UIQuestionnaire, UIQuestion, UIWidget>
{

  @Override
  public UIQuestionnaire form(Form form)
  {
    List<UIQuestion> questions;
    Environment env;

    env = new Environment();

    questions = new ArrayList<>();

    form.body().accept(new TopDown<Void, Expression>()
                       {
                         @Override
                         public Void visit(IfThen node, Expression condition)
                         {
                           node.then().accept(this,
                                              new And(condition,
                                                      node.condition()));
                           return null;
                         }

                         @Override
                         public Void visit(NormalQuestion question, Expression condition)
                         {
                           questions.add(question(question,
                                                  condition,
                                                  null,
                                                  env));
                           return null;
                         }

                         @Override
                         public Void visit(ComputedQuestion question, Expression condition)
                         {
                           questions.add(question(question,
                                                  condition,
                                                  question.computation(),
                                                  env));
                           return null;
                         }
                       },
                       new LiteralExpr(BooleanLiteral.TRUE));

    return new UIQuestionnaire(questions);
  }

  @Override
  public UIQuestion question(Question question, Expression condition, Expression computation, Environment env)
  {
    UIWidget labelWidget;
    UIWidget valueWidget;

    labelWidget = labelWidget(question,
                              env);
    valueWidget = valueWidget(question,
                              env);

    return new UIQuestion(env,
                          question,
                          labelWidget,
                          valueWidget,
                          condition,
                          computation);
  }

  @Override
  public UIWidget labelWidget(Question question, Environment env)
  {
    return new UILabel(question.label());
  }

  @Override
  public UIWidget valueWidget(Question question, Environment env)
  {
    return question.type().accept(new ValueTypeVisitor<UIWidget, Void>()
                                  {

                                    @Override
                                    public UIWidget visit(UnknownType type, Void context)
                                    {
                                      throw new IllegalStateException("Cannot create widget for an unknown type.");
                                    }

                                    @Override
                                    public UIWidget visit(BooleanType type, Void unused)
                                    {
                                      final UIWidgetChoice YES;
                                      final UIWidgetChoice NO;
                                      UIWidgetChoices choices;

                                      YES = new UIWidgetChoice("Yes",
                                                               BooleanValue.TRUE);
                                      NO = new UIWidgetChoice("No",
                                                              BooleanValue.FALSE);

                                      choices = new UIWidgetChoices(Arrays.asList(YES,
                                                                                  NO),
                                                                    NO);

                                      return new UIRadioButton(env,
                                                               question,
                                                               choices);
                                    }

                                    @Override
                                    public UIWidget visit(StringType type, Void unused)
                                    {
                                      return new UITextField(env,
                                                             question,
                                                             new StringValue(""));
                                    }

                                    @Override
                                    public UIWidget visit(IntegerType type, Void unused)
                                    {
                                      return new UITextField(env,
                                                             question,
                                                             new NumberValue(0));
                                    }
                                  },
                                  null);
  }
}
