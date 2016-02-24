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
import ast.expression.VariableExpression;
import ast.literal.BoolLiteral;
import ast.literal.IntLiteral;
import ast.literal.StringLiteral;
import ast.statement.AssignmentQuestion;
import ast.statement.IfStatement;
import ast.statement.Question;

public class EvalVisitor extends BasicVisitor {
	public HashMap<String, Object> evaluationMap;//andere naam, naar eigen klasses met interface(api) met minder low level names die representeren wat er daadwerkelijk gebeurd.
	
	public EvalVisitor(){
		evaluationMap = new HashMap<String, Object>();
	}
	
	@Override
	public Object visit(AssignmentQuestion assignmentQuestion){
		Object value = assignmentQuestion.getExpression().accept(this);
		putValue(assignmentQuestion.getVariable().getName(), value);
		return null;
	}

	@Override
	public Object visit(IfStatement ifStatement){
		Object value = ifStatement.getExpression().accept(this);
		ifStatement.getBody().accept(this);
		return null;
	}
	
	@Override
	public Object visit(Question question){
		putValue(question.getVariable().getName(), question.getVariable().getValue());
		return null;
	}
	
	@Override
	public Object visit(OrExpression orExpression){
		return (boolean)orExpression.getLhs().accept(this) || (boolean)orExpression.getRhs().accept(this);
	}
	
	@Override
	public Object visit(AndExpression andExpression){
		return (boolean)andExpression.getLhs().accept(this) && (boolean)andExpression.getRhs().accept(this);
	}
	
	@Override
	public Object visit(Eq eq){
		return eq.getLhs().accept(this) == (eq.getRhs().accept(this));
	}
	
	@Override
	public Object visit(GEq geq){
		return (int)geq.getLhs().accept(this) >= (int)geq.getRhs().accept(this);
	}
	
	@Override
	public Object visit(GT gt){
		return (int)gt.getLhs().accept(this) > (int)gt.getRhs().accept(this);
	}
	
	@Override
	public Object visit(LEq leq){
		return (int)leq.getLhs().accept(this) <= (int)leq.getRhs().accept(this);
	}
	
	@Override
	public Object visit(LT lt){
		return (int)lt.getLhs().accept(this) < (int)lt.getRhs().accept(this);
	}
	
	@Override
	public Object visit(NEq neq){
		return neq.getLhs().accept(this) != neq.getRhs().accept(this);
	}
	
	@Override
	public Object visit(Add add){
		return (int)add.getLhs().accept(this) + (int)add.getRhs().accept(this);
	}
	
	@Override
	public Object visit(Sub sub){
		return (int)sub.getLhs().accept(this) - (int)sub.getRhs().accept(this);
	}
	
	@Override
	public Object visit(Mul mul){
		return (int)mul.getLhs().accept(this) * (int)mul.getRhs().accept(this);
	}
	
	@Override
	public Object visit(Div div){
		return (int)div.getLhs().accept(this) / (int)div.getRhs().accept(this);
	}
	
	@Override
	public Object visit(Pos pos){
		return Math.abs((int) pos.getExpression().accept(this));
	}
	
	@Override 
	public Object visit(Neg neg){
		return -Math.abs((int) neg.getExpression().accept(this));
	}
	
	@Override
	public Object visit(Not not){
		return !(boolean)not.getExpression().accept(this);
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
	
	@Override
	public Object visit(VariableExpression variableExpression){
		return getValue(variableExpression.getName(), variableExpression.getLineNumber());
	}

	private void putValue(String name, Object value){
		evaluationMap.put(name, value);
	}
	
	//naar andere klassen verplaatsen en het printen van warning scheiden van het ophalen van een value etc
	private Object getValue(String name, int lineNumber){
		if(!evaluationMap.containsKey(name)){
			System.out.println(String.format("Variable: %s, unknown. At line number: %d",
					name, lineNumber));
			return null;
		}
		
		if(evaluationMap.get(name) == null){
			System.out.println(String.format("Variable: %s, has no value. At line number: %d",
					name, lineNumber));
			return null;
		}
		
		return evaluationMap.get(name);
	}

}
