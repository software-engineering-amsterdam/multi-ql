package ql.ast.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ql.ast.expression.VariableExpression;
import ql.ast.statement.ComputedQuestion;
import ql.issue.CyclicDependency;

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
		//No duplicate identifiers in the dependency list
		if(!currentDependencies.contains(variableExpression.getIdentifier())){
			currentDependencies.add(variableExpression.getIdentifier());
			direcltyDependentOn.put(currentQuestionIdentifier, currentDependencies);
		}
	}
	
	public void findCyclicDependencies(){
		List<String> visitedVariables = new ArrayList<String>();
		direcltyDependentOn.forEach((identifier, dependencies) -> recursiveDepencyCheck(identifier, identifier, visitedVariables));
	}
	
	private void recursiveDepencyCheck(String currentIdentifier, String originalIdentifier, List<String> visitedVariables){
		List<String> dependenciesCurrentIdentifier = direcltyDependentOn.get(currentIdentifier);
		
		if(!visitedVariables.contains(originalIdentifier)){
			visitedVariables.add(originalIdentifier);
		}
		
		if(dependenciesCurrentIdentifier == null){
			return;
		}
		
		for(String dependency : dependenciesCurrentIdentifier){
			if(visitedVariables.contains(dependency)){
				context.addIssue(new CyclicDependency(originalIdentifier, dependency));
				return;
			}
			visitedVariables.add(dependency);
		}
		
		for(String dependency : dependenciesCurrentIdentifier){
			recursiveDepencyCheck(dependency, originalIdentifier, visitedVariables);
		}
	}
	
	public Context getContext(){
		return context;
	}
}
