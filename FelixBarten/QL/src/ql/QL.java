/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ql.parser.QLLexer;
import ql.parser.QLParser;
/**
 *
 * @author felixbarten
 */
public class QL {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
    	String path = "/Users/felixbarten/Git/multi-ql/FelixBarten/QL/src/ql/parser/QL.g4";
    	String path2 = "";
    	String parseTest = "\"is this a test?\" testQuestion: money";
    	String parseTest2 = ""
    			+ "\"What was the selling price?\""
    			+ "    sellingPrice : money";
        
        
        System.out.println("Starting parsing");
        QLLexer lexer = null;
		lexer = new QLLexer( new ANTLRInputStream(parseTest2));
        CommonTokenStream tokens = new CommonTokenStream( lexer );
        QLParser parser = new QLParser( tokens );
        ParseTree tree = parser.question();
      //  ParseTreeWalker walker = new ParseTreeWalker();
        //walker.walk( new QLWalker(), tree );
        
        System.out.println("Finished parsing");

    
    }
}
