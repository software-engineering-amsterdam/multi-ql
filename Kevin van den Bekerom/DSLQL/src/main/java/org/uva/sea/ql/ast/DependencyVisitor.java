package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.exceptions.UndefinedQuestionError;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.uva.sea.ql.ast.expr.*;

public class DependencyVisitor extends LeftDFSVisitor {
	private Set<String> visited; // keep track of visited questions that are safe.
	private Set<String> undefinedQuestionIDs;
	boolean cyclicDependancy;
	private List<Question> undefinedQuestions;
	private NodeCollector collector;
	boolean add = true;
	
	public DependencyVisitor() {
		cyclicDependancy = false;
		undefinedQuestions = new ArrayList<Question>();
		collector = new NodeCollector();
		visited = new HashSet<String>();
		undefinedQuestionIDs = new HashSet<String>();
	}
	
	@Override
	public void visit(Question question) {
		if (! undefinedQuestionIDs.contains(question.getIdentifier()) ) {
			System.out.println("Visiting Question : " + question.getIdentifier());
			visited.add(question.getIdentifier());
		} else {
			throw new UndefinedQuestionError(question);
		}
		int size = question.getComputedResult().size();
		if (size >= 1) { // question contains a computed result	
			System.out.println("we have a clause" );
			Expr clause = question.getComputedResult().get(0);
			clause.accept(collector);
			Set<String> clauseVariables = new HashSet<String>(collector.getVariableNames());
			clauseVariables.removeAll(visited); // now contains all conflicting variable identifiers.
			for (String var : clauseVariables) {
				System.out.println("We have an undefined variable : " + var);
			}
			
			collector.reset();
		}
		super.visit(question);	
	}
	
	
	@Override
	public void visit(IfStatement ifStatement) {
		ifStatement.getClause().accept(collector);
		Set<String> clauseVariables = new HashSet<String>(collector.getVariableNames());
		clauseVariables.removeAll(visited); // now contains all conflicting variable identifiers.
		for (String var : clauseVariables) {
			System.out.println("We have an undefined variable in ifclause: " + var);
		}
		
		if (clauseVariables.size() >= 1) {
			collector.reset();
			ifStatement.getBlock().accept(collector);
			undefinedQuestionIDs.addAll(collector.getQuestionIdentifiers());
		}
		collector.reset();
		super.visit(ifStatement);
	}
	
	
	public boolean containsCyclicDependancy() {
		return this.cyclicDependancy;
	}
}
