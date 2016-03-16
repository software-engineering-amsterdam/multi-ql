package uva.ql.typechecker;

import uva.ql.ast.Block;
import uva.ql.ast.EnumType;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.CondIfElseStatement;
import uva.ql.ast.conditionals.CondIfStatement;
import uva.ql.ast.expressions.abstracts.LogicalOperatorBinary;
import uva.ql.ast.expressions.abstracts.RelationalOperatorBinary;
import uva.ql.ast.expressions.abstracts.LogicalOperatorUnary;
import uva.ql.typechecker.abstracts.AbstractTypeChecker;
import uva.ql.typechecker.errors.ErrorCondition;
import uva.ql.typechecker.visitors.IBinaryOperatorVisitor;

public class BooleanOperators extends AbstractTypeChecker implements IBinaryOperatorVisitor {
		
	@Override
	public void visitForm(Form form) {
		
		for(int i=0; i<form.size(); i++) {
			
			Block block = (Block) form.get(i);
			block.accept(this);
		}
	}

	@Override
	public void visitBlock(Block block) {

		for(int i=0; i<block.size(); i++) {
			
			block.get(i).accept(this);
		}
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
	public void visitLogicalOperator(LogicalOperatorBinary exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
		
		if(!exp.evalType().equals(EnumType.BOOLEAN)) {
			errorMessages.add(new ErrorCondition(exp.getType().toString(), exp.getLine(), exp.getColumn()));
		}
	}

	@Override
	public void visitRelationalOperator(RelationalOperatorBinary exp) {
		exp.getLhs().accept(this);
		exp.getRhs().accept(this);
		
		if(!exp.evalType().equals(EnumType.BOOLEAN)) {
			errorMessages.add(new ErrorCondition(exp.getType().toString(), exp.getLine(), exp.getColumn()));
		}
	}

	@Override
	public void visitSingleLogicalOperator(LogicalOperatorUnary exp) {
		exp.getLhs().accept(this);
		
		if(!exp.evalType().equals(EnumType.BOOLEAN)) {
			errorMessages.add(new ErrorCondition(exp.getType().toString(), exp.getLine(), exp.getColumn()));
		}
	}
}
