package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.gui.questionItems.QuestionItem;

public interface TypeVisitor {

	public QuestionItem visit(IntType intType, Question question);
	public QuestionItem visit(BoolType boolType, Question question);
	public QuestionItem visit(StrType strType, Question question);
	
}
