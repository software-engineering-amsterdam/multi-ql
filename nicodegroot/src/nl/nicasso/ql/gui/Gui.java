package nl.nicasso.ql.gui;

import java.util.ArrayList;
import java.util.List;

import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.ast.expressions.conditional.Not;
import nl.nicasso.ql.ast.literals.BooleanLit;
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
import nl.nicasso.ql.gui.panels.Panel;
import nl.nicasso.ql.gui.panels.QuestionPanel;
import nl.nicasso.ql.gui.questionFields.BooleanQuestionField;
import nl.nicasso.ql.gui.questionFields.IntegerQuestionField;
import nl.nicasso.ql.gui.questionFields.MoneyQuestionField;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.questionFields.TextQuestionField;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.values.Value;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;
import nl.nicasso.ql.visitors.TypeVisitor;

public class Gui implements StructureVisitor<List<Panel>, Expression>, StatementVisitor<List<Panel>, Expression>, TypeVisitor<QuestionField, Identifier> {
	
	private boolean debug = true;
	
	private MainFrame main;
	
	private SymbolTable symbolTable;
	
	public Gui(SymbolTable symbolTable, MainFrame main) {
		this.main = main;
		
		this.symbolTable = symbolTable;
	}

	@Override
	public List<Panel> visit(Form value, Expression ignore) {
		if (debug) {
			System.out.println("Form");
		}

		List<Panel> blockPanel = value.getBlock().accept(this, new BooleanLit(true));
		
		for (Panel p : blockPanel) {
			main.addPanel(p);
		}
		
		main.addPanelsToMainFrame();
		
		return null;
	}

	@Override
	public List<Panel> visit(Block value, Expression expr) {
		if (debug) {
			System.out.println("Block");
		}
		
		List<Panel> panelList = new ArrayList<Panel>();

		// @TODO Improve
		for (Statement cur : value.getStatements()) {
			List<Panel> panels = cur.accept(this, expr);
			for (Panel p : panels) {
				panelList.add(p);	
			}
		}

		return panelList;
	}

	@Override
	public List<Panel> visit(Question question, Expression expr) {
		if (debug) {
			System.out.println("Question: "+question.getId().getValue());
		}
		
		QuestionField field = question.getType().accept(this, question.getId());
		
		Value value = symbolTable.getEntryValue(question.getId());
		
		QuestionPanel qp = new QuestionPanel(question, field, value, expr);
		
		List<Panel> panels = new ArrayList<Panel>();
		panels.add(qp);

		return panels;
	}

	@Override
	public List<Panel> visit(ComputedQuestion question, Expression expr) {
		if (debug) {
			System.out.println("ComputedQuestion: "+question.getId().getValue());
		}
		
		QuestionField field = question.getType().accept(this, question.getId());
		
		Value value = symbolTable.getEntryValue(question.getId());
		
		QuestionPanel qp = new QuestionPanel(question, field, value, expr);
		
		List<Panel> panels = new ArrayList<Panel>();
		panels.add(qp);

		return panels;
	}

	@Override
	public List<Panel> visit(IfStatement value, Expression expr) {
		if (debug) {
			System.out.println("IfStatement");
		}
				
//		Evaluator evaluator = new Evaluator(symbolTable);
//		Value a = value.getExpr().accept(evaluator);
//		System.out.println("VALUE VAN DE IF IS: "+a.getValue());
		
		List<Panel> ifBlockPanel = value.getBlock_if().accept(this, value.getExpr());
		
		return ifBlockPanel;
	}

	@Override
	public List<Panel> visit(IfElseStatement value, Expression expr) {
		if (debug) {
			System.out.println("IfElseStatement");
		}
		
		List<Panel> ifBlockPanel = value.getBlock_if().accept(this, value.getExpr());
		List<Panel> elseBlockPanel = value.getBlock_else().accept(this, new Not(value.getExpr(), null));
		
		ifBlockPanel.addAll(elseBlockPanel);
		
		return ifBlockPanel;
	}

	@Override
	public QuestionField visit(BooleanType value, Identifier identifier) {
		return new BooleanQuestionField(identifier, symbolTable);
	}

	@Override
	public QuestionField visit(MoneyType value, Identifier identifier) {
		return new MoneyQuestionField(identifier, symbolTable);
	}

	@Override
	public QuestionField visit(StringType value, Identifier identifier) {
		return new TextQuestionField(identifier, symbolTable);
	}

	@Override
	public QuestionField visit(IntegerType value, Identifier identifier) {
		return new IntegerQuestionField(identifier, symbolTable);
	}

}
