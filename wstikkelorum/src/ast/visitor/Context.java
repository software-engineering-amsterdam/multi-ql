package ast.visitor;

import issue.DisplayIssues;
import issue.DuplicateLabel;
import issue.DuplicateQuestionWithDifferentType;
import issue.ReferenceToUndifinedQuestion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ast.statement.Question;

public class Context {
	private List<Question> declaredQuestions;
	private List<String> labels;
	private HashMap<String, Type> nameToType;
	private HashMap<String, Object> nameToValue;

	public Context() {
		declaredQuestions = new ArrayList<Question>();
		labels = new ArrayList<String>();
		nameToType = new HashMap<String, Type>();
		nameToValue = new HashMap<String, Object>();
	}
	
	public void putValueQuestion(Question question, Object value){
		nameToValue.put(question.getVariable().getIdentifier(), value);
	}
	
	public Object getValueForVariable(String identifier){
		if(!nameToValue.containsKey(identifier)){
			System.err.println(String.format("unknown variable: %s", identifier));
			return null;
		}
		
		if(nameToValue.get(identifier) == null){
			System.err.println(String.format("No value for variable: %s", identifier));
			return null;
		}
		
		return nameToValue.get(identifier);
	}

	public void addQuestion(Question question) {
		String identifier = question.getVariable().getIdentifier();
		Type type = question.getVariable().getType().getType();
		addLabel(question);

		
		if(nameToType.containsKey(identifier) && nameToType.get(identifier) != type){
			DisplayIssues.dislayIssue(new DuplicateQuestionWithDifferentType(question));
		}
		
		declaredQuestions.add(question);
		nameToType.put(identifier, type);
		nameToValue.put(identifier, null);
	}

	private void addLabel(Question question) {
		String label = question.getStr();
		if(!labels.contains(label)){
			labels.add(question.getStr());
		}else{
			DisplayIssues.dislayIssue(new DuplicateLabel(label, question.getLineNumber()));
		}
	}

	public Type getType(String identifier, int lineNumber) {
		if (nameToType.containsKey(identifier)) {
			return nameToType.get(identifier);
		}
		DisplayIssues.dislayIssue(new ReferenceToUndifinedQuestion(identifier, lineNumber));
		return null;
	}

	public List<Question> getDeclaredQuestions() {
		return declaredQuestions;
	}
	
	public HashMap<String, Type> getSymbolTable(){
		return nameToType;
	}
	
	public HashMap<String, Object> getVartoValueTable(){
		return nameToValue;
	}
}
