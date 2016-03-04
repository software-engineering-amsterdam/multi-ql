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
		direcltyDependentOn.forEach((identifier, dependencies) -> recursiveDepencyCheck(identifier, identifier));
	}
	
	private void recursiveDepencyCheck(String currentIdentifier, String originalIdentifier){
		List<String> dependenciesCurrentIdentifier = direcltyDependentOn.get(currentIdentifier);
		
		if(dependenciesCurrentIdentifier == null){
			return;
		}
		
		if(dependenciesCurrentIdentifier.contains(originalIdentifier)){
			context.addIssue(new CyclicDependency(originalIdentifier, currentIdentifier));
//			System.out.println(String.format("Cyclic between: %s and %s!", originalIdentifier, currentIdentifier));
			return;
		}
		
		for(String dependency : dependenciesCurrentIdentifier){
			recursiveDepencyCheck(dependency, originalIdentifier);
		}
	}
	
	public Context getContext(){
		return context;
	}
}
