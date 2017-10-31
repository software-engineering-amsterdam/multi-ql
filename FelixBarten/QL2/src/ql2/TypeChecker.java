package ql2;

import ql2.ast.BinaryExpr;
import ql2.ast.CalculatedQuestion;
import ql2.ast.Expr;
import ql2.ast.InputQuestion;
import ql2.ast.UnaryExpr;
import ql2.ast.expression.And;
import ql2.ast.expression.Equal;
import ql2.ast.expression.GreaterThan;
import ql2.ast.expression.GreaterThanOrEqual;
import ql2.ast.expression.IdentityExpr;
import ql2.ast.expression.LesserThan;
import ql2.ast.expression.LesserThanOrEqual;
import ql2.ast.expression.LiteralExpr;
import ql2.ast.expression.Negative;
import ql2.ast.expression.Not;
import ql2.ast.expression.NotEqual;
import ql2.ast.expression.Or;
import ql2.ast.expression.Positive;
import ql2.ast.expression.arithmatic.Addition;
import ql2.ast.expression.arithmatic.Divide;
import ql2.ast.expression.arithmatic.Multiply;
import ql2.ast.expression.arithmatic.Subtract;
import ql2.ast.literal.BooleanLiteral;
import ql2.ast.literal.IntegerLiteral;
import ql2.ast.literal.Literal;
import ql2.ast.literal.StringLiteral;
import ql2.ast.statement.IfStatement;
import ql2.ast.type.BooleanType;
import ql2.ast.type.IntegerType;
import ql2.ast.type.QuestionType;
import ql2.ast.type.StringType;
import ql2.conflict.InvalidConditionType;
import ql2.conflict.TypeMismatch;
import ql2.conflict.VariableNotDeclared;

public class TypeChecker<T> extends BaseVisitor<QuestionType> {

	private Context context; 
	
	public TypeChecker (Context context) {
		// Typechecker needs an established context for checking types.
		this.context = context; 
	}

	@Override
	public QuestionType visit(IfStatement node) {
		node.getBlock().accept(this);
		checkCondition(node.getCondition());
		return null;
	}

	@Override
	public QuestionType visit(CalculatedQuestion node) {
		node.getInput().accept(this);
		checkType(node.getCalculation(), node.getInput().getType());
		return null;
	}

	@Override
	public QuestionType visit(And node) {
		checkBinary(node, new BooleanType());
		return new BooleanType();
	}

	@Override
	public QuestionType visit(Divide node) {
		checkBinary(node, new IntegerType());
		return new IntegerType();
	}

	@Override
	public QuestionType visit(Equal node) {
		checkBinary(node, new BooleanType());
		return new BooleanType();
	}

	@Override
	public QuestionType visit(GreaterThanOrEqual node) {
		checkBinary(node, new BooleanType());
		return new BooleanType();
	}

	@Override
	public QuestionType visit(GreaterThan node) {
		checkBinary(node, new BooleanType());
		return new BooleanType();
	}

	@Override
	public QuestionType visit(LesserThan node) {
		checkBinary(node, new BooleanType());
		return new BooleanType();
	}

	@Override
	public QuestionType visit(LesserThanOrEqual node) {
		checkBinary(node, new BooleanType());
		return new BooleanType();
	}

	@Override
	public QuestionType visit(Multiply node) {
		checkBinary(node, new IntegerType());
		return new IntegerType();
	}

	@Override
	public QuestionType visit(Negative node) {
		checkType(node, new IntegerType());
		return new IntegerType();
	}

	@Override
	public QuestionType visit(NotEqual node) {
		checkBinary(node, new BooleanType());
		return new BooleanType();
	}

	@Override
	public QuestionType visit(Or node) {
		checkBinary(node, new BooleanType());
		return new BooleanType();
	}

	@Override
	public QuestionType visit(Positive node) {
		checkType(node.getExpr(), new IntegerType());
		return new IntegerType(); // int type?
	}

	@Override
	public QuestionType visit(Subtract node) {
		checkBinary(node, new IntegerType());
		return new IntegerType();
	}


	@Override
	public QuestionType visit(Addition node) {
		checkBinary(node, new IntegerType());
		return new IntegerType();
	}

	@Override
	public QuestionType visit(IdentityExpr node) {
		// check if identity is declared in questiontable.
		if (!context.getVariables().containsKey(node.getID())) {
			context.addConflict(new VariableNotDeclared(node.getID())); 
			return null;
		}
		return context.getQuestionType(node.getID());
	}

	@Override
	public QuestionType visit(Not node) {
		checkType(node.getExpr(), new BooleanType());
		return new BooleanType();
	}
	
	@Override
	public QuestionType visit(LiteralExpr node) {
		return node.getLiteral().accept(this);
	}

	@Override
	public QuestionType visit(BooleanLiteral node) {
		return new BooleanType();
	}

	@Override
	public QuestionType visit(IntegerLiteral node) {
		return new IntegerType();
	}

	@Override
	public QuestionType visit(StringLiteral node) {
		return new StringType();
	}
	
	void checkType(Expr e, QuestionType qt) {
		QuestionType actual = e.accept(this); 
		if (actual != null) {
			if (actual.getClass() == qt.getClass()) {
				return;
			}
		}
		context.addConflict( new TypeMismatch(e, qt, actual));

	} 
	
	void checkCondition (Expr e) {
		QuestionType actual = e.accept(this);
		QuestionType cond = new BooleanType(); //cond always bool
		if (actual != null) {
			if(actual.getClass() == cond.getClass()) {
				System.out.println("cond is bool");
				return;
			}
		}
		context.addConflict(new InvalidConditionType(e, cond, actual));
	}
	
	void checkBinary(BinaryExpr e, QuestionType exp) {
		checkType(e.getLefthand(), exp);
		checkType(e.getRighthand(), exp);
	}
	
	public Context getContext() {
		return context;
	}

	
	
}
