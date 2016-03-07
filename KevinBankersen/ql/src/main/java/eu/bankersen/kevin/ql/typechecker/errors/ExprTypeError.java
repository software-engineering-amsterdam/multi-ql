package eu.bankersen.kevin.ql.typechecker.errors;

import eu.bankersen.kevin.ql.ast.expr.BooleanExpr;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.NumberExpr;
import eu.bankersen.kevin.ql.ast.expr.math.Div;
import eu.bankersen.kevin.ql.ast.expr.math.Mul;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.type.Type;

public class ExprTypeError extends TypeCheckError {
    
    public ExprTypeError(IFStatement o, Type expr) {
	super(o.line(), String.format("If-Expression must resolve to Boolean, got %s!", expr));
    }
    
    public ExprTypeError(ElseStatement o, Type expr) {
	super(o.line(), String.format("If-Expression must resolve to Boolean, got %s!", expr));
    }
    
    public ExprTypeError(ComputedQuestion o, Type question, Type expr) {
	super(o.line(), String.format("Computed Question \"%s\" not compatible, expected %s got %s!", o.name(), question, expr));
    }
    
    public ExprTypeError(Expr o, Type lhs, Type rhs) {
	super(o.line(), String.format("An expression must have two equal sides, got %s and %s!", lhs, rhs));
    }
    
    public ExprTypeError(BooleanExpr o, Type type) {
 	super(o.line(), String.format("Expression must be boolean, got %s!", type));
     }
    
    public ExprTypeError(Mul o, Type lhs, Type rhs) {
 	super(o.line(), String.format("Can only multiply Money by Int or Int by Int, not %s by %s!", lhs, rhs));
     }
    
    public ExprTypeError(Div o, Type lhs, Type rhs) {
 	super(o.line(), String.format("Can only divide Money by Int or Int by Int, not %s by %s!", lhs, rhs));
     }
    
    public ExprTypeError(NumberExpr o, Type type) {
 	super(o.line(), String.format("Expression must be of the number type, got %s!", type));
     }
}