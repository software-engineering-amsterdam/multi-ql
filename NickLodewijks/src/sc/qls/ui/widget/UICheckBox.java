package sc.qls.ui.widget;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.UIManager;

import sc.ql.ast.Statement.Question;
import sc.ql.eval.Environment;
import sc.ql.ui.widget.AbstractUIWidget;
import sc.ql.ui.widget.UIWidgetChoice;
import sc.ql.ui.widget.UIWidgetChoices;
import sc.ql.ui.widget.UIWidgetStyle;
import sc.ql.value.Value;

public class UICheckBox
    extends AbstractUIWidget
{
  private final UIWidgetChoices choices;
  private final JCheckBox checkBox;
  private final JPanel panel;

  private UIWidgetStyle style = new UIWidgetStyle(UIManager.getDefaults().getFont("JCheckBox.font"),
                                                  new Dimension(100,
                                                                10),
                                                  Color.BLACK);

  public UICheckBox(Environment env, Question question, UIWidgetChoices choices)
  {
    super(env,
          question.name(),
          choices.defaultValue().getValue());

    this.choices = choices;

    checkBox = new JCheckBox();
    checkBox.addChangeListener(e -> {
      setValue(getCurrentChoice().getValue());
    });

    panel = new JPanel(new BorderLayout());
    panel.add(checkBox,
              BorderLayout.CENTER);
    panel.setPreferredSize(new Dimension(150,
                                         30));

    setViewValue(getDefaultValue());

    setStyle(style);
  }

  @Override
  public UIWidgetStyle getStyle()
  {
    return style;
  }

  @Override
  public void setStyle(UIWidgetStyle style)
  {
    checkBox.setFont(style.getFont());
    checkBox.setForeground(style.getColor());
    checkBox.setPreferredSize(new Dimension(style.getWidth(),
                                            style.getHeight()));
  }

  @Override
  public JComponent getComponent()
  {
    return panel;
  }

  private UIWidgetChoice getCurrentChoice()
  {
    if (checkBox.isSelected())
    {
      return choices.values().get(0);
    }
    else
    {
      return choices.values().get(1);
    }
  }

  @Override
  public Value getViewValue()
  {
    return getCurrentChoice().getValue();
  }

  @Override
  public void setViewValue(Value value)
  {
    UIWidgetChoice choice;

    choice = choices.getByValue(value);
    if (choices.indexOf(choice) == 0)
    {
      checkBox.setSelected(true);
    }
    else
    {
      checkBox.setSelected(false);
    }
  }

  @Override
  public void setVisible(boolean visible)
  {
    panel.setVisible(visible);
  }

  @Override
  public void setEditable(boolean editable)
  {
    checkBox.setEnabled(editable);
  }
}