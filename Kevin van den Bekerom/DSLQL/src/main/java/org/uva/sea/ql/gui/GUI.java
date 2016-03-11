package org.uva.sea.ql.gui;

import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.uva.sea.ql.ASTBuilder;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.form.ReachableQuestionsMap;
import org.uva.sea.ql.ast.stat.ComputedQuestion;
import org.uva.sea.ql.ast.stat.Question;
import org.uva.sea.ql.gui.widget.BooleanWidget;
import org.uva.sea.ql.gui.widget.ComputedQuestionWidget;
import org.uva.sea.ql.gui.widget.FieldWidget;
import org.uva.sea.ql.gui.widget.Widget;
import org.uva.sea.ql.type.BoolType;
import org.uva.sea.ql.type.StrType;
import org.uva.sea.ql.visit.VisibilityChecker;
import org.uva.sea.ql.visit.Visitor;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;


public class GUI extends JFrame {
	private JPanel contentPane;
	private Form form;
	private List<Widget> widgets;
	private FormDataManager dataManager;
	
	static String FA = "D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\SampleForm.txt";
	
	public GUI(Form form) {
		this.form = form;
		widgets = new ArrayList<Widget>();
		initializeDataManager();
		initializeGUI();
	}
	
	private void initializeGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		// add the components
		Box questionsContainer = Box.createVerticalBox();
		
		//TODO: HOW TO DISTINGUISH Question from ComputedQuestion?!!!
		
		for (Question question : form.getQuestions()) {
			if (question instanceof ComputedQuestion) { //TODO: get rid of this uglyness!!!!!!!
				Widget w = new ComputedQuestionWidget((ComputedQuestion) question, questionsContainer, dataManager);
				widgets.add(w);
				dataManager.attach(w);
			} else if (question.getType().equals(new BoolType())) {
				Widget w = new BooleanWidget(question, questionsContainer, dataManager);
				widgets.add(w);
				dataManager.attach(w);
			} else {
				Widget w = new FieldWidget(question, questionsContainer, dataManager);
				widgets.add(w);
				dataManager.attach(w);
			}
		}
		
		contentPane.add(questionsContainer);
		
		// set visibility
		dataManager.notifyAllObserversOnVisibility();
		
	}
	
	private void initializeDataManager() {
		dataManager = new FormDataManager(form.getValueMap(), initializeReachableQuestionsMap(), form.getMainBlock());
	}
	
	private ReachableQuestionsMap initializeReachableQuestionsMap() {
		List<String> qIdentifiers = new ArrayList<String>();
		
		for (Question question : form.getQuestions()) {
			qIdentifiers.add(question.getIdentifier());
		}
		
		ReachableQuestionsMap visibilityMap = new ReachableQuestionsMap(qIdentifiers);
		
		Visitor<ReachableQuestionsMap> v = new VisibilityChecker(form.getValueMap());
		form.getMainBlock().accept((VisibilityChecker) v, visibilityMap);
		
		return visibilityMap;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ASTBuilder builder = new ASTBuilder();
					GUI frame = new GUI(builder.buildAST(FA));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
