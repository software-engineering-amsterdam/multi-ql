package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.visit.QuestionCollector;
import org.uva.sea.ql.ast.visit.VariableCollector;

public class DependencyChecker extends LeftDFSVisitor<Map<String, Boolean>> {
	private List<ASTNode> violations;
	
	private boolean noNewPaths;
	
	public DependencyChecker(Map<String, Boolean> context) {
		violations = new ArrayList<ASTNode>();
	}
	
	@Override 
	public void visit(Block block, Map<String, Boolean> visited) {
		System.out.println("Block: " + block.toString());
		for (String qID : QuestionCollector.getQuestionIDs(block, false)) {
			System.out.println("Question: " + qID);
			if (! visited.containsKey(qID)) {
				visited.put(qID, true);
			}
		}
		
		super.visit(block, visited);
	}
	
	@Override
	public void visit(Question question, Map<String, Boolean> visited) {
		// explicitly ignore questions, visited variables already added to visited.
	}
	
	@Override
	public void visit(IfStatement ifStatement, Map<String, Boolean> visited) {
		//Check if variables in ifStatement already visited.
		boolean safe = true;
		
		for (String varID : VariableCollector.geVariableIDs(ifStatement.getClause())) {
			if (! visited.containsKey(varID) || ! visited.get(varID)) {
				safe = false;
				break;
			}
		}
		
		if (! safe) {
			System.out.println("If statement that caused violation: " + ifStatement.getStartLine());
			violations.add(ifStatement);
			
			for (String qID : QuestionCollector.getQuestionIDs(ifStatement.getBlock(), true)) {
				if (! visited.containsKey(qID)) {
				visited.put(qID, false); // all questions in AST with *ifStatement* as root are unreachable;
				}
			}
			
		} else {
			
			for (String qID : QuestionCollector.getQuestionIDs(ifStatement.getBlock(), false)) {
				if (! visited.containsKey(qID)) {
				visited.put(qID, true); // all questions within one level of if statement are reachable;
				}
			}
			
		}
		super.visit(ifStatement, visited);
		//TODO: improve this check by looking at each variable seperately in the if-clause. This can pinpoint errors.
	}
	
	public List<ASTNode> getViolations() {
		
		
		List<ASTNode> result = violations;
		violations.removeAll(violations); // clear state of dependency checker.
		return result;
	}
	
	public static List<ASTNode> getViolations(ASTNode root, Map<String, Boolean> context) {
		DependencyChecker depChecker = new DependencyChecker(new HashMap<String, Boolean>());
		root.accept(depChecker,);
		List<ASTNode> result = depChecker.violations;
		int size = 0;
		while (result.size() != size) {
			// reset visitor
			
		}
		root.accept(context);
		return collector.questions;
	}
}
