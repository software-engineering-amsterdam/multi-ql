package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.visit.LeftDFSVisitor;
import org.uva.sea.ql.ast.form.*;

public class ContextVisitor extends LeftDFSVisitor<Context> {
	
	@Override
	public void visit(Question question, Context context) {
		context.putQuestionType(question.getIdentifier(), question.getType());
	}
	
	@Override
	public void visit(ComputedQuestion question, Context context) {
		context.putQuestionType(question.getIdentifier(), question.getType());
	}
}
