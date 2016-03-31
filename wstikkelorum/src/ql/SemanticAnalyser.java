package ql;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.ast.visitor.DependencyChecker;
import ql.ast.visitor.QuestionVisitor;
import ql.ast.visitor.TypeChecker;
import ql.issue.Issue;

public class SemanticAnalyser {
	private Context context;

	public SemanticAnalyser() {
		context = new Context();
	}

	public void analyseForm(Form form) {
		findDeclaredQuestions(form);
		typeCheck(form);
		cyclicDependenciesCheck(form);
	}

	public boolean noIssues() {
		Iterator<Issue> iterator = context.getIssueIterator();
		while(iterator.hasNext()){
			Issue issue = iterator.next();
			if(issue.isProblem()){
				return false;
			}
		}
		return true;
	}
	
	public List<Issue> getWarnings(){
		List<Issue> warnings = new ArrayList<Issue>();
		Iterator<Issue> iterator = context.getIssueIterator();
		while(iterator.hasNext()){
			Issue issue = iterator.next();
			if(issue.isWarning()){
				warnings.add(issue);
			}
		}
		return warnings;
	}

	private void findDeclaredQuestions(Form form) {
		QuestionVisitor<Object> questionVisitor = new QuestionVisitor<>(context);
		questionVisitor.visit(form);
		context = questionVisitor.getContext();
	}

	private void typeCheck(Form form) {
		TypeChecker<Object> typeChecker = new TypeChecker<>(context);
		typeChecker.visit(form);
		context = typeChecker.getContext();
	}

	private void cyclicDependenciesCheck(Form form) {
		DependencyChecker<Object> dependencyChecker = new DependencyChecker<>(context);
		dependencyChecker.findCyclicDependencies(form);
		context = dependencyChecker.getContext();
	}

	public Context getContext() {
		return context;
	}
}
