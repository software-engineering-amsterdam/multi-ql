package sc.qls.ui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import sc.ql.ui.UIQuestion;
import sc.ql.ui.UIQuestionnaire;
import sc.qls.ast.Page;
import sc.qls.ast.StyleSheet;

public class StyledUIQuestionnaire
    extends UIQuestionnaire
{
  private final StyleSheet styleSheet;

  public StyledUIQuestionnaire(List<UIQuestion> questions, StyleSheet styleSheet)
  {
    super(questions);

    this.styleSheet = styleSheet;
  }

  @Override
  protected JFrame getComponent()
  {
    JPanel panel;
    JPanel root;
    JScrollPane scrollPanel;
    JPanel formPanel;
    JFrame jframe;

    formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel,
                                      BoxLayout.PAGE_AXIS));
    for (Page page : styleSheet.getPages())
    {
      UIPage uiPage;

      uiPage = new UIPage(page,
                          page.filter(questions()));

      formPanel.add(uiPage.getComponent());
      formPanel.add(Box.createRigidArea(new Dimension(0,
                                                      2)));
    }

    scrollPanel = new JScrollPane();
    scrollPanel.setViewportView(formPanel);
    scrollPanel.setBorder(null);

    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel,
                                  BoxLayout.PAGE_AXIS));
    panel.add(scrollPanel);

    root = new JPanel();
    root.setLayout(new BoxLayout(root,
                                 BoxLayout.X_AXIS));

    root.add(Box.createGlue());
    root.add(panel);
    root.add(Box.createGlue());

    jframe = new JFrame();
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jframe.setContentPane(root);
    jframe.setSize(500,
                   800);
    jframe.setLocationRelativeTo(null);

    return jframe;
  }
}