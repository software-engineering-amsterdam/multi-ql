package ast.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ast.expression.VariableExpression;
import ast.statement.ComputedQuestion;

public class CyclicDependencyChecker<T> extends BasicVisitor<T>{
	private HashMap<String, List<String>> direcltyDependentOn;
	private String currentQuestionIdentifier;
	
	public CyclicDependencyChecker(){
		direcltyDependentOn = new HashMap<String, List<String>>();
	}
	
	@Override
	public T visit(ComputedQuestion computedQuestion){
		currentQuestionIdentifier = computedQuestion.getVariable().getIdentifier();
		direcltyDependentOn.put(computedQuestion.getVariable().getIdentifier(), new ArrayList<String>());
		computedQuestion.getExpression().accept(this);
		
		System.out.println("---------Dependent---------");
		System.out.println(computedQuestion.getVariable().getIdentifier());
		System.out.println("Dependent on: ");
		
		for(String s : direcltyDependentOn.get(computedQuestion.getVariable().getIdentifier())){
			System.out.println(s);
		}
		System.out.println();
		
		currentQuestionIdentifier = null;
		return null;
	}
	
	@Override
	public T visit(VariableExpression variableExpression){
		if(currentQuestionIdentifier != null){
			addDependencyToQuestion(variableExpression);
		}
		return null;
	}
	
	private void addDependencyToQuestion(VariableExpression variableExpression){
		List<String> currentDependencies = direcltyDependentOn.get(currentQuestionIdentifier);
		if(!currentDependencies.contains(variableExpression.getIdentifier())){
			currentDependencies.add(variableExpression.getIdentifier());
			direcltyDependentOn.put(currentQuestionIdentifier, currentDependencies);
		}
	}
	
	public void findCyclicDependencies(){
		Iterator iterator = direcltyDependentOn.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, List<String>> currentQuestion = (Entry<String, List<String>>) iterator.next();
			f(currentQuestion.getKey(), currentQuestion.getKey());
		}
	}
	
	private void f(String identifier, String original){
		List<String> dependencies = getAllDependencies(identifier);
		if(dependencies == null){
			return;
		}
		
		if(dependencies.contains(original)){
			System.out.println(String.format("Cyclic between: %s and %s", original, identifier));
			return;
		}
		
		for(String d : dependencies){
			f(d, original);
		}
	}
	
	private List<String> getAllDependencies(String identifier){
		return direcltyDependentOn.get(identifier);
	}
}
