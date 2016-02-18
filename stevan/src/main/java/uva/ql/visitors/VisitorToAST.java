package uva.ql.visitors;

import org.antlr.v4.runtime.misc.NotNull;

import uva.ql.antlr4.QLBaseVisitor;
import uva.ql.antlr4.QLParser;
import uva.ql.ast.AST;
import uva.ql.ast.ASTBlock;
import uva.ql.ast.ASTExpression;
import uva.ql.ast.ASTForm;
import uva.ql.ast.ASTIfStatement;
import uva.ql.ast.ASTQuestion;
import uva.ql.ast.ASTVariable;

public class VisitorToAST extends QLBaseVisitor<Object> {

	ASTForm form = AST.newForm();

	@Override
	public ASTForm visitForm( @NotNull QLParser.FormContext ctx ) {
		
		form.setName(ctx.varName().getText());
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			if (ctx.getChild(i) == ctx.block()) {
				
				ASTBlock block = AST.newBlock();
				
				block = (ASTBlock) ctx.getChild(i).accept(this);
				block.setParent(form);
				
				form.addChild(block);
			}
		}
		
		return form;
	}
	
	@Override
	public ASTBlock visitBlock( @NotNull QLParser.BlockContext ctx ) {
		
		ASTBlock block = AST.newBlock();
		
		for (int i=0; i<ctx.question().size(); i++) {
			
			ASTQuestion question = AST.newQuestion();
			
			question = (ASTQuestion) ctx.question(i).accept(this);
			question.setParent(block);
			
			block.addChild(question);
		}
		
		for (int i=0; i<ctx.condition().size(); i++) {
			
			// Assuming that its an IfStatment... make an ASTCondition node?!
			ASTIfStatement ifStmnt = AST.newIfStatement();
			
			ifStmnt = (ASTIfStatement) ctx.condition(i).accept(this);
			ifStmnt.setParent(block);
			
			block.addChild(ifStmnt);
		}
		
		return block;
	}
	
	@Override
	public ASTIfStatement visitIfCondition( @NotNull QLParser.IfConditionContext ctx ) {
		
		ASTIfStatement ifStmnt = AST.newIfStatement();
		
		for (int i=0; i<ctx.expression().size(); i++) {
			
			ASTExpression exp = AST.newExpression();
			
			exp = (ASTExpression) ctx.expression(i).accept(this);
			exp.setParent(ifStmnt);
			
			ifStmnt.setExpression(exp);
		}
		
		for (int i=0; i<ctx.block().size(); i++) {
			
			ASTBlock block = AST.newBlock();
			
			block = (ASTBlock) ctx.block(i).accept(this);
			block.setParent(ifStmnt);
			
			ifStmnt.addChild(block);
		}

		return ifStmnt;
	}
	
	@Override 
	public ASTQuestion visitQuestion( @NotNull QLParser.QuestionContext ctx) {
		
		ASTQuestion question = AST.newQuestion();
		ASTVariable var = AST.newVariable();
		
		question.setLabel(ctx.label().getText().substring(1, ctx.label().getText().length()-1));
		question.addChild(var);
		
		var.setName(ctx.varName().getText());
		var.setType(ctx.varType().getText());
		var.setParent(question);
		
		for (int i=0; i<ctx.expression().size(); i++) {
			
			ASTExpression exp = AST.newExpression();
			
			exp = (ASTExpression) ctx.expression(i).accept(this);
			exp.setParent(question);
			
			question.setExpression(exp);
			question.setComputed(true);
		}

		return question;
	}
	
	@Override
	public ASTExpression visitExpParentheses( @NotNull QLParser.ExpParenthesesContext ctx ) {
		
		ASTExpression expPar = AST.newExpression();
		ASTExpression exp = AST.newExpression();
		
		exp = (ASTExpression) ctx.expression().accept(this);
		exp.setParent(expPar);
		
		expPar.setLeftNode(exp);

		return exp;
	}
	
	@Override
	public ASTExpression visitExpVar( @NotNull QLParser.ExpVarContext ctx ) {
		
		ASTExpression exp = AST.newExpression();
		ASTVariable var = AST.newVariable();
		
		var.setName(ctx.varName().getText());
		var.setParent(exp);
		
		exp.setLeftNode(var);

		return exp;
	}
	
	@Override
	public ASTExpression visitExpPlusMinus( @NotNull QLParser.ExpPlusMinusContext ctx ) {

		ASTExpression exp = AST.newExpression();
		ASTExpression leftExp = (ASTExpression) ctx.expression(0).accept(this);
		ASTExpression rightExp = (ASTExpression) ctx.expression(1).accept(this);
		
		leftExp.setParent(exp);
		rightExp.setParent(exp);
		
		exp.setLeftNode(leftExp);
		exp.setRightNode(rightExp);
		
		return exp;
	}

	
	/*	@Override
	public ASTExpression visitExpEquality( @NotNull QLParser.ExpEqualityContext ctx ) {
		
		ASTExpression exp = AST.newExpression();
		
		System.out.println("ExpEqu: " + ctx.getText() + " - " + ctx.expression().size());
		
		return exp;
	}
	
	@Override
	public ASTVariable visitVarName( @NotNull QLParser.VarNameContext ctx ) {
		
		varNode = AST.newVariable();
		varNode.setName(ctx.getText());
		//System.out.println("Create Variable Name - " + ctx.getText());
		//System.out.println(ctx.getChildCount());
		//System.out.println(ctx.getChild(0).getText());
		
		return varNode;
	}

	@Override
	public ASTVariable visitVarType( @NotNull QLParser.VarTypeContext ctx ) {
		
		varNode = AST.newVariable();
		//System.out.println("Create Variable - " + ctx.getText());
		//System.out.println(ctx.getChildCount());
		//System.out.println(ctx.getChild(0).getText());

		return varNode;
	}
	
	@Override
	public ASTExpression visitExpNum( @NotNull QLParser.ExpNumContext ctx ) {
		return exp;
	}
	
	@Override
	public ASTExpression visitExpNot( @NotNull QLParser.ExpNotContext ctx ) {
		return exp;
	}
	
	@Override
	public ASTExpression visitExpMultDivide( @NotNull QLParser.ExpMultDivideContext ctx ) {
		return exp;
	}
	
	@Override
	public ASTExpression visitExpAndOr( @NotNull QLParser.ExpAndOrContext ctx ) {
		return exp;
	}
	*/
	
}
