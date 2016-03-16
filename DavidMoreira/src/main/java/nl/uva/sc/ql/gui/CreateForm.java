package nl.uva.sc.ql.gui;

import java.util.List;
import java.util.function.Function;

import nl.uva.sc.ql.compiler.parser.ast.AdditionNode;
import nl.uva.sc.ql.compiler.parser.ast.AndNode;
import nl.uva.sc.ql.compiler.parser.ast.AssignedQuestionNode;
import nl.uva.sc.ql.compiler.parser.ast.BlockNode;
import nl.uva.sc.ql.compiler.parser.ast.BooleanNode;
import nl.uva.sc.ql.compiler.parser.ast.ConditionBlockNode;
import nl.uva.sc.ql.compiler.parser.ast.DifferentNode;
import nl.uva.sc.ql.compiler.parser.ast.DivisionNode;
import nl.uva.sc.ql.compiler.parser.ast.EqualsNode;
import nl.uva.sc.ql.compiler.parser.ast.ExpressionNode;
import nl.uva.sc.ql.compiler.parser.ast.FormNode;
import nl.uva.sc.ql.compiler.parser.ast.GreatEqualsThanNode;
import nl.uva.sc.ql.compiler.parser.ast.GreatThanNode;
import nl.uva.sc.ql.compiler.parser.ast.IdentifierNode;
import nl.uva.sc.ql.compiler.parser.ast.IfNode;
import nl.uva.sc.ql.compiler.parser.ast.IntegerNode;
import nl.uva.sc.ql.compiler.parser.ast.LessEqualsThanNode;
import nl.uva.sc.ql.compiler.parser.ast.LessThanNode;
import nl.uva.sc.ql.compiler.parser.ast.ListStatementsNode;
import nl.uva.sc.ql.compiler.parser.ast.MultiplicationNode;
import nl.uva.sc.ql.compiler.parser.ast.Node;
import nl.uva.sc.ql.compiler.parser.ast.NotNode;
import nl.uva.sc.ql.compiler.parser.ast.OrNode;
import nl.uva.sc.ql.compiler.parser.ast.QuestionNode;
import nl.uva.sc.ql.compiler.parser.ast.StatementNode;
import nl.uva.sc.ql.compiler.parser.ast.StringNode;
import nl.uva.sc.ql.compiler.parser.ast.SubtractionNode;
import nl.uva.sc.ql.compiler.parser.value.Value;
import nl.uva.sc.ql.compiler.typechecker.Visitor;
import nl.uva.sc.ql.gui.form.Form;
import nl.uva.sc.ql.gui.form.ConditionBlock;
import nl.uva.sc.ql.gui.form.IfStatement;
import nl.uva.sc.ql.gui.form.Question;
import nl.uva.sc.ql.gui.form.QuestionBoolean;
import nl.uva.sc.ql.gui.form.QuestionInteger;
import nl.uva.sc.ql.gui.form.QuestionString;
import nl.uva.sc.ql.gui.state.State;

public class CreateForm implements Visitor {

	private Form form = null;
	private IfStatement ifForm = null;
	private ConditionBlock conditionBlockForm = null;
	private State state;
		
	public CreateForm(State state) {
		this.state = state;
	}
	
	public Form getForm(Node ast){
		ast.accept(this);
		return form;
	}

	@Override
	public void visit(AdditionNode additionNode) {		
	}

	@Override
	public void visit(AndNode andNode) {	
	}

	@Override
	public void visit(AssignedQuestionNode assignedQuestionNode) {
		QuestionNode questionNode = assignedQuestionNode.getVariableNode();
		ExpressionNode expression = assignedQuestionNode.getExpression();	
		
		auxVisitQuestionNode(questionNode, expression, false);
	}

	@Override
	public void visit(BlockNode blockNode) {
		blockNode.getListStatements().accept(this);		
	}

	@Override
	public void visit(BooleanNode booleanNode) {		
	}

	@Override
	public void visit(ConditionBlockNode conditionBlockNode) {
		ExpressionNode expression = conditionBlockNode.getExpression();
		BlockNode block = conditionBlockNode.getBlock();
		
		conditionBlockForm = new ConditionBlock(form, state, expression);
		
		block.accept(this);
		
		ifForm.addConditionBlock(conditionBlockForm);
		conditionBlockForm = null;		
	}

	@Override
	public void visit(DifferentNode differentNode) {		
	}

	@Override
	public void visit(DivisionNode divisionNode) {		
	}

	@Override
	public void visit(EqualsNode equalsNode) {		
	}

	@Override
	public void visit(FormNode formNode) {
		form = new Form(formNode.getName());
		formNode.getBlock().accept(this);		
	}

	@Override
	public void visit(GreatEqualsThanNode greatEqualsThanNode) {		
	}

	@Override
	public void visit(GreatThanNode greatThanNode) {		
	}

	@Override
	public void visit(IdentifierNode identifierNode) {		
	}

	@Override
	public void visit(IfNode ifNode) {
		List<ConditionBlockNode> conditions = ifNode.getConditions();
		BlockNode elseBlock = ifNode.getElseBlock();
		
		ifForm = new IfStatement(form);
	
		for(ConditionBlockNode cbn : conditions){
			cbn.accept(this);
		}
		
		if(elseBlock != null) {
			elseBlock.accept(this);
		}
		
		form.addIfForm(ifForm);
		ifForm = null;
	}

	@Override
	public void visit(IntegerNode integerNode) {		
	}

	@Override
	public void visit(LessEqualsThanNode lessEqualsThanNode) {		
	}

	@Override
	public void visit(LessThanNode lessThanNode) {		
	}

	@Override
	public void visit(ListStatementsNode listStatementsNode) {
		for (StatementNode s : listStatementsNode.getListStatements()){
			s.accept(this);
		}		
	}

	@Override
	public void visit(MultiplicationNode multiplicationNode) {		
	}

	@Override
	public void visit(NotNode notNode) {		
	}

	@Override
	public void visit(OrNode orNode) {		
	}

	@Override
	public void visit(QuestionNode variableNode) {
		auxVisitQuestionNode(variableNode, null, true);
	}

	@Override
	public void visit(StringNode stringNode) {		
	}

	@Override
	public void visit(SubtractionNode subtractionNode) {		
	}

	private void auxVisitQuestionNode(QuestionNode questionNode, ExpressionNode valueNode, boolean editable){
		String questionText = questionNode.getQuestion();
		String identifier = questionNode.getIdentifier();
		String type = questionNode.getType();
		
		Function<String, Question> chooseNode = s -> { 
			Question question = null;
			switch(s) {
				case "string":
					question = new QuestionString(state, questionText, identifier, valueNode, editable);
					break;
				case "boolean":
					question = new QuestionBoolean(state, questionText, identifier, valueNode, editable);
					break;
				case "int":
					question = new QuestionInteger(state, questionText, identifier, valueNode, editable);
					break;
			}
			return question;
		};
		
		Question question = chooseNode.apply(type);
		
		if(conditionBlockForm != null){
			conditionBlockForm.addQuestion(question);
		} else if(ifForm != null) {
			ifForm.addQuestion(question);
		} else {
			form.addQuestion(question);
		}
		
		// this fills the map of values
		Value value = valueNode == null ? null : valueNode.eval(state);
		state.add(identifier, value);
	}
}
