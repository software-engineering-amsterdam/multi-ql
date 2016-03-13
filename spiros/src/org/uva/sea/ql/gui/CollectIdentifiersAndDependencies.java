package org.uva.sea.ql.gui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.FormVisitor;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.IfElseStatement;
import org.uva.sea.ql.ast.statement.IfStatement;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.Statement;
import org.uva.sea.ql.ast.statement.StatementVisitor;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.type_checker.DependentVariables;
import org.uva.sea.ql.type_checker.IdentifierData;

public class CollectIdentifiersAndDependencies implements FormVisitor,StatementVisitor {

	private Set<Identifier> identifiers;
	private Map<Identifier, Set<Identifier>> dependencies;
	
	public CollectIdentifiersAndDependencies(Form form) {
		this.identifiers = new HashSet<Identifier>();
		this.dependencies = new HashMap<Identifier, Set<Identifier>>();
		visitForm(form);
	}
	
	public Set<Identifier> getIdentifiers() {
		return this.identifiers;
	}
	

	@Override
	public void visitComputedQuestion(ComputedQuestion computedQuestion) {
		this.identifiers.add(computedQuestion.getId());
		
		CollectDependencies collectDependencies = new CollectDependencies();
		Set<Identifier> dependencies = computedQuestion.getExpression().accept(collectDependencies);
		
		Identifier identifier = computedQuestion.getId();
		insertAtHashMap(identifier, dependencies);
				
//		for (Identifier dependentIdentifier: dependencies) {
//			this.setDependencies(identifier,dependentIdentifier);
//		}
		
		for(Map.Entry<Identifier, Set<Identifier>> entry : this.dependencies.entrySet()){
			Set<Identifier> setIdentifiers = entry.getValue();
			Iterator<Identifier> iterator = setIdentifiers.iterator();		// just changed this...
			while (iterator.hasNext())
				System.out.printf("Identifier %s has %s as a dependency \n", entry.getKey().getValue(), iterator.next().getValue());
		}
		
	}
	
	private void insertAtHashMap(Identifier identifier, Set<Identifier> dependencyIdentifiers) {
		this.dependencies.put(identifier,dependencyIdentifiers);
		
		for (Identifier id: dependencyIdentifiers) {
			
			if (this.dependencies.keySet().contains(id))
				dependencies.get(id).add(identifier);
			
			else {
				Set<Identifier> newSet = new HashSet<Identifier>(); 
				newSet.add(identifier);
				this.dependencies.put(id, newSet);
				
			}
				
		}
	}
	

	@Override
	public void visitQuestion(Question question) {
		this.identifiers.add(question.getId());
	}

	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		ifStatement.getBlock().accept(this);	
	}

	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitForm(Form form) {
		form.getBlock().accept(this);		
	}

	@Override
	public void visitBlock(Block block) {
		for (Statement statement: block.getStatements()) {
			statement.accept(this);
		}			
	}

}
