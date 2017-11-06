package ql2;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import ql2.parser.generated.Ql2Parser.*;
import ql2.parser.generated.Ql2ParserBaseListener;

public class Ql2Walker extends Ql2ParserBaseListener {
	
	private boolean print = false;
	
	public Ql2Walker() {}
	
	public Ql2Walker(boolean print) {
		this.print = print;
	}

	@Override
	public void enterQuestionnaire(QuestionnaireContext ctx) {
		debugOutput("Entering Questionnaire:");
		super.enterQuestionnaire(ctx);
	}

	@Override
	public void exitQuestionnaire(QuestionnaireContext ctx) {
		debugOutput("Leaving Questionnaire");
		super.exitQuestionnaire(ctx);
	}

	@Override
	public void enterForms(FormsContext ctx) {
		debugOutput("Enter forms");
		super.enterForms(ctx);
	}

	@Override
	public void exitForms(FormsContext ctx) {
		debugOutput("Leaving forms");
		super.exitForms(ctx);
	}

	@Override
	public void enterForm(FormContext ctx) {
		debugOutput("Entering Form:" + ctx.formname.result);
		super.enterForm(ctx);
	}

	@Override
	public void exitForm(FormContext ctx) {
		debugOutput("Leaving Form:" + ctx.formname.result);
		super.exitForm(ctx);
	}

	@Override
	public void enterFormname(FormnameContext ctx) {
		debugOutput("Entering form name");
		super.enterFormname(ctx);
	}

	@Override
	public void exitFormname(FormnameContext ctx) {
		super.exitFormname(ctx);
	}

	@Override
	public void enterBlock(BlockContext ctx) {
		debugOutput("Entering block:");
		if (ctx.result != null) {
			debugOutput("Block contains: " + ctx.result.getQuestionsList().size() + "Q and " + ctx.result.getStatementsList().size()+ " Statements");
		}else {
			debugOutput("Block is Null");
		}
		super.enterBlock(ctx);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		debugOutput("Leaving block");
		super.exitBlock(ctx);
	}

	@Override
	public void enterStatement(StatementContext ctx) {
		debugOutput("Entering statement");
		super.enterStatement(ctx);
	}

	@Override
	public void exitStatement(StatementContext ctx) {
		super.exitStatement(ctx);
	}

	@Override
	public void enterConditions(ConditionsContext ctx) {
		debugOutput("entering conditions");
		super.enterConditions(ctx);
	}

	@Override
	public void exitConditions(ConditionsContext ctx) {
		super.exitConditions(ctx);
	}

	@Override
	public void enterCondition(ConditionContext ctx) {
		debugOutput("entering cond");
		super.enterCondition(ctx);
	}

	@Override
	public void exitCondition(ConditionContext ctx) {
		debugOutput("Leaving condition");
		super.exitCondition(ctx);
	}

	@Override
	public void enterIfstatement(IfstatementContext ctx) {
		debugOutput("Entering Ifstatement");
		super.enterIfstatement(ctx);
	}

	@Override
	public void exitIfstatement(IfstatementContext ctx) {
		
		debugOutput("Leaving Ifstatement");
		super.exitIfstatement(ctx);
	}

	@Override
	public void enterIfelsestatement(IfelsestatementContext ctx) {
		debugOutput("Entering IfElseStatement");
		super.enterIfelsestatement(ctx);
	}

	@Override
	public void exitIfelsestatement(IfelsestatementContext ctx) {
		super.exitIfelsestatement(ctx);
	}

	@Override
	public void enterIfelseifstatement(IfelseifstatementContext ctx) {
		debugOutput("Entering IfElseIfstatement");
		super.enterIfelseifstatement(ctx);
	}

	@Override
	public void exitIfelseifstatement(IfelseifstatementContext ctx) {
		super.exitIfelseifstatement(ctx);
	}

	@Override
	public void enterQuestion(QuestionContext ctx) {
		debugOutput("Entering Question");
		super.enterQuestion(ctx);
	}

	@Override
	public void exitQuestion(QuestionContext ctx) {
		debugOutput("Exiting Question");
		super.exitQuestion(ctx);
	}

	@Override
	public void enterInputquestion(InputquestionContext ctx) {
		debugOutput("Entering inputQuestion");
		debugOutput("Name: " + ctx.qname.result +  " txt: " + ctx.qtext.result);
		super.enterInputquestion(ctx);
	}

	@Override
	public void exitInputquestion(InputquestionContext ctx) {
		debugOutput("leaving inputquestion");
		super.exitInputquestion(ctx);
	}

	@Override
	public void enterCalculatedquestion(CalculatedquestionContext ctx) {
		debugOutput("Entering CalculatedQuestion");
		debugOutput(ctx.q.qname.result + ctx.q.qtext.result);
		super.enterCalculatedquestion(ctx);
	}

