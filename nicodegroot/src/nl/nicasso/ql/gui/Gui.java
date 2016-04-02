package nl.nicasso.ql.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import nl.nicasso.ql.ast.nodes.expressions.Expression;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Not;
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

public class Gui implements StructureVisitor<List<Panel>, Stack<Expression>>,
		StatementVisitor<List<Panel>, Stack<Expression>>, TypeVisitor<QuestionField, QuestionFieldArguments> {

	private MainWindow mainWindow;
	private StateTable stateTable;

	public Gui(Form ast, StateTable stateTable, MainWindow main) {
		this.stateTable = stateTable;
		this.mainWindow = main;

		ast.accept(this, new Stack<Expression>());

		this.mainWindow.addPanelsToMainFrame();
	}

	@Override
	public List<Panel> visit(Form structure, Stack<Expression> visibilityConditions) {
		List<Panel> blockPanel = structure.getBlock().accept(this, visibilityConditions);

		for (Panel p : blockPanel) {
			mainWindow.add(p);
		}

		return null;
	}

	@Override
	public List<Panel> visit(Block structure, Stack<Expression> visibilityConditions) {
		List<Panel> panelList = new ArrayList<Panel>();

		for (Statement cur : structure.getStatements()) {
			List<Panel> panels = cur.accept(this, visibilityConditions);
			panelList.addAll(panels);
		}

		return panelList;
	}

	@Override
	public List<Panel> visit(Question statement, Stack<Expression> visibilityConditions) {
		QuestionField field = statement.getType().accept(this, new QuestionFieldArguments(statement.getIdentifier(),
				mainWindow, true, statement.getType().getDefaultValue()));

		QuestionPanel qp = new QuestionPanel(statement, field, visibilityConditions, stateTable, mainWindow);

		List<Panel> panelList = new ArrayList<Panel>();
		panelList.add(qp);

		return panelList;
	}

	@Override
	public List<Panel> visit(ComputedQuestion statement, Stack<Expression> visibilityConditions) {
		Value value = stateTable.getEntryValue(statement.getIdentifier());

		QuestionFieldArguments questionFieldParameterObject = new QuestionFieldArguments(statement.getIdentifier(),
				mainWindow, false, value);

		QuestionField field = statement.getType().accept(this, questionFieldParameterObject);

		List<Panel> panelList = new ArrayList<Panel>();
		ComputedQuestionPanel qp = new ComputedQuestionPanel(statement, field, value, visibilityConditions, stateTable,
				mainWindow);
		panelList.add(qp);

		return panelList;
	}

	@Override
	public List<Panel> visit(IfStatement statement, Stack<Expression> visibilityConditions) {
		visibilityConditions.push(statement.getExpression());
		List<Panel> ifBlockPanels = statement.getBlockIf().accept(this, visibilityConditions);
		visibilityConditions.pop();

		return ifBlockPanels;
	}

	@Override
	public List<Panel> visit(IfElseStatement statement, Stack<Expression> visibilityConditions) {
		visibilityConditions.push(statement.getExpression());
		List<Panel> ifBlockPanels = statement.getBlockIf().accept(this, visibilityConditions);
		visibilityConditions.pop();

		visibilityConditions.push(new Not(statement.getExpression(), null));
		List<Panel> elseBlockPanels = statement.getBlockElse().accept(this, visibilityConditions);
		visibilityConditions.pop();

		ifBlockPanels.addAll(elseBlockPanels);

		return ifBlockPanels;
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
