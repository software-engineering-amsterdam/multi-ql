package ql2;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ql2.parser.generated.Ql2Lexer;
import ql2.parser.generated.Ql2Parser;

public class QLMain {
	
	public static void main(String[] args) {
		String parseTest2 = ""
    			+ "\"What was the selling price\""
    			+ "    sellingPrice : money";
        String parseTest3 = ""
        		+ "form taxExampleForm {"
        		+ " \"What was your income last year?\""
        		+ "		incomeQuestion : money"
        		+ "}";
        
        System.out.println("Starting parsing");
        System.out.println(parseTest2);
        Ql2Lexer lexer = null;
		lexer = new Ql2Lexer( new ANTLRInputStream(parseTest2));
        CommonTokenStream tokens = new CommonTokenStream( lexer );
        
        Ql2Parser parser = new Ql2Parser( tokens );
        ParseTree tree = parser.question();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk( new Ql2Walker(), tree );
        
        // parse test for simple forms
        Ql2Lexer lexer2 = null;
		lexer2 = new Ql2Lexer( new ANTLRInputStream(parseTest3));
        CommonTokenStream tokens2 = new CommonTokenStream( lexer2 );
        Ql2Parser parser2 = new Ql2Parser( tokens2 );
        /* Disabled until further notice
        ParseTree tree2 = parser2.form();
        ParseTreeWalker walker2 = new ParseTreeWalker();
        */
       // walker2.walk( new QLWalker(), tree2 );
    
        parseQuestionnaireExample();
        
        //  BaseVisitor<T> basevisit = new BaseVisitor<T>();
        //   basevisit.visit(lexer);
        
        System.out.println("Finished parsing");
	}

	private static void parseQuestionnaireExample() {
		// TODO Auto-generated method stub
		
	}

}
