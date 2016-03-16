package eu.bankersen.kevin.ql.typechecker.analytics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eu.bankersen.kevin.ql.ast.BaseVisitor;
import eu.bankersen.kevin.ql.ast.expr.Expr;
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
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.typechecker.analytics.dependancies.DependancyChecker;
import eu.bankersen.kevin.ql.typechecker.errors.TypeCheckError;

public class DependancyAnalyzer implements BaseVisitor<DependancyChecker>, ExprVisitor<Set<String>, Void> {

    private final List<TypeCheckError> errorList;

    public DependancyAnalyzer(Form form) {
	DependancyChecker data = form.accept(this, new DependancyChecker());
	this.errorList = data.getErrors();
    }

    public List<TypeCheckError> getErrors() {
	return errorList;
    }

    @Override
    public DependancyChecker visit(Form o, DependancyChecker context) {
	context.openNewBlock();
	o.body().accept(this, context);
	Set<String> name = new HashSet<>();
	name.add(o.name());
	context.closeBlock(name);
	return context;

    }

    @Override
    public DependancyChecker visit(Body o, DependancyChecker context) {
	for (AbstractStatement s : o.statements()) {
	    context = s.accept(this, context);
	}
	return context;
    }

    @Override
    public DependancyChecker visit(IFStatement o, DependancyChecker context) {

	context.openNewBlock();

	Set<String> condition = o.condition().accept(this, null);

	context = o.body().accept(this, context);
	context.closeIfBlock();

	context.closeBlock(condition);
	return context;
    }

    @Override
    public DependancyChecker visit(ElseStatement o, DependancyChecker context) {

	context.openNewBlock();
	Set<String> condition = o.condition().accept(this, null);
	context = o.body().accept(this, context);
	context.closeIfBlock();

	context = o.elseBody().accept(this, context);
	context.closeBlock(condition);
	return context;
    }

    @Override
    public DependancyChecker visit(NormalQuestion o, DependancyChecker context) {
	context.registerQuestion(o.name());
	return context;
    }

    @Override
    public DependancyChecker visit(ComputedQuestion o, DependancyChecker context) {
	Set<String> identifiers = o.computation().accept(this, null);
	context.registerQuestion(o.name(), identifiers);
	return context;
    }

    private Set<String> visitDoubleExpression(Expr lhs, Expr rhs, Void context) {

	Set<String> left = lhs.accept(this, context);
	Set<String> right = rhs.accept(this, context);

	left.addAll(right);
	return left;
    }

    @Override
    public Set<String> visit(Identifier o, Void context) {
	Set<String> identifier = new HashSet<>();
	identifier.add(o.name());
	return identifier;
    }

    private Set<String> visitSingleExpression(Expr expr, Void context) {
	return expr.accept(this, context);
    }

    @Override
    public Set<String> visit(Sub o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(Add o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(Div o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(Mul o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(Pos o, Void context) {
	return visitSingleExpression(o.expr(), context);
    }

    @Override
    public Set<String> visit(Neg o, Void context) {
	return visitSingleExpression(o.expr(), context);
    }

    @Override
    public Set<String> visit(Or o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(And o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(Eq o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(GEq o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(GT o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(LEq o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(LT o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(NEq o, Void context) {
	return visitDoubleExpression(o.lhs(), o.rhs(), context);
    }

    @Override
    public Set<String> visit(Not o, Void context) {
	return visitSingleExpression(o.expr(), context);
    }

    @Override
    public Set<String> visit(Literal o, Void context) {
	return new HashSet<String>();
    }

}