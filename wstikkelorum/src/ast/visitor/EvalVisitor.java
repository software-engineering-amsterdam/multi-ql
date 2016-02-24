package ast.visitor;

import java.util.HashMap;

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
import ast.literal.BoolLiteral;
import ast.literal.IntLiteral;
import ast.literal.StringLiteral;
import ast.statement.AssignmentQuestion;
import ast.statement.IfStatement;
import ast.statement.Question;

public class EvalVisitor extends BasicVisitor {
	private EvalVisitor visitor;
	public HashMap<String, Object> evaluationMap;
	
	public EvalVisitor(){
		visitor = this;
		evaluationMap = new HashMap<String, Object>();
	}
	
	@Override
	public Object visit(AssignmentQuestion assignmentQuestion){
		Object value = assignmentQuestion.getExpression().accept(visitor);
		putValue(assignmentQuestion.getVariable().getName(), value);
		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement){
		Object value = ifStatement.getExpression().accept(visitor);
		ifStatement.getBody().accept(visitor);
		return null;
	}
	
	@Override
	public Object visit(Question question){
		putValue(question.getVariable().getName(), question.getVariable().getValue());
		return null;
	}
	
	@Override
	public Object visit(OrExpression orExpression){
		return (boolean)orExpression.getLhs().accept(visitor) || (boolean)orExpression.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(AndExpression andExpression){
		return (boolean)andExpression.getLhs().accept(visitor) && (boolean)andExpression.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(Eq eq){
		return eq.getLhs().accept(visitor) == (eq.getRhs().accept(visitor));
	}
	
	@Override
	public Object visit(GEq geq){
		return (int)geq.getLhs().accept(visitor) >= (int)geq.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(GT gt){
		return (int)gt.getLhs().accept(visitor) > (int)gt.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(LEq leq){
		return (int)leq.getLhs().accept(visitor) <= (int)leq.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(LT lt){
		return (int)lt.getLhs().accept(visitor) < (int)lt.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(NEq neq){
		return neq.getLhs().accept(visitor) != neq.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(Add add){
		return (int)add.getLhs().accept(visitor) + (int)add.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(Sub sub){
		return (int)sub.getLhs().accept(visitor) - (int)sub.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(Mul mul){
		return (int)mul.getLhs().accept(visitor) * (int)mul.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(Div div){
		return (int)div.getLhs().accept(visitor) / (int)div.getRhs().accept(visitor);
	}
	
	@Override
	public Object visit(Pos pos){
		return Math.abs((int) pos.getExpression().accept(visitor));
	}
	
	@Override 
	public Object visit(Neg neg){
		return -Math.abs((int) neg.getExpression().accept(visitor));
	}
	
	@Override
	public Object visit(Not not){
		return !(boolean)not.getExpression().accept(visitor);
	}
	
	@Override
	public Object visit(IntLiteral intLiteral){
		return intLiteral.getValue();
	}
	
	@Override 
	public Object visit(BoolLiteral boolLiteral){
		return boolLiteral.getValue();
	}
	
	@Override
	public Object visit(StringLiteral stringLiteral){
		return stringLiteral.getValue();
	}

	private void putValue(String name, Object value){
		evaluationMap.put(name, value);
	}
	
	private void getValue(String name){
		evaluationMap.get(name);
	}

}
