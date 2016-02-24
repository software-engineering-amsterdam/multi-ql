package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.visit.QuestionCollector;
import org.uva.sea.ql.ast.visit.VariableCollector;

public class DependencyChecker extends LeftDFSVisitor<Map<String, Boolean>> {
	private List<ASTNode> violations;
	
	public DependencyChecker() {
		violations = new ArrayList<ASTNode>();
	}
	
	@Override 
	public void visit(Block block, Map<String, Boolean> visited) {
		
		for (String qID : QuestionCollector.getQuestionIDs(block)) {
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
		
		for (String varID : VariableCollector.geVariableIDs(ifStatement)) {
			if (! visited.containsKey(varID) || ! visited.get(varID)) {
				safe = false;
				break;
			}
		}
		
		if (! safe) {
			System.out.println("If statement that caused violation: " + ifStatement.getStartLine());
			violations.add(ifStatement);
			
			for (String qID : QuestionCollector.getQuestionIDs(ifStatement.getBlock())) {
				if (! visited.containsKey(qID)) {
				visited.put(qID, false); // all questions within if statement rendered false;
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
}
