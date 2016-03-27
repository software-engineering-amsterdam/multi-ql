package qlqui;

/**
 *
 * @author Dominique
 */

import AST.form.ComputedQuestion;
import AST.form.Form;
import AST.form.Question;
import AST.types.Bool;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import qlqui.widget.BooleanWidget;
import qlqui.widget.ComputedQuestionWidget;
import qlqui.widget.FieldWidget;
import qlqui.widget.Widget;

public class GUI extends JFrame {
	private JPanel contentPane;
	private List<Widget> widgets;
	private FormDataManager dataManager;
	private Form form;
	
	static String FA = "/Users/Dominique/NetBeansProjects/multi-ql/Mickeydus/QL/src/ql/Form.ql";
	
	public GUI(Form form, FormDataManager dataManager) {
//		this.form = form;
//		this.widgets = new ArrayList<Widget>();
//		this.dataManager = dataManager;
//		initializeGUI(form.getQuestions());
		
	}
	
//	private Box initializeFormComponents(List<Question> components) {
//		// add the components
//		Box questionsContainer = Box.createVerticalBox();
//	
//		for (Question question : components) {
//			if (question instanceof ComputedQuestion) { //TODO: get rid of this uglyness!!!!!!!
//				Widget w = new ComputedQuestionWidget((ComputedQuestion) question, questionsContainer, dataManager);
//				widgets.add(w);
//				dataManager.attach(w);
//			} else if (question.getType().equals(new Bool())) {
//				Widget w = new BooleanWidget(question, questionsContainer, dataManager);
//				widgets.add(w);
//				dataManager.attach(w);
//			} else {
//				Widget w = new FieldWidget(question, questionsContainer, dataManager);
//				widgets.add(w);
//				dataManager.attach(w);
//			}
//		}
//		
//		return questionsContainer;
//	}
//	
//	private void initializeGUI(List<Question> components) {
//		initializeFrame();
//		contentPane.add(initializeFormComponents(components));
//		contentPane.add(Box.createRigidArea(new Dimension(20,contentPane.getWidth())));
//		contentPane.add(initializeControls());
//		// set visibility
//		dataManager.notifyAllObserversOnVisibility();
//	}
//	
//	private void initializeFrame() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 600, 450);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
//	}
//	
//	private Box initializeControls() {
//		Box controlBox = Box.createHorizontalBox();
//		JButton exportButton = new JButton("Export to XML");
//		exportButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				JFileChooser fileChooser = new JFileChooser();
//				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//			    // disable the "All files" option.
//				fileChooser.setAcceptAllFileFilterUsed(false);
//				int returnVal = fileChooser.showOpenDialog(contentPane);
//				
//	            if (returnVal == JFileChooser.APPROVE_OPTION) {
//	            	File curDir = fileChooser.getCurrentDirectory();
//	            } else {
//	                
//	            }
//			}
//		});
//		
//		JButton importButton = new JButton("Import file");
//		importButton.addActionListener((ActionEvent arg0) -> {
//                    // import a file.
//                });
//		
//		controlBox.add(importButton);
//		controlBox.add(exportButton);
//		return controlBox;
//	}
//	
}
