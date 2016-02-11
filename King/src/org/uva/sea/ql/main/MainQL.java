package org.uva.sea.ql.main;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;
import org.uva.sea.ql.ast.Edge;
import org.uva.sea.ql.ast.Graph;
import org.uva.sea.ql.ast.Vertex;
import org.uva.sea.ql.ast.TaxForm.Form;
import org.uva.sea.ql.parser.antlr.AST;
import org.uva.sea.ql.parser.antlr.GraphBaseListener;
import org.uva.sea.ql.parser.antlr.GraphLexer;
import org.uva.sea.ql.parser.antlr.GraphParser;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.FileContext;
import org.uva.sea.ql.parser.antlr.QLParser.FormContext;


public class MainQL {

	public static void main(String[] args) throws IOException {
		//Loading the DSL script into the ANTLR stream.
	    ANTLRInputStream input = new ANTLRFileStream(new File("resources/questionaire.gr").getPath());

	    //Passing the input to the lexer to create tokens
	    QLLexer lexer = new QLLexer(input);

	    CommonTokenStream tokens = new CommonTokenStream(lexer);

	    //Passing the tokens to the parser to create the parse trea. 
	    QLParser parser = new QLParser(tokens);
	   
	    //Tree ast = new AST(parser.form());
	    FileContext fileContext = parser.file();
	    Form tree = fileContext.form(0).result;
	    //System.out.println(tree.toStringTree());
	    System.out.println(tree);
	    //show AST in GUI
        //JFrame frame = new JFrame("Antlr AST");
         //JPanel panel = new JPanel();
        //TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()),tree);
        //viewr.setScale(1.5);//scale a little
        //viewr.open();
        
        

	}

}


