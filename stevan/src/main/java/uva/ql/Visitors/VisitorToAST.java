package uva.ql.Visitors;

import org.antlr.v4.runtime.misc.NotNull;
import uva.ql.antlr4.QLBaseVisitor;
import uva.ql.antlr4.QLParser;
import uva.ql.antlr4.QLParser.BlockContext;
import uva.ql.antlr4.QLParser.ExpressionContext;
import uva.ql.antlr4.QLParser.VarTypeContext;
import uva.ql.ast.AST;
import uva.ql.ast.ASTBlock;
import uva.ql.ast.ASTExpression;
import uva.ql.ast.ASTForm;
import uva.ql.ast.ASTIfStatement;
import uva.ql.ast.ASTQuestion;
import uva.ql.ast.ASTVariable;

public class VisitorToAST extends QLBaseVisitor<Object> {

	@Override
	public ASTForm visitForm( @NotNull QLParser.FormContext ctx ) {
		
		ASTForm form = AST.newForm();
		form.setName(ctx.varName().getText());
		System.out.println("Create Form");
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			System.out.println("\tVisit children of Form: " + ctx.getChild(i).getText());
			ctx.getChild(i).accept(this);
			/*
			BlockContext blockCTX = (BlockContext) ctx.getChild(i);
			visitBlock( blockCTX );
			*/
		}
		
		return form;
	}
	
	@Override
	public ASTBlock visitBlock( @NotNull QLParser.BlockContext ctx ) {
		
		ASTBlock block = AST.newBlock();
		System.out.println("Create Block");
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			System.out.println("\tVisit children of Block: " + ctx.getChild(i).getText());
			ctx.getChild(i).accept(this);
			
			/*
			try {
				Object c = ctx.accept(this);
				IfConditionContext ifCTX = (IfConditionContext) ctx.getChild(i);
				ASTIfStatement ifStatement = AST.newIfStatement();
				ifStatement.setParent(block);
				block.addChild(ifStatement);
				
				visitIfCondition(ifCTX, ifStatement);
			} catch (ClassCastException e) {}
			try {
				QuestionContext questionCTX = (QuestionContext) ctx.getChild(i);
				ASTQuestion question = AST.newQuestion();
				question.setParent(block);
				
				block.addChild( visitQuestion(questionCTX, question) );
			} catch (ClassCastException e) {}
			*/
		}
		//System.out.println("EndBlockVisit");
		
		return block;
	}
	
	@Override 
	public ASTQuestion visitQuestion( @NotNull QLParser.QuestionContext ctx) {
		
		System.out.println("Create Question");
		
		ASTQuestion question = AST.newQuestion();
		question.setLabel(ctx.label().getText().substring(1, ctx.label().getText().length()-1));
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			System.out.println("\tVisit children of Question: " + ctx.getChild(i).getText());
			ctx.getChild(i).accept(this);
		}
		/*
		System.out.println(ctx.getChildCount());
		
		ASTExpression expression = AST.newExpression();
		expression.setParent(question);
		question.setExpression(expression);
		
		ASTVariable variable = AST.newVariable();
		variable.setParent(expression);
		variable.setName(ctx.varName().getText());
		visitVarType( (VarTypeContext) ctx.varType() );
		
		expression.setLeftNode(variable);
		*/
		/*
		if (ctx.ASSIGN() != null) {
			//expression.setExpressionType(ASTExpression.EXP);
			expression.setExpressionType(ASTExpression.ASSIGN_EXP);
			//question.setComputed(true);
			//System.out.println(ctx.expression().size());
			for (int i=0; i<ctx.expression().size(); i++) {
				//System.out.println(ctx.expression(i).getText());
				ASTExpression rightNodeExp = AST.newExpression();
				rightNodeExp.setParent(expression);
				expression.setRightNode(rightNodeExp);
				visitExpression( (ExpressionContext) ctx.expression(i), rightNodeExp );
			}
		}
		else {
			expression.setExpressionType(ASTExpression.SINGLE_EXP);
		}
		*/
		return question;
	}
	
	@Override
	public ASTVariable visitVarName( @NotNull QLParser.VarNameContext ctx ) {
		
		ASTVariable varNode = AST.newVariable();
		System.out.println("Create Variable Name - " + ctx.getText());
		System.out.println(ctx.getChildCount());
		System.out.println(ctx.getChild(0).getText());
		
		return varNode;
	}

	@Override
	public ASTVariable visitVarType( @NotNull QLParser.VarTypeContext ctx ) {
		
		ASTVariable varNode = AST.newVariable();
		System.out.println("Create Variable - " + ctx.getText());
		System.out.println(ctx.getChildCount());
		System.out.println(ctx.getChild(0).getText());
		/*
		if (varType.BOOLEAN() != null) {
			variableNode.setType(ASTVariable.BOOLEAN);
		} else if (varType.INT() != null) {
			variableNode.setType(ASTVariable.INT);
		} else if (varType.MONEY() != null) {
			variableNode.setType(ASTVariable.MONEY);
		} else if (varType.STRING() != null) {
			variableNode.setType(ASTVariable.STRING);
		} else if (varType.DATE() != null) {
			variableNode.setType(ASTVariable.DATE);
		}
		*/
		return varNode;
	}
	
	@Override
	public ASTExpression visitExpression( @NotNull QLParser.ExpressionContext ctx ) {
		
		ASTExpression exp = AST.newExpression();
		System.out.println("Create Expression - " + ctx.getText());
		
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			System.out.println("\tVisit children of Expression: " + ctx.getChild(i).getText());
			ctx.getChild(i).accept(this);
		}
		/*
		ArrayList<Object> infixList = ContextUtils.expressionToInfix(ctx, null);
		ShuntingYardAlgorithm.infixToAST(infixList, exp);
		*/
		//System.out.println(ShuntingYardAlgorithm.astToPostfix(exp));
		return exp;
	}
	
	@Override
	public ASTIfStatement visitIfCondition( @NotNull QLParser.IfConditionContext ctx ) {
		
		ASTIfStatement ifStmnt = AST.newIfStatement();
		System.out.println("Create IfStatement - " + ctx.getText());
		
		//System.out.println("StartVisit IfCondition");
		for (int i=0; i<ctx.getChildCount(); i++) {
			
			System.out.println("\tVisit children of IfStatement: " + ctx.getChild(i).getText());
			ctx.getChild(i).accept(this);
			
			//System.out.println(i);
			
			/*
			try {
				ExpressionContext expressionCTX = (ExpressionContext) ctx.getChild(i);
				ASTExpression exp = AST.newExpression();
				exp.setParent(ifStmnt);
				ifStmnt.setExpression(exp);
				
				visitExpression(expressionCTX);
				//System.out.println(ctx.getChild(i).getText());
			} catch (ClassCastException e) {}

			try {
				if (ifStmnt.getLeftNode() == null) {
					BlockContext blockCTX = (BlockContext) ctx.getChild(i);
					ASTBlock block = AST.newBlock();
					block.setParent(ifStmnt);
					ifStmnt.setLeftNode(block);
					
					visitBlock(blockCTX);
				} else {
					BlockContext blockCTX = (BlockContext) ctx.getChild(i);
					ASTBlock block = AST.newBlock();
					block.setParent(ifStmnt);
					ifStmnt.setRightNode(block);
					
					visitBlock(blockCTX);
				}
				//System.out.println(ctx.getChild(i).getText());
			} catch (ClassCastException e) {}
			*/
		}
		//System.out.println("EndVisit IfCondition");
		return ifStmnt;
	}
}
