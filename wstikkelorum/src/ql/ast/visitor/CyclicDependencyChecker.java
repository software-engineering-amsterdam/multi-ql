package ql.ast.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ql.ast.expression.VariableExpression;
import ql.ast.statement.ComputedQuestion;

public class CyclicDependencyChecker<T> extends BasicVisitor<T>{
	private HashMap<String, List<String>> direcltyDependentOn;
	private String currentQuestionIdentifier;
	private Context context;
	
	public CyclicDependencyChecker(Context context){
		direcltyDependentOn = new HashMap<String, List<String>>();
		this.context = context;
	}
	
	
	@Override
	public T visit(ComputedQuestion computedQuestion){
		currentQuestionIdentifier = computedQuestion.getVariable().getIdentifier();
		
		direcltyDependentOn.put(computedQuestion.getVariable().getIdentifier(), new ArrayList<String>());
		computedQuestion.getExpression().accept(this);
		
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
			recursiveDepencyCheck(currentQuestion.getKey(), currentQuestion.getKey());
		}
	}
	
	private void recursiveDepencyCheck(String identifier, String original){
		List<String> dependencies = getAllDependencies(identifier);
		if(dependencies == null){
			return;
		}
		
		if(dependencies.contains(original)){
			//TODO:add error to context
			System.out.println(String.format("Cyclic between: %s and %s", original, identifier));
			
			return;
		}
		
		for(String d : dependencies){
			recursiveDepencyCheck(d, original);
		}
	}
	
	private List<String> getAllDependencies(String identifier){
		return direcltyDependentOn.get(identifier);
	}
	
	public Context getContext(){
		return context;
	}
}
