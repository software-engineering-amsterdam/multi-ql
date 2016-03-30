package eu.bankersen.kevin.ql.form.typechecker.analytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.bankersen.kevin.ql.form.ast.TopDownQuestionVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.Identifier;
import eu.bankersen.kevin.ql.form.ast.expr.Literal;
import eu.bankersen.kevin.ql.form.ast.expr.logic.And;
import eu.bankersen.kevin.ql.form.ast.expr.logic.Eq;
import eu.bankersen.kevin.ql.form.ast.expr.logic.GEq;
import eu.bankersen.kevin.ql.form.ast.expr.logic.GT;
import eu.bankersen.kevin.ql.form.ast.expr.logic.LEq;
import eu.bankersen.kevin.ql.form.ast.expr.logic.LT;
import eu.bankersen.kevin.ql.form.ast.expr.logic.NEq;
import eu.bankersen.kevin.ql.form.ast.expr.logic.Not;
import eu.bankersen.kevin.ql.form.ast.expr.logic.Or;
import eu.bankersen.kevin.ql.form.ast.expr.math.Add;
import eu.bankersen.kevin.ql.form.ast.expr.math.Div;
import eu.bankersen.kevin.ql.form.ast.expr.math.Mul;
import eu.bankersen.kevin.ql.form.ast.expr.math.Neg;
import eu.bankersen.kevin.ql.form.ast.expr.math.Pos;
import eu.bankersen.kevin.ql.form.ast.expr.math.Sub;
import eu.bankersen.kevin.ql.form.ast.form.Form;
import eu.bankersen.kevin.ql.form.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.form.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.form.ast.types.BooleanType;
import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.form.ast.types.UndifinedType;
import eu.bankersen.kevin.ql.form.typechecker.errors.AllreadyDeclaredError;
import eu.bankersen.kevin.ql.form.typechecker.errors.ExprTypeError;
import eu.bankersen.kevin.ql.form.typechecker.errors.TypeCheckError;
import eu.bankersen.kevin.ql.form.typechecker.errors.UndefinedQuestionError;

public class TypeChecker {

    private final Map<String, Type> symbolTable;
    private final List<TypeCheckError> errorList;

    public List<TypeCheckError> getErrors() {
	return errorList;
    }

    public TypeChecker(Form form) {
	this.errorList = new ArrayList<>();
	this.symbolTable = new HashMap<>();

	// Build a table mapping questions and types.
	form.accept(new TopDownQuestionVisitor<Void>() {

	    @Override
	    public void visit(NormalQuestion o, Void empty) {
		if (symbolTable.containsKey(o.name())) {
		    errorList.add(new AllreadyDeclaredError(o.line(), o.name()));
		} else {
		    symbolTable.put(o.name(), o.type());
		}
	    }

	    @Override
	    public void visit(ComputedQuestion o, Void empty) {
		if (symbolTable.containsKey(o.name())) {
		    errorList.add(new AllreadyDeclaredError(o.line(), o.name()));
		} else {
		    symbolTable.put(o.name(), o.type());
		}
	    }
	}, null);

	// TypeCheck the form.
	form.accept(new TopDownQuestionVisitor<Map<String, Type>>() {

	    @Override
	    public void visit(IFStatement o, Map<String, Type> context) {

		o.body().accept(this, context);

		Type expr = o.condition().accept(new ExprTypeChecker(), context);

		if (!expr.equals(new BooleanType())) {
		    errorList.add(new ExprTypeError(o, expr));
		}
	    }

	    @Override
	    public void visit(ElseStatement o, Map<String, Type> context) {

		o.body().accept(this, context);
		o.elseBody().accept(this, context);

		Type expr = o.condition().accept(new ExprTypeChecker(), context);

		if (!expr.equals(new BooleanType())) {
		    errorList.add(new ExprTypeError(o, expr));
		}
	    }

	    @Override
	    public void visit(ComputedQuestion o, Map<String, Type> context) {

		Type question = o.type();
		Type expr = o.computation().accept(new ExprTypeChecker(), context);

		if (!question.equals(expr) && !expr.equals(new UndifinedType())) {
		    errorList.add(new ExprTypeError(o, question, expr));
		}
	    }

	    @Override
	    public void visit(NormalQuestion o, Map<String, Type> context) {
	    }
	}, symbolTable);

    }

    // The class responsible for type-checking expressions.
    private class ExprTypeChecker implements ExprVisitor<Type, Map<String, Type>> {

	private Boolean isError(Type result, Type expr) {
	    return result.equals(new UndifinedType()) && !expr.equals(result);
	}

	private Boolean isError(Type result, Type lhs, Type rhs) {
	    return result.equals(new UndifinedType()) && !lhs.equals(result) && !rhs.equals(result);
	}

	@Override
	public Type visit(Sub o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isSubtractSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(Add o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isAddSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(Div o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isDivideSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(Mul o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isMultiplySupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(Pos o, Map<String, Type> context) {

	    Type expr = o.expr().accept(this, context);
	    Type result = expr.isAbsoluteSupported();

	    if (isError(result, expr)) {
		errorList.add(new ExprTypeError(o, expr));
	    }
	    return result;
	}

	@Override
	public Type visit(Neg o, Map<String, Type> context) {

	    Type expr = o.expr().accept(this, context);
	    Type result = expr.isNegateSupported();

	    if (isError(result, expr)) {
		errorList.add(new ExprTypeError(o, expr));
	    }
	    return result;
	}

	@Override
	public Type visit(Or o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isOrSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(And o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isAndSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(Eq o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isEqualSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(GEq o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isGreaterOrEqualSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(GT o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isGreaterSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(LEq o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isLowerOrEqualSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(LT o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isLowerSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(NEq o, Map<String, Type> context) {

	    Type left = o.lhs().accept(this, context);
	    Type right = o.rhs().accept(this, context);
	    Type result = left.isNotEqualSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public Type visit(Not o, Map<String, Type> context) {

	    Type expr = o.expr().accept(this, context);
	    Type result = expr.isNotSupported();

	    if (isError(expr, result)) {
		errorList.add(new ExprTypeError(o, expr));
	    }
	    return result;
	}

	@Override
	public Type visit(Literal o, Map<String, Type> context) {
	    return o.type();
	}

	@Override
	public Type visit(Identifier o, Map<String, Type> context) {
	    if (context.containsKey(o.name())) {
		return context.get(o.name());
	    } else {
		errorList.add(new UndefinedQuestionError(o));
		return new UndifinedType();
	    }
	}
    }
}
