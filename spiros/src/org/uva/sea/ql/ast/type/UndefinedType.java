package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.gui.questionItems.QuestionItem;

public class UndefinedType extends Type {

	public UndefinedType(CodeFragment fragment) {
		super(fragment, "Undefined");
	}
	
	public UndefinedType() {
		// TODO Auto-generated constructor stub
		super(new CodeFragment(-1, -1), "Undefined");
	}

	public QuestionItem  accept(TypeVisitor visitor, Question question) {
		//return visitor.visit(this, question);
		return null;
	}
	
//	@Override
//	public boolean isUndefined() {
//		return true;
//	}
	
//	@Override
//	public ASTNode accept(TypeVisitor<ASTNode> visitor) {
//		return visitor.visit(this);
//	}

}