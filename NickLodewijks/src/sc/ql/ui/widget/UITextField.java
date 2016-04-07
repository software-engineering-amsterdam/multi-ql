package sc.ql.ui.widget;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import sc.ql.ast.Statement.Question;
import sc.ql.eval.Environment;
import sc.ql.value.Value;

public class UITextField
    extends AbstractUIWidget
{
  private final JTextField textField;
  private final JPanel panel;

  private UIWidgetStyle style = new UIWidgetStyle(UIManager.getDefaults().getFont("JTextField.font"),
                                                  new Dimension(130,
                                                                30),
                                                  Color.BLACK);

  public UITextField(Environment env, Question q, Value defaultValue)
  {
    super(env,
          q.name(),
          defaultValue);

    panel = new JPanel();

    textField = new JTextField();
    textField.addKeyListener(new KeyAdapter()
    {

      @Override
      public void keyReleased(KeyEvent e)
      {
        setValue(getViewValue());
      }
    });
    panel.add(textField);

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
    textField.setFont(style.getFont());
    textField.setPreferredSize(new Dimension(style.getWidth(),
                                             style.getHeight()));
    textField.setForeground(style.getColor());
  }

  @Override
  public JComponent getComponent()
  {
    return panel;
  }

  @Override
  protected Value getViewValue()
  {
    return getDefaultValue().parse(textField.getText());
  }

  @Override
  protected void setViewValue(Value value)
  {
    textField.setText(value == null ? "" : value.toString());
  }

  @Override
  public void setVisible(boolean visible)
  {
    textField.setVisible(visible);
    textField.invalidate();
    panel.repaint();
  }

  @Override
  public void setEditable(boolean editable)
  {
    textField.setEditable(editable);
  }
}