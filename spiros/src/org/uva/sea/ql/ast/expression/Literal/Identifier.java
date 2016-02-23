package org.uva.sea.ql.ast.expression.Literal;

import java.util.List;

import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.ComputedQuestionsVisitor;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.QuestionsVisitor;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.ast.type.UndefinedType;

public class Identifier extends Literal {
	
	private final String id;
	
	public Identifier(CodeFragment fragment, String id) {
		super(fragment);
		this.id = id;
	}

	public String getValue() {
		return this.id;
	}
	
	@Override
	public Type getTypeOfExpression(Form form) {
		
		// below is duplicate...F	I	X	!	!	!
		
		QuestionsVisitor questionsVisitor = new QuestionsVisitor(form);
		ComputedQuestionsVisitor computedQuestionsVisitor = new ComputedQuestionsVisitor(form);
		
		List<Question> questions = questionsVisitor.getQuestions();
		List<ComputedQuestion> computedQuestions = computedQuestionsVisitor.getComputedQuestions();
		questions.addAll(computedQuestions);
		System.out.println("so far so good.The list has " + questions.size() + "questions!. To id aytou pou psaxnw einai: " + this.id );
		
		for (Question question:questions) {
			System.out.println(question.getLabel());
			if (this.id == question.getLabel())
				return question.getType();
		}
		return new UndefinedType(new CodeFragment(-1, -1), "Undefined");
	}

	
	@Override
	public Type getTypeOfExpression() {
		// TODO Auto-generated method stub
		return new BoolType();
	}

	@Override
	public <T> T accept(ExpressionVisitor<T> visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this);
	}

}