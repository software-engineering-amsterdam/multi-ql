package sc.qls.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;

import sc.ql.ui.UIQuestion;
import sc.qls.ast.Page;
import sc.qls.ast.Section;

public class UIPage
{
  private final List<UISection> sections = new ArrayList<>();
  private final JComponent component;
  private final Page page;

  public UIPage(Page page, List<UIQuestion> questions)
  {
    Box titleBox;
    JLabel title;

    this.page = page;

    title = new JLabel(page.name());
    title.setFont(new Font("Serif",
                           Font.BOLD,
                           30));

    titleBox = Box.createHorizontalBox();
    titleBox.add(title);
    titleBox.add(Box.createHorizontalGlue());

    component = Box.createVerticalBox();
    component.add(titleBox);

    for (Section section : page.sections())
    {
      UISection uiSection;

      uiSection = new UISection(section,
                                section.filter(questions));
      sections.add(uiSection);

      component.add(uiSection.getComponent());
      component.add(Box.createRigidArea(new Dimension(0,
                                                      2)));
    }
  }

  public String name()
  {
    return page.name();
  }

  public JComponent getComponent()
  {
    return component;
  }
}