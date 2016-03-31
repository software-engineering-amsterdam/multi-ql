package org.uva.sea.ql.main;

import java.io.File;
import java.io.IOException;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.ast.domain.Form;
import org.uva.sea.ql.gui.QLController;
import org.uva.sea.ql.gui.QLView;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.FileContext;
import org.uva.sea.ql.semantic.TypeChecker;

public class MainQL {

	public static void main(String[] args) throws IOException {
		ANTLRInputStream input = new ANTLRFileStream(new File("resources/questionaire.gr").getPath());
		QLLexer lexer = new QLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);

		FileContext fileContext = parser.file();
		Form ast = fileContext.form(0).result;
		TypeChecker typeChecker = new TypeChecker(ast);
		if(typeChecker.hasErrorMessages()){
			typeChecker.getQLAllSemanticMessages().print();
			//exit program if errors occurs
			System.exit(0);
		}
		showQL(ast, typeChecker);
		
	}

	private static void showQL(Form ast, TypeChecker typeChecker) {
		QLView qLView = new QLView();
		QLController qlController = new QLController(ast, qLView,typeChecker.getQLAllSemanticMessages(),typeChecker.getQLSymbolsTbale());
		qlController.showQLview();
	}

}
