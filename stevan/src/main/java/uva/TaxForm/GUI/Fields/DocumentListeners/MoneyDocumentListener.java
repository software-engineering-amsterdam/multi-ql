package uva.TaxForm.GUI.Fields.DocumentListeners;

import java.awt.Component;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.event.DocumentEvent;

import uva.TaxForm.GUI.Fields.MoneyTextField;
import uva.ql.deprecated.ASTExpression;
import uva.ql.deprecated.ASTNode;
import uva.ql.deprecated.ASTVariable;
import uva.ql.deprecated.ASTVisitorToGUIUtils;

public class MoneyDocumentListener  extends AbstractDocumentListener {
	
	private Stack<ASTNode> treeNodeStack;
	private Stack<ASTExpression> operatorExpStack;
	private ASTVariable vParent;
	private Component cParent;
	private MoneyTextField mtfParent;
	private JFrame frame;
	
	public MoneyDocumentListener (JFrame frame, ASTNode parent, Stack<ASTNode> treeStack, Stack<ASTExpression> operatorStack) {
		this.frame = frame;
		this.treeNodeStack = treeStack;
		this.operatorExpStack = operatorStack;
		this.vParent = (ASTVariable) parent;
		this.cParent = ASTVisitorToGUIUtils.getComponentByName(frame, vParent.getName());
		this.mtfParent = (MoneyTextField) cParent;
		
		mtfParent.setEditable(false);
		mtfParent.setFocusable(false);
		
	}
	
	@Override
	public void insertUpdate(DocumentEvent e) {
		double total = Double.parseDouble(mtfParent.getText());
		boolean firstPass = true;
		ASTVariable vLeft, vRight;
		Component cLeft, cRight;
		MoneyTextField mtfLeft = null, mtfRight;
		
		// Weird warning...
		Stack<ASTNode> tempTNS = (Stack<ASTNode>) treeNodeStack.clone();
		Stack<ASTExpression> tempOES = (Stack<ASTExpression>)  operatorExpStack.clone();
		
		while (!tempTNS.isEmpty()) {
			
			if (firstPass) {
				vLeft = (ASTVariable) tempTNS.pop();
				cLeft = ASTVisitorToGUIUtils.getComponentByName(frame, vLeft.getName());
				mtfLeft = (MoneyTextField) cLeft;
				total = Double.parseDouble(mtfLeft.getText());
				firstPass = false;
				mtfLeft.getDocument().addDocumentListener(this);
			}
			vRight = (ASTVariable) tempTNS.pop();
			cRight = ASTVisitorToGUIUtils.getComponentByName(frame, vRight.getName());
			mtfRight = (MoneyTextField) cRight;
			mtfRight.getDocument().addDocumentListener(this);
			
			switch (tempOES.pop().getExpressionType()) {
			
				case ASTExpression.MINUS_EXP:
					total -= Double.parseDouble(mtfRight.getText());
					break;
				case ASTExpression.ADD_EXP:
					total += Double.parseDouble(mtfRight.getText());
					break;	
				default: break;
			}
			mtfParent.setText(Double.toString(total));
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {}

	@Override
	public void changedUpdate(DocumentEvent e) {}
	
}
