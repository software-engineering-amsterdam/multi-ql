package uva.ql.ast;

import uva.ql.ast.expressions.Add;
import uva.ql.ast.expressions.And;
import uva.ql.ast.expressions.Div;
import uva.ql.ast.expressions.Eql;
import uva.ql.ast.expressions.GrtEql;
import uva.ql.ast.expressions.GrtThen;
import uva.ql.ast.expressions.Minus;
import uva.ql.ast.expressions.Mult;
import uva.ql.ast.expressions.Not;
import uva.ql.ast.expressions.NotEql;
import uva.ql.ast.expressions.Or;
import uva.ql.ast.expressions.SmlEql;
import uva.ql.ast.expressions.SmlThen;
import uva.ql.ast.numbers.NumDouble;
import uva.ql.ast.numbers.NumInt;
import uva.ql.ast.variables.Bool;
import uva.ql.ast.variables.Date;
import uva.ql.ast.variables.Decimal;
import uva.ql.ast.variables.Double;
import uva.ql.ast.variables.Generic;
import uva.ql.ast.variables.Int;
import uva.ql.ast.variables.Money;
import uva.ql.ast.variables.Str;

public final class AST {

	public static AST newAST() {
		return new AST();
	}
	
	public static Form newForm() {
		return new Form(newAST());
	}
	
	public static Block newBlock() {
		return new Block(newAST());
	}

	public static Question newQuestion() {
		return new Question(newAST());
	}
	
	public static IfStatement newIfStatement() {
		return new IfStatement(newAST());
	}
	
	public static Add newExprAdd() {
		return new Add(newAST());
	}
	
	public static Minus newExprMinus() {
		return new Minus(newAST());
	}
	
	public static Div newExprDiv() {
		return new Div(newAST());
	}
	
	public static Mult newExprMult() {
		return new Mult(newAST());
	}
	
	public static And newExprAnd() {
		return new And(newAST());
	}
	
	public static Or newExprOr() {
		return new Or(newAST());
	}
	
	public static Str newVarStr() {
		return new Str(newAST());
	}
	
	public static Bool newVarBool() {
		return new Bool(newAST());
	}
	
	public static Date newVarDate() {
		return new Date(newAST());
	}
	
	public static Decimal newVarDecimal() {
		return new Decimal(newAST());
	}
	
	public static Double newVarDouble() {
		return new Double(newAST());
	}
	
	public static Int newVarInt() {
		return new Int(newAST());
	}
	
	public static Money newVarMoney() {
		return new Money(newAST());
	}

	public static Not newExprNot() {
		return new Not(newAST());
	}
	
	public static Generic newVarGeneric() {
		return new Generic(newAST());
	}
	
	public static NumInt newNumInt() {
		return new NumInt(newAST());
	}
	
	public static NumDouble newNumDouble() {
		return new NumDouble(newAST());
	}

	public static SmlThen newExpSmlThen() {
		return new SmlThen(newAST());
	}
	
	public static GrtThen newExpGrtThen() {
		return new GrtThen(newAST());
	}
	
	public static SmlEql newExpSmlEql() {
		return new SmlEql(newAST());
	}
	
	public static GrtEql newExpGrtEql() {
		return new GrtEql(newAST());
	}
	
	public static NotEql newExpNotEql() {
		return new NotEql(newAST());
	}
	
	public static Eql newExpEql() {
		return new Eql(newAST());
	}
}
