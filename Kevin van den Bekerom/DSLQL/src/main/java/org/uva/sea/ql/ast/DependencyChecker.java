package org.uva.sea.ql.ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.visit.QuestionCollector;
import org.uva.sea.ql.ast.visit.VariableCollector;
import org.uva.sea.ql.errors.QLError;
import org.uva.sea.ql.graph.Graph;
import org.uva.sea.ql.graph.Vertex;

public class DependencyChecker extends LeftDFSVisitor<Graph> {
	private List<ASTNode> violations;
	private List<QLError> errors;
	
	private boolean noNewPaths;
	
	public DependencyChecker() {
		violations = new ArrayList<ASTNode>();
		errors = new ArrayList<QLError>();
	}
		
	@Override
	public void visit(Question question, Graph dependencyGraph) {
		if (! dependencyGraph.containsVertex(question.getIdentifier())) {
			dependencyGraph.addVertex(new Vertex(question.getIdentifier()));
		}
	}
	
	@Override
	public void visit(IfStatement ifStatement, Graph dependencyGraph) {
		Set<String> varIDs = new HashSet<String>();
		varIDs.addAll(VariableCollector.geVariableIDs(ifStatement.getClause()));
		
		for (String varID : varIDs) {
			Vertex start = dependencyGraph.getVertex(varID);
			
			if (start == null) {
				if (dependencyGraph.containsVertex(varID)) {
					// ok
				}
				String errMessage = "There is a dependency on question " + varID + " which is not defined!";
				errors.add(new QLError(ifStatement, errMessage));
			} else {

			for (String nextID : start.getNeighbors()) {
				Vertex next = dependencyGraph.getVertex(nextID);
				String errMessage = dependencyCheck(start, new ArrayList<String>(), next, dependencyGraph);
				if (! errMessage.equals("")) {
					errors.add(new QLError(ifStatement, errMessage));
				}
			}
			
			}
			
		}
		
	}

	public static List<QLError> getErrors(ASTNode root, Graph dependencyGraph) {
		DependencyChecker depChecker = new DependencyChecker();
		root.accept(depChecker, dependencyGraph);
		List<QLError> result = depChecker.errors;
		//depChecker.errors.removeAll(depChecker.errors);
		return result;
	}
	
	private String dependencyCheck(Vertex start, List<String> visited, Vertex next, Graph g) {
		if (next == null) {
			String errMessage = "There is a dependency on question " + next.getIdentifier() + " which is not defined!";
			return errMessage;
		} else if (next.isTerminal()) {
			return "";
		} else if (visited.contains(next.getIdentifier())) {
			System.out.println("Cycle: ");
			String errMessage = "Cyclic dependency detected : ";
			for (String v: visited) {
				errMessage += (v + " -> ");
			}
			errMessage += next.getIdentifier(); // vertex causing cycle.
			return errMessage;
		} else {
			visited.add(next.getIdentifier());
			
			for (String neighborID : next.getNeighbors()) {
				return dependencyCheck(start, visited, g.getVertex(neighborID), g);
			}	
	
			return ""; 
		}
	}
}
