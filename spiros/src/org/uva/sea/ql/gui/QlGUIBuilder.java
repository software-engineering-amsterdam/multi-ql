package org.uva.sea.ql.gui;


import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import org.uva.sea.ql.ast.block.Block;
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
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.gui.questionItems.BoolQuestionItem;
import org.uva.sea.ql.gui.questionItems.IntQuestionItem;
import org.uva.sea.ql.gui.questionItems.QuestionItem;
import org.uva.sea.ql.gui.questionItems.StrQuestionItem;
import org.uva.sea.ql.gui.widgets.CheckBox;
import org.uva.sea.ql.gui.widgets.TextField;

// typevisitor not used so far . . .


public class QlGUIBuilder implements FormVisitor, StatementVisitor {
	
	private JFrame frame;
	private GUIPanel panel;		// fix this, settings not on frame...
	private List<QuestionItem> questionItems;
	
	
	public QlGUIBuilder(Form form) {
		
		this.panel = new GUIPanel();
		this.questionItems = new ArrayList<>();
		
		collectQuestions(form);	// maybe collectQuestionsAndIfConditions		
		
		frame = new JFrame("Questionnaire");
		frame.setLayout(new MigLayout("al left, gapy 66"));
		
		for (QuestionItem item: questionItems)
			addItem(item);			// mallon de xreiazetai...add sto GUIPanel...
		
		frame.setSize(550,550);
		frame.setVisible(true);
		
	}

	
	private void collectQuestions(Form form) {
		visitForm(form);
	}


	private void addItem (QuestionItem item) {
		this.frame.add(item.getWidget().getLabel());
		this.frame.add(item.getWidget().getWidget(),"wrap");
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
		
		System.out.println("Visiting computed question...");
		
		Type type = computedQuestion.getType();
		//Widget widget = widgetAccordingToType(type);
//		QuestionPanel questionPanel = new QuestionPanel(computedQuestion,widget);
//		panels.add(questionPanel.getPanel());
	}

	@Override
	public void visitQuestion(Question question) {
		
		System.out.println("Visiting question...");
		
		Type type = question.getType();
		
		QuestionItem questionItem = questionItemAccordingToType(type,question);	// de mou aresei... na ginei me visitor...
		// add sti lista me ta QuestionItem?
		
		//this.panel.addToPanel(questionItem);
		
		questionItems.add(questionItem);
		
	}
	
	private QuestionItem questionItemAccordingToType(Type type,Question question) {
		if (type instanceof BoolType)
			return new BoolQuestionItem(question,new CheckBox(question.getLabel()));
		else if (type instanceof IntType)
			return new IntQuestionItem(question,new TextField(question.getLabel()));	
		else
			return new StrQuestionItem(question,new TextField(question.getLabel()));
	}


	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public Widget visit(IntType intType) {
//		return new TextField();
//	}
//
//	@Override
//	public Widget visit(BoolType boolType) {
//		return new CheckBox();
//	}
//
//	@Override
//	public Widget visit(StrType strType) {
//		return new TextField();
//	}
//
//	@Override
//	public Widget visit(UndefinedType undefinedType) {
//		return null;
//	}

}