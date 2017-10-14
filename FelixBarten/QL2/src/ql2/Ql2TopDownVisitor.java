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


public class Ql2TopDownVisitor<T> extends BaseVisitor<T> {

	private Context context; 

	public Ql2TopDownVisitor() {

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
		
		return null;
	}

	@Override
	public T visit(Form node) {
		node.getFormContent().accept(this);
		
		return null;
	}

	@Override
	public T visit(Question node) {
		node.accept(this);
		return null;
	}

	@Override
	public T visit(InputQuestion node) {
		node.getType().accept(this);
		
		return null;
	}

	@Override
	public T visit(CalculatedQuestion node) {
		node.getInput().accept(this);
		node.getCalculation().accept(this);
		
		return null;
	}

	@Override
	public T visit(Questionnaire node) {

		for (Form f: node.getForms()) {
			f.accept(this);
		}
		
		return null;
	}

	@Override
	public T visit(AndExpr node) {
		return visit((BinaryExpr) node);	
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
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(EqExpr node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(GeExpr node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(GEqExpr node) {
		return visit((BinaryExpr) node);	
	}
	
	@Override
	public T visit(GTExpr node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(LTExpr node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(LTeExpr node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(MulExpr node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(NegExpr node) {
		return visit((UnaryExpr) node);
	}

	@Override
	public T visit(NEqExpr node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(OrExpr node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(PosExpr node) {
		node.accept(this);
		return null;
	}

	@Override
	public T visit(SubExpr node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(UnaryExpr node) {
		node.getExpr().accept(this);
		return null;
	}

	@Override
	public T visit(LiteralExpr node) {
		return super.visit(node);
	}

	@Override
	public T visit(IfStatement node) {		
		node.getCondition().accept(this);
		node.getBlock().accept(this);
		
		return null;
	}

	@Override
	public T visit(IfElseStatement node) {
		node.getIfStatement().accept(this);
		node.getElseBlock().accept(this);
		
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
		return (T) node.getValue();
	}

	@Override
	public T visit(CurrencyLiteral node) {
		// TODO Auto-generated method stub
		return super.visit(node);
	}

	@Override
	public T visit(IntegerLiteral node) {
		// TODO Auto-generated method stub
		return (T) node.getValue();
	}

	@Override
	public T visit(StringLiteral node) {
		// TODO Auto-generated method stub
		return (T) node.getValue();
	}

	@Override
	public T visit(QuestionType node) {
		return super.visit(node);
	}

	@Override
	public T visit(BooleanType node) {
		return super.visit(node);
	}

	@Override
	public T visit(CurrencyType node) {
		return super.visit(node);
	}

	@Override
	public T visit(IntegerType node) {
		return super.visit(node);
	}

	@Override
	public T visit(StringType node) {
		return super.visit(node);
	}

	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	
	
}
