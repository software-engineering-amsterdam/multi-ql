package sc.ql.ui.widget;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;

import sc.ql.value.StringValue;
import sc.ql.value.Value;

public class UILabel
    implements UIWidget
{
  private final JLabel label;

  private UIWidgetStyle style = new UIWidgetStyle(UIManager.getDefaults().getFont("JLabel.font"),
                                                  new Dimension(150,
                                                                50),
                                                  Color.BLACK);

  public UILabel(String text)
  {
    label = new JLabel(text);
    label.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

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
    label.setFont(style.getFont());
    label.setPreferredSize(new Dimension(style.getWidth(),
                                         style.getHeight()));
    label.setForeground(style.getColor());
  }

  @Override
  public JComponent getComponent()
  {
    return label;
  }

  @Override
  public StringValue getValue()
  {
    return new StringValue(label.getText());
  }

  @Override
  public void setValue(Value value)
  {
    label.setText(value.toString());
  }

  @Override
  public void setVisible(boolean visible)
  {
    label.setVisible(visible);
  }

  @Override
  public void setEditable(boolean editable)
  {
    // NOOP
  }
}