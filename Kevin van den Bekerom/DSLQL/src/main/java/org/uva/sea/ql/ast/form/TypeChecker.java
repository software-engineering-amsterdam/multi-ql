package org.uva.sea.ql.ast.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.visit.QuestionCollector;
import org.uva.sea.ql.errors.AbstractQLError;
import org.uva.sea.ql.errors.QLError;
import org.uva.sea.ql.errors.QLWarning;
import org.uva.sea.ql.graph.Graph;
import org.uva.sea.ql.graph.Vertex;
import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.ContextVisitor;
import org.uva.sea.ql.ast.DependencyChecker;
import org.uva.sea.ql.ast.DependencyGraphBuilder;
import org.uva.sea.ql.ast.TypeCheckVisitor;
import org.uva.sea.ql.ast.expr.*;

public class TypeChecker {
	
	/*
		@returns: true if there exists a duplicate question identifier in the list <questions>
	*/
	public List<QLError> duplicateQuestionVariable(List<Question> questions) {
		List<QLError> errors = new ArrayList<QLError>();
		Map<String, Type> questionTypeTable = new HashMap<String, Type>();
		
		for (Question q : questions) { 
			String key = q.getIdentifier();
			if (questionTypeTable.containsKey(key)) {
				// same question identifier -> check if types mismatch. If so, duplicate question.
				if (! q.getType().equals(questionTypeTable.get(key))) {
					String errMessage = "Duplicate question. Make sure questions with the same identifier have the same type!";
					errors.add(new QLError(q, errMessage)); // error message here!
				}
			}
			questionTypeTable.put(q.getIdentifier(), q.getType());
		}
		
		return errors;
	}
	
	public List<QLWarning> duplicateQuestionLabel(List<Question> questions) {
		List<QLWarning> errors = new ArrayList<QLWarning>();
		Set<String> labels = new HashSet<String>();
		
		for (Question q : questions) { //TODO: document note -> q to not confuse with the list 'questions'
			if (labels.contains(q.getLabel())) {
				String errMessage = "Duplicate labels. This might confuse users!";
				errors.add(new QLWarning(q, errMessage));
			}
			labels.add(q.getLabel());
		}
		
		return errors;
	}
	
	public boolean referenceToUndefinedQuestion(List<Question> questions, List<Variable> variables) {
		List<String> qIdentifiers = new ArrayList<String>();
		
		for (Question q : questions) {
			qIdentifiers.add(q.getIdentifier());
		}
		
		for (Variable v : variables) {
			if (qIdentifiers.contains(v.getIdentifier())) {
				return false;
			}
		}
		
		return true;
	}
	
	public boolean typeMismatchBooleanExpressions() {
		return true;
		//TODO: implement skeleton method
	}
	
	public boolean typeMismatchIntegerExpressions() {
		return true;
		//TODO: implement skeleton method
	}
	
	public boolean typeMismatchUnaryExpression() {
		return true;
		//TODO: implement skeleton method
	}
	
	public void detectCyclicDependency(Graph dependencyGraph) {
		
	}
	
	public void typeCheck(ASTNode root) {
		// 0.1 Get list of all questions
		List<Question> questions = QuestionCollector.getQuestions(root, true);
		
		// 0.2 Get dependency graph
		Graph dependencyGraph = new DependencyGraphBuilder((Block) root).getDependencyGraph();
		
		Context context = new Context();
		
		ContextVisitor cv = new ContextVisitor();
		root.accept(cv, context);
		
		Map<String, Type> typeMap = context.getTypeMap();
		for (String key : typeMap.keySet()) {
			System.out.println("key : " + key + " and value " + typeMap.get(key));
		}
		
		// test dependencygraph 
		for (Vertex v : dependencyGraph.getVertices()) {
			System.out.println(v.getIdentifier());
		}
		
		// 1. duplicate questions:
		
		for (QLError error : this.duplicateQuestionVariable(questions)) {
			System.out.println(error.getErrorMessage());
		}
		
		// 2. duplicate labels:
		
		for (QLWarning warning : this.duplicateQuestionLabel(questions)) {
			System.out.println(warning.getErrorMessage());
		}
		
		// 3. dependency check:
		
		for (QLError error : DependencyChecker.getErrors(root, dependencyGraph)) {
			System.out.println(error.getErrorMessage());
		}
		
		// 4. Type check
		
		for (QLError error : TypeCheckVisitor.getErrorMessages(root, context)) {
			System.out.println(error.getErrorMessage());
		}
		
		
		
	}
	
	
	

}
