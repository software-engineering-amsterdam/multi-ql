package semanticAction.tree.intermediate;

import semanticAction.tree.questionNode.CalculatedQuestion;
import semanticAction.tree.questionNode.IfElseQuestion;
import semanticAction.tree.questionNode.IfQuestion;
import semanticAction.tree.questionNode.NormalQuestion;

public interface InterfaceVisitQuestion<T> {

		 public T visit(NormalQuestion NQ);
		 public T visit(CalculatedQuestion CQ); 
		 public T visit(IfQuestion ifQ);
		 public T visit(IfElseQuestion ifelseQ);
			
}
