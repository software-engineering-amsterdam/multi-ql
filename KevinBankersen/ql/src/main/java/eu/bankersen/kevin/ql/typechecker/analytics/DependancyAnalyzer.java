package eu.bankersen.kevin.ql.typechecker.analytics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eu.bankersen.kevin.ql.ast.TopDownQuestionVisitor;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.expr.Identifier;
import eu.bankersen.kevin.ql.ast.expr.Literal;
import eu.bankersen.kevin.ql.ast.expr.TopDownExprVisitor;
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;
import eu.bankersen.kevin.ql.typechecker.analytics.dependancies.DependancyChecker;
import eu.bankersen.kevin.ql.typechecker.errors.TypeCheckError;

public class DependancyAnalyzer {

    private final List<TypeCheckError> errorList;

    public DependancyAnalyzer(Form form) {
	DependancyChecker data = form.accept(new DependancyVisitor(), new DependancyChecker());
	this.errorList = data.getErrors();
    }

    public List<TypeCheckError> getErrors() {
	return errorList;
    }

    public class DependancyVisitor extends TopDownQuestionVisitor<DependancyChecker> {

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
	    Set<String> identifiers = o.computation().accept(new ExprIdentifierVisitor(), null);
	    context.registerQuestion(o.name(), identifiers);
	    return context;
	}

	public class ExprIdentifierVisitor extends TopDownExprVisitor<Set<String>, Void> {

	    @Override
	    public Set<String> visitDoubleExpression(Expr lhs, Expr rhs, Void context) {

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

	    @Override
	    public Set<String> visit(Literal o, Void context) {
		return new HashSet<String>();
	    }
	}
    }
}