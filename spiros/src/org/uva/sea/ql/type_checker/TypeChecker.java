package org.uva.sea.ql.type_checker;

import java.util.ArrayList;
import java.util.List;

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
//	private final QuestionsVisitor questionsVisitor;
//	private final ComputedQuestionsVisitor computedQuestionsVisitor;
//	private final IfStatementVisitor ifStatementVisitor;
	
	
	public TypeChecker(Form form) {
		this.form = form;
//		this.questionsVisitor = new QuestionsVisitor(form);
//		this.computedQuestionsVisitor = new ComputedQuestionsVisitor(form);
//		this.ifStatementVisitor = new IfStatementVisitor(form);
	}
	
//	public List<Question> getAllQuestions() {
//		// mallon edw to VisitForm...
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
		boolean duplicatedLabelsFound = checkForDuplicatedLabels();
		boolean booleanConditions = checkBooleanConditions();
		System.out.println("alles goet!");
	}
	
	private boolean checkBooleanConditions() {
		// TODO Auto-generated method stub
//		List<IfStatement> ifStatements = this.ifStatementVisitor.getIfStatements();
//		System.out.println("Size of IfStatements is " + ifStatements.size());
//		for (IfStatement ifStatement: ifStatements) {
//			Expression expression = ifStatement.getExpression();
//			if (expression.getTypeOfExpression(this.form) instanceof BoolType)		// allakse to!
//				System.out.println("This is boolean type: " +expression.getClass().toString());
//			else if (expression.getTypeOfExpression(this.form) instanceof UndefinedType)
//				//System.out.println("wtf");
//				System.out.println("Undefined type: " + expression.getClass().toString());
//			
//			else
//				//System.out.println("wtf");
//				System.out.println("This is not boolean type: " + expression.getClass().toString());
//			
//		}
		return false;
	}

	private boolean checkForDuplicatedLabels() {
//		List<Question> questions = this.getAllQuestions();
//		
//		if (questions.isEmpty())
//			System.out.println("Fuck ");
//		else
//			System.out.println("The size of the questionsList is " + questions.size());
//		List<String> labels = new ArrayList<String>();
//		
//		for (Question question: questions) {
//			String label = question.getLabel();
//			
//			if (labels.contains(label)) {
//				System.out.println("Duplicate label found ");
//				return false;
//			}
//			
//			else 
//				labels.add(label);
//			
//		}
//		
//		System.out.println("No duplicates found ");
		return true;
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
		// TODO Auto-generated method stub
		
		// to do for boolean conditions
		return null;
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

	@Override
	public void visitComputedQuestion(ComputedQuestion computedQuestion) {
		// TODO Auto-generated method stub
		
		// TO DO
	}

	@Override
	public void visitQuestion(Question question) {
		// TODO Auto-generated method stub
		
		// TO DO!
		
	}

	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		
		// TO DO
		
	}

	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		
		// TO DO

	}

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