package eu.bankersen.kevin.ql.form.analyzer.scanners;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.OutOfScope;
import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.ScannerError;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.IdentifierGatherer;
import eu.bankersen.kevin.ql.form.ast.statements.Body;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Form;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Statement;
import eu.bankersen.kevin.ql.form.ast.statements.UserQuestion;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitor;

public class Scoping {

	private List<ScannerError> errorList;

	public Scoping(Form form) {
		errorList = new ArrayList<>();
		analyzeForm(form);
	}

	public List<ScannerError> getErrors() {
		return errorList;
	}

	private void analyzeForm(Form form) {

		Scope formScope = new Scope(form.line());
		form.accept(new Visitor<Scope>() {

			@Override
			public void visit(Form form, Scope context) {
				form.body().accept(this, context);
			}

			@Override
			public void visit(Body body, Scope context) {
				Iterator<Statement> statements = body.statements();
				while (statements.hasNext()) {
					statements.next().accept(this, context);
				}
			}

			@Override
			public void visit(IFStatement statement, Scope context) {
				Set<String> condition = statement.condition().accept(new IdentifierGatherer(), new HashSet<>());

				Scope ifBody = new Scope(statement.line());
				statement.body().accept(this, ifBody);

				context.addCondition(new IFBody(statement.condition().line(), condition, ifBody));
			}

			@Override
			public void visit(ElseStatement statement, Scope context) {

				Set<String> condition = statement.condition().accept(new IdentifierGatherer(), new HashSet<>());

				Scope ifBody = new Scope(statement.body().line());
				statement.body().accept(this, ifBody);

				Scope ifElseBody = new Scope(statement.elseBody().line());
				statement.elseBody().accept(this, ifElseBody);

				context.addCondition(new IfElseBody(statement.condition().line(), condition, ifBody, ifElseBody));

			}

			@Override
			public void visit(UserQuestion question, Scope context) {
				context.addQuestion(question.name());
			}

			@Override
			public void visit(ComputedQuestion question, Scope context) {
				context.addQuestion(question.name(),
						question.computation().accept(new IdentifierGatherer(), new HashSet<>()));
			}
		}, formScope);
		formScope.scan(new HashSet<>());
	}

	private class Scope {
		private final int line;
		private final Set<String> questions;
		private final Set<String> identifiers;
		private final Set<IFBody> ifElseBodies;

		public Scope(int line) {
			this.line = line;
			this.questions = new HashSet<>();
			this.identifiers = new HashSet<>();
			this.ifElseBodies = new HashSet<>();
		}

		public void addQuestion(String question) {
			questions.add(question);
		}

		public void addQuestion(String question, Set<String> identifiers) {
			this.questions.add(question);
			this.identifiers.addAll(identifiers);
		}

		public void addCondition(IFBody block) {
			ifElseBodies.add(block);
		}

		public void scan(Set<String> declaredQuestions) {
			declaredQuestions.addAll(questions);
			for (String identifier : identifiers) {
				if (!declaredQuestions.contains(identifier)) {
					errorList.add(new OutOfScope(line, identifier));
				}
			}
			for (IFBody body : ifElseBodies) {
				body.scan(new HashSet<>(declaredQuestions));
			}
		}

	}

	private class IFBody {
		private final int line;
		private final Set<String> condition;
		private final Scope ifBody;

		public IFBody(int line, Set<String> condition, Scope ifBody) {
			this.line = line;
			this.condition = condition;
			this.ifBody = ifBody;
		}

		public void scan(Set<String> declaredQuestions) {
			for (String identifier : condition) {
				if (!declaredQuestions.contains(identifier)) {
					errorList.add(new OutOfScope(line, condition, identifier));
				}
			}
			ifBody.scan(declaredQuestions);
		}

	}

	private class IfElseBody extends IFBody {
		private final Scope elseBody;

		public IfElseBody(int line, Set<String> condition, Scope ifBody, Scope elseBody) {
			super(line, condition, ifBody);
			this.elseBody = elseBody;
		}

		@Override
		public void scan(Set<String> declared) {
			super.scan(declared);
			elseBody.scan(declared);
		}

	}
}
