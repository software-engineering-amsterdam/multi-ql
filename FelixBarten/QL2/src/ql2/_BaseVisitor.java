package ql2;

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



































import ql2.parser.generated.Ql2ParserVisitor;

public class _BaseVisitor<T> implements Ql2VisitorInterface<T>{

	@Override
	public T visit(ASTNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Block node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Form node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Question node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(InputQuestion node) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public T visit(Statement node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(CalculatedQuestion node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Questionnaire node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(And node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(BinaryExpr node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Divide node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Equal node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(GreaterThanOrEqual node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(GreaterThan node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(LesserThan node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(LesserThanOrEqual node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Multiply node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Negative node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(NotEqual node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Or node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Positive node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Subtract node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(UnaryExpr node) {
		// TODO Auto-generated method stub
		return null;
	} 

	@Override
	public T visit(Addition node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(IdentityExpr node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(LiteralExpr node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(IfStatement node) {
		// TODO Auto-generated method stub
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
		return null;
	}

	@Override
	public T visit(BooleanLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(CurrencyLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(IntegerLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(StringLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(QuestionType node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(BooleanType node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(CurrencyType node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(IntegerType node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(StringType node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T visit(Not node) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}