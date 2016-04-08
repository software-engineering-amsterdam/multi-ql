package sc.qls.ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import sc.ql.ui.UIQuestion;
import sc.qls.ast.Section;

public class UISection
{
  private final Section section;
  private final JComponent component;

  public UISection(Section section, List<UIQuestion> questions)
  {
    this.section = section;

    component = Box.createVerticalBox();
    component.setBorder(createBorder());

    for (UIQuestion question : section.sort(questions))
    {
      component.add(question.getComponent());
      component.add(Box.createRigidArea(new Dimension(0,
                                                      2)));
    }
  }

  private Border createBorder()
  {
    TitledBorder title;

    title = BorderFactory.createTitledBorder(section.name());
    title.setTitleJustification(TitledBorder.LEFT);

    return title;
  }

  public JComponent getComponent()
  {
    return component;
  }
}