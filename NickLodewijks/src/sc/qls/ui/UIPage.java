package sc.qls.ui;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

import sc.ql.ui.UIQuestion;
import sc.qls.ast.Page;
import sc.qls.ast.Section;

public class UIPage
{
  private final List<UISection> sections = new ArrayList<>();
  private final JPanel panel;

  public UIPage(Page page, List<UIQuestion> questions)
  {
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel,
                                  BoxLayout.PAGE_AXIS));

    for (Section section : page.sections())
    {
      UISection uiSection;

      uiSection = new UISection(section,
                                section.filter(questions));
      sections.add(uiSection);

      panel.add(uiSection.getComponent());
      panel.add(Box.createRigidArea(new Dimension(0,
                                                  2)));
    }
  }

  public JComponent getComponent()
  {
    return panel;
  }
}