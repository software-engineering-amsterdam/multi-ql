package sc.ql.ui;

import java.awt.Dimension;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UIQuestionnaire
{
  private final List<UIQuestion> questions;

  public UIQuestionnaire(List<UIQuestion> questions)
  {
    this.questions = questions;
  }

  public List<UIQuestion> questions()
  {
    return Collections.unmodifiableList(questions);
  }

  public void show()
  {
    JScrollPane contentScrollPane;
    JComponent contentPane;
    JPanel formPanel;
    JFrame jframe;

    formPanel = new JPanel();
    formPanel.setLayout(new BoxLayout(formPanel,
                                      BoxLayout.PAGE_AXIS));
    for (UIQuestion question : questions())
    {
      JComponent qPanel;

      qPanel = question.getComponent();

      formPanel.add(qPanel);
      formPanel.add(Box.createRigidArea(new Dimension(0,
                                                      2)));
    }

    contentScrollPane = new JScrollPane(formPanel);
    contentScrollPane.setPreferredSize(new Dimension(500,
                                                     800));

    contentPane = Box.createHorizontalBox();
    contentPane.add(Box.createHorizontalGlue());
    contentPane.add(contentScrollPane);
    contentPane.add(Box.createHorizontalGlue());

    jframe = new JFrame();
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jframe.setContentPane(contentPane);
    jframe.setSize(600,
                   800);
    jframe.setLocationRelativeTo(null);

    jframe.setVisible(true);
  }
}