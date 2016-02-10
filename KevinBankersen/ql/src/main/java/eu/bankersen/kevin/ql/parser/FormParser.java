package eu.bankersen.kevin.ql.parser;

import java.util.Arrays;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.gui.TreeViewer;

import eu.bankersen.kevin.ql.ast.expr.*;
import eu.bankersen.kevin.ql.ast.expr.logic.*;
import eu.bankersen.kevin.ql.ast.expr.math.*;
import eu.bankersen.kevin.ql.ast.stat.*;
import eu.bankersen.kevin.ql.ast.form.*;
import eu.bankersen.kevin.ql.ast.var.*;
import eu.bankersen.kevin.ql.parser.QLParser.FileContext;

public class FormParser {

	public static void main(String[] args) {
		// Lexer
		QLLexer lexer = new QLLexer(new ANTLRInputStream("Form test{"
				+ "q1 \"My first question\" int(1+2) "
				+ "q2 \"My seccond question\" bool(1<=2) "
				+ "if(1<=2){ "
				+ "q3 \"My seccond question\" int "
				+ "q4 \"My seccond question\" str "
				+ "if(q2){ "
				+ "q5 \"My seccond question\" bool"
				+ " }}"
				+ " }\r\n"));

		// Tokens.
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// Parse.
		QLParser p = new QLParser(tokens);

		// Contains the actual model.
		FileContext fileContext = p.file();	
		
		// Here we get our Form and have our AST present!
		Form form = fileContext.form().get(0).result;
		 
		// Form to string
		System.out.println(form.toString());
		
		// Show UI
		TreeViewer viewr = new TreeViewer(Arrays.asList(p.getRuleNames()), fileContext);
		viewr.setScale(1.5);// scale a little
		viewr.open(); // Open the viewer
		
		//TypeChecker test
		System.out.println("TypeCheck result: " + form.checkType());
		
	}
}
