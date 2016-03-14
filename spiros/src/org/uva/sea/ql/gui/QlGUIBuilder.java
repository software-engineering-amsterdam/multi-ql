package org.uva.sea.ql.gui;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.expression.Literal.Identifier;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.FormVisitor;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.IfElseStatement;
import org.uva.sea.ql.ast.statement.IfStatement;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.Statement;
import org.uva.sea.ql.ast.statement.StatementVisitor;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.IntType;
import org.uva.sea.ql.ast.type.StrType;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.ast.type.TypeVisitor;
import org.uva.sea.ql.ast.type.UndefinedType;
import org.uva.sea.ql.evaluator.Evaluator;
import org.uva.sea.ql.gui.questionItems.BoolQuestionItem;
import org.uva.sea.ql.gui.questionItems.IntQuestionItem;
import org.uva.sea.ql.gui.questionItems.QuestionItem;
import org.uva.sea.ql.gui.questionItems.StrQuestionItem;
import org.uva.sea.ql.gui.widgets.CheckBox;
import org.uva.sea.ql.gui.widgets.TextField;
import org.uva.sea.ql.gui.widgets.Widget;

// typevisitor not used so far . . .


public class QlGUIBuilder implements FormVisitor, StatementVisitor, TypeVisitor {
	
	private JFrame frame;
	private GUIPanel panel;		// do i need this?
	private List<QuestionItem> questionItems;
	private CollectIdentifiersAndDependencies identifiers;		// needed?
	private CollectIdentifiersInConditions identifiersInCOnditions;
	//private Evaluator evaluator;
	
	//private Map
	
	
	public QlGUIBuilder(Form form) {
		
		this.panel = new GUIPanel();
		this.questionItems = new ArrayList<>();
		
		this.identifiers = new CollectIdentifiersAndDependencies(form);
		this.identifiersInCOnditions = new CollectIdentifiersInConditions(form);
		
//		for(Identifier id: identifiers.getIdentifiers())
//			System.out.println("Exw ton identifier" + id.getValue());
		
		
		
		collectQuestions(form);	// maybe collectQuestionsAndIfConditions		
		
		frame = new JFrame("Questionnaire");
		frame.setLayout(new MigLayout("al left, gapy 66"));
		
		for (QuestionItem item: questionItems)
			addItem(item);			// mallon de xreiazetai...add sto GUIPanel...
			//addWidget(item.getWidget());
		
		//frame.add(panel.getPanel());
		frame.setSize(550,550);
		frame.setVisible(true);
		
	}
	
	public void addWidget (Widget widget) {
		this.panel.add(widget);
	}

	
	private void collectQuestions(Form form) {
		visitForm(form);
	}


	private void addItem (QuestionItem item) {
		
		// add sto frame h kainourio Panel? -- klasi GUIPanel?
		
		JPanel panel = new JPanel();
		panel.add(item.getWidget().getLabel());
		panel.add(item.getWidget().getComponent());
		//panel.setLayout(new MigLayout());
		this.frame.add(panel,"wrap");
		
//		this.frame.add(item.getWidget().getLabel());
//		this.frame.add(item.getWidget().getComponent(),"wrap");
	}


	@Override
	public void visitForm(Form form) {
		// listener goes here i guess . . .
		form.getBlock().accept(this);
	}


	@Override
	public void visitBlock(Block block) {
		
		System.out.println("Visiting block");
		
		for (Statement statement: block.getStatements()) {
			statement.accept(this);
		}	
		
	}
	
		

	@Override
	public void visitComputedQuestion(ComputedQuestion computedQuestion) {
		
		// not the functionality i want
		
		System.out.println("Visiting computed question...");
		Type type = computedQuestion.getType();
		
		QuestionItem questionItem = type.accept(this,computedQuestion);	// rename : accept--> collectItems?
		// add sti lista me ta QuestionItem?
		
		questionItems.add(questionItem);
	}

	@Override
	public void visitQuestion(Question question) {
		
		System.out.println("Visiting question...");
		Type type = question.getType();
		
		QuestionItem questionItem = type.accept(this,question);	// rename : accept--> collectItems?
		// add sti lista me ta QuestionItem?
		
		//this.panel.addToPanel(questionItem);
		questionItems.add(questionItem);
		
	}


	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public QuestionItem visit(IntType intType, Question question) {
		return new IntQuestionItem(question,new TextField(question.getLabel()));
	}

	@Override
	public QuestionItem visit(BoolType boolType, Question question) {
		return new BoolQuestionItem(question,new CheckBox(question.getLabel()));
	}

	@Override
	public QuestionItem visit(StrType strType, Question question) {
		return new StrQuestionItem(question,new TextField(question.getLabel()));
	}

}