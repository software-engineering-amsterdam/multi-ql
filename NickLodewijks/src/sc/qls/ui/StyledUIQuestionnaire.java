package sc.qls.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import sc.ql.ui.UIQuestion;
import sc.ql.ui.UIQuestionnaire;
import sc.qls.ast.Page;
import sc.qls.ast.StyleSheet;

public class StyledUIQuestionnaire
    extends UIQuestionnaire
{
  private final JList<UIPage> pageMenu;
  private final JPanel pageContentPanel;

  public StyledUIQuestionnaire(UIQuestionnaire questionnaire, StyleSheet styleSheet)
  {
    super(questionnaire.questions());
    List<UIPage> uiPages;

    uiPages = createUIPages(styleSheet.pages());

    pageMenu = new UIPageList(uiPages);
    pageMenu.addListSelectionListener(e -> show(pageMenu.getSelectedValue()));

    pageContentPanel = new JPanel();
    pageContentPanel.setLayout(new BoxLayout(pageContentPanel,
                                             BoxLayout.PAGE_AXIS));
  }

  @Override
  public void show()
  {
    JScrollPane pageContentScrollPane;
    JScrollPane pageMenuScrollPane;
    JComponent contentPane;
    JFrame jframe;

    pageMenuScrollPane = new JScrollPane(pageMenu);
    pageMenuScrollPane.setPreferredSize(new Dimension(100,
                                                      800));
    pageMenuScrollPane.setMaximumSize(new Dimension(100,
                                                    800));

    pageContentScrollPane = new JScrollPane(pageContentPanel);
    pageContentScrollPane.setPreferredSize(new Dimension(500,
                                                         800));

    contentPane = Box.createHorizontalBox();
    contentPane.add(pageMenuScrollPane);
    contentPane.add(pageContentScrollPane);

    pageMenu.setSelectedIndex(0);

    jframe = new JFrame();
    jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    jframe.setContentPane(contentPane);
    jframe.setSize(700,
                   800);
    jframe.setLocationRelativeTo(null);

    jframe.setVisible(true);
  }

  private List<UIPage> createUIPages(List<Page> pages)
  {
    List<UIPage> uiPages;

    uiPages = new ArrayList<>();
    for (Page page : pages)
    {
      List<UIQuestion> questions;

      questions = page.filter(questions());

      uiPages.add(new UIPage(page,
                             questions));
    }

    return uiPages;
  }

  private void show(UIPage page)
  {
    SwingUtilities.invokeLater(() -> {
      pageContentPanel.removeAll();

      pageContentPanel.add(page.getComponent());
      pageContentPanel.revalidate();
      pageContentPanel.repaint();
    });
  }

  private static class UIPageList
      extends JList<UIPage>
  {
    private static final long serialVersionUID = 1L;

    public UIPageList(List<UIPage> pages)
    {
      super(pages.toArray(new UIPage[pages.size()]));

      setCellRenderer(new PageListCellRenderer());
      setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      setLayoutOrientation(JList.HORIZONTAL_WRAP);
      setVisibleRowCount(-1);
    }

    protected void processMouseEvent(MouseEvent e)
    {
      // filter out control click that unselects
      if (e.isControlDown())
      {
        return;
      }

      super.processMouseEvent(e);
    }
  }

  private static class PageListCellRenderer
      extends DefaultListCellRenderer
  {
    private static final long serialVersionUID = 1L;

    @Override
    public Component getListCellRendererComponent(JList<?> list,
        Object value,
        int index,
        boolean isSelected,
        boolean cellHasFocus)
    {
      JLabel label;

      // DefaultListCellRenderer always returns a JLabel
      label = (JLabel) super.getListCellRendererComponent(list,
                                                          value,
                                                          index,
                                                          isSelected,
                                                          cellHasFocus);

      label.setText(((UIPage) value).name());

      return label;
    }
  }
}