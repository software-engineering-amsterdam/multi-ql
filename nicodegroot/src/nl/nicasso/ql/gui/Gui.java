package nl.nicasso.ql.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import nl.nicasso.ql.ast.statements.ComputedQuestion;
import nl.nicasso.ql.ast.statements.IfElseStatement;
import nl.nicasso.ql.ast.statements.IfStatement;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.ast.statements.Statement;
import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.structures.Form;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;

public class Gui implements StructureVisitor<Void>, StatementVisitor<Void, Void> {
	
	private boolean debug = false;
	
	private JFrame frame;

	public Gui() {
		initGui();
	}
	
	private void initGui() {
		frame = new JFrame("Questionnaire");
        frame.setSize(1024, 768);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(0, 1));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.setVisible(true);
	}

	@Override
	public Void visit(Form value) {
		if (debug) {
			System.out.println("Form");
		}

		value.getBlock().accept(this);
		
		return null;
	}

	@Override
	public Void visit(Block value) {
		if (debug) {
			System.out.println("Block");
		}

		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}

		return null;
	}

	@Override
	public Void visit(Question value, Void context) {
		if (debug) {
			System.out.println("Question");
		}
						
		return null;
	}

	@Override
	public Void visit(ComputedQuestion value, Void context) {
		if (debug) {
			System.out.println("ComputedQuestion");
		}
						
		//value.getExpr().accept(this);
				
		return null;
	}

	@Override
	public Void visit(IfStatement value, Void context) {
		return null;
	}

	@Override
	public Void visit(IfElseStatement value, Void context) {
		return null;
	}

}
