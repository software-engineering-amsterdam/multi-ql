package eu.bankersen.kevin.ql.form.formchecker.analytics;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import eu.bankersen.kevin.ql.form.ast.expressions.Binary;
import eu.bankersen.kevin.ql.form.ast.expressions.Identifier;
import eu.bankersen.kevin.ql.form.ast.expressions.Literal;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.LeafVisitor;
import eu.bankersen.kevin.ql.form.ast.statements.Body;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Form;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Statement;
import eu.bankersen.kevin.ql.form.ast.statements.UserQuestion;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitor;

public class ScopeCheck {

	public ScopeCheck(Form form) {
		analyzeForm(form);
	}

	private void analyzeForm(Form form) {

		Block scopes = new Block();
		form.accept(new Visitor<Block>() {

			@Override
			public void visit(Form o, Block context) {
				o.body().accept(this, context);
			}

			@Override
			public void visit(Body o, Block context) {
				Iterator<Statement> statements = o.statements();
				while (statements.hasNext()) {
					statements.next().accept(this, context);
				}
			}

			@Override
			public void visit(IFStatement o, Block context) {
				Set<String> condition = o.condition().accept(new findIdentifiers(), new HashSet<>());

				Block ifBlock = new Block();
				o.body().accept(this, ifBlock);

				context.addCondition(new IFBlock(condition, ifBlock));
			}

			@Override
			public void visit(ElseStatement o, Block context) {

				Set<String> condition = o.condition().accept(new findIdentifiers(), new HashSet<>());

				Block ifBlock = new Block();
				o.body().accept(this, ifBlock);

				Block elseBlock = new Block();
				o.elseBody().accept(this, elseBlock);

				context.addCondition(new ElseBlock(condition, ifBlock, elseBlock));

			}

			@Override
			public void visit(UserQuestion o, Block context) {
				context.addQuestion(o.name());
			}

			@Override
			public void visit(ComputedQuestion o, Block context) {
				context.addQuestion(o.name(), o.computation().accept(new findIdentifiers(), new HashSet<>()));
			}
		}, scopes);
		System.out.println(scopes);
		scopes.scan(new HashSet<>());
	}

	private class IFBlock {
		private final Set<String> condition;
		private final Block ifBlock;

		public IFBlock(Set<String> condition, Block ifBlock) {
			this.condition = condition;
			this.ifBlock = ifBlock;
		}

		public void scan(Set<String> declared) {
			for (String identifier : condition) {
				if (!declared.contains(identifier)) {
					System.out.println("Error! condition out of scope " + identifier);
				}
			}
		}

		@Override
		public String toString() {
			return "Condition: " + condition + "\nIf Block: " + ifBlock.toString();
		}
	}

	private class ElseBlock extends IFBlock {
		private final Block elseBlock;

		public ElseBlock(Set<String> condition, Block ifBlock, Block elseBlock) {
			super(condition, ifBlock);
			this.elseBlock = elseBlock;
		}

		@Override
		public void scan(Set<String> declared) {
			super.scan(declared);
		}

		@Override
		public String toString() {
			return super.toString() + "\nElse Block: " + elseBlock.toString();
		}
	}

	private class Block {
		private final Set<String> questions;
		private final Set<String> identifiers;
		private final Set<IFBlock> ifElseBlocks;

		public Block() {
			this.questions = new HashSet<>();
			this.identifiers = new HashSet<>();
			this.ifElseBlocks = new HashSet<>();
		}

		public void addQuestion(String question) {
			questions.add(question);
		}

		public void addQuestion(String question, Set<String> identifiers) {
			this.questions.add(question);
			this.identifiers.addAll(identifiers);
		}

		public void addCondition(IFBlock block) {
			ifElseBlocks.add(block);
		}

		public void scan(Set<String> declared) {
			declared.addAll(questions);
			for (String identifier : identifiers) {
				if (!declared.contains(identifier)) {
					System.out.println("Error! not declared " + identifier);
				}
			}
			for (IFBlock block : ifElseBlocks) {
				block.scan(new HashSet<>(declared));
			}
		}

		@Override
		public String toString() {

			return "Questions " + questions.toString() + "\nIdentifiers: " + identifiers + "\n\t\tBlock: "
					+ ifElseBlocks.toString() + "\n\n";

		}
	}

	private class findIdentifiers extends LeafVisitor<Set<String>, Set<String>> {

		@Override
		public Set<String> visit(Literal expression, Set<String> context) {
			return context;
		}

		@Override
		public Set<String> visit(Identifier expression, Set<String> context) {
			context.add(expression.name());
			return context;
		}

		@Override
		public Set<String> visitBinary(Binary expression, Set<String> context) {
			context.addAll(expression.lhs().accept(this, context));
			context.addAll(expression.rhs().accept(this, context));
			return context;
		}
	}
}
