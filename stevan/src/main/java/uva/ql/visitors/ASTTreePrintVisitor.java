package uva.ql.visitors;

import uva.ql.ast.Block;
import uva.ql.ast.Form;
import uva.ql.ast.conditionals.IfStatement;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.values.ValueDouble;
import uva.ql.ast.values.ValueInt;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.interfaces.IExpression;
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
			Variable var = (Variable) ifStmnt.getExpression().getLeftNode();
			System.out.println("ifStmnt: " + var.getName() + " - " + var.toString());
		}
		ifStmnt.getExpression().accept(this);
		
		for(int i=0; i<ifStmnt.size(); i++) {
			ifStmnt.get(i).accept(this);
		}
	}

	@Override
	public void visitQuestion(QuestionVanilla question) {
		
		System.out.println("question: " + question.getLabel());
		
		for(int i=0; i<question.size(); i++) {
			question.get(i).accept(this);
		}
	}

	@Override
	public <T> void visitExp(T expression) {
		
		Expression exp = (Expression) expression;
		
		System.out.println("exp: " + exp.getExprType());
		if (exp.getExprType() != IExpression.NUMBER) {
			exp.getLeftNode().accept(this);
			if (exp.getRightNode() != null) {
				exp.getRightNode().accept(this);
			}
		}
	}

	@Override
	public void visitNum(Values number) {
		
		if(number.getNumType() == INumber.DOUBLE) {
			ValueDouble num = (ValueDouble) number;
			System.out.println("number: " + num.getValue());
		}
		else {
			ValueInt num = (ValueInt) number;
			System.out.println("number: " + num.getValue());
		}
	}

	@Override
	public void visitVar(Variable var) {
		
		System.out.println(var.getName());
	}
	
}
