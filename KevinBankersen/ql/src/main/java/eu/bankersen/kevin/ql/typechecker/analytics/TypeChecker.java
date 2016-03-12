package eu.bankersen.kevin.ql.typechecker.analytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.bankersen.kevin.ql.ast.ASTVisitor;
import eu.bankersen.kevin.ql.ast.expr.ExprVisitor;
import eu.bankersen.kevin.ql.ast.expr.Identifier;
import eu.bankersen.kevin.ql.ast.expr.Literal;
import eu.bankersen.kevin.ql.ast.expr.logic.And;
import eu.bankersen.kevin.ql.ast.expr.logic.Eq;
import eu.bankersen.kevin.ql.ast.expr.logic.GEq;
import eu.bankersen.kevin.ql.ast.expr.logic.GT;
import eu.bankersen.kevin.ql.ast.expr.logic.LEq;
import eu.bankersen.kevin.ql.ast.expr.logic.LT;
import eu.bankersen.kevin.ql.ast.expr.logic.NEq;
import eu.bankersen.kevin.ql.ast.expr.logic.Not;
import eu.bankersen.kevin.ql.ast.expr.logic.Or;
import eu.bankersen.kevin.ql.ast.expr.math.Add;
import eu.bankersen.kevin.ql.ast.expr.math.Div;
import eu.bankersen.kevin.ql.ast.expr.math.Mul;
import eu.bankersen.kevin.ql.ast.expr.math.Neg;
import eu.bankersen.kevin.ql.ast.expr.math.Pos;
import eu.bankersen.kevin.ql.ast.expr.math.Sub;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.object.type.BooleanType;
import eu.bankersen.kevin.ql.ast.object.type.QLType;
import eu.bankersen.kevin.ql.ast.object.type.UndifinedType;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.typechecker.errors.AllreadyDeclaredError;
import eu.bankersen.kevin.ql.typechecker.errors.ExprTypeError;
import eu.bankersen.kevin.ql.typechecker.errors.TypeCheckError;
import eu.bankersen.kevin.ql.typechecker.errors.UndefinedQuestionError;

public class TypeChecker {

    private final Map<String, QLType> symbolTable;
    private final List<TypeCheckError> errorList;

    public List<TypeCheckError> getErrors() {
	return errorList;
    }

    public TypeChecker(Form form) {
	this.errorList = new ArrayList<>();
	this.symbolTable = new HashMap<>();

	// Build a table mapping questions and types.
	form.accept(new ASTVisitor<Void>() {

	    @Override
	    public Void visit(NormalQuestion o, Void empty) {
		if (symbolTable.containsKey(o.name())) {
		    errorList.add(new AllreadyDeclaredError(o.line(), o.name()));
		} else {
		    symbolTable.put(o.name(), o.type());
		}
		return null;
	    }

	    @Override
	    public Void visit(ComputedQuestion o, Void empty) {
		if (symbolTable.containsKey(o.name())) {
		    errorList.add(new AllreadyDeclaredError(o.line(), o.name()));
		} else {
		    symbolTable.put(o.name(), o.type());
		}
		return null;
	    }
	}, null);

	// TypeCheck the form.
	form.accept(new ASTVisitor<Void>() {

	    @Override
	    public Void visit(IFStatement o, Void context) {

		context = o.body().accept(this, context);

		QLType expr = o.condition().accept(new ExprTypeChecker(), symbolTable);

		if (!expr.equals(new BooleanType())) {
		    errorList.add(new ExprTypeError(o, expr));
		}

		return null;
	    }

	    @Override
	    public Void visit(ElseStatement o, Void context) {

		context = o.body().accept(this, context);
		context = o.elseBody().accept(this, context);

		QLType expr = o.condition().accept(new ExprTypeChecker(), symbolTable);

		if (!expr.equals(new BooleanType())) {
		    errorList.add(new ExprTypeError(o, expr));
		}

		return null;
	    }

	    @Override
	    public Void visit(ComputedQuestion o, Void context) {

		QLType question = o.type();
		QLType expr = o.computation().accept(new ExprTypeChecker(), symbolTable);

		if (!question.equals(expr) && !expr.equals(new UndifinedType())) {
		    errorList.add(new ExprTypeError(o, question, expr));
		}
		return null;
	    }
	}, null);

    }

    // The class responsible for type-checking expressions.
    private class ExprTypeChecker implements ExprVisitor<QLType, Map<String, QLType>> {

	private Boolean isError(QLType result, QLType expr) {
	    return result.equals(new UndifinedType()) && !expr.equals(result);
	}

	private Boolean isError(QLType result, QLType lhs, QLType rhs) {
	    return result.equals(new UndifinedType()) && !lhs.equals(result) && !rhs.equals(result);
	}

	@Override
	public QLType visit(Sub o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isSubtractSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(Add o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isAddSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(Div o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isDivideSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(Mul o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isMultiplySupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(Pos o, Map<String, QLType> context) {

	    QLType expr = o.expr().accept(this, context);
	    QLType result = expr.isAbsoluteSupported();

	    if (isError(result, expr)) {
		errorList.add(new ExprTypeError(o, expr));
	    }
	    return result;
	}

	@Override
	public QLType visit(Neg o, Map<String, QLType> context) {

	    QLType expr = o.expr().accept(this, context);
	    QLType result = expr.isNegateSupported();

	    if (isError(result, expr)) {
		errorList.add(new ExprTypeError(o, expr));
	    }
	    return result;
	}

	@Override
	public QLType visit(Or o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isOrSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(And o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isAndSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(Eq o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isEqualSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(GEq o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isGreaterOrEqualSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(GT o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isGreaterSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(LEq o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isLowerOrEqualSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(LT o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isLowerSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(NEq o, Map<String, QLType> context) {

	    QLType left = o.lhs().accept(this, context);
	    QLType right = o.rhs().accept(this, context);
	    QLType result = left.isNotEqualSupported(right);

	    if (isError(result, left, right)) {
		errorList.add(new ExprTypeError(o, left, right));
	    }
	    return result;
	}

	@Override
	public QLType visit(Not o, Map<String, QLType> context) {

	    QLType expr = o.expr().accept(this, context);
	    QLType result = expr.isNotSupported();

	    if (isError(expr, result)) {
		errorList.add(new ExprTypeError(o, expr));
	    }
	    return result;
	}

	@Override
	public QLType visit(Literal o, Map<String, QLType> context) {
	    return o.getType();
	}

	@Override
	public QLType visit(Identifier o, Map<String, QLType> context) {
	    if (context.containsKey(o.name())) {
		return context.get(o.name());
	    } else {
		errorList.add(new UndefinedQuestionError(o));
		return new UndifinedType();
	    }
	}
    }
}
