package ql2;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ql2.parser.generated.Ql2Parser.*;
import ql2.parser.generated.Ql2ParserBaseListener;

public class Ql2Walker extends Ql2ParserBaseListener {

	@Override
	public void enterQuestionnaire(QuestionnaireContext ctx) {
		System.out.println("Entering Questionnaire:");
		// TODO Auto-generated method stub
		super.enterQuestionnaire(ctx);
	}

	@Override
	public void exitQuestionnaire(QuestionnaireContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Leaving Questionnaire");
		super.exitQuestionnaire(ctx);
	}

	@Override
	public void enterForms(FormsContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Enter forms");
		super.enterForms(ctx);
	}

	@Override
	public void exitForms(FormsContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Leaving forms");
		super.exitForms(ctx);
	}

	@Override
	public void enterForm(FormContext ctx) {
		System.out.println("Entering Form:" + ctx.formname.result);
		// TODO Auto-generated method stub
		super.enterForm(ctx);
	}

	@Override
	public void exitForm(FormContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Leaving Form:" + ctx.formname.result);
		super.exitForm(ctx);
	}

	@Override
	public void enterFormname(FormnameContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Entering form name");
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
	//	System.out.println("ENTERING block: " + ctx.result.hashCode());
		System.out.println("ENTERING block:");
		if (ctx.result != null) {
			System.out.println("Block contains: " + ctx.result.getQuestionsList().size() + "Q and " + ctx.result.getStatementsList().size()+ " Statements");
		}else {
			System.out.println("Block is Null");
		}
		super.enterBlock(ctx);
	}

	@Override
	public void exitBlock(BlockContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Leaving block");
		super.exitBlock(ctx);
	}


	@Override
	public void enterStatements(StatementsContext ctx) {
		System.out.println("entering statements");
		// TODO Auto-generated method stub
		super.enterStatements(ctx);
	}

	@Override
	public void exitStatements(StatementsContext ctx) {
		// TODO Auto-generated method stub
		super.exitStatements(ctx);
	}

	@Override
	public void enterStatementz(StatementzContext ctx) {
		System.out.println("entering statementz");

		// TODO Auto-generated method stub
		super.enterStatementz(ctx);
	}

	@Override
	public void exitStatementz(StatementzContext ctx) {
		// TODO Auto-generated method stub
		super.exitStatementz(ctx);
	}

	@Override
	public void enterConditions(ConditionsContext ctx) {
		System.out.println("entering conditions");

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
		System.out.println("entering cond");

		// TODO Auto-generated method stub
		super.enterCondition(ctx);
	}

	@Override
	public void exitCondition(ConditionContext ctx) {
		// TODO Auto-generated method stub
		super.exitCondition(ctx);
	}

	@Override
	public void enterIfstatement(IfstatementContext ctx) {
		// TODO Auto-generated method stub
		super.enterIfstatement(ctx);
	}

	@Override
	public void exitIfstatement(IfstatementContext ctx) {
		// TODO Auto-generated method stub
		super.exitIfstatement(ctx);
	}

	@Override
	public void enterIfelsestatement(IfelsestatementContext ctx) {
		// TODO Auto-generated method stub
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
		super.enterIfelseifstatement(ctx);
	}

	@Override
	public void exitIfelseifstatement(IfelseifstatementContext ctx) {
		// TODO Auto-generated method stub
		super.exitIfelseifstatement(ctx);
	}

	@Override
	public void enterWhilestatement(WhilestatementContext ctx) {
		// TODO Auto-generated method stub
		super.enterWhilestatement(ctx);
	}

	@Override
	public void exitWhilestatement(WhilestatementContext ctx) {
		// TODO Auto-generated method stub
		super.exitWhilestatement(ctx);
	}

	@Override
	public void enterQuestion(QuestionContext ctx) {
		System.out.println("Entering Question");
		// TODO Auto-generated method stub
		super.enterQuestion(ctx);
	}

	@Override
	public void exitQuestion(QuestionContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Exiting Question");

		super.exitQuestion(ctx);
	}

	@Override
	public void enterInputquestion(InputquestionContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Entering inputQuestion");
		System.out.println("Name: " + ctx.qname.result +  " txt: " + ctx.qtext.result);
		super.enterInputquestion(ctx);
	}

	@Override
	public void exitInputquestion(InputquestionContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("leaving inputquestion");

		super.exitInputquestion(ctx);
	}

	@Override
	public void enterCalculatedquestion(CalculatedquestionContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Entering calcQuestion");
		System.out.println(ctx.inputquestion.qname.result + ctx.inputquestion().qtext.result);

		super.enterCalculatedquestion(ctx);
	}

	@Override
	public void exitCalculatedquestion(CalculatedquestionContext ctx) {
		// TODO Auto-generated method stub
		super.exitCalculatedquestion(ctx);
	}

	@Override
	public void enterQuestiontext(QuestiontextContext ctx) {
		System.out.println("Qtext");
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
		//System.out.println("Type");
		super.enterQuestiontype(ctx);
	}

	@Override
	public void exitQuestiontype(QuestiontypeContext ctx) {
		// TODO Auto-generated method stub
		super.exitQuestiontype(ctx);
	}

	@Override
	public void enterExpr(ExprContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Expr");
		super.enterExpr(ctx);
	}

	@Override
	public void exitExpr(ExprContext ctx) {
		// TODO Auto-generated method stub
		super.exitExpr(ctx);
	}

	@Override
	public void enterBinaryexpr(BinaryexprContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("BinaryExpr");
		super.enterBinaryexpr(ctx);
	}

	@Override
	public void exitBinaryexpr(BinaryexprContext ctx) {
		// TODO Auto-generated method stub
		super.exitBinaryexpr(ctx);
	}

	@Override
	public void enterUnaryexpr(UnaryexprContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Unary");
		super.enterUnaryexpr(ctx);
	}

	@Override
	public void exitUnaryexpr(UnaryexprContext ctx) {
		// TODO Auto-generated method stub
		super.exitUnaryexpr(ctx);
	}

	@Override
	public void enterPosexpr(PosexprContext ctx) {
		// TODO Auto-generated method stub
		super.enterPosexpr(ctx);
	}

	@Override
	public void exitPosexpr(PosexprContext ctx) {
		// TODO Auto-generated method stub
		super.exitPosexpr(ctx);
	}

	@Override
	public void enterNotexpr(NotexprContext ctx) {
		// TODO Auto-generated method stub
		super.enterNotexpr(ctx);
	}

	@Override
	public void exitNotexpr(NotexprContext ctx) {
		// TODO Auto-generated method stub
		super.exitNotexpr(ctx);
	}

	@Override
	public void enterNegexpr(NegexprContext ctx) {
		// TODO Auto-generated method stub
		super.enterNegexpr(ctx);
	}

	@Override
	public void exitNegexpr(NegexprContext ctx) {
		// TODO Auto-generated method stub
		super.exitNegexpr(ctx);
	}

	@Override
	public void enterValue(ValueContext ctx) {
		System.out.println("Value");
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
		System.out.println("Name");
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
		//System.out.println("Terminal node: " + node.getText()); // Clogs up console.
		super.visitTerminal(node);
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		System.out.println("!!!Error: " + node.getText());
		super.visitErrorNode(node);
	}

}
