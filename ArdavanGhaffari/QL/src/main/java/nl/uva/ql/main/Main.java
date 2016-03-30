package nl.uva.ql.main;

import java.io.IOException;

import nl.uva.ql.ast.Form;
import nl.uva.ql.ast.builder.ASTBuilder;
import nl.uva.ql.gui.GuiBuilder;
import nl.uva.ql.typechecker.TypeChecker;
import nl.uva.ql.typechecker.errorhandler.ErrorHandler;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlrsources.QLLexer;
import antlrsources.QLParser;



public class Main {

	public static void main(String[] args) throws IOException {
		ANTLRFileStream input = new ANTLRFileStream("src/main/resources/quest2.ql");
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);
		ParseTree parseTree = parser.form();
		ASTBuilder astBuilder = new ASTBuilder();
		Form form = (Form) parseTree.accept(astBuilder);
		
		ErrorHandler errorHandler = new ErrorHandler();
		TypeChecker typeChecker = new TypeChecker(errorHandler);
		form.accept(typeChecker);
		
		errorHandler.printWarnings();
		if (errorHandler.hasError()) {
			errorHandler.printErrors();
		} else {
			GuiBuilder guiBuilder = new GuiBuilder();
			form.accept(guiBuilder);
		}
		
	}

}
