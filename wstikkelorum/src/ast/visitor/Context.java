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
	private HashMap<String, Type> symbolTable;

	public Context() {
		declaredQuestions = new ArrayList<Question>();
		labels = new ArrayList<String>();
		symbolTable = new HashMap<String, Type>();
	}

	public void addQuestion(Question question) {
		String identifier = question.getVariable().getName();
		Type type = question.getVariable().getType().getType();
		addLabel(question);

		if (!symbolTable.containsKey(identifier)) {
			declaredQuestions.add(question);
			symbolTable.put(identifier, type);
		}else if(symbolTable.get(identifier) != type){
			DisplayIssues.dislayIssue(new DuplicateQuestionWithDifferentType(question));
		}
	}

	private void addLabel(Question question) {
		//Duplicate Label
		String label = question.getStr();
		if(!labels.contains(label)){
			labels.add(question.getStr());
		}else{
			DisplayIssues.dislayIssue(new DuplicateLabel(label, question.getLineNumber()));
		}
	}

	public Type getType(String identifier, int lineNumber) {
		if (symbolTable.containsKey(identifier)) {
			return symbolTable.get(identifier);
		}
		DisplayIssues.dislayIssue(new ReferenceToUndifinedQuestion(identifier, lineNumber));
		return null;
	}

	public List<Question> getDeclaredQuestions() {
		return declaredQuestions;
	}
	
	public HashMap<String, Type> getSymbolTable(){
		return symbolTable;
	}
}
