package ql;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.ast.visitor.CyclicDependencyChecker;
import ql.ast.visitor.FindAllDeclaredQuestions;
import ql.ast.visitor.TypeChecker;

public class SemanticAnalyser {
	private Context context;
	
	public SemanticAnalyser(){
		context = new Context();
	}
	
	public void analyseForm(Form form){
		findVariables();
		typeCheck();
		cyclicDependenciesCheck();
	}
	
	public boolean noIssues(){
		return context.getIssues().isEmpty();
	}
	
	private void findVariables(){
		FindAllDeclaredQuestions<Object> fadq = new FindAllDeclaredQuestions<>(context);
		context = fadq.getContext();
	}
	
	private void typeCheck(){
		TypeChecker<Object> typeChecker = new TypeChecker<>(context);
		context = typeChecker.getContext();
	}
	
	private void cyclicDependenciesCheck(){
		CyclicDependencyChecker<Object> cdc = new CyclicDependencyChecker<>(context);
		context = cdc.getContext();
	}
	
	public Context getContext(){
		return context;
	}
}
