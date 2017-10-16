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
import ql2.ast.expression.And;
import ql2.ast.expression.Equal;
import ql2.ast.expression.GreaterThanOrEqual;
import ql2.ast.expression.GreaterThan;
import ql2.ast.expression.IdentityExpr;
import ql2.ast.expression.LesserThan;
import ql2.ast.expression.LesserThanOrEqual;
import ql2.ast.expression.LiteralExpr;
import ql2.ast.expression.NotEqual;
import ql2.ast.expression.Negative;
import ql2.ast.expression.Not;
import ql2.ast.expression.Or;
import ql2.ast.expression.Positive;
import ql2.ast.expression.arithmatic.Addition;
import ql2.ast.expression.arithmatic.Divide;
import ql2.ast.expression.arithmatic.Multiply;
import ql2.ast.expression.arithmatic.Subtract;
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
		System.out.println(node.toString());
		node.accept(this);
		return null;
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
		System.out.println(node.getFormID());
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
		context.addQuestion(node);
		return null;
	}

	@Override
	public T visit(CalculatedQuestion node) {
		node.getInput().accept(this);
		node.getCalculation().accept(this);
		context.addQuestion(node);

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
	public T visit(And node) {
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
	public T visit(Divide node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(Equal node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(GreaterThanOrEqual node) {
		return visit((BinaryExpr) node);	
	}
	
	@Override
	public T visit(GreaterThan node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(LesserThan node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(LesserThanOrEqual node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(Multiply node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(Negative node) {
		return visit((UnaryExpr) node);
	}

	@Override
	public T visit(NotEqual node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(Or node) {
		return visit((BinaryExpr) node);	
	}

	@Override
	public T visit(Positive node) {
		node.accept(this);
		return null;
	}

	@Override
	public T visit(Subtract node) {
		return visit((BinaryExpr) node);	
	}	
	
	@Override 
	public T visit(Addition node) {
		return visit((BinaryExpr) node);
	}

	@Override
	public T visit(Not node) {
		node.accept(this);
		return null;
	}

	@Override
	public T visit(UnaryExpr node) {
		node.getExpr().accept(this);
		return null;
	}

	@Override
	public T visit(IdentityExpr node) {
		return null;
	}
	
	@Override
	public T visit(LiteralExpr node) {
		//node.accept(this);
		return null;
	}

	@Override
	public T visit(Statement node) {
		node.accept(this);
		return null;
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
