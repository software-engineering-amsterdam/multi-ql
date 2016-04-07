package sc.qls.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sc.ql.ast.ASTNode;
import sc.ql.ui.UIQuestion;

public class Page
    extends ASTNode
{
  private final String name;
  private final List<Section> sections;

  public Page(String name, List<Section> sections)
  {
    this.name = name;
    this.sections = sections;
  }

  public String name()
  {
    return name;
  }

  public List<Section> sections()
  {
    return Collections.unmodifiableList(sections);
  }

  public List<UIQuestion> filter(List<UIQuestion> questions)
  {
    List<UIQuestion> filteredList;

    filteredList = new ArrayList<>();

    sections.stream().forEach(s -> filteredList.addAll(s.filter(questions)));

    return Collections.unmodifiableList(filteredList);
  }
}
