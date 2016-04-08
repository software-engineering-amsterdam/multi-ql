package sc.ql.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import sc.ql.ast.Expression;
import sc.ql.ast.Statement.Question;
import sc.ql.eval.Environment;
import sc.ql.eval.Environment.ContextListener;
import sc.ql.eval.Evaluator;
import sc.ql.ui.widget.UIWidget;
import sc.ql.value.BooleanValue;

public class UIQuestion
    implements ContextListener
{
  private final JPanel panel;
  private final Question question;
  private final Expression condition;
  private final Expression valueComputation;

  private final UIWidget labelWidget;
  private final UIWidget valueWidget;

  public UIQuestion(Environment env,
      Question question,
      UIWidget labelWidget,
      UIWidget valueWidget,
      Expression condition,
      Expression valueComputation)
  {
    this.question = question;
    this.condition = condition;

    this.labelWidget = labelWidget;
    this.valueWidget = valueWidget;
    this.valueComputation = valueComputation;

    if (valueComputation != null)
    {
      this.valueWidget.setEditable(false);
      env.addComputedValue(question.name(),
                           valueComputation);
    }

    env.addContextListener(this);

    setVisible(isEnabled(env));

    panel = new JPanel(new BorderLayout());
    panel.add(labelWidget.getComponent(),
              BorderLayout.CENTER);
    panel.add(valueWidget.getComponent(),
              BorderLayout.EAST);
    panel.setPreferredSize(new Dimension(500,
                                         50));
    panel.setMinimumSize(new Dimension(500,
                                       50));
    panel.setMaximumSize(new Dimension(500,
                                       50));
  }

  public Question question()
  {
    return question;
  }

  public String name()
  {
    return question.name();
  }

  public boolean isEnabled(Environment env)
  {
    return Evaluator.evaluate(condition,
                              env).equals(BooleanValue.TRUE);
  }

  @Override
  public void contextChanged(Environment env)
  {
    setVisible(isEnabled(env));

    if (valueComputation != null)
    {
      valueWidget.setValue(env.getValue(question.name()));
    }
  }

  private void setVisible(boolean visible)
  {
    SwingUtilities.invokeLater(() -> {
      labelWidget.setVisible(visible);
      valueWidget.setVisible(visible);
    });
  }

  public JComponent getComponent()
  {
    return panel;
  }
}