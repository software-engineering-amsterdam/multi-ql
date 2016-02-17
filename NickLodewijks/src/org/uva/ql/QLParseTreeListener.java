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
import org.uva.ql.parser.antlr.QLParser.QuestionnaireContext;
import org.uva.ql.parser.antlr.QLParser.RelExprContext;
import org.uva.ql.parser.antlr.QLParser.UnExprContext;
import org.uva.ql.parser.antlr.QLParser.VariableContext;
import org.uva.ql.parser.antlr.QLParser.VariableTypeContext;

public class QLParseTreeListener implements QLListener {

	@Override
	public void visitTerminal(TerminalNode node) {
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
	}

	@Override
	public void enterFile(FileContext ctx) {
	}

	@Override
	public void exitFile(FileContext ctx) {
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
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterIfStat(IfStatContext ctx) {
	}

	@Override
	public void exitIfStat(IfStatContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterQuestion(QuestionContext ctx) {
	}

	@Override
	public void exitQuestion(QuestionContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterVariable(VariableContext ctx) {
	}

	@Override
	public void exitVariable(VariableContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterVariableType(VariableTypeContext ctx) {
	}

	@Override
	public void exitVariableType(VariableTypeContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterAddExpr(AddExprContext ctx) {
	}

	@Override
	public void exitAddExpr(AddExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterMulExpr(MulExprContext ctx) {
	}

	@Override
	public void exitMulExpr(MulExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterUnExpr(UnExprContext ctx) {
	}

	@Override
	public void exitUnExpr(UnExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterPrimary(PrimaryContext ctx) {
	}

	@Override
	public void exitPrimary(PrimaryContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterIdentifier(IdentifierContext ctx) {
	}

	@Override
	public void exitIdentifier(IdentifierContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterLiteral(LiteralContext ctx) {
	}

	@Override
	public void exitLiteral(LiteralContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterOrExpr(OrExprContext ctx) {
	}

	@Override
	public void exitOrExpr(OrExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterAndExpr(AndExprContext ctx) {
	}

	@Override
	public void exitAndExpr(AndExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterRelExpr(RelExprContext ctx) {
	}

	@Override
	public void exitRelExpr(RelExprContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}

	@Override
	public void enterQuestionnaire(QuestionnaireContext ctx) {
	}

	@Override
	public void exitQuestionnaire(QuestionnaireContext ctx) {
		ctx.result.setToken(ctx.getStart());
	}
}
