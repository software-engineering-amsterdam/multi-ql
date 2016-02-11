package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.stat.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.uva.sea.ql.ast.expr.*;

public class DependancyVisitor extends LeftDFSVisitor {
	private Set<String> identifiers;
	boolean cyclicDependancy;
	
	public DependancyVisitor() {
		identifiers = new HashSet<String>();
		cyclicDependancy = false;
	}
	
	@Override
	public void visit(Question question) {
		identifiers.add(question.getIdentifier());
		super.visit(question);
	}
	
	@Override
	public void visit(IfStatement ifStatement) {
		NodeCollector collector = new NodeCollector(); 
		ifStatement.getClause().accept(collector);
		ArrayList<Variable> variables = new ArrayList<Variable>(collector.getVariables());
		Set<String> varNames = new HashSet<String>();
		
		for (Variable var : variables) {
			varNames.add(var.getIdentifier());
		}		
		
		if (identifiers.containsAll(varNames)) {
			cyclicDependancy = true;
		} else {
			super.visit(ifStatement);
		}
	}
	
	public boolean containsCyclicDependancy() {
		return this.cyclicDependancy;
	}
}
