package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.errors.QLError;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.uva.sea.ql.ast.expr.*;

public class DependencyVisitor extends LeftDFSVisitor {
	private Set<String> visited; // keep track of visited questions that are safe.
	private Set<String> undefinedQuestionIDs;
	private List<Question> undefinedQuestions;
	private NodeCollector collector;
	
	public DependencyVisitor() {
		undefinedQuestions = new ArrayList<Question>();
		collector = new NodeCollector();
		visited = new HashSet<String>();
		undefinedQuestionIDs = new HashSet<String>();
	}
	
	@Override
	public void visit(Question question) {
		if (! undefinedQuestionIDs.contains(question.getIdentifier()) ) {
			visited.add(question.getIdentifier());
		} // refactor, i.e. remove 
		else {
			String errorMessage = "is dependent on question(s) that are not yet defined!";
			System.out.println(new QLError(question, errorMessage).getErrorMessage());
		}// refactor, i.e. remove 
		
		int size = question.getComputedResult().size();
		if (size >= 1) { // question contains a computed result	
			Expr clause = question.getComputedResult().get(0);
			clause.accept(collector);
			Set<String> clauseVariables = new HashSet<String>(collector.getVariableNames());
			clauseVariables.removeAll(visited); // now contains all conflicting variable identifiers.
			undefinedQuestionIDs.addAll(clauseVariables);
			undefinedQuestions.add(question);
			collector.reset();
		}
		super.visit(question);	
	}
	
	//TODO: Fix bug of overreporting errors!!!
	@Override
	public void visit(IfStatement ifStatement) {
		ifStatement.getClause().accept(collector);
		Set<String> clauseVariables = new HashSet<String>(collector.getVariableNames());
		clauseVariables.removeAll(visited); // now contains all conflicting variable identifiers.
		undefinedQuestionIDs.addAll(clauseVariables);
		for (String var : clauseVariables) {
			String errorMessage = "contains variable " + var + " which is not yet defined!";
			System.out.println(new QLError(ifStatement, errorMessage).getErrorMessage());
		}
		
		if (! clauseVariables.isEmpty()) {
			collector.reset();
			ifStatement.getBlock().accept(collector);
			undefinedQuestionIDs.addAll(collector.getQuestionIdentifiers());
		}
		collector.reset();
		super.visit(ifStatement);
	}
	
	public Set<String> getUndefinedQuestionIDs() {
		return this.undefinedQuestionIDs;
	}
	
}
