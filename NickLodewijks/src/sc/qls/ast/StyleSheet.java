package sc.qls.ast;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import sc.ql.ast.ASTNode;
import sc.ql.ast.Statement.Question;
import sc.qls.ast.Rule.QuestionRule;
import sc.qls.parser.QLSLexer;
import sc.qls.parser.QLSParser;

public class StyleSheet
    extends ASTNode
{
  private final List<Page> pages;

  public StyleSheet(String id, List<Page> pages)
  {
    this.pages = pages;
  }

  public List<Page> pages()
  {
    return Collections.unmodifiableList(pages);
  }

  public QuestionRule ruleFor(Question question)
  {
    for (Page page : pages)
    {
      for (Section section : page.sections())
      {
        if (!section.contains(question))
        {
          continue;
        }

        return section.ruleFor(question);
      }
    }

    assert false : "Question not contained in stylesheet " + question;

    return null;
  }

  public static StyleSheet create(File file)
      throws IOException
  {
    return StyleSheet.create(new ANTLRFileStream(file.getAbsolutePath()));
  }

  private static StyleSheet create(CharStream cs)
      throws IOException
  {
    TokenStream tokenStream;
    QLSParser parser;

    tokenStream = new CommonTokenStream(new QLSLexer(cs));
    parser = new QLSParser(tokenStream);

    return parser.stylesheet().result;
  }
}
