package ast.visitor;

import ast.statement.ComputedQuestion;
import ast.statement.InputQuestion;

public class FindAllDeclaredQuestions<T> extends BasicVisitor<T>{
	private Context context;
	
	public FindAllDeclaredQuestions(){
		context = new Context();
	}
	
	@Override
	public T visit(ComputedQuestion computedQuestion){
		context.addQuestion(computedQuestion);
		return null;
	}
	
	@Override
	public T visit(InputQuestion inputQuestion){
		context.addQuestion(inputQuestion);
		return null;
	}
	
	public Context getContext(){
		return context;
	}
}
