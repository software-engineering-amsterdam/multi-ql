package eu.bankersen.kevin.ql.form.typechecker.analytics;

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
import eu.bankersen.kevin.ql.form.typechecker.analytics.dependancies.DependancyChecker;
import eu.bankersen.kevin.ql.form.typechecker.errors.TypeCheckError;

public class DependancyAnalyzer {

	private final List<TypeCheckError> errorList;

	public List<TypeCheckError> getErrors() {
		return errorList;
	}

	public DependancyAnalyzer(Form form) {
		DependancyChecker data = new DependancyChecker();
		form.accept(new TopDownVisitor<DependancyChecker>() {

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

				Set<String> condition = o.condition().accept(new IdentifierVisitor(), null);

				o.body().accept(this, context);
				context.closeIfBlock();

				context.closeBlock(condition);
			}

			@Override
			public void visit(ElseStatement o, DependancyChecker context) {

				context.openNewBlock(o.line());
				Set<String> condition = o.condition().accept(new IdentifierVisitor(), null);

				o.body().accept(this, context);
				context.closeIfBlock();

				o.elseBody().accept(this, context);
				context.closeBlock(condition);
			}

			@Override
			public void visit(UserQuestion o, DependancyChecker context) {
				context.registerQuestion(o.name());
			}

			@Override
			public void visit(ComputedQuestion o, DependancyChecker context) {
				Set<String> identifiers = o.computation().accept(new IdentifierVisitor(), null);
				context.registerQuestion(o.name(), identifiers);
			}

		}, data);
		this.errorList = data.getErrors();
	}

	private class IdentifierVisitor extends LeafVisitor<Set<String>, Void> {

		@Override
		public Set<String> visitBinary(Binary expression, Void context) {

			Set<String> leftIdentifiers = expression.lhs().accept(this, context);
			Set<String> rightIdentifiers = expression.rhs().accept(this, context);

			leftIdentifiers.addAll(rightIdentifiers);
			return leftIdentifiers;
		}

		@Override
		public Set<String> visit(Identifier expression, Void context) {
			Set<String> identifier = new HashSet<>();
			identifier.add(expression.name());
			return identifier;
		}

		@Override
		public Set<String> visit(Literal expression, Void context) {
			return new HashSet<String>();
		}
	}
}