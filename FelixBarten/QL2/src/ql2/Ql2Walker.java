package ql2;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ql2.parser.generated.Ql2Parser.AndContext;
import ql2.parser.generated.Ql2Parser.BinaryexprContext;
import ql2.parser.generated.Ql2Parser.BlockContext;
import ql2.parser.generated.Ql2Parser.CalculatedquestionContext;
import ql2.parser.generated.Ql2Parser.ConditionContext;
import ql2.parser.generated.Ql2Parser.ConditionsContext;
import ql2.parser.generated.Ql2Parser.EqContext;
import ql2.parser.generated.Ql2Parser.ExprContext;
import ql2.parser.generated.Ql2Parser.FormContext;
import ql2.parser.generated.Ql2Parser.FormnameContext;
import ql2.parser.generated.Ql2Parser.FormsContext;
import ql2.parser.generated.Ql2Parser.GeContext;
import ql2.parser.generated.Ql2Parser.GteContext;
import ql2.parser.generated.Ql2Parser.IfelseifstatementContext;
import ql2.parser.generated.Ql2Parser.IfelsestatementContext;
import ql2.parser.generated.Ql2Parser.IfstatementContext;
import ql2.parser.generated.Ql2Parser.InputquestionContext;
import ql2.parser.generated.Ql2Parser.LtContext;
import ql2.parser.generated.Ql2Parser.LteContext;
import ql2.parser.generated.Ql2Parser.NameContext;
import ql2.parser.generated.Ql2Parser.NegexprContext;
import ql2.parser.generated.Ql2Parser.NeqContext;
import ql2.parser.generated.Ql2Parser.NotexprContext;
import ql2.parser.generated.Ql2Parser.OrContext;
import ql2.parser.generated.Ql2Parser.PosexprContext;
import ql2.parser.generated.Ql2Parser.QuestionContext;
import ql2.parser.generated.Ql2Parser.QuestionnaireContext;
import ql2.parser.generated.Ql2Parser.QuestionnameContext;
import ql2.parser.generated.Ql2Parser.QuestionsContext;
import ql2.parser.generated.Ql2Parser.QuestiontextContext;
import ql2.parser.generated.Ql2Parser.QuestiontypeContext;
import ql2.parser.generated.Ql2Parser.StatementsContext;
import ql2.parser.generated.Ql2Parser.StatementzContext;
import ql2.parser.generated.Ql2Parser.UnaryexprContext;
import ql2.parser.generated.Ql2Parser.ValueContext;
import ql2.parser.generated.Ql2Parser.WhilestatementContext;
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
	public void enterQuestions(QuestionsContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Questions");
		super.enterQuestions(ctx);
	}

	@Override
	public void exitQuestions(QuestionsContext ctx) {

		System.out.println("Exit Questions");
		super.exitQuestions(ctx);
	}

	@Override
	public void enterStatements(StatementsContext ctx) {
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
		System.out.println("Entering Question: ");
		// TODO Auto-generated method stub
		super.enterQuestion(ctx);
	}

	@Override
	public void exitQuestion(QuestionContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Exit Question");

		super.exitQuestion(ctx);
	}

	@Override
	public void enterInputquestion(InputquestionContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("Entering inputQuestion");
		System.out.println(ctx.qname.result +  "" + ctx.qtext.result);
		super.enterInputquestion(ctx);
	}

	@Override
	public void exitInputquestion(InputquestionContext ctx) {
		// TODO Auto-generated method stub
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
		super.enterBinaryexpr(ctx);
	}

	@Override
	public void exitBinaryexpr(BinaryexprContext ctx) {
		// TODO Auto-generated method stub
		super.exitBinaryexpr(ctx);
	}

	@Override
	public void enterAnd(AndContext ctx) {
		// TODO Auto-generated method stub
		super.enterAnd(ctx);
	}

	@Override
	public void exitAnd(AndContext ctx) {
		// TODO Auto-generated method stub
		super.exitAnd(ctx);
	}

	@Override
	public void enterOr(OrContext ctx) {
		// TODO Auto-generated method stub
		super.enterOr(ctx);
	}

	@Override
	public void exitOr(OrContext ctx) {
		// TODO Auto-generated method stub
		super.exitOr(ctx);
	}

	@Override
	public void enterEq(EqContext ctx) {
		// TODO Auto-generated method stub
		super.enterEq(ctx);
	}

	@Override
	public void exitEq(EqContext ctx) {
		// TODO Auto-generated method stub
		super.exitEq(ctx);
	}

	@Override
	public void enterNeq(NeqContext ctx) {
		// TODO Auto-generated method stub
		super.enterNeq(ctx);
	}

	@Override
	public void exitNeq(NeqContext ctx) {
		// TODO Auto-generated method stub
		super.exitNeq(ctx);
	}

	@Override
	public void enterGe(GeContext ctx) {
		// TODO Auto-generated method stub
		super.enterGe(ctx);
	}

	@Override
	public void exitGe(GeContext ctx) {
		// TODO Auto-generated method stub
		super.exitGe(ctx);
	}

	@Override
	public void enterLt(LtContext ctx) {
		// TODO Auto-generated method stub
		super.enterLt(ctx);
	}

	@Override
	public void exitLt(LtContext ctx) {
		// TODO Auto-generated method stub
		super.exitLt(ctx);
	}

	@Override
	public void enterLte(LteContext ctx) {
		// TODO Auto-generated method stub
		super.enterLte(ctx);
	}

	@Override
	public void exitLte(LteContext ctx) {
		// TODO Auto-generated method stub
		super.exitLte(ctx);
	}

	@Override
	public void enterGte(GteContext ctx) {
		// TODO Auto-generated method stub
		super.enterGte(ctx);
	}

	@Override
	public void exitGte(GteContext ctx) {
		// TODO Auto-generated method stub
		super.exitGte(ctx);
	}

	@Override
	public void enterUnaryexpr(UnaryexprContext ctx) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		super.visitTerminal(node);
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		super.visitErrorNode(node);
	}

}
