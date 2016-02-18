package ast.visitor;

import ast.expression.Add;
import ast.expression.AndExpression;
import ast.expression.BinaryExpression;
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

public class TypeChecker implements Visitor {
	private Visitor visitor;
	
	public TypeChecker(){
		visitor = this;
	}

	@Override
	public Types visit(Form form) {
		form.getBody().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Body body) {
		for(Visitable v : body.getStatements()){
			v.accept(visitor);
		}
		return null;
	}
	
	@Override
	public Types visit(Statement statement) {
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
	public Types visit(AssignmentQuestion assignmentQuestion) {
		assignmentQuestion.getExpression().accept(visitor);
		//TODO: typecheck
		return null;
	}

	@Override
	public Types visit(IfStatement ifStatement) {
		ifStatement.getExpression().accept(visitor);
		//TODO: typecheck
		ifStatement.getBody().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Question question) {
		//Question has a type...
		return null;
	}
	
	@Override
	public Types visit(OrExpression orExpression) {
		orExpression.getLhs().accept(visitor);
		orExpression.getRhs().accept(visitor);
		//TODO: typecheck lhs and rhs... or a binary expression
		return null;
	}
	
	@Override
	public Types visit(AndExpression andExpression) {
		andExpression.getLhs().accept(visitor);
		andExpression.getLhs().accept(visitor);
		//check types for lhs and rhs are both boolean
		return null;
	}

	@Override
	public Types visit(Eq eq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Types visit(GEq geq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Types visit(GT gt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Types visit(LEq leq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Types visit(LT lt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Types visit(NEq neq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Types visit(Neg neg) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Types visit(Mul mul) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Types visit(Add add) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Types visit(Div div) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Types visit(Sub sub) {
		sub.getLhs().accept(visitor);
		sub.getRhs().accept(visitor);
		return null;
	}
	
	@Override
	public Types visit(Not not) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Types visit(Pos pos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Types visit(Literal literal) {
		if(literal.getIntLiteral() != null){
			literal.getIntLiteral().accept(visitor);
		}
		
		if(literal.getBoolLiteral() != null){
			literal.getBoolLiteral().accept(visitor);
		}
		
		if(literal.getStringLiteral() != null){
			literal.getStringLiteral().accept(visitor);
		}
		
		if(literal.getVariableExpression() != null){
			literal.getVariableExpression().accept(visitor);
		}
		
		return null;
	}

	@Override
	public Types visit(IntLiteral intLiteral) {
		return Types.INT;
	}
	
	@Override
	public Types visit(BoolLiteral boolLiteral) {
		return Types.BOOLEAN;
	}

	@Override
	public Types visit(StringLiteral stringLiteral) {
		return Types.STRING;
	}

	@Override
	public Types visit(Variable variable) {
		variable.accept(visitor);
		return null;
	}

	@Override
	public Types visit(VariableExpression variableExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(BinaryExpression binaryExpression) {
		// TODO Auto-generated method stub
		return null;
	}
}
