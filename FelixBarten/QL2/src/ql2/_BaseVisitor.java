package ql2;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ql2.ast.ASTNode;
import ql2.ast.Block;
import ql2.ast.CalculatedQuestion;
import ql2.ast.Form;
import ql2.ast.InputQuestion;
import ql2.ast.Question;
import ql2.ast.Questionnaire;
import ql2.ast.Statement;
import ql2.ast.expression.BinaryExpr;
import ql2.ast.expression.IdentityExpr;
import ql2.ast.expression.LiteralExpr;
import ql2.ast.expression.UnaryExpr;
import ql2.ast.expression.arithmatic.Addition;
import ql2.ast.expression.arithmatic.Divide;
import ql2.ast.expression.arithmatic.Multiply;
import ql2.ast.expression.arithmatic.Negative;
import ql2.ast.expression.arithmatic.Positive;
import ql2.ast.expression.arithmatic.Subtract;
import ql2.ast.expression.logic.And;
import ql2.ast.expression.logic.Equal;
import ql2.ast.expression.logic.GreaterThan;
import ql2.ast.expression.logic.GreaterThanOrEqual;
import ql2.ast.expression.logic.LesserThan;
import ql2.ast.expression.logic.LesserThanOrEqual;
import ql2.ast.expression.logic.Not;
import ql2.ast.expression.logic.NotEqual;
import ql2.ast.expression.logic.Or;
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
		return null;
	}

	@Override
	public T visit(Block node) {
		return null;
	}

	@Override
	public T visit(Form node) {
		return null;
	}

	@Override
	public T visit(Question node) {
		
		return null;
	}

	@Override
	public T visit(InputQuestion node) {
		
		return null;
	}
	
	@Override
	public T visit(Statement node) {
		
		return null;
	}

	@Override
	public T visit(CalculatedQuestion node) {
		
		return null;
	}

	@Override
	public T visit(Questionnaire node) {
		
		return null;
	}

	@Override
	public T visit(And node) {
		
		return null;
	}

	@Override
	public T visit(BinaryExpr node) {
		
		return null;
	}

	@Override
	public T visit(Divide node) {
		
		return null;
	}

	@Override
	public T visit(Equal node) {
		
		return null;
	}

	@Override
	public T visit(GreaterThanOrEqual node) {
		
		return null;
	}

	@Override
	public T visit(GreaterThan node) {
		
		return null;
	}

	@Override
	public T visit(LesserThan node) {
		
		return null;
	}

	@Override
	public T visit(LesserThanOrEqual node) {
		
		return null;
	}

	@Override
	public T visit(Multiply node) {
		
		return null;
	}

	@Override
	public T visit(Negative node) {
		
		return null;
	}

	@Override
	public T visit(NotEqual node) {
		
		return null;
	}

	@Override
	public T visit(Or node) {
		
		return null;
	}

	@Override
	public T visit(Positive node) {
		
		return null;
	}

	@Override
	public T visit(Subtract node) {
		
		return null;
	}

	@Override
	public T visit(UnaryExpr node) {
		
		return null;
	} 

	@Override
	public T visit(Addition node) {
		
		return null;
	}

	@Override
	public T visit(IdentityExpr node) {
		
		return null;
	}

	@Override
	public T visit(LiteralExpr node) {
		
		return null;
	}

	@Override
	public T visit(IfStatement node) {
		
		return null;
	}

	@Override
	public T visit(IfElseStatement node) {
		
		return null;
	}

	@Override
	public T visit(Literal node) {
		
		return null;
	}

	@Override
	public T visit(BooleanLiteral node) {
		return null;
	}

	@Override
	public T visit(CurrencyLiteral node) {
		return null;
	}

	@Override
	public T visit(IntegerLiteral node) {
		return null;
	}

	@Override
	public T visit(StringLiteral node) {
		
		return null;
	}

	@Override
	public T visit(QuestionType node) {
		
		return null;
	}

	@Override
	public T visit(BooleanType node) {
		return null;
	}

	@Override
	public T visit(CurrencyType node) {
		return null;
	}

	@Override
	public T visit(IntegerType node) {
		return null;
	}

	@Override
	public T visit(StringType node) {
		return null;
	}

	@Override
	public T visit(Not node) {
		return null;
	}
}