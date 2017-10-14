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
		// TODO Auto-generated method stub
		debugOutput("Enter forms");
		super.enterForms(ctx);
	}

	@Override
	public void exitForms(FormsContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Leaving forms");
		super.exitForms(ctx);
	}

	@Override
	public void enterForm(FormContext ctx) {
		debugOutput("Entering Form:" + ctx.formname.result);
		// TODO Auto-generated method stub
		super.enterForm(ctx);
	}

	@Override
	public void exitForm(FormContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Leaving Form:" + ctx.formname.result);
		super.exitForm(ctx);
	}

	@Override
	public void enterFormname(FormnameContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering form name");
		super.enterFormname(ctx);
	}

	@Override
	public void exitFormname(FormnameContext ctx) {
		// TODO Auto-generated method stub
		super.exitFormname(ctx);
	}

	@Override
	public void enterBlock(BlockContext ctx) {
		// TODO Auto-generated method stub
	//	debugOutput("ENTERING block: " + ctx.result.hashCode());
		debugOutput("ENTERING block:");
		if (ctx.result != null) {
			debugOutput("Block contains: " + ctx.result.getQuestionsList().size() + "Q and " + ctx.result.getStatementsList().size()+ " Statements");
		}else {
			debugOutput("Block is Null");
		}
		super.enterBlock(ctx);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Leaving block");
		super.exitBlock(ctx);
	}

	@Override
	public void enterStatement(StatementContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering statement");
		super.enterStatement(ctx);
	}

	@Override
	public void exitStatement(StatementContext ctx) {
		// TODO Auto-generated method stub
		super.exitStatement(ctx);
	}

	@Override
	public void enterConditions(ConditionsContext ctx) {
		debugOutput("entering conditions");

		// TODO Auto-generated method stub
		super.enterConditions(ctx);
	}

	@Override
	public void exitConditions(ConditionsContext ctx) {
		// TODO Auto-generated method stub
		super.exitConditions(ctx);
	}

	@Override
	public void enterCondition(ConditionContext ctx) {
		debugOutput("entering cond");

		// TODO Auto-generated method stub
		super.enterCondition(ctx);
	}

	@Override
	public void exitCondition(ConditionContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Leaving condition");
		super.exitCondition(ctx);
	}

	@Override
	public void enterIfstatement(IfstatementContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering Ifstatement");
		super.enterIfstatement(ctx);
	}

	@Override
	public void exitIfstatement(IfstatementContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Leaving Ifstatement");
		super.exitIfstatement(ctx);
	}

	@Override
	public void enterIfelsestatement(IfelsestatementContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering IfElseStatement");

		super.enterIfelsestatement(ctx);
	}

	@Override
	public void exitIfelsestatement(IfelsestatementContext ctx) {
		// TODO Auto-generated method stub
		super.exitIfelsestatement(ctx);
	}

	@Override
	public void enterIfelseifstatement(IfelseifstatementContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering IfElseIfstatement");
		super.enterIfelseifstatement(ctx);
	}

	@Override
	public void exitIfelseifstatement(IfelseifstatementContext ctx) {
		// TODO Auto-generated method stub
		super.exitIfelseifstatement(ctx);
	}

	@Override
	public void enterQuestion(QuestionContext ctx) {
		debugOutput("Entering Question");
		// TODO Auto-generated method stub
		super.enterQuestion(ctx);
	}

	@Override
	public void exitQuestion(QuestionContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Exiting Question");

		super.exitQuestion(ctx);
	}

	@Override
	public void enterInputquestion(InputquestionContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering inputQuestion");
		debugOutput("Name: " + ctx.qname.result +  " txt: " + ctx.qtext.result);
		super.enterInputquestion(ctx);
	}

	@Override
	public void exitInputquestion(InputquestionContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("leaving inputquestion");

		super.exitInputquestion(ctx);
	}

	@Override
	public void enterCalculatedquestion(CalculatedquestionContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering CalculatedQuestion");
		debugOutput(ctx.q.qname.result + ctx.q.qtext.result);

		super.enterCalculatedquestion(ctx);
	}

	@Override
	public void exitCalculatedquestion(CalculatedquestionContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("leaving CalculatedQuestion");
		super.exitCalculatedquestion(ctx);
	}

	@Override
	public void enterQuestiontext(QuestiontextContext ctx) {
		//debugOutput("Qtext");
		// TODO Auto-generated method stub
		super.enterQuestiontext(ctx);
	}

	@Override
	public void exitQuestiontext(QuestiontextContext ctx) {
		// TODO Auto-generated method stub
		super.exitQuestiontext(ctx);
	}

	@Override
	public void enterQuestionname(QuestionnameContext ctx) {
		// TODO Auto-generated method stub
		super.enterQuestionname(ctx);
	}

	@Override
	public void exitQuestionname(QuestionnameContext ctx) {
		// TODO Auto-generated method stub
		super.exitQuestionname(ctx);
	}

	@Override
	public void enterQuestiontype(QuestiontypeContext ctx) {
		// TODO Auto-generated method stub
		//debugOutput("Type");
		super.enterQuestiontype(ctx);
	}

	@Override
	public void enterAddExpr(AddExprContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("AddExpr");
		super.enterAddExpr(ctx);
	}

	@Override
	public void exitAddExpr(AddExprContext ctx) {
		// TODO Auto-generated method stub
		super.exitAddExpr(ctx);
	}

	@Override
	public void exitQuestiontype(QuestiontypeContext ctx) {
		// TODO Auto-generated method stub
		super.exitQuestiontype(ctx);
	}

	@Override
	public void enterConditionsplaceholder(ConditionsplaceholderContext ctx) {
		// TODO Auto-generated method stub
		super.enterConditionsplaceholder(ctx);
	}

	@Override
	public void exitConditionsplaceholder(ConditionsplaceholderContext ctx) {
		// TODO Auto-generated method stub
		super.exitConditionsplaceholder(ctx);
	}

	@Override
	public void enterOrExpr(OrExprContext ctx) {
		debugOutput("Entering OrExpr");

		// TODO Auto-generated method stub
		super.enterOrExpr(ctx);
	}

	@Override
	public void exitOrExpr(OrExprContext ctx) {
		// TODO Auto-generated method stub
		super.exitOrExpr(ctx);
	}

	@Override
	public void enterAndExpr(AndExprContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering AndExpr");

		super.enterAndExpr(ctx);
	}

	@Override
	public void exitAndExpr(AndExprContext ctx) {
		// TODO Auto-generated method stub
		super.exitAndExpr(ctx);
	}

	@Override
	public void enterRelExpr(RelExprContext ctx) {
		// TODO Auto-generated method stub]		
		debugOutput("Entering RelExpr");

		super.enterRelExpr(ctx);
	}

	@Override
	public void exitRelExpr(RelExprContext ctx) {
		// TODO Auto-generated method stub
		super.exitRelExpr(ctx);
	}

	@Override
	public void enterMulExpr(MulExprContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering MulExpr");
		super.enterMulExpr(ctx);
	}

	@Override
	public void exitMulExpr(MulExprContext ctx) {
		// TODO Auto-generated method stub
		super.exitMulExpr(ctx);
	}

	@Override
	public void enterUnaryExpr(UnaryExprContext ctx) {
		// TODO Auto-generated method stub
		debugOutput("Entering Unary");
		super.enterUnaryExpr(ctx);
	}

	@Override
	public void exitUnaryExpr(UnaryExprContext ctx) {
		// TODO Auto-generated method stub
		super.exitUnaryExpr(ctx);
	}

	@Override
	public void enterBooleanliteral(BooleanliteralContext ctx) {
		// TODO Auto-generated method stub
		super.enterBooleanliteral(ctx);
	}

	@Override
	public void exitBooleanliteral(BooleanliteralContext ctx) {
		// TODO Auto-generated method stub
		super.exitBooleanliteral(ctx);
	}

	@Override
	public void enterIntliteral(IntliteralContext ctx) {
		// TODO Auto-generated method stub
		super.enterIntliteral(ctx);
	}

	@Override
	public void exitIntliteral(IntliteralContext ctx) {
		// TODO Auto-generated method stub
		super.exitIntliteral(ctx);
	}


	@Override
	public void enterValue(ValueContext ctx) {
		debugOutput("Value");
		// TODO Auto-generated method stub
		super.enterValue(ctx);
	}

	@Override
	public void exitValue(ValueContext ctx) {
		// TODO Auto-generated method stub
		super.exitValue(ctx);
	}

	@Override
	public void enterName(NameContext ctx) {
		// TODO Auto-generated method stub]
		debugOutput("Name");
		super.enterName(ctx);
	}

	@Override
	public void exitName(NameContext ctx) {
		// TODO Auto-generated method stub
		super.exitName(ctx);
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		super.enterEveryRule(ctx);
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		super.exitEveryRule(ctx);
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		//debugOutput("Terminal node: " + node.getText()); // Clogs up console.
		super.visitTerminal(node);
	}
	

	@Override
	public void enterLiteral(LiteralContext ctx) {
		// TODO Auto-generated method stub
		super.enterLiteral(ctx);
	}

	@Override
	public void exitLiteral(LiteralContext ctx) {
		// TODO Auto-generated method stub
		super.exitLiteral(ctx);
	}

	@Override
	public void enterCurrencyliteral(CurrencyliteralContext ctx) {
		// TODO Auto-generated method stub
		super.enterCurrencyliteral(ctx);
	}

	@Override
	public void exitCurrencyliteral(CurrencyliteralContext ctx) {
		// TODO Auto-generated method stub
		super.exitCurrencyliteral(ctx);
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		System.out.println("!!!Error: " + node.getText());
		super.visitErrorNode(node);
	}
	
	public void debugOutput(String msg) {
		//output into console Quite verbose. 
		if(this.print) {
			System.out.println(msg);
		}
	}

}
