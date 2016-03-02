package uva.ql.deprecated;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import uva.TaxForm.GUI.GUI;
import uva.TaxForm.GUI.Fields.IntTextField;
import uva.TaxForm.GUI.Fields.MoneyTextField;
import uva.TaxForm.GUI.Fields.ActionListeners.AbstractActionListener;
import uva.TaxForm.GUI.Fields.ActionListeners.JCheckBoxActionListener;
import uva.TaxForm.GUI.Fields.DocumentListeners.IntTextFieldDocumentListener;
import uva.TaxForm.GUI.Fields.DocumentListeners.MoneyDocumentListener;
import uva.TaxForm.GUI.Fields.DocumentListeners.MoneyTextFieldDocumentListener;
import uva.ql.deprecated.ASTBlock;
import uva.ql.deprecated.ASTExpression;
import uva.ql.deprecated.ASTIfStatement;
import uva.ql.deprecated.ASTNode;
import uva.ql.deprecated.ASTQuestion;
import uva.ql.deprecated.ASTVariable;

public class ASTVisitorToGUIListeners {
	
	private GUI gui;
	
	public ASTVisitorToGUIListeners(GUI gui) {
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
						visitQuestion(node);
						break;
						
					case ASTNode.IF_STATEMENT: 
						visitIfStatement(node);
						break;
						
					default: break;
				}
			}
		}
	}
	
	public void visitIfStatement(ASTNode node) {
		ASTIfStatement stmnt = (ASTIfStatement) node;
		ASTNode leftNode = stmnt.getLeftNode();
		ASTNode rightNode = stmnt.getRightNode();
		
		if (leftNode != null) {
			ASTBlock block = (ASTBlock) leftNode;
			visit(block, this.gui.panel);
		}
		
		if (rightNode != null) {
			ASTBlock block = (ASTBlock) rightNode;
			visit(block, this.gui.panel);
		}
		
		if (stmnt.getExpression() != null) {
			
			ASTVariable var = (ASTVariable) stmnt.getExpression().getLeftNode();
			Component[] comps = this.gui.panel.getComponents();
			
			for(Component comp : comps) {
				
				JPanel panel = (JPanel) comp;
				if (panel.getName() == var.getName()) {
					visitExpression(stmnt.getExpression(), panel);
				}
			}
		}
	}

	public void visitQuestion(ASTNode node) {
		
		ASTQuestion questionNode = (ASTQuestion) node;
		
		if (questionNode.isComputed()) {
			ASTExpression exp = (ASTExpression) questionNode.getExpression();
			visitExpression(exp, null);
		}
	}
	
	public void visitExpression(ASTExpression exp, final JPanel panel) {
		
		/*System.out.println(ShuntingYardAlgorithm.astToPostfixString(exp));
		System.out.println(exp.getExpressionType());*/
		
		ArrayList<ASTNode> pfNodeList = ShuntingYardAlgorithm.astToPostfix(exp);
		
		if (exp.getExpressionType() == ASTExpression.NUM_VAR_EXP) {
			
			ASTVariable var = (ASTVariable) exp.getLeftNode();
			
			if (var.getValue().isEmpty()) {
				AbstractActionListener.enablePanel(panel, false);
			}
			
			Component c = ASTVisitorToGUIUtils.getComponentByName(this.gui.frame, var.getName());
			
			// CheckBox
			try {
				final JCheckBox checkBox = (JCheckBox) c;
				checkBox.addActionListener( 
						new JCheckBoxActionListener(checkBox, panel) );
			} catch (ClassCastException e) {}
			
			// IntTextField
			try {
				final IntTextField textField = (IntTextField) c;
				textField.getDocument().addDocumentListener( 
						new IntTextFieldDocumentListener(textField, panel) );
			} catch (ClassCastException e) {}
			
			// MoneyTextField
			try {
				final MoneyTextField textField = (MoneyTextField) c;
				textField.getDocument().addDocumentListener( 
						new MoneyTextFieldDocumentListener(textField, panel) );
			} catch (ClassCastException e) {}
			
		}
		else if (exp.getExpressionType() == ASTExpression.ASSIGN_EXP) {
			
			if (pfNodeList.size() == 1) {
				System.out.println("List size = 1");
			} 
			else {
				ASTNode parent = null;
				Stack<ASTNode> treeStack = new Stack<ASTNode>();
				Stack<ASTExpression> operatorStack = new Stack<ASTExpression>();
				Stack<ASTNode> pfNodeStack = new Stack<ASTNode>();
				
				pfNodeStack.addAll(pfNodeList);
				
				while( !pfNodeStack.isEmpty() ) {
					
					ASTNode node = pfNodeStack.pop();
					
					if (node.getNodeType() == ASTNode.EXPRESSION) {
						operatorStack.add( (ASTExpression) node );
					}
					else if (node.getNodeType() != ASTNode.EXPRESSION) {
						treeStack.add(node);
					}
				}
				
				if (treeStack.size() >= 3 && operatorStack.size() > 1) {
					parent = treeStack.pop();
					operatorStack.remove(0);
					
					for (ASTNode node: treeStack) {
					
						switch ( ((ASTVariable) parent).getType() ) {
						
							case ASTVariable.MONEY:
								ASTVariable v = (ASTVariable) node;
								MoneyTextField mtf = (MoneyTextField) ASTVisitorToGUIUtils.getComponentByName(this.gui.frame, v.getName());
								mtf.getDocument().addDocumentListener( new MoneyDocumentListener(this.gui.frame, parent, treeStack, operatorStack) );
								break;
								
							default: break;
						}
					}
				}
			}
		}
	}
}
