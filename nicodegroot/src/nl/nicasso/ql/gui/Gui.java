package nl.nicasso.ql.gui;

import nl.nicasso.ql.ast.statements.ComputedQuestion;
import nl.nicasso.ql.ast.statements.IfElseStatement;
import nl.nicasso.ql.ast.statements.IfStatement;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.ast.statements.Statement;
import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.structures.Form;
import nl.nicasso.ql.gui.panels.BlockPanel;
import nl.nicasso.ql.gui.panels.ControlPanel;
import nl.nicasso.ql.gui.panels.IfElseStatementPanel;
import nl.nicasso.ql.gui.panels.IfStatementPanel;
import nl.nicasso.ql.gui.panels.Panel;
import nl.nicasso.ql.gui.panels.QuestionPanel;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;

public class Gui implements StructureVisitor<Panel>, StatementVisitor<Panel, Void> {
	
	private boolean debug = true;
	
	private MainFrame main;

	public Gui() {
		// Maybe move this to visit form?
		main = new MainFrame();
	}

	@Override
	public Panel visit(Form value) {
		if (debug) {
			System.out.println("Form");
		}

		value.getBlock().accept(this);
		
		ControlPanel cp = new ControlPanel();
		main.addPanel(cp);
		main.updateMainFrame();
		
		return null;
	}

	@Override
	public Panel visit(Block value) {
		if (debug) {
			System.out.println("Block");
		}
		
		BlockPanel bp = new BlockPanel();

		for (Statement cur : value.getStatements()) {
			Panel currentPanel = cur.accept(this);
			bp.addPanel(currentPanel);
		}
		
		main.addPanel(bp);
		//main.updateMainFrame();

		return bp;
	}

	@Override
	public Panel visit(Question value, Void context) {
		if (debug) {
			System.out.println("Question");
		}
		
		QuestionPanel qp = new QuestionPanel(value);
						
		return qp;
	}

	@Override
	public Panel visit(ComputedQuestion value, Void context) {
		if (debug) {
			System.out.println("ComputedQuestion");
		}
		
		QuestionPanel qp = new QuestionPanel(value);

		return qp;
	}

	@Override
	public Panel visit(IfStatement value, Void context) {
		if (debug) {
			System.out.println("IfStatement");
		}
		
		IfStatementPanel panel = new IfStatementPanel();
		
		Panel ifBlockPanel = value.getBlock_if().accept(this);
		
		panel.addIfPanel(ifBlockPanel);
		
		return panel;
	}

	@Override
	public Panel visit(IfElseStatement value, Void context) {
		if (debug) {
			System.out.println("IfElseStatement");
		}
		
		IfElseStatementPanel panel = new IfElseStatementPanel();
		
		Panel ifBlockPanel = value.getBlock_if().accept(this);
		Panel elseBlockPanel = value.getBlock_else().accept(this);
		
		panel.addIfPanel(ifBlockPanel);
		panel.addElsePanel(elseBlockPanel);
		
		return panel;
	}

}
