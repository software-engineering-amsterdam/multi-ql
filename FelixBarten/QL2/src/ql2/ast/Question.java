package ql2.ast;

import ql2.ASTNode;
import ql2.BaseVisitor;
import ql2.ast.type.QuestionType;

public class Question extends ASTNode {
	String questionText;
	QuestionType questionType;
	String questionName;
	@Override
	
	
	public <T> T accept(BaseVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
