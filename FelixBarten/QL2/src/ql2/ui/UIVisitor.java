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

		UICalculatedQuestion question = new UICalculatedQuestion(node, parent);

		node.getCalculation().accept(this);
		return null;
	}




	@Override
	public Object visit(IfStatement node) {

		try { 
			boolean condition = (boolean) node.getCondition().accept(this);
			
			if (condition) {
				// enable questions 
				node.getBlock().accept(this);
			} 
			
		} catch (NullPointerException e) {
			// log error msg 
			System.out.println(e.getMessage());
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
