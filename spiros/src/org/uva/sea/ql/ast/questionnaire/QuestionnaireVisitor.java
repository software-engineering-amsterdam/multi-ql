package org.uva.sea.ql.ast.questionnaire;

import org.uva.sea.ql.ast.node.ASTNode;

public interface QuestionnaireVisitor<ASTNode> {

	public ASTNode visit(Questionnaire questionnaire);
	
}