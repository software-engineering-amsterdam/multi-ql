package ast.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ast.expression.VariableExpression;
import ast.statement.ComputedQuestion;

public class CyclicDependencyChecker<T> extends BasicVisitor<T>{
	private HashMap<String, List<String>> dependentOn;
	private String currentQuestion;
	
	public CyclicDependencyChecker(){
		dependentOn = new HashMap<String, List<String>>();
	}
	
	@Override
	public T visit(ComputedQuestion computedQuestion){
		currentQuestion = computedQuestion.getVariable().getName();
		dependentOn.put(computedQuestion.getVariable().getName(), new ArrayList<String>());
		computedQuestion.getExpression().accept(this);
		
		System.out.println("---------Dependent---------");
		System.out.println(computedQuestion.getVariable().getName());
		System.out.println("Dependent on: ");
		
		for(String s : dependentOn.get(computedQuestion.getVariable().getName())){
			System.out.println(s);
		}
		System.out.println();
		
		currentQuestion = null;
		return null;
	}
	
	@Override
	public T visit(VariableExpression variableExpression){
		if(currentQuestion != null){
			List<String> dependencies = dependentOn.get(currentQuestion);
			dependencies.add(variableExpression.getName());
			dependentOn.put(currentQuestion, dependencies);
		}
		return null;
	}
}
