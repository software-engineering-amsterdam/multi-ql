package uva.ql.Visitors;

import uva.ql.ast.ASTBlock;
import uva.ql.ast.ASTExpression;
import uva.ql.ast.ASTForm;
import uva.ql.ast.ASTIfStatement;
import uva.ql.ast.ASTNode;
import uva.ql.ast.ASTNumber;
import uva.ql.ast.ASTQuestion;
import uva.ql.ast.ASTVariable;
import uva.ql.interfaces.IASTNodeVisitor;

public class ASTTreePrintVisitor implements IASTNodeVisitor {

	@Override
	public void visitForm(ASTForm form) {
		System.out.println("form: " + form.getName());
		if (form.size() > 0) {
			form.get(0).accept(this);
		}
	}

	@Override
	public void visitNode(ASTNode node) {
		System.out.println("Node: " + node.getNodeType());
	}

	@Override
	public void visitBlock(ASTBlock block) {
		System.out.println("block: " + block.getNodeType());
		System.out.println("block.size(): " + block.size());
		for(int i=0; i<block.size(); i++) {
			block.get(i).accept(this);
		}
	}

	@Override
	public void visitIfStmnt(ASTIfStatement ifStmnt) {
		System.out.println("ifStmnt: " + ifStmnt.getNodeType());
	}

	@Override
	public void visitQuestion(ASTQuestion question) {
		System.out.println("question: " + question.getNodeType());
		
		for(int i=0; i<question.size(); i++) {
			question.get(i).accept(this);
		}
	}

	@Override
	public void visitExp(ASTExpression exp) {
		System.out.println("exp: " + exp.getNodeType());
	}

	@Override
	public void visitNum(ASTNumber number) {
		System.out.println("number: " + number.getNodeType());
	}

	@Override
	public void visitVar(ASTVariable var) {
		System.out.println("var: " + var.getNodeType());
	}

}
