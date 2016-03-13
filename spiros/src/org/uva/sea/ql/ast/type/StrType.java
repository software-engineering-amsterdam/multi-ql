package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.gui.questionItems.QuestionItem;


public class StrType extends Type {

	public StrType(CodeFragment fragment) {
		super(fragment, "str");
	}
	
	public StrType() {
		super(new CodeFragment(-1, -1), "str");
	}
	
	public QuestionItem  accept(TypeVisitor visitor, Question question) {
		return visitor.visit(this, question);
	}
//	@Override
//	public boolean isStrType() {
//		return true;
//	}
	
//	@Override
//	public ASTNode accept(TypeVisitor<ASTNode> visitor) {
//		return visitor.visit(this);
//	}
	
}