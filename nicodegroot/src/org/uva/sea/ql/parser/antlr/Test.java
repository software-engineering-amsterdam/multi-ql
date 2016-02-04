package org.uva.sea.ql.parser.antlr;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("START");
		
		ANTLRStringStream in = new ANTLRStringStream("12*(5-6)");
        QLLexer lexer = new QLLexer(in);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        QLParser parser = new QLParser(tokens);
        System.out.println(parser.relExpr()); // print the value
	}

}
