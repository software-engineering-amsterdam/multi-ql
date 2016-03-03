package org.uva.ql.ast.form;

public interface QLQuestionnaireVisitor<U> {

	public void visit(QLQuestionnaire node, U Context);
}
