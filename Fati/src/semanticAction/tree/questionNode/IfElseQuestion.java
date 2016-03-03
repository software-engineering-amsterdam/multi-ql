package semanticAction.tree.questionNode;

import java.util.List;

import semanticAction.tree.expressionNode.AbsExpression;
import semanticAction.tree.intermediate.InterfaceVisitQuestion;

public class IfElseQuestion extends IfQuestion {

	private List<AbsQuestion> elseQuestion;
		
	public IfElseQuestion(AbsExpression ifExpression, List<AbsQuestion> ifQuestion, List<AbsQuestion> elseQuestion) {
		super(ifExpression, ifQuestion);
		this.elseQuestion = elseQuestion;
	}
		
	public List<AbsQuestion> getElseQuestion(){
		return elseQuestion;
	}
				
	@Override
	public <T> T accept(InterfaceVisitQuestion<T> visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public String toString() {
		String output = super.toString();
		output += "\n else { \n";
			for(AbsQuestion qe: elseQuestion)
				output += qe.toString() + "\n";
		output += " }";
	return output;
	}
}