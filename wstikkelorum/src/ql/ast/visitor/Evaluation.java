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
import ql.ast.literal.BoolLiteral;
import ql.ast.literal.IntLiteral;
import ql.ast.literal.StringLiteral;
import ql.ast.literal.Variable;
import ql.ast.literal.VariableExpression;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.InputQuestion;

public class Evaluation extends BasicVisitor<Object> {
	protected Context context;

	public Evaluation(Context context) {
		this.context = context;
	}

	@Override
	public Object visit(ComputedQuestion computedQuestion) {
		Object value = computedQuestion.getExpression().accept(this);
		context.putValueForQuestion(computedQuestion, value);
		return value;
	}
	
	public Object visit(InputQuestion inputQuestion){
		return context.getValueForVariable(inputQuestion.getVariable());
	}

	@Override
	public Object visit(OrExpression orExpression) {
		if(orExpression.getLhs().accept(this) == null || orExpression.getRhs().accept(this) == null){ 
			return null;
		}
		return (boolean) orExpression.getLhs().accept(this) || (boolean) orExpression.getRhs().accept(this);
	}

	@Override
	public Object visit(AndExpression andExpression) {
		if(andExpression.getLhs().accept(this) == null || andExpression.getRhs().accept(this) == null){ 
			return null;
		}
		return (boolean) andExpression.getLhs().accept(this) && (boolean) andExpression.getRhs().accept(this);
	}

	@Override
	public Object visit(Eq eq) {
		if(eq.getLhs().accept(this) == null || eq.getRhs().accept(this) == null){
			return null;
		}
		return eq.getLhs().accept(this) == eq.getRhs().accept(this);
	}

	@Override
	public Object visit(GEq geq) {
		if(geq.getLhs().accept(this) == null || geq.getRhs().accept(this) == null){
			return null;
		}
		return (int) geq.getLhs().accept(this) >= (int) geq.getRhs().accept(this);
	}

	@Override
	public Object visit(GT gt) {
		if(gt.getLhs().accept(this) == null || gt.getRhs().accept(this) == null){
			return null;
		}
		return (int) gt.getLhs().accept(this) > (int) gt.getRhs().accept(this);
	}

	@Override
	public Object visit(LEq leq) {
		if(leq.getLhs().accept(this) == null || leq.getRhs().accept(this) == null){
			return null;
		}
		return (int) leq.getLhs().accept(this) <= (int) leq.getRhs().accept(this);
	}

	@Override
	public Object visit(LT lt) {
		if(lt.getLhs().accept(this) == null || lt.getRhs().accept(this) == null){
			return null;
		}
		return (int) lt.getLhs().accept(this) < (int) lt.getRhs().accept(this);
	}

	@Override
	public Object visit(NEq neq) {
		if(neq.getLhs().accept(this) == null || neq.getRhs().accept(this) == null){
			return null;
		}
		return neq.getLhs().accept(this) != neq.getRhs().accept(this);
	}

	@Override
	public Object visit(Add add) {
		if(add.getLhs().accept(this) == null || add.getRhs().accept(this) == null){
			return null;
		}
		return (int) add.getLhs().accept(this) + (int) add.getRhs().accept(this);
	}

	@Override
	public Object visit(Sub sub) {
		if(sub.getLhs().accept(this) == null || sub.getRhs().accept(this) == null){
			return null;
		}
		return (int) sub.getLhs().accept(this) - (int) sub.getRhs().accept(this);
	}

	@Override
	public Object visit(Mul mul) {
		if(mul.getLhs().accept(this) == null || mul.getRhs().accept(this) == null){
			return null;
		}
		return (int) mul.getLhs().accept(this) * (int) mul.getRhs().accept(this);
	}

	@Override
	public Object visit(Div div) {
		if(div.getLhs().accept(this) == null || div.getRhs().accept(this) == null){
			return null;
		}
		return (int) div.getLhs().accept(this) / (int) div.getRhs().accept(this);
	}

	@Override
	public Object visit(Pos pos) {
		if(pos.getExpression().accept(this) == null){
			return null;
		}
		return Math.abs((int) pos.getExpression().accept(this));
	}

	@Override
	public Object visit(Neg neg) {
		if(neg.getExpression().accept(this) == null){
			return null;
		}
		return -Math.abs((int) neg.getExpression().accept(this));
	}

	@Override
	public Object visit(Not not) {
		if(not.getExpression().accept(this) == null){ 
			return null;
		}
		return !(boolean) not.getExpression().accept(this);
	}

	@Override
	public Object visit(IntLiteral intLiteral) {
		return intLiteral.getValue();
	}

	@Override
	public Object visit(BoolLiteral boolLiteral) {
		return boolLiteral.getValue();
	}

	@Override
	public Object visit(StringLiteral stringLiteral) {
		return stringLiteral.getValue();
	}

	@Override
	public Object visit(VariableExpression variableExpression) {
		return context.getValueForVariable(variableExpression);
	}

	@Override
	public Object visit(Variable variable) {
		return context.getValueForVariable(variable);
	}

	public Context getContext() {
		return context;
	}

	protected void addValueForQuestion(ComputedQuestion computedQuestion, Object value) {
		context.putValueForQuestion(computedQuestion, value);
	}
}