	@Override
	public void exitCalculatedquestion(CalculatedquestionContext ctx) {
		debugOutput("leaving CalculatedQuestion");
		super.exitCalculatedquestion(ctx);
	}

	@Override
	public void enterQuestiontext(QuestiontextContext ctx) {		
		super.enterQuestiontext(ctx);
	}

	@Override
	public void exitQuestiontext(QuestiontextContext ctx) {
		super.exitQuestiontext(ctx);
	}

	@Override
	public void enterQuestionname(QuestionnameContext ctx) {
		super.enterQuestionname(ctx);
	}

	@Override
	public void exitQuestionname(QuestionnameContext ctx) {
		super.exitQuestionname(ctx);
	}

	@Override
	public void enterQuestiontype(QuestiontypeContext ctx) {
		super.enterQuestiontype(ctx);
	}

	@Override
	public void enterAddExpr(AddExprContext ctx) {
		debugOutput("AddExpr");
		super.enterAddExpr(ctx);
	}

	@Override
	public void exitAddExpr(AddExprContext ctx) {
		super.exitAddExpr(ctx);
	}

	@Override
	public void exitQuestiontype(QuestiontypeContext ctx) {
		super.exitQuestiontype(ctx);
	}

	@Override
	public void enterConditionsplaceholder(ConditionsplaceholderContext ctx) {
		super.enterConditionsplaceholder(ctx);
	}

	@Override
	public void exitConditionsplaceholder(ConditionsplaceholderContext ctx) {
		super.exitConditionsplaceholder(ctx);
	}

	@Override
	public void enterOrExpr(OrExprContext ctx) {
		debugOutput("Entering OrExpr");

		super.enterOrExpr(ctx);
	}

	@Override
	public void exitOrExpr(OrExprContext ctx) {
		super.exitOrExpr(ctx);
	}

	@Override
	public void enterAndExpr(AndExprContext ctx) {
		debugOutput("Entering AndExpr");
		super.enterAndExpr(ctx);
	}

	@Override
	public void exitAndExpr(AndExprContext ctx) {
		super.exitAndExpr(ctx);
	}

	@Override
	public void enterRelExpr(RelExprContext ctx) {
		debugOutput("Entering RelExpr");
		super.enterRelExpr(ctx);
	}

	@Override
	public void exitRelExpr(RelExprContext ctx) {
		super.exitRelExpr(ctx);
	}

	@Override
	public void enterMulExpr(MulExprContext ctx) {
		debugOutput("Entering MulExpr");
		super.enterMulExpr(ctx);
	}

	@Override
	public void exitMulExpr(MulExprContext ctx) {
		super.exitMulExpr(ctx);
	}

	@Override
	public void enterUnaryExpr(UnaryExprContext ctx) {
		debugOutput("Entering Unary");
		super.enterUnaryExpr(ctx);
	}

	@Override
	public void exitUnaryExpr(UnaryExprContext ctx) {
		super.exitUnaryExpr(ctx);
	}

	@Override
	public void enterBooleanliteral(BooleanliteralContext ctx) {
		super.enterBooleanliteral(ctx);
	}

	@Override
	public void exitBooleanliteral(BooleanliteralContext ctx) {
		super.exitBooleanliteral(ctx);
	}

	@Override
	public void enterIntliteral(IntliteralContext ctx) {
		super.enterIntliteral(ctx);
	}

	@Override
	public void exitIntliteral(IntliteralContext ctx) {
		super.exitIntliteral(ctx);
	}


	@Override
	public void enterValue(ValueContext ctx) {
		debugOutput("Value");
		super.enterValue(ctx);
	}

	@Override
	public void exitValue(ValueContext ctx) {
		super.exitValue(ctx);
	}

	@Override
	public void enterName(NameContext ctx) {
		debugOutput("Name");
		super.enterName(ctx);
	}

	@Override
	public void exitName(NameContext ctx) {
		super.exitName(ctx);
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		super.enterEveryRule(ctx);
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		super.exitEveryRule(ctx);
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		super.visitTerminal(node);
	}
	
	@Override
	public void enterLiteral(LiteralContext ctx) {
		super.enterLiteral(ctx);
	}

	@Override
	public void exitLiteral(LiteralContext ctx) {
		super.exitLiteral(ctx);
	}

	@Override
	public void enterCurrencyliteral(CurrencyliteralContext ctx) {
		super.enterCurrencyliteral(ctx);
	}

	@Override
	public void exitCurrencyliteral(CurrencyliteralContext ctx) {
		super.exitCurrencyliteral(ctx);
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		System.out.println("!!!Error: " + node.getText());
		super.visitErrorNode(node);
	}
	
	public void debugOutput(String msg) {
		//output into console. Quite verbose. 
		if(this.print) {
			System.out.println(msg);
		}
	}

}
