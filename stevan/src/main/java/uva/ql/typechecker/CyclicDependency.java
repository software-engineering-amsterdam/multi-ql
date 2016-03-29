package uva.ql.typechecker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.ArithmeticOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorUnary;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.questions.QuestionComputed;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.variables.VarGeneric;
import uva.ql.ast.variables.Variable;
import uva.ql.typechecker.abstracts.AbstractTypeChecker;
import uva.ql.typechecker.errors.ErrorCyclic;
import uva.ql.typechecker.visitors.ICyclicDependencyVisitor;

public class CyclicDependency extends AbstractTypeChecker implements ICyclicDependencyVisitor {

	private final Map<String, Variable> questionVariables = new HashMap<String, Variable>(0);
	private final Map<String, Variable> cyclicVariables = new HashMap<String, Variable>(0);
	
	@Override
	public void visitForm(Form form) {
		
		for(int i=0; i<form.size(); i++) {
			
			Block block = (Block) form.get(i);
			block.accept(this);
		}
		
		Iterator<Entry<String, Variable>> varIterator = cyclicVariables.entrySet().iterator();

		while(varIterator.hasNext()) {
			
			Entry<String, Variable> pair = varIterator.next();
			
			if(questionVariables.containsKey(pair.getKey())) {

				errorMessages.add(new ErrorCyclic(pair.getKey(), pair.getValue().getLine(), pair.getValue().getColumn()));
			}
		}
	}

	@Override
	public void visitBlock(Block block) {
		
		for(int i=0; i<block.size(); i++) {
			
			block.get(i).accept(this);
		}
	}
	
	@Override
	public void visitQuestionVanilla(QuestionVanilla questionVanilla) {
		Variable var = questionVanilla.getVariable();
		questionVariables.put(var.getName(), var);
	}
	
	@Override
	public void visitQuestionComputed(QuestionComputed questionComputed) {
		Variable var = questionComputed.getVariable();
		questionVariables.put(var.getName(), var);
		
		questionComputed.getExp().accept(this);
	}
	
	@Override
	public void visitCondIfStatement(CondIfStatement condIfStatement) {
		condIfStatement.getExpression().accept(this);
		condIfStatement.getLhs().accept(this);
	}
	
	@Override
	public void visitCondIfElseStatement(CondIfElseStatement condIfElseStatement) {
		condIfElseStatement.getExpression().accept(this);
		condIfElseStatement.getLhs().accept(this);
		condIfElseStatement.getRhs().accept(this);
	}

	@Override
	public void visitVarGeneric(VarGeneric var) {
		cyclicVariables.put(var.getName(), var);
	}

	@Override
	public void visitArithmeticOperator(ArithmeticOperatorBinary exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitLogicalOperatorBinary(LogicalOperatorBinary exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitRelationalOperator(RelationalOperatorBinary exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
	}

	@Override
	public void visitLogicalOperatorUnary(LogicalOperatorUnary exp) {
		exp.getLhs().accept(this);
	}

}
