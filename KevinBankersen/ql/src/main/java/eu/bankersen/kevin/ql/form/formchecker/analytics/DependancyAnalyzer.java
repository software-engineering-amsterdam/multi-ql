package eu.bankersen.kevin.ql.form.formchecker.analytics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eu.bankersen.kevin.ql.form.ast.expressions.Binary;
import eu.bankersen.kevin.ql.form.ast.expressions.Identifier;
import eu.bankersen.kevin.ql.form.ast.expressions.Literal;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.LeafVisitor;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Form;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.statements.UserQuestion;
import eu.bankersen.kevin.ql.form.ast.visitors.TopDownVisitor;
import eu.bankersen.kevin.ql.form.formchecker.analytics.errors.AnalyticsError;

public class DependancyAnalyzer {

	private final List<AnalyticsError> errorList;

	public List<AnalyticsError> getErrors() {
		return errorList;
	}

	public DependancyAnalyzer(Form form) {
		DependancyChecker data = new DependancyChecker();
		form.accept(new TopDownVisitor<DependancyChecker>() {

			@Override
			public void visit(Form form, DependancyChecker context) {
				context.openNewBlock(0);

				form.body().accept(this, context);

				Set<String> name = new HashSet<>();
				name.add(form.name());

				context.closeBlock(name);
			}

			@Override
			public void visit(IFStatement statement, DependancyChecker context) {

				context.openNewBlock(statement.line());

				Set<String> condition = statement.condition().accept(new IdentifierRetriever(), new HashSet<>());

				statement.body().accept(this, context);
				context.closeIfBlock();

				context.closeBlock(condition);
			}

			@Override
			public void visit(ElseStatement statement, DependancyChecker context) {

				context.openNewBlock(statement.line());
				Set<String> condition = statement.condition().accept(new IdentifierRetriever(), new HashSet<>());

				statement.body().accept(this, context);
				context.closeIfBlock();

				statement.elseBody().accept(this, context);
				context.closeBlock(condition);
			}

			@Override
			public void visit(UserQuestion question, DependancyChecker context) {
				context.registerQuestion(question.name());
			}

			@Override
			public void visit(ComputedQuestion question, DependancyChecker context) {
				Set<String> identifiers = question.computation().accept(new IdentifierRetriever(), new HashSet<>());
				context.registerQuestion(question.name(), identifiers);
			}

		}, data);
		this.errorList = data.getErrors();
	}

	private class IdentifierRetriever extends LeafVisitor<Set<String>, Set<String>> {

		@Override
		public Set<String> visitBinary(Binary expression, Set<String> context) {
			context.addAll(expression.lhs().accept(this, context));
			context.addAll(expression.rhs().accept(this, context));

			return context;
		}

		@Override
		public Set<String> visit(Identifier expression, Set<String> context) {
			context.add(expression.name());
			return context;
		}

		@Override
		public Set<String> visit(Literal expression, Set<String> context) {
			return context;
		}
	}
}