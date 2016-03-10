package ql.ast.visitor;

import ql.ast.expression.Add;
import ql.ast.expression.AndExpression;
import ql.ast.expression.Div;
import ql.ast.expression.Eq;
import ql.ast.expression.GEq;
import ql.ast.expression.GT;
import ql.ast.expression.LEq;
import ql.ast.expression.LT;
import ql.ast.expression.Mul;
import ql.ast.expression.NEq;
import ql.ast.expression.Neg;
import ql.ast.expression.Not;
import ql.ast.expression.OrExpression;
import ql.ast.expression.Pos;
import ql.ast.expression.Sub;
import ql.ast.expression.VariableExpression;
import ql.ast.literal.BoolLiteral;
import ql.ast.literal.IntLiteral;
import ql.ast.literal.StringLiteral;
import ql.ast.statement.ComputedQuestion;

public class Evaluation extends BasicVisitor<Object> {
	protected Context context;
	
	public Evaluation(Context context){
		this.context = context;
	}
	
	@Override
	public Object visit(ComputedQuestion computedQuestion){
		Object value = computedQuestion.getExpression().accept(this);
		context.putValueQuestion(computedQuestion, value);
		return null;
	}
	
	@Override
	public Boolean visit(OrExpression orExpression){
		return (boolean)orExpression.getLhs().accept(this) || (boolean)orExpression.getRhs().accept(this);
	}
	
	@Override
	public Boolean visit(AndExpression andExpression){
		return (boolean)andExpression.getLhs().accept(this) && (boolean)andExpression.getRhs().accept(this);
	}
	
	@Override
	public Boolean visit(Eq eq){
		return eq.getLhs().accept(this) == (eq.getRhs().accept(this));
	}
	
	@Override
	public Boolean visit(GEq geq){
		return (int)geq.getLhs().accept(this) >= (int)geq.getRhs().accept(this);
	}
	
	@Override
	public Boolean visit(GT gt){
		return (int)gt.getLhs().accept(this) > (int)gt.getRhs().accept(this);
	}
	
	@Override
	public Boolean visit(LEq leq){
		return (int)leq.getLhs().accept(this) <= (int)leq.getRhs().accept(this);
	}
	
	@Override
	public Boolean visit(LT lt){
		return (int)lt.getLhs().accept(this) < (int)lt.getRhs().accept(this);
	}
	
	@Override
	public Boolean visit(NEq neq){
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
		return context.getValueForVariable(variableExpression);
	}
	
	public Context getContext(){
		return context;
	}
	
	protected void addValueForQuestion(ComputedQuestion computedQuestion, Object value){
		context.putValueQuestion(computedQuestion, value);
	}
}
