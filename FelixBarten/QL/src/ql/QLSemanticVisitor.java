package ql;

import ql.ast.Block;
import ql.ast.Form;
import ql.ast.Question;
import ql.ast.Questionnaire;
import ql.ast.expression.Add;
import ql.ast.expression.And;
import ql.ast.expression.BinaryExpr;
import ql.ast.expression.Div;
import ql.ast.expression.Eq;
import ql.ast.expression.GEq;
import ql.ast.expression.GT;
import ql.ast.expression.Ge;
import ql.ast.expression.LEq;
import ql.ast.expression.LT;
import ql.ast.expression.Le;
import ql.ast.expression.Mul;
import ql.ast.expression.NEq;
import ql.ast.expression.Neg;
import ql.ast.expression.Not;
import ql.ast.expression.Or;
import ql.ast.expression.Pos;
import ql.ast.expression.Sub;
import ql.ast.expression.UnaryExpr;
import ql.ast.literal.BooleanLiteral;
import ql.ast.literal.IntegerLiteral;
import ql.ast.literal.MoneyLiteral;
import ql.ast.literal.StringLiteral;
import ql.ast.statement.IfElseStatement;
import ql.ast.statement.IfStatement;
import ql.ast.type.BooleanType;
import ql.ast.type.IntegerType;
import ql.ast.type.MoneyType;
import ql.ast.type.StringType;
import ql.parser.QLBaseVisitor;

public class QLSemanticVisitor implements QLVisitorInterface<ASTNode>{
	

	@Override
	public ASTNode visit(ASTNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Block node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Form node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Question node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Questionnaire node) {
		// TODO Auto-generated method stub
		return null;
	}

	// expr
	
	@Override
	public ASTNode visit(Add node) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ASTNode visit(And node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(BinaryExpr node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Div node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Eq node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Ge node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(GEq node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(GT node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Le node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(LEq node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(LT node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Mul node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Neg node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(NEq node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Not node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Or node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Pos node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Sub node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(UnaryExpr node) {
		// TODO Auto-generated method stub
		return null;
	}

	// statements
	
	@Override
	public ASTNode visit(IfStatement node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(IfElseStatement node) {
		// TODO Auto-generated method stub
		return null;
	}



	// Literals
	
	@Override
	public ASTNode visit(BooleanLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(MoneyLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(IntegerLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(StringLiteral node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(BooleanType node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(MoneyType node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(IntegerType node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(StringType node) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
