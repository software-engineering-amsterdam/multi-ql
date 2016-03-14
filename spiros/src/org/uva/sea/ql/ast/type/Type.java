package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.gui.questionItems.QuestionItem;


public abstract class Type extends ASTNode {
	
	private final String typeName;
	
	public Type(CodeFragment fragment, String typeName) {
		super(fragment);
		this.typeName = typeName;
	}

	public String getTypeName() {
		return this.typeName;
	}
	
	public abstract QuestionItem  accept(TypeVisitor visitor, Question question);
	
//	public boolean isUndefined() {
//		return false;
//	}
//	
//	public boolean isIntType(){
//		return false;
//	}
//	
//	public boolean isBoolType(){
//		return false;
//	}
//	
//	public boolean isStrType(){
//		return false;
//	}
//	
//	public boolean isDoubleType() {
//		return false;
//	}
	

}
