package nl.nicasso.ql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.nicasso.ql.antlr.QLLexer;
import nl.nicasso.ql.antlr.QLParser;
import nl.nicasso.ql.ast.CreateAbstractSyntaxTree;
import nl.nicasso.ql.ast.nodes.structures.Form;
import nl.nicasso.ql.gui.Gui;
import nl.nicasso.ql.gui.MainWindow;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.semanticAnalysis.SemanticAnalysis;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;

public class QL {

	public final static String DSLFILE = "examples/good/allTypes";
	// public final static String DSLFILE = "examples/good/booleanType";
	// public final static String DSLFILE = "examples/good/integerType";
	// public final static String DSLFILE = "examples/good/moneyType";
	// public final static String DSLFILE = "examples/good/nestedIfs";

	// public final static String DSLFILE = "examples/bad/cyclicDependency";
	// public final static String DSLFILE = "examples/bad/duplicateIds";
	// public final static String DSLFILE =
	// "examples/bad/duplicateIdsDifferentType";
	// public final static String DSLFILE = "examples/bad/duplicateLabels";
	// public final static String DSLFILE = "examples/bad/undefinedIdentifier";

	public QL() {
		QLLexer lexer = new QLLexer(readInputDSL());
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ParseTree parseTree = new QLParser(tokens).form();

		Form abstractSyntaxTree = new CreateAbstractSyntaxTree(parseTree).getAbstractSyntaxTree();

		StateTable stateTable = new StateTable();

		SemanticAnalysis semantics = new SemanticAnalysis(abstractSyntaxTree, new SymbolTable(), stateTable);

		MainWindow window = new MainWindow(stateTable, semantics.getMessages());

		if (!semantics.containsErrors()) {
			new Gui(abstractSyntaxTree, stateTable, window);
		}
	}

	private ANTLRInputStream readInputDSL() {
		File dslFile = new File(DSLFILE);
		FileInputStream inputStream;
		ANTLRInputStream input = null;

		try {
			inputStream = new FileInputStream(dslFile);
			input = new ANTLRInputStream(inputStream);
			inputStream.close();
			return input;
		} catch (IOException exception) {
			exception.printStackTrace();
			return null;
		}
	}

	public static void main(String[] arguments) throws Exception {
		new QL();
	}

}