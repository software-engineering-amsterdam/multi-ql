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

public class BasicVisitor implements Visitor<Object> {
	private Visitor<Object> visitor;
	
	public BasicVisitor(){
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
		};
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
	public Types visit(AssignmentQuestion assignementQuestion) {
		assignementQuestion.getExpression().accept(visitor);
		return null;
	}
	
	@Override
	public Types visit(IfStatement ifStatement) {
		ifStatement.getExpression().accept(visitor);
		ifStatement.getBody().accept(visitor);
		return null;
	}
	
	@Override
	public Types visit(Question question) {
		//TODO: moet ik hier nog verder een visit doen op de variable?
		return null;
	}
	
	@Override
	public Types visit(BinaryExpression binaryExpression) {
		binaryExpression.getLhs().accept(visitor);
		binaryExpression.getRhs().accept(visitor);
		return null;
	}
	
	@Override
	public Types visit(OrExpression orExpression) {
		visit((BinaryExpression)orExpression);
		return null;
	}
	
	@Override
	public Types visit(AndExpression andExpression) {
		visit((BinaryExpression)andExpression);
		return null;
	}

	@Override
	public Types visit(Add add) {
		visit((BinaryExpression)add);
		return null;
	}

	@Override
	public Types visit(Div div) {
		visit((BinaryExpression)div);
		return null;
	}

	@Override
	public Types visit(Eq eq) {
		visit((BinaryExpression)eq);
		return null;
	}

	@Override
	public Types visit(GEq geq) {
		visit((BinaryExpression)geq);
		return null;
	}

	@Override
	public Types visit(GT gt) {
		visit((BinaryExpression)gt);
		return null;
	}

	@Override
	public Types visit(LEq leq) {
		visit((BinaryExpression)leq);
		return null;
	}

	@Override
	public Types visit(LT lt) {
		visit((BinaryExpression)lt);
		return null;
	}

	@Override
	public Types visit(Mul mul) {
		visit((BinaryExpression)mul);
		return null;
	}

	@Override
	public Types visit(NEq neq) {
		visit((BinaryExpression)neq);
		return null;
	}

	@Override
	public Types visit(Neg neg) {
		neg.getExpression().accept(visitor);	
		return null;
	}

	@Override
	public Types visit(Not not) {
		not.getExpression().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Pos pos) {
		pos.getExpression().accept(visitor);
		return null;
	}

	@Override
	public Types visit(Sub sub) {
		visit((BinaryExpression)sub);
		return null;
	}

	@Override
	public Types visit(Literal literal) {
		if(literal.getIntLiteral() != null){
			literal.getIntLiteral().accept(visitor);
		}
		if(literal.getVariableExpression() != null){
			literal.getVariableExpression().accept(visitor);
		}
		return null;
	}
	
	@Override
	public Types visit(IntLiteral intLiteral) {
		return null;
	}
	
	@Override
	public Types visit(BoolLiteral boolLiteral) {
		return null;
	}

	@Override
	public Types visit(StringLiteral stringLiteral) {
		return null;
	}

	@Override
	public Types visit(Variable variable) {
		return null;
	}

	@Override
	public Types visit(VariableExpression variableExpression) {
		return null;
	}
}
