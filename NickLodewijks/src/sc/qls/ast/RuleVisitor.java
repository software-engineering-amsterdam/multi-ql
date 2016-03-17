package sc.qls.ast;

import sc.qls.ast.Rule.QuestionRule;
import sc.qls.ast.Rule.ValueTypeRule;

public interface RuleVisitor<T, U> {

	public T visit(QuestionRule rule, U context);

	public T visit(ValueTypeRule rule, U context);

}
