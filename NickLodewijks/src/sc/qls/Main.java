package sc.qls;

import java.io.File;

import javax.swing.UIManager;

import sc.ql.ast.Form;
import sc.ql.check.SemanticAnalyser;
import sc.ql.ui.UIQuestionnaire;
import sc.qls.ast.StyleSheet;
import sc.qls.ui.QLSUIFactory;

public class Main
{
  public static void main(String[] args)
      throws Exception
  {
    StyleSheet styleSheet;
    Form form;
    File inputFile;

    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

    // inputFile = new File(args[0]);
    inputFile = new File("resources/Questionnaire.ql");

    form = Form.create(inputFile);
    new SemanticAnalyser().validateTypes(form);

    styleSheet = StyleSheet.create(new File("resources/Stylesheet.qls"));

    createUI(form,
             styleSheet);
  }

  private static void createUI(Form form, StyleSheet styleSheet)
  {
    UIQuestionnaire uiForm;

    uiForm = new QLSUIFactory(styleSheet).create(form);
    uiForm.show();
  }
}
