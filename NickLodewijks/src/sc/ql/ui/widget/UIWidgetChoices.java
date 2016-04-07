package sc.ql.ui.widget;

import java.util.Collections;
import java.util.List;

import sc.ql.value.Value;

public class UIWidgetChoices
{
  private final List<UIWidgetChoice> choices;
  private final UIWidgetChoice defaultChoice;

  public UIWidgetChoices(List<UIWidgetChoice> choices, UIWidgetChoice defaultValue)
  {
    this.choices = choices;
    this.defaultChoice = defaultValue;
  }

  public List<UIWidgetChoice> values()
  {
    return Collections.unmodifiableList(choices);
  }

  public UIWidgetChoice defaultValue()
  {
    return defaultChoice;
  }

  public int indexOf(UIWidgetChoice choice)
  {
    return choices.indexOf(choice);
  }

  public UIWidgetChoice getByName(String name)
  {
    for (UIWidgetChoice choice : choices)
    {
      if (choice.getName().equals(name))
      {
        return choice;
      }
    }

    return null;
  }

  public UIWidgetChoice getByValue(Value value)
  {
    for (UIWidgetChoice choice : choices)
    {
      if (choice.getValue().equals(value))
      {
        return choice;
      }
    }

    return null;
  }
}