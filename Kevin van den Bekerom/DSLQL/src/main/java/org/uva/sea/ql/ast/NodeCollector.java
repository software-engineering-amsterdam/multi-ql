package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;

/* Visitor that extends LeftDFSVisitor and collects all nodes of a specific type */
public class NodeCollector extends LeftDFSVisitor {
	
	private List<Expr> literals;
	private List<Variable> variables;
	private List<Question> questions;
	
	public NodeCollector() {
		literals = new ArrayList<Expr>();
		variables = new ArrayList<Variable>();
		questions = new ArrayList<Question>();
	}

	@Override 
	public void visit(Question question) {
		super.visit(question);
		questions.add(question);
	}
	
	@Override
	public void visit(IntegerLiteral e) {
		literals.add(e);
	}
	
	@Override
	public void visit(BooleanLiteral e) {
		literals.add(e);
	}
	
	@Override
	public void visit(StringLiteral e) {
		literals.add(e);
	}
	
	@Override
	public void visit(Variable e) {
		variables.add(e);
	}
	
	public List<Expr> getLiterals() {
		return this.literals;
	}
	
	public List<Variable> getVariables() {
		return this.variables;
	}
	
	public List<Question> getQuestions() {
		return this.questions;
	}
	
	public List<String> getQuestionIdentifiers() {
		List<String> qIds = new ArrayList<String>();
		for (Question q : questions) {
			qIds.add(q.getIdentifier());
		}
		return qIds;
	}
	
	public List<String> getVariableNames() {
		List<String> varNames = new ArrayList<String>();
		for (Variable v : variables) {
			varNames.add(v.getIdentifier());
		}
		return varNames;
	}
	
	/*
	 * Reset all datastructures 
	 */
	public void reset() {
		literals.removeAll(literals);
		variables.remove(variables);
		questions.removeAll(questions);
	}
}
