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

public interface QLVisitorInterface<T> {

	public ASTNode visit(ASTNode node);
	

	public ASTNode visit(Block node);
	public ASTNode visit(Form node);
	public ASTNode visit(Question node);
	public ASTNode visit(Questionnaire node);
	// visit expressions
	public ASTNode visit(Add node);
	public ASTNode visit(And node);
	public ASTNode visit(BinaryExpr node);
	public ASTNode visit(Div node);
	public ASTNode visit(Eq node);
	public ASTNode visit(Ge node);
	public ASTNode visit(GEq node);
	public ASTNode visit(GT node);
	public ASTNode visit(Le node);
	public ASTNode visit(LEq node);
	public ASTNode visit(LT node);
	public ASTNode visit(Mul node);
	public ASTNode visit(Neg node);
	public ASTNode visit(NEq node);
	public ASTNode visit(Not node);
	public ASTNode visit(Or node);
	public ASTNode visit(Pos node);
	public ASTNode visit(Sub node);
	public ASTNode visit(UnaryExpr node);
	// visit statements
	public ASTNode visit(IfStatement node);
	public ASTNode visit(IfElseStatement node);
	// visit literals
	public ASTNode visit(BooleanLiteral node);
	public ASTNode visit(MoneyLiteral node);	
	public ASTNode visit(IntegerLiteral node);
	public ASTNode visit(StringLiteral node);
	// visit not quite literals. exclude these from project?
	public ASTNode visit(BooleanType node);
	public ASTNode visit(MoneyType node);	
	public ASTNode visit(IntegerType node);
	public ASTNode visit(StringType node);

	
}
