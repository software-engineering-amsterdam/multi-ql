package uva.ql.visitors;

import uva.ql.ast.AExpression;
import uva.ql.ast.ANumber;
import uva.ql.ast.AVariable;
import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.IfStatement;
import uva.ql.ast.Question;
import uva.ql.ast.numbers.NumDouble;
import uva.ql.ast.numbers.NumInt;
import uva.ql.interfaces.IExpression;
import uva.ql.interfaces.INodeVisitor;
import uva.ql.interfaces.INumber;

public class ASTTreePrintVisitor implements INodeVisitor {

	@Override
	public void visitForm(Form form) {
		
		System.out.println("form: " + form.getName());
		
		if (form.size() > 0) {
			form.get(0).accept(this);
		}
	}

	@Override
	public void visitBlock(Block block) {
		
		System.out.println("block: " + block.size());

		for(int i=0; i<block.size(); i++) {
			block.get(i).accept(this);
		}
	}

	@Override
	public void visitIfStmnt(IfStatement ifStmnt) {
		
		System.out.println("If: ");
		
		if (IExpression.VARIABLE == ifStmnt.getExpression().getLeftNode().getNodeType()) {
			AVariable var = (AVariable) ifStmnt.getExpression().getLeftNode();
			System.out.println("ifStmnt: " + var.getName() + " - " + var.toString());
		}
		ifStmnt.getExpression().accept(this);
		
		for(int i=0; i<ifStmnt.size(); i++) {
			ifStmnt.get(i).accept(this);
		}
	}

	@Override
	public void visitQuestion(Question question) {
		
		System.out.println("question: " + question.getLabel());
		
		for(int i=0; i<question.size(); i++) {
			question.get(i).accept(this);
		}
	}

	@Override
	public <T> void visitExp(T expression) {
		
		AExpression exp = (AExpression) expression;
		
		System.out.println("exp: " + exp.getExprType());
		if (exp.getExprType() != IExpression.NUMBER) {
			exp.getLeftNode().accept(this);
			if (exp.getRightNode() != null) {
				exp.getRightNode().accept(this);
			}
		}
	}

	@Override
	public void visitNum(ANumber number) {
		
		if(number.getNumType() == INumber.DOUBLE) {
			NumDouble num = (NumDouble) number;
			System.out.println("number: " + num.getValue());
		}
		else {
			NumInt num = (NumInt) number;
			System.out.println("number: " + num.getValue());
		}
	}

	@Override
	public void visitVar(AVariable var) {
		
		System.out.println(var.getName());
	}
	
}
