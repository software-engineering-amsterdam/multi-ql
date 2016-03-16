package nl.nicasso.ql.gui;

import java.util.ArrayList;
import java.util.List;

import nl.nicasso.ql.Evaluator;
import nl.nicasso.ql.ast.expressions.Expression;
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
import nl.nicasso.ql.gui.panels.ComputedQuestionPanel;
import nl.nicasso.ql.gui.panels.Panel;
import nl.nicasso.ql.gui.panels.QuestionPanel;
import nl.nicasso.ql.gui.questionFields.BooleanQuestionField;
import nl.nicasso.ql.gui.questionFields.IntegerQuestionField;
import nl.nicasso.ql.gui.questionFields.MoneyQuestionField;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.questionFields.TextQuestionField;
import nl.nicasso.ql.stateTable.StateTable;
import nl.nicasso.ql.values.Value;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;
import nl.nicasso.ql.visitors.TypeVisitor;

public class Gui implements StructureVisitor<List<Panel>, Expression>, StatementVisitor<List<Panel>, Expression>, TypeVisitor<QuestionField, QuestionFieldParameter> {
	
	private boolean debug = false;
	private MainFrame main;
	private StateTable stateTable;
	
	public Gui(StateTable stateTable, MainFrame main) {
		this.stateTable = stateTable;
		this.main = main;
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
		
		QuestionFieldParameter questionFieldParameterObject = new QuestionFieldParameter(question.getId(), main, true);
		QuestionField field = question.getType().accept(this, questionFieldParameterObject);
		
		QuestionPanel qp = new QuestionPanel(question, field, expr, stateTable);
		
		List<Panel> panels = new ArrayList<Panel>();
		panels.add(qp);

		return panels;
	}

	@Override
	public List<Panel> visit(ComputedQuestion question, Expression expr) {
		if (debug) {
			System.out.println("ComputedQuestion: "+question.getId().getValue());
		}
		
		QuestionFieldParameter questionFieldParameterObject = new QuestionFieldParameter(question.getId(), main, false);
		QuestionField field = question.getType().accept(this, questionFieldParameterObject);
		
		Value value = stateTable.getEntryValue(question.getId());
		
		ComputedQuestionPanel qp = new ComputedQuestionPanel(question, field, value, expr, stateTable, main);
		
		List<Panel> panels = new ArrayList<Panel>();
		panels.add(qp);

		return panels;
	}

	@Override
	public List<Panel> visit(IfStatement value, Expression expr) {
		if (debug) {
			System.out.println("IfStatement");
		}
				
		Evaluator evaluator = new Evaluator(stateTable);
		value.getExpr().accept(evaluator);
		
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
	public QuestionField visit(BooleanType value, QuestionFieldParameter params) {
		return new BooleanQuestionField(params);
	}

	@Override
	public QuestionField visit(MoneyType value, QuestionFieldParameter params) {
		return new MoneyQuestionField(params);
	}

	@Override
	public QuestionField visit(StringType value, QuestionFieldParameter params) {
		return new TextQuestionField(params);
	}

	@Override
	public QuestionField visit(IntegerType value, QuestionFieldParameter params) {
		return new IntegerQuestionField(params);
	}

}
