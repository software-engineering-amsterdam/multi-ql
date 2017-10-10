package ql2;

import java.util.List;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ql2.ast.BinaryExpr;
import ql2.ast.Block;
import ql2.ast.CalculatedQuestion;
import ql2.ast.Form;
import ql2.ast.InputQuestion;
import ql2.ast.Question;
import ql2.ast.Questionnaire;
import ql2.ast.Statement;
import ql2.ast.UnaryExpr;
import ql2.ast.expression.AndExpr;
import ql2.ast.expression.EqExpr;
import ql2.ast.expression.GEqExpr;
import ql2.ast.expression.GTExpr;
import ql2.ast.expression.GeExpr;
import ql2.ast.expression.LTExpr;
import ql2.ast.expression.LTeExpr;
import ql2.ast.expression.LiteralExpr;
import ql2.ast.expression.NEqExpr;
import ql2.ast.expression.NegExpr;
import ql2.ast.expression.OrExpr;
import ql2.ast.expression.PosExpr;
import ql2.ast.expression.arithmatic.DivExpr;
import ql2.ast.expression.arithmatic.MulExpr;
import ql2.ast.expression.arithmatic.SubExpr;
import ql2.ast.literal.BooleanLiteral;
import ql2.ast.literal.CurrencyLiteral;
import ql2.ast.literal.IntegerLiteral;
import ql2.ast.literal.Literal;
import ql2.ast.literal.StringLiteral;
import ql2.ast.statement.IfElseStatement;
import ql2.ast.statement.IfStatement;
import ql2.ast.type.BooleanType;
import ql2.ast.type.CurrencyType;
import ql2.ast.type.IntegerType;
import ql2.ast.type.QuestionType;
import ql2.ast.type.StringType;
import ql2.parser.generated.Ql2Parser.*;


public class Ql2SemanticVisitor<T> extends BaseVisitor<T> {

	private Context context; 

	public Ql2SemanticVisitor() {

		context = new Context();
	}
	
	@Override
	public T visit(ASTNode node) {
		// TODO Auto-generated method stub
		return node.accept(this);
	}

	@Override
	public T visit(Block node) {
		List<Question> questionsList = node.getQuestionsList();
		List<Statement> statementsList = node.getStatementsList();
		
		for (Question q : questionsList) {
			q.accept(this);
		}
		for (Statement s : statementsList) {
			s.accept(this);
		}
		
		return super.visit(node);
	}

	@Override
	public T visit(Form node) {

		return node.accept(this);
	}

	@Override
	public T visit(Question node) {
		// TODO Auto-generated method stub
		return node.accept(this);
	}

	@Override
	public T visit(InputQuestion node) {
		// TODO Auto-generated method stub
		return node.accept(this);
	}

	@Override
	public T visit(CalculatedQuestion node) {
		// TODO Auto-generated method stub
		return node.accept(this);
	}

	@Override
	public T visit(Questionnaire node) {
		// TODO Auto-generated method stub
		return node.accept(this);
	}

	@Override
	public T visit(AndExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;
	}

	@Override
	public T visit(BinaryExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(DivExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;
	}

	@Override
	public T visit(EqExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(GeExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(GEqExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}
	
	@Override
	public T visit(GTExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(LTExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(LTeExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(MulExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(NegExpr node) {
		// TODO Auto-generated method stub
		return node.accept(this);
	}

	@Override
	public T visit(NEqExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(OrExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(PosExpr node) {
		// TODO Auto-generated method stub
		return node.accept(this);
	}

	@Override
	public T visit(SubExpr node) {
		// TODO Auto-generated method stub
		node.getLefthand().accept(this);
		node.getRighthand().accept(this);
		return null;	
	}

	@Override
	public T visit(UnaryExpr node) {
		// TODO Auto-generated method stub
		return node.accept(this);
	}

	@Override
	public T visit(LiteralExpr node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(IfStatement node) {
		// TODO Auto-generated method stub
		
		node.getBlock().accept(this);
		node.getCondition().accept(this);
		
		return null;
	}

	@Override
	public T visit(IfElseStatement node) {
		// TODO Auto-generated method stub

		
		
		return null;
	}

	@Override
	public T visit(Literal node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(BooleanLiteral node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(CurrencyLiteral node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(IntegerLiteral node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(StringLiteral node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(QuestionType node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(BooleanType node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(CurrencyType node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(IntegerType node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(StringType node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	
}
