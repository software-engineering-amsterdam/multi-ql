package nl.nicasso.ql.gui;

import java.util.ArrayList;
import java.util.List;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Not;
import nl.nicasso.ql.ast.nodes.literals.BooleanLiteral;
import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.ast.nodes.statements.IfElseStatement;
import nl.nicasso.ql.ast.nodes.statements.IfStatement;
import nl.nicasso.ql.ast.nodes.statements.Question;
import nl.nicasso.ql.ast.nodes.statements.Statement;
import nl.nicasso.ql.ast.nodes.structures.Block;
import nl.nicasso.ql.ast.nodes.structures.Form;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.StringType;
import nl.nicasso.ql.gui.evaluator.Evaluator;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.gui.panels.ComputedQuestionPanel;
import nl.nicasso.ql.gui.panels.Panel;
import nl.nicasso.ql.gui.panels.QuestionPanel;
import nl.nicasso.ql.gui.questionFields.BooleanQuestionField;
import nl.nicasso.ql.gui.questionFields.IntegerQuestionField;
import nl.nicasso.ql.gui.questionFields.MoneyQuestionField;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.questionFields.TextQuestionField;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;
import nl.nicasso.ql.visitors.TypeVisitor;

public class Gui implements StructureVisitor<List<Panel>, Expression>, StatementVisitor<List<Panel>, Expression>, TypeVisitor<QuestionField, QuestionFieldParameter> {
	
	private boolean debug = true;
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

		List<Panel> blockPanel = value.getBlock().accept(this, new BooleanLiteral(true));
		
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
			System.out.println("Question: "+question.getIdentifier().getIdentifier());
		}
		
		QuestionField field = question.getType().accept(this, new QuestionFieldParameter(question.getIdentifier(), main, true, question.getType().getDefaultValue()));
		
		QuestionPanel qp = new QuestionPanel(question, field, expr, stateTable);
		
		List<Panel> panels = new ArrayList<Panel>();
		panels.add(qp);

		return panels;
	}

	@Override
	public List<Panel> visit(ComputedQuestion question, Expression expr) {
		if (debug) {
			System.out.println("ComputedQuestion: "+question.getIdentifier().getIdentifier());
		}
		
		Value value = stateTable.getEntryValue(question.getIdentifier());
		
		System.out.println("VALUE CQ: "+value.getValue());
		
		QuestionFieldParameter questionFieldParameterObject = new QuestionFieldParameter(question.getIdentifier(), main, false, value);
		QuestionField field = question.getType().accept(this, questionFieldParameterObject);
		
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
