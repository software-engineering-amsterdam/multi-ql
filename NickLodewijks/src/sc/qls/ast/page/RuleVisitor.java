package sc.qls.ast.page;

import sc.qls.ast.page.Rule.QuestionRule;
import sc.qls.ast.page.Rule.ValueTypeRule;

public interface RuleVisitor<T, U> {

	public T visit(QuestionRule rule, U context);

	public T visit(ValueTypeRule rule, U context);

}
