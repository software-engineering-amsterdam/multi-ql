package org.uva.sea.ql.ast.expression.Literal;

import java.util.List;

import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.form.Form;
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
		
		for (Question question:questions) {
			if (this.id.equals(question.getId().getValue()))
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