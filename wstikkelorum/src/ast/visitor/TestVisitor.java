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
import ast.expression.VariableExpression;
import ast.form.Body;
import ast.form.Form;
import ast.literal.BoolLiteral;
import ast.literal.IntLiteral;
import ast.literal.Literal;
import ast.literal.StringLiteral;
import ast.literal.Variable;
import ast.statement.AssignmentQuestion;
import ast.statement.IfStatement;
import ast.statement.Question;
import ast.statement.Statement;

public class TestVisitor implements Visitor {
	public List<String> types = new ArrayList<String>();
	private Visitor visitor;
	
	public TestVisitor(){
		visitor = this;
	}

	@Override
	public Types visit(Form form) {
		types.add("Form");
		form.getBody().accept(visitor);
		return null;
	} 

	@Override
	public Types visit(Body body) {
		types.add("Body");
		for(Visitable v : body.getStatements()){
			v.accept(visitor);
		};
		return null;
	}

	@Override
	public Types visit(Add add) {
		types.add("Add");
		add.getLhs().accept(visitor);
		add.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(AndExpression andExpression) {
		types.add("AndExpression");
		andExpression.getLhs().accept(visitor);
		andExpression.getLhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Div div) {
		types.add("Div");
		div.getLhs().accept(visitor);
		div.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Eq eq) {
		types.add("Eq");
		eq.getLhs().accept(visitor);
		eq.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(GEq geq) {
		types.add("GEq");
		geq.getLhs().accept(visitor);
		geq.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(GT gt) {
		types.add("Gt");
		gt.getLhs().accept(visitor);
		gt.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(LEq leq) {
		types.add("LEq");
		leq.getLhs().accept(visitor);
		leq.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(LT lt) {
		types.add("LT");
		lt.getLhs().accept(visitor);
		lt.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Mul mul) {
		types.add("MUL");
		mul.getLhs().accept(visitor);
		mul.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(NEq neq) {
		types.add("NEq");
		neq.getLhs().accept(visitor);
		neq.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Neg neg) {
		types.add("Neg");
		neg.getExpression().accept(visitor);	
		return null;
	}

	@Override
	public Types visit(Not not) {
		types.add("Not");
		not.getExpression().accept(visitor);
		return null;
	}

	@Override
	public Types visit(OrExpression orExpression) {
		types.add("OrExpression");
		orExpression.getLhs().accept(visitor);
		orExpression.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Pos pos) {
		types.add("Pos");
		pos.getExpression().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Sub sub) {
		types.add("Sub");
		sub.getLhs().accept(visitor);
		sub.getRhs().accept(visitor);
		return null;
	}

	@Override
	public Types visit(IntLiteral intLiteral) {
		String s = String.format("IntLiteral: %d", intLiteral.getValue());
		types.add(s);
		return null;
	}
	
	@Override
	public Types visit(BoolLiteral boolLiteral) {
		String s = String.format("BoolLiteral: %s", boolLiteral.getValue());
		types.add(s);
		return null;
	}

	@Override
	public Types visit(StringLiteral stringLiteral) {
		String s = String.format("StringLiteral: %s", stringLiteral.getValue());
		types.add(s);
		return null;
	}

	@Override
	public Types visit(Literal literal) {
		types.add("Literal");
		if(literal.getIntLiteral() != null){
			literal.getIntLiteral().accept(visitor);
		}
		if(literal.getVariableExpression() != null){
			literal.getVariableExpression().accept(visitor);
		}
		return null;
	}

	@Override
	public Types visit(Variable variable) {
		String s = String.format("Variable: %s %s", variable.getName(), variable.getType());
		types.add(s);
		return null;
	}

	@Override
	public Types visit(AssignmentQuestion assignementQuestion) {
		String s = String.format("AssignmentQuestion: %s %s %s", assignementQuestion.getVariable().getName(), assignementQuestion.getVariable().getType(), assignementQuestion.getStr());
		types.add(s);
		assignementQuestion.getExpression().accept(visitor);
		return null;
	}

	@Override
	public Types visit(IfStatement ifStatement) {
		types.add("IfStatement");
		ifStatement.getExpression().accept(visitor);
		ifStatement.getBody().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Question question) {
		String s = String.format("Question: %s %s %s", question.getVariable().getName(), question.getVariable().getType(), question.getStr());
		types.add(s);
		return null;
	}

	@Override
	public Types visit(Statement statement) {
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
		return null;
	}

	@Override
	public Types visit(VariableExpression variableExpression) {
		String s = String.format("VariableExpression %s", variableExpression.getName());
		types.add(s);
		return null;
	}
}
