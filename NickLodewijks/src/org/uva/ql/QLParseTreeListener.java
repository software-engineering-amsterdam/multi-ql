package org.uva.ql;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.uva.ql.parser.antlr.QLListener;
import org.uva.ql.parser.antlr.QLParser.AddExprContext;
import org.uva.ql.parser.antlr.QLParser.AndExprContext;
import org.uva.ql.parser.antlr.QLParser.BlockContext;
import org.uva.ql.parser.antlr.QLParser.FileContext;
import org.uva.ql.parser.antlr.QLParser.FormContext;
import org.uva.ql.parser.antlr.QLParser.IdentifierContext;
import org.uva.ql.parser.antlr.QLParser.IfStatContext;
import org.uva.ql.parser.antlr.QLParser.LiteralContext;
import org.uva.ql.parser.antlr.QLParser.MulExprContext;
import org.uva.ql.parser.antlr.QLParser.OrExprContext;
import org.uva.ql.parser.antlr.QLParser.PrimaryContext;
import org.uva.ql.parser.antlr.QLParser.QuestionContext;
import org.uva.ql.parser.antlr.QLParser.RelExprContext;
import org.uva.ql.parser.antlr.QLParser.UnExprContext;
import org.uva.ql.parser.antlr.QLParser.VariableContext;
import org.uva.ql.parser.antlr.QLParser.VariableTypeContext;

public class QLParseTreeListener implements QLListener {

	@Override
	public void visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterFile(FileContext ctx) {
	}

	@Override
	public void exitFile(FileContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterForm(FormContext ctx) {

	}

	@Override
	public void exitForm(FormContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterBlock(BlockContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitBlock(BlockContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterIfStat(IfStatContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitIfStat(IfStatContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterQuestion(QuestionContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitQuestion(QuestionContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterVariable(VariableContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitVariable(VariableContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterVariableType(VariableTypeContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitVariableType(VariableTypeContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterAddExpr(AddExprContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitAddExpr(AddExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterMulExpr(MulExprContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitMulExpr(MulExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterUnExpr(UnExprContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitUnExpr(UnExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterPrimary(PrimaryContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitPrimary(PrimaryContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterIdentifier(IdentifierContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitIdentifier(IdentifierContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterLiteral(LiteralContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitLiteral(LiteralContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterOrExpr(OrExprContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitOrExpr(OrExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterAndExpr(AndExprContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitAndExpr(AndExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterRelExpr(RelExprContext ctx) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exitRelExpr(RelExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}
}
