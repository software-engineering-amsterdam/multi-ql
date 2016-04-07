package sc.qls.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import sc.ql.ast.ASTNode;
import sc.ql.ast.Statement.Question;
import sc.ql.ui.UIQuestion;
import sc.qls.ast.Rule.QuestionRule;
import sc.qls.ast.Rule.ValueTypeRule;

public class Section
    extends ASTNode
{
  private final String name;
  private final List<Rule> rules;

  public Section(String name, List<Rule> rules)
  {
    this.name = name;
    this.rules = rules;
  }

  public String name()
  {
    return name;
  }

  public List<Rule> rules()
  {
    return Collections.unmodifiableList(rules);
  }

  public List<UIQuestion> filter(List<UIQuestion> questions)
  {
    return questions.stream().filter(q -> contains(q.question())).collect(Collectors.toList());
  }

  public boolean contains(Question question)
  {
    return ruleFor(question) != null;
  }

  public List<UIQuestion> sort(List<UIQuestion> questions)
  {
    List<UIQuestion> sortedQuestions;

    sortedQuestions = new ArrayList<>(questions);
    sortedQuestions.sort((q1, q2) -> {
      int q1Index;
      int q2Index;

      q1Index = rules.indexOf(ruleFor(q1.question()));
      q2Index = rules.indexOf(ruleFor(q2.question()));
      return Integer.compare(q1Index,
                             q2Index);
    });

    return sortedQuestions;
  }

  public QuestionRule ruleFor(Question question)
  {
    for (QuestionRule rule : getQuestionRules())
    {
      if (rule.name().equals(question.name()))
      {
        return rule;
      }
    }

    return null;
  }

  private List<QuestionRule> getQuestionRules()
  {
    List<QuestionRule> questionRules;

    questionRules = new ArrayList<>();

    for (Rule rule : rules)
    {
      rule.accept(new RuleVisitor<Void, Void>()
                  {

                    @Override
                    public Void visit(QuestionRule rule, Void context)
                    {
                      questionRules.add(rule);
                      return null;
                    }

                    @Override
                    public Void visit(ValueTypeRule rule, Void context)
                    {
                      return null;
                    }
                  },
                  null);
    }

    return questionRules;
  }
}
