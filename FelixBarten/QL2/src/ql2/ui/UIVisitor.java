package ql2.ui;

import ql2.Context;
import ql2.EvalVisitor;
import ql2.ast.CalculatedQuestion;
import ql2.ast.InputQuestion;
import ql2.ast.statement.IfElseStatement;
import ql2.ast.statement.IfStatement;

public class UIVisitor extends EvalVisitor {
	
	private Context context;
	private QlGui parent;

	public UIVisitor (Context context, QlGui gui) 	{
		super(context);
		this.context = super.getContext();
		this.parent = gui;
	}
	
	@Override
	public Object visit(InputQuestion node) {
		UIInputQuestion question = new UIInputQuestion(node, parent);
		
		return null;
	}

	@Override
	public Object visit(CalculatedQuestion node) {

		Object value = node.getCalculation().accept(this);
		if (value != null) {
			context.addVariable(node.getQuestionID(), value);
		}
		UICalculatedQuestion question = new UICalculatedQuestion(node, parent);
		question.update();
		return null;
	}
	
	@Override
	public Object visit(IfStatement node) {
		Boolean condition = null;
		try { 
			//LiteralExpr = 
			condition = (boolean) node.getCondition().accept(this);
			if (condition != null && condition) {
				// enable questions 
				node.getBlock().accept(this);
			} 
		} catch (NullPointerException e) {
			// log error msg 
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Object visit(IfElseStatement node) {
		try { 
			boolean condition = (boolean) node.getIfStatement().getCondition().accept(this);
			// enable/disable questions 
			if (condition) {
				node.getIfStatement().getBlock().accept(this);
			} else {
				node.getElseBlock().accept(this);
			}
			
		} catch (NullPointerException e) {
			// log error msg 
			System.out.println(e.getMessage());
		}
		
		return null;
	}

	public Context getContext() {
		return context;
	}
	
	
	
	
	
}
