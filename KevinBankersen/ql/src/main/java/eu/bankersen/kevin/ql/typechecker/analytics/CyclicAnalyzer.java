package eu.bankersen.kevin.ql.typechecker.analytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.bankersen.kevin.ql.ast.AbstractVisitor;
import eu.bankersen.kevin.ql.ast.Identifier;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.typechecker.errors.CyclicDependencyError;
import eu.bankersen.kevin.ql.typechecker.errors.TypeCheckError;

public class CyclicAnalyzer extends AbstractVisitor<Void, Void> {

    private final List<TypeCheckError> errorList;

    // Hold values to track relations.
    private final Map<String, Set<String>> qRelations;
    private  Set<String> qIdentifiers;
    private final Map<Set<String>, Set<String>> ifRelations;
    private  LinkedList<Set<String>> ifIdentifiers;

    public CyclicAnalyzer(Form form) {
	this.ifRelations = new HashMap<>();
	this.ifIdentifiers = new LinkedList<>();
	this.ifIdentifiers.add(new HashSet<>());
	this.qRelations = new HashMap<>();
	this.qIdentifiers = new HashSet<>();
	this.errorList = new ArrayList<>();

	this.analyzeFromDependencies(form);
    }

    @Override
    public Void visit(Form o, Void context) {
	o.body().accept(this, context);
	return null;
    }

    @Override
    public void visit(Body o) {
	o.statements().forEach(s -> s.accept(this, null));
    }

    @Override
    public void visit(IFStatement o) {
	defaultIfstatement(o.expr(), o.body());
    }

    @Override
    public void visit(ElseStatement o) {
	defaultIfstatement(o.expr(), o.body());
	o.elseBody().accept(this, null);
    }   

    private void defaultIfstatement(Expr expr, Body body) {
	qIdentifiers = new HashSet<>();

	expr.accept(this, null);

	if (!qIdentifiers.isEmpty()) {
	    Set<String> ifId = qIdentifiers;
	    ifIdentifiers.add(new HashSet<>());
	    qIdentifiers = new HashSet<>();
	    body.accept(this, null);
	    defineIfRelation(ifId);
	} else {
	    body.accept(this, null);
	}
    }

    @Override
    public void visit(ComputedQuestion o) {
	registerForIf(o.name());
	o.expr().accept(this, null);
	defineQuestionRelation(o.name()); 
    }

    @Override
    public void visit(NormalQuestion o) {
	qIdentifiers = new HashSet<>();
	registerForIf(o.name());
	defineQuestionRelation(o.name()); 
    }

    @Override
    public void visit(Expr o) {
	o.lhs().accept(this, null);
	if (o.rhs() != null) {
	    o.rhs().accept(this, null);
	}
    }

    @Override
    public void visit(Identifier o) {
	qIdentifiers.add(o.name());
    }

    private void registerForIf(String name) {
	Set<String> lastElement;
	lastElement = ifIdentifiers.getLast();
	ifIdentifiers.removeLast();
	lastElement.add(name);
	ifIdentifiers.add(lastElement);
    }

    private void registerForIf(Set<String> names) {
	Set<String> lastElement;
	lastElement = ifIdentifiers.getLast();
	ifIdentifiers.removeLast();
	lastElement.addAll(names);
	ifIdentifiers.add(lastElement);
    }

    // Define a relationship between Question and identifiers.
    private void defineQuestionRelation(String variable) {     	
	if (!qIdentifiers.isEmpty()) {
	    Set<String> data = qIdentifiers;
	    if (qRelations.containsKey(variable)) {
		data.addAll(qRelations.get(variable));    
	    } 
	    qRelations.put(variable, data);
	}
	qIdentifiers = new HashSet<>();
    }

    // Define a relationship between If-Statement and identifiers.
    private void defineIfRelation(Set<String> variable) {
	Set<String> data = ifIdentifiers.getLast();
	ifIdentifiers.removeLast();

	if (!data.isEmpty()) {
	    registerForIf(data);
	}	

	if (ifRelations.containsKey(variable)) {
	    data.addAll(ifRelations.get(variable));    
	}
	ifRelations.put(variable, data);
    }

    public List<TypeCheckError> getErrors() {
	return errorList;
    }

    private void addError(TypeCheckError error) {
	errorList.add(error);
    }

    // Algorithm to calculate dependencies between Questions
    private void analyzeFromDependencies(Form form) {

	//form.accept(this);
	this.visit(form, null);

	this.computeDependencies();
    }

    private void computeDependencies() {

	Map<String, Set<String>> computedDependencies = new HashMap<>();

	for (String var : qRelations.keySet()) {
	    Set<String> dependencies = qRelations.get(var);
	    int previousSize = 0;

	    while (dependencies.size() != previousSize) {
		previousSize = dependencies.size();

		for (String ref : dependencies.toArray(new String[dependencies.size()])) {
		    if (qRelations.containsKey(ref)) {
			dependencies.addAll(qRelations.get(ref));
		    }
		}
	    }
	    if (dependencies.contains(var)) {
		addError(new CyclicDependencyError(var, dependencies));
	    }
	    computedDependencies.put(var, dependencies);
	}
	this.analyzeDependencies(computedDependencies);
    }

    // Algorithm to calculate dependencies between If-statements
    private void analyzeDependencies(Map<String, Set<String>> computedDependencies) {
	Set<String> computedQuestions = computedDependencies.keySet();
	for (Set<String> ifVars : ifRelations.keySet()) {
	    for (String ifVar : ifVars) {
		if (computedDependencies.containsKey(ifVar)) {
		    if (containsAny(qRelations.get(ifVar), ifRelations.get(ifVars), computedQuestions)) {
			addError(new CyclicDependencyError(ifVars, computedDependencies.get(ifVar)));
		    }
		}
	    }
	}
    }

    private Boolean containsAny(Set<String> qSet, Set<String> ifSet, Set<String> comp) {
	qSet.removeAll(comp);
	//ifSet.removeAll(comp);
	return qSet.stream().anyMatch(ifSet::contains);
    }

}