package org.uva.sea.ql.type_checker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.stringtemplate.v4.compiler.STParser.ifstat_return;
import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.ExpressionVisitor;
import org.uva.sea.ql.ast.expression.Comparison.Equal;
import org.uva.sea.ql.ast.expression.Comparison.Greater;
import org.uva.sea.ql.ast.expression.Comparison.GreaterOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.Less;
import org.uva.sea.ql.ast.expression.Comparison.LessOrEqual;
import org.uva.sea.ql.ast.expression.Comparison.NotEqual;
import org.uva.sea.ql.ast.expression.Literal.BooleanLiteral;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.expression.Literal.IntegerLiteral;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.expression.Logical.And;
import org.uva.sea.ql.ast.expression.Logical.Or;
import org.uva.sea.ql.ast.expression.Numerical.Add;
import org.uva.sea.ql.ast.expression.Numerical.Div;
import org.uva.sea.ql.ast.expression.Numerical.Mul;
import org.uva.sea.ql.ast.expression.Numerical.Sub;
import org.uva.sea.ql.ast.expression.Parenthesis.Parenthesis;
import org.uva.sea.ql.ast.expression.Unary.Negative;
import org.uva.sea.ql.ast.expression.Unary.Not;
import org.uva.sea.ql.ast.expression.Unary.Positive;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.FormVisitor;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.ComputedQuestionsVisitor;
import org.uva.sea.ql.ast.statement.IfElseStatement;
import org.uva.sea.ql.ast.statement.IfStatement;
import org.uva.sea.ql.ast.statement.IfStatementVisitor;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.QuestionsVisitor;
import org.uva.sea.ql.ast.statement.StatementVisitor;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.IntType;
import org.uva.sea.ql.ast.type.StrType;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.ast.type.UndefinedType;
import org.uva.sea.ql.ast.statement.Statement;


public class TypeChecker implements FormVisitor, StatementVisitor, ExpressionVisitor<Type> {
	
	private final Form form;
	private HashMap<String, IdentifierData> questionData;
//	private final QuestionsVisitor questionsVisitor;
//	private final ComputedQuestionsVisitor computedQuestionsVisitor;
//	private final IfStatementVisitor ifStatementVisitor;
	
	
	public TypeChecker(Form form) {
		this.form = form;
		this.questionData = new HashMap<>();
//		this.questionsVisitor = new QuestionsVisitor(form);
//		this.computedQuestionsVisitor = new ComputedQuestionsVisitor(form);
//		this.ifStatementVisitor = new IfStatementVisitor(form);
	}
	
//	public List<Question> getAllQuestions() {
//		
//		List<Question> questions = this.questionsVisitor.getQuestions();
//		List<ComputedQuestion> computedQuestions = this.computedQuestionsVisitor.getComputedQuestions();
//		questions.addAll(computedQuestions);
//		return questions;
//	}

	public Form getForm() {
		return form;
	}
	
	public void performTypeChecking() {	
		this.visitForm(form);
	}
	
	

	

	@Override
	public Type visit(Equal node) {
		return new BoolType();
	}

	@Override
	public Type visit(NotEqual node) {
		return new BoolType();
	}

	@Override
	public Type visit(Greater node) {
		return new BoolType();
	}

	@Override
	public Type visit(GreaterOrEqual node) {
		return new BoolType();
	}

	@Override
	public Type visit(Less node) {
		return new BoolType();
	}

	@Override
	public Type visit(LessOrEqual node) {
		return new BoolType();
	}

	@Override
	public Type visit(BooleanLiteral node) {
		return new BoolType();
	}

	@Override
	public Type visit(Identifier node) {
		
		for (IdentifierData identifierData: questionData.values())
			if (identifierData.getId().getValue().equals(node.getValue()))
				return identifierData.getType();
		
		return new UndefinedType();
	}

	@Override
	public Type visit(IntegerLiteral node) {
		return new IntType();
	}

	@Override
	public Type visit(StringLiteral node) {
		return new StrType();
	}

	@Override
	public Type visit(And node) {
		return new IntType();
	}

	@Override
	public Type visit(Or node) {
		return new IntType();
	}

	@Override
	public Type visit(Add node) {
		return new IntType();
	}

	@Override
	public Type visit(Sub node) {
		return new IntType();
	}

	@Override
	public Type visit(Mul node) {
		return new IntType();
	}

	@Override
	public Type visit(Div node) {
		return new IntType();
	}
	
	/** parenthsis to be removed! **/

	@Override
	public Type visit(Parenthesis node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type visit(Not node) {
		return new BoolType();
	}

	@Override
	public Type visit(Positive node) {
		return new IntType();
	}

	@Override
	public Type visit(Negative node) {
		return new IntType();
	}
	
	/****************************
	******Statement Visitor******
	*****************************/

	@Override
	public void visitComputedQuestion(ComputedQuestion computedQuestion) {
		if (labelIsDuplicate(computedQuestion))
			System.out.println("Duplicate label found!");
		else {
			//System.out.println("Pass");
			insertAtHashMap(computedQuestion.getLabel(),computedQuestion.getId(),computedQuestion.getType());
		}
	}

	@Override
	public void visitQuestion(Question question) {
		if (labelIsDuplicate(question))
			System.out.println("Duplicate label found!");
		else {
			//System.out.println("Pass");
			insertAtHashMap(question.getLabel(),question.getId(),question.getType());
		}
	}

	private boolean labelIsDuplicate(Question question) {
		if (questionData.containsKey(question.getLabel()))
			return true;

		return false;
	}
	
	private void insertAtHashMap(String label,Identifier id,Type type) {
		this.questionData.put(label, new IdentifierData(type, id));
	}

	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		if (isConditionBooleanType(ifStatement)) {
			System.out.println("Condition is boolean");
			ifStatement.getBlock().accept(this);
		}
		else
			System.out.println("Condition is not boolean");
	}
	
	private boolean isConditionBooleanType(IfStatement ifStatement) {
		Type type = ifStatement.getExpression().accept(this);
		if (type.getTypeName().equals("boolean"))
			return true;
		
		return false;
	}

	
	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		ifElseStatement.getBlock().accept(this);
		ifElseStatement.getElseBlock().accept(this);
	}
	
	/****************************
	*********Form Visitor********
	*****************************/
	
	
	@Override
	public void visitForm(Form form) {
		form.getBlock().accept(this);
		
	}

	@Override
	public void visitBlock(Block block) {
		for (Statement statement: block.getStatements())
			statement.accept(this);
	}

}