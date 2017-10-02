package ql2;

import ql2.ast.*;
import ql2.ast.expression.*;
import ql2.ast.expression.arithmatic.*;
import ql2.ast.literal.*;
import ql2.ast.statement.*;
import ql2.ast.type.*;

public interface Ql2VisitorInterface<T> {

	public T visit(ASTNode node);
	

	public T visit(Block node);
	public T visit(Form node);
	public T visit(Question node);
	public T visit(InputQuestion node);
	public T visit(CalculatedQuestion node);
	public T visit(Questionnaire node);
	// visit expressions
	public T visit(AndExpr node);
	//public T visit(AndExpr node);
	public T visit(BinaryExpr node);
	public T visit(DivExpr node);
	public T visit(EqExpr node);
	public T visit(GeExpr node);
	public T visit(GEqExpr node);
	public T visit(GTExpr node);
	public T visit(LTExpr node);
	public T visit(LTeExpr node);
	public T visit(MulExpr node);
	public T visit(NegExpr node);
	public T visit(NEqExpr node);
	public T visit(OrExpr node);
	public T visit(PosExpr node);
	public T visit(SubExpr node);
	public T visit(UnaryExpr node);
	//public T visit(IdentityExpr node);
	public T visit(LiteralExpr node);
	// visit statements
	public T visit(IfStatement node);
	public T visit(IfElseStatement node);
	// visit literals
	public T visit(Literal node);
	public T visit(BooleanLiteral node);
	public T visit(CurrencyLiteral node);	
	public T visit(IntegerLiteral node);
	public T visit(StringLiteral node);
	// visit not quite literals. exclude these from project?
	public T visit(QuestionType node);
	public T visit(BooleanType node);
	public T visit(CurrencyType node);	
	public T visit(IntegerType node);
	public T visit(StringType node);

	
}
