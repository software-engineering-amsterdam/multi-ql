package sc.ql;

import java.io.File;
import java.io.IOException;

import sc.ql.ast.Form;
import sc.ql.check.SemanticAnalyser;
import sc.ql.ui.UIFactory;
import sc.ql.ui.UIQuestionnaire;

public class Main
{
  public static void main(String[] args)
      throws IOException
  {
    Form form;
    File inputFile;

    inputFile = new File("resources/Questionnaire.ql");

    form = Form.create(inputFile);
    SemanticAnalyser.validate(form);

    createUI(form);
  }

  private static void createUI(Form form)
  {
    UIQuestionnaire questionnaire;

    questionnaire = new UIFactory().form(form);
    questionnaire.show();
  }
}
