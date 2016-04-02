package ql.ast.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ql.ast.expression.VariableExpression;
import ql.ast.literal.Variable;
import ql.ast.statement.question.Question;
import ql.ast.type.ValueType;
import ql.ast.value.Value;
import ql.issue.Issue;
import ql.issue.problem.DuplicateQuestionWithDifferentType;
import ql.issue.problem.ReferenceToUndefinedQuestion;
import ql.issue.warning.DuplicateLabel;

public class Context {
	private List<Question> declaredQuestions;
	private List<String> labels;
	private HashMap<String, ValueType> nameToType;
	private HashMap<String, Value> nameToValue;
	private List<Issue> issues;

	public Context() {
		declaredQuestions = new ArrayList<Question>();
		labels = new ArrayList<String>();
		nameToType = new HashMap<String, ValueType>();
		nameToValue = new HashMap<String, Value>();
		issues = new ArrayList<Issue>();
	}

	public void putValueForQuestion(Question question, Value value) {
		nameToValue.put(question.getVariable().getIdentifier(), value);
	}

	public Value getValueForVariable(VariableExpression variableExpression) {
		return getValue(variableExpression.getIdentifier(), variableExpression.getLineNumber());
	}

	public Value getValueForVariable(Variable variable) {
		return getValue(variable.getIdentifier(), variable.getLineNumber());
	}

	private Value getValue(String identifier, int lineNumber) {
		if (!nameToValue.containsKey(identifier)) {
			issues.add(new ReferenceToUndefinedQuestion(identifier, lineNumber));
			return null;
		}
		return nameToValue.get(identifier);
	}

	public void addQuestion(Question question) {
		String identifier = question.getVariable().getIdentifier();
		ValueType type = question.getVariable().getType();

		if (nameToType.containsKey(identifier) && nameToType.get(identifier) != type) {
			issues.add(new DuplicateQuestionWithDifferentType(question));
			return;
		}

		declaredQuestions.add(question);
		nameToType.put(identifier, type);
		nameToValue.put(identifier, defaultValue(question));
		addLabel(question);
	}
	
	private Value defaultValue(Question question){
		ValueType type = question.getVariable().getType();
		return type.getDefaultValue();
	}

	private void addLabel(Question question) {
		String label = question.getQuestionString();
		if (!labels.contains(label)) {
			labels.add(question.getQuestionString());
		} else {
			issues.add(new DuplicateLabel(label, question.getLineNumber()));
		}
	}

	public ValueType getType(String identifier, int lineNumber) {
		if (!nameToType.containsKey(identifier)) {
			issues.add(new ReferenceToUndefinedQuestion(identifier, lineNumber));
			return null;
		}
		return nameToType.get(identifier);
	}

	public void addIssue(Issue issue) {
		issues.add(issue);
	}

	public List<Question> getDeclaredQuestions() {
		return declaredQuestions;
	}

	public HashMap<String, ValueType> getIdentifierToTypeMap() {
		return nameToType;
	}

	public HashMap<String, Value> getIdentifierToValueMap() {
		return nameToValue;
	}

	public Iterator<Issue> getIssueIterator() {
		return issues.iterator();
	}
	
	public int numberOfIssues(){
		return issues.size();
	}
	
	public boolean onlyWarnings(){
		return issues.size() > 0 && noIssues();
	}
	
	private boolean noIssues(){
		for(Issue issue : issues){
			if(issue.isProblem()){
				return false;
			}
		}
		return true;
	}
}
