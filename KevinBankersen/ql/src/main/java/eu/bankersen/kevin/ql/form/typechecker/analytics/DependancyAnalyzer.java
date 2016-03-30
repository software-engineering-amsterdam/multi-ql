package eu.bankersen.kevin.ql.form.typechecker.analytics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eu.bankersen.kevin.ql.form.ast.TopDownQuestionVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.Expr;
import eu.bankersen.kevin.ql.form.ast.expr.Identifier;
import eu.bankersen.kevin.ql.form.ast.expr.IdentifierVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.Literal;
import eu.bankersen.kevin.ql.form.ast.form.Form;
import eu.bankersen.kevin.ql.form.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.form.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.form.typechecker.analytics.dependancies.DependancyChecker;
import eu.bankersen.kevin.ql.form.typechecker.errors.TypeCheckError;

public class DependancyAnalyzer {

    private final List<TypeCheckError> errorList;

    public DependancyAnalyzer(Form form) {
	DependancyChecker data = new DependancyChecker();
	form.accept(new FormDependancyVisitor(), data);
	this.errorList = data.getErrors();
    }

    public List<TypeCheckError> getErrors() {
	return errorList;
    }

    public class FormDependancyVisitor extends TopDownQuestionVisitor<DependancyChecker> {

	@Override
	public void visit(Form o, DependancyChecker context) {
	    context.openNewBlock(0);

	    o.body().accept(this, context);

	    Set<String> name = new HashSet<>();
	    name.add(o.name());

	    context.closeBlock(name);
	}

	@Override
	public void visit(IFStatement o, DependancyChecker context) {

	    context.openNewBlock(o.line());

	    Set<String> condition = o.condition().accept(new ExprDependancyVisitor(), null);

	    o.body().accept(this, context);
	    context.closeIfBlock();

	    context.closeBlock(condition);
	}

	@Override
	public void visit(ElseStatement o, DependancyChecker context) {

	    context.openNewBlock(o.line());
	    Set<String> condition = o.condition().accept(new ExprDependancyVisitor(), null);

	    o.body().accept(this, context);
	    context.closeIfBlock();

	    o.elseBody().accept(this, context);
	    context.closeBlock(condition);
	}

	@Override
	public void visit(NormalQuestion o, DependancyChecker context) {
	    context.registerQuestion(o.name());
	}

	@Override
	public void visit(ComputedQuestion o, DependancyChecker context) {
	    Set<String> identifiers = o.computation().accept(new ExprDependancyVisitor(), null);
	    context.registerQuestion(o.name(), identifiers);
	}

	public class ExprDependancyVisitor extends IdentifierVisitor<Set<String>, Void> {

	    @Override
	    public Set<String> visitDoubleExpression(Expr lhs, Expr rhs, Void context) {

		Set<String> leftIdentifiers = lhs.accept(this, context);
		Set<String> rightIdentifiers = rhs.accept(this, context);

		leftIdentifiers.addAll(rightIdentifiers);
		return leftIdentifiers;
	    }

	    @Override
	    public Set<String> visit(Identifier o, Void context) {
		Set<String> identifier = new HashSet<>();
		identifier.add(o.name());
		return identifier;
	    }

	    @Override
	    public Set<String> visit(Literal o, Void context) {
		return new HashSet<String>();
	    }
	}
    }
}