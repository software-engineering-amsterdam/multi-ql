package nl.nicasso.ql.gui;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.ast.statements.ComputedQuestion;
import nl.nicasso.ql.ast.statements.IfElseStatement;
import nl.nicasso.ql.ast.statements.IfStatement;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.ast.statements.Statement;
import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.structures.Form;
import nl.nicasso.ql.ast.types.BooleanType;
import nl.nicasso.ql.ast.types.IntegerType;
import nl.nicasso.ql.ast.types.MoneyType;
import nl.nicasso.ql.ast.types.StringType;
import nl.nicasso.ql.gui.panels.BlockPanel;
import nl.nicasso.ql.gui.panels.IfElseStatementPanel;
import nl.nicasso.ql.gui.panels.IfStatementPanel;
import nl.nicasso.ql.gui.panels.Panel;
import nl.nicasso.ql.gui.panels.QuestionPanel;
import nl.nicasso.ql.gui.questionFields.BooleanQuestionField;
import nl.nicasso.ql.gui.questionFields.IntegerQuestionField;
import nl.nicasso.ql.gui.questionFields.MoneyQuestionField;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.questionFields.TextQuestionField;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;
import nl.nicasso.ql.visitors.TypeVisitor;

public class Gui implements StructureVisitor<Panel>, StatementVisitor<Panel, Void>, TypeVisitor<QuestionField, Identifier> {
	
	private boolean debug = true;
	
	private MainFrame main;
	
	private SymbolTable symbolTable;
	
	public Gui(SymbolTable symbolTable, MainFrame main) {
		this.main = main;
		
		this.symbolTable = symbolTable;
	}

	@Override
	public Panel visit(Form value) {
		if (debug) {
			System.out.println("Form");
		}

		value.getBlock().accept(this);
		
		//ControlPanel cp = new ControlPanel();
		
		//main.addPanel(cp);
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

		return bp;
	}

	@Override
	public Panel visit(Question value, Void context) {
		if (debug) {
			System.out.println("Question: "+value.getId().getValue());
		}
		
		QuestionField field = value.getType().accept(this, value.getId());
		
		QuestionPanel qp = new QuestionPanel(value, field, symbolTable);
						
		return qp;
	}

	@Override
	public Panel visit(ComputedQuestion value, Void context) {
		if (debug) {
			System.out.println("ComputedQuestion: "+value.getId().getValue());
		}
		
		QuestionField field = value.getType().accept(this, value.getId());
		
		QuestionPanel qp = new QuestionPanel(value, field, symbolTable);

		return qp;
	}

	@Override
	public Panel visit(IfStatement value, Void context) {
		if (debug) {
			System.out.println("IfStatement");
		}
		
		IfStatementPanel panel = new IfStatementPanel();
		
		//Evaluator evaluator = new Evaluator(symbolTable);
		//Value a = value.getExpr().accept(evaluator);
		//System.out.println("VALUE VAN DE IF IS: "+a.getValue());
		
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

	@Override
	public QuestionField visit(BooleanType value, Identifier identifier) {
		return new BooleanQuestionField(identifier);
	}

	@Override
	public QuestionField visit(MoneyType value, Identifier identifier) {
		return new MoneyQuestionField(identifier);
	}

	@Override
	public QuestionField visit(StringType value, Identifier identifier) {
		return new TextQuestionField(identifier);
	}

	@Override
	public QuestionField visit(IntegerType value, Identifier identifier) {
		return new IntegerQuestionField(identifier);
	}

}
