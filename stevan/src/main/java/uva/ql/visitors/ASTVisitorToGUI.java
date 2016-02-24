package uva.ql.visitors;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import uva.TaxForm.GUI.GUI;
import uva.TaxForm.GUI.GUIQuestion;
import uva.ql.deprecated.ASTBlock;
import uva.ql.deprecated.ASTExpression;
import uva.ql.deprecated.ASTIfStatement;
import uva.ql.deprecated.ASTNode;
import uva.ql.deprecated.ASTQuestion;
import uva.ql.deprecated.ASTVariable;

public class ASTVisitorToGUI {

	private GUI gui;
	
	public ASTVisitorToGUI(GUI gui) {
		this.gui = gui;
	}
	
	public void visit(ASTBlock ast) {
		visit(ast, this.gui.panel);
	}
	
	public void visit(ASTBlock ast, JPanel parentPanel) {
		
		if ( ast.size() > 0 ) {
			for ( int i=0; i<ast.size(); i++ ) {
				
				ASTNode node = ast.get(i);
				int nodeType = node.getNodeType();
				
				switch ( nodeType ) {
					case ASTNode.FORM:
						break;
					case ASTNode.QUESTION: 
						parentPanel.add(addQuestion(node));
						parentPanel.revalidate();
						break;
					case ASTNode.IF_STATEMENT: 
						parentPanel.add(addIfStatement(node));
						parentPanel.revalidate();
						break;
					default: break;
				}
			}
		}
	}

	private JPanel addIfStatement(ASTNode node) {
		final JPanel panel = addContainerPanel();
		
		ASTIfStatement stmnt = (ASTIfStatement) node;
		ASTExpression exp = (ASTExpression) stmnt.getExpression();
		ASTNode leftNode = stmnt.getLeftNode();
		ASTNode rightNode = stmnt.getRightNode();
		
		if (leftNode != null) {
			ASTBlock block = (ASTBlock) leftNode;
			visit(block, panel);
		}
		
		if (rightNode != null) {
			ASTBlock block = (ASTBlock) rightNode;
			visit(block, panel);
		}
		
		if (exp != null) {
			ASTVariable var = (ASTVariable) exp.getLeftNode();
			panel.setName(var.getName());
		}
		
		return panel;
	}
	
	private GUIQuestion addQuestion(ASTNode node) {
		
		ASTQuestion questionNode = (ASTQuestion) node;
		
		String label = questionNode.getLabel();
		ASTVariable var = (ASTVariable) questionNode.getExpression().getLeftNode();
		
		final GUIQuestion question = new GUIQuestion(label, var);
		question.setPreferredSize(new Dimension(this.gui.panel.getWidth()-30, 20));
		
		if (questionNode.getExpression().getExpressionType() == ASTExpression.ASSIGN_EXP) {
			questionNode.setComputed(true);
		}
		
		return question;
	}
	
	public JPanel addContainerPanel() {

		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.PAGE_AXIS));
		//containerPanel.setPreferredSize(new Dimension(this.gui.frame.getWidth() - 30, 20));
		//this.gui.frame.add(containerPanel);
		
		return containerPanel;
	}
}
