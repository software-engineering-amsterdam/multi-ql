package nl.uva.sc.ql;

import java.io.IOException;

import nl.uva.sc.ql.parser.QLCompiler;

public class App {

	public static void main(String[] args) throws IOException {
        System.out.println( "Start..." );

		QLCompiler compiler = new QLCompiler();
		compiler.compile("src/main/resources/example.ql");

		System.out.println("Finished");
	}
}
