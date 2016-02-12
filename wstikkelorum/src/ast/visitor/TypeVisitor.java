package ast.visitor;

import java.util.ArrayList;
import java.util.List;

import ast.expression.Add;
import ast.expression.AndExpression;
import ast.expression.Div;
import ast.expression.Eq;
import ast.expression.GEq;
import ast.expression.GT;
import ast.expression.LEq;
import ast.expression.LT;
import ast.expression.Mul;
import ast.expression.NEq;
import ast.expression.Neg;
import ast.expression.Not;
import ast.expression.OrExpression;
import ast.expression.Pos;
import ast.expression.Sub;
import ast.form.Body;
import ast.form.Form;
import ast.literal.IntLiteral;
import ast.literal.Literal;
import ast.literal.Variable;
import ast.statement.AssignmentQuestion;
import ast.statement.IfStatement;
import ast.statement.Question;
import ast.statement.Statement;

public class TypeVisitor implements Visitor {
	public List<String> types = new ArrayList<String>();
	private Visitor visitor;
	
	public TypeVisitor(){
		visitor = this;
	}

	@Override
	public void visit(Form form) {
		types.add("Form");
		form.getBody().accept(visitor);
	} 

	@Override
	public void visit(Body body) {
		types.add("Body");
		for(Visitable v : body.getStatements()){
			v.accept(visitor);
		};
	}

	@Override
	public void visit(Add add) {
		types.add("Add");
		add.getLhs().accept(visitor);
		add.getRhs().accept(visitor);
	}

	@Override
	public void visit(AndExpression andExpression) {
		types.add("AndExpression");
		andExpression.getLhs().accept(visitor);
		andExpression.getLhs().accept(visitor);
	}

	@Override
	public void visit(Div div) {
		types.add("Div");
		div.getLhs().accept(visitor);
		div.getRhs().accept(visitor);
	}

	@Override
	public void visit(Eq eq) {
		types.add("Eq");
		eq.getLhs().accept(visitor);
		eq.getRhs().accept(visitor);
	}

	@Override
	public void visit(GEq geq) {
		types.add("GEq");
		geq.getLhs().accept(visitor);
		geq.getRhs().accept(visitor);
	}

	@Override
	public void visit(GT gt) {
		types.add("Gt");
		gt.getLhs().accept(visitor);
		gt.getRhs().accept(visitor);
	}

	@Override
	public void visit(LEq leq) {
		types.add("LEq");
		leq.getLhs().accept(visitor);
		leq.getRhs().accept(visitor);
	}

	@Override
	public void visit(LT lt) {
		types.add("LT");
		lt.getLhs().accept(visitor);
		lt.getRhs().accept(visitor);
	}

	@Override
	public void visit(Mul mul) {
		types.add("MUL");
		mul.getLhs().accept(visitor);
		mul.getRhs().accept(visitor);
	}

	@Override
	public void visit(NEq neq) {
		types.add("NEq");
		neq.getLhs().accept(visitor);
		neq.getRhs().accept(visitor);
	}

	@Override
	public void visit(Neg neg) {
		types.add("Neg");
		neg.getExpression().accept(visitor);	
	}

	@Override
	public void visit(Not not) {
		types.add("Not");
		not.getExpression().accept(visitor);		
	}

	@Override
	public void visit(OrExpression orExpression) {
		types.add("OrExpression");
		orExpression.getLhs().accept(visitor);
		orExpression.getRhs().accept(visitor);
	}

	@Override
	public void visit(Pos pos) {
		types.add("Pos");
		pos.getExpression().accept(visitor);
	}

	@Override
	public void visit(Sub sub) {
		types.add("Sub");
		sub.getLhs().accept(visitor);
		sub.getRhs().accept(visitor);
	}

	@Override
	public void visit(IntLiteral intLiteral) {
		String s = String.format("IntLiteral: %d", intLiteral.getValue());
		types.add(s);
	}

	@Override
	public void visit(Literal literal) {
		types.add("Literal");
		if(literal.getIntLiteral() != null){
			literal.getIntLiteral().accept(visitor);
		}
		if(literal.getVariable() != null){
			literal.getVariable().accept(visitor);
		}
	}

	@Override
	public void visit(Variable variable) {
		String s = String.format("Variable: %s", variable.getText());
		types.add(s);
	}

	@Override
	public void visit(AssignmentQuestion assignementQuestion) {
		String s = String.format("AssignmentQuestion: %s %s %s", assignementQuestion.getId(), assignementQuestion.getStr(), assignementQuestion.getType());
		types.add(s);
		assignementQuestion.getExpression().accept(visitor);
	}

	@Override
	public void visit(IfStatement ifStatement) {
		types.add("IfStatement");
		ifStatement.getExpression().accept(visitor);
		ifStatement.getBody().accept(visitor);
	}

	@Override
	public void visit(Question question) {
		String s = String.format("Question: %s %s %s", question.getId(), question.getStr(), question.getType());
		types.add(s);
	}

	@Override
	public void visit(Statement statement) {
		types.add("Statement");
		if(statement.getAssignmentQuestion() != null){
			statement.getAssignmentQuestion().accept(visitor);
		}
		if(statement.getIfStatement() != null){
			statement.getIfStatement().accept(visitor);
		}
		if(statement.getQuestion() != null){
			statement.getQuestion().accept(visitor);
		}
	}
}
