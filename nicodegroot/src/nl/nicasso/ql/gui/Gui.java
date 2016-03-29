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

public class Gui implements StructureVisitor<List<Panel>, Expression>, StatementVisitor<List<Panel>, Expression>, TypeVisitor<QuestionField, QuestionFieldArguments> {
	
	private MainFrame main;
	private StateTable stateTable;
	
	public Gui(Form ast, StateTable stateTable, MainFrame main) {
		this.stateTable = stateTable;
		this.main = main;
		
		ast.accept(this, null);
	}
	
	@Override
	public List<Panel> visit(Form structure, Expression ignore) {
		List<Panel> blockPanel = structure.getBlock().accept(this, new BooleanLiteral(true));
		
		for (Panel p : blockPanel) {
			main.addPanel(p);
		}
		
		main.addPanelsToMainFrame();
		
		return null;
	}

	@Override
	public List<Panel> visit(Block structure, Expression expr) {
		List<Panel> panelList = new ArrayList<Panel>();

		for (Statement cur : structure.getStatements()) {
			List<Panel> panels = cur.accept(this, expr);
			panelList.addAll(panels);	
		}

		return panelList;
	}

	@Override
	public List<Panel> visit(Question statement, Expression expression) {
		QuestionField field = statement.getType().accept(this, new QuestionFieldArguments(statement.getIdentifier(), main, true, statement.getType().getDefaultValue()));
		
		QuestionPanel qp = new QuestionPanel(statement, field, expression, stateTable);
		
		List<Panel> panels = new ArrayList<Panel>();
		panels.add(qp);

		return panels;
	}

	@Override
	public List<Panel> visit(ComputedQuestion statement, Expression expression) {
		Value value = stateTable.getEntryValue(statement.getIdentifier());
				
		QuestionFieldArguments questionFieldParameterObject = new QuestionFieldArguments(statement.getIdentifier(), main, false, value);
		
		QuestionField field = statement.getType().accept(this, questionFieldParameterObject);
		
		List<Panel> panels = new ArrayList<Panel>();
		ComputedQuestionPanel qp = new ComputedQuestionPanel(statement, field, value, expression, stateTable, main);
		panels.add(qp);

		return panels;
	}

	@Override
	public List<Panel> visit(IfStatement statement, Expression expression) {
		List<Panel> ifBlockPanel = statement.getBlock_if().accept(this, statement.getExpr());
		
		return ifBlockPanel;
	}

	@Override
	public List<Panel> visit(IfElseStatement statement, Expression expression) {
		List<Panel> ifBlockPanel = statement.getBlock_if().accept(this, statement.getExpr());
		List<Panel> elseBlockPanel = statement.getBlock_else().accept(this, new Not(statement.getExpr(), null));
		
		ifBlockPanel.addAll(elseBlockPanel);
		
		return ifBlockPanel;
	}

	@Override
	public QuestionField visit(BooleanType type, QuestionFieldArguments context) {
		return new BooleanQuestionField(context);
	}

	@Override
	public QuestionField visit(MoneyType type, QuestionFieldArguments context) {
		return new MoneyQuestionField(context);
	}

	@Override
	public QuestionField visit(StringType type, QuestionFieldArguments context) {
		return new TextQuestionField(context);
	}

	@Override
	public QuestionField visit(IntegerType type, QuestionFieldArguments context) {
		return new IntegerQuestionField(context);
	}

}
