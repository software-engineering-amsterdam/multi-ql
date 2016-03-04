package nl.uva.sc.ql;

import java.io.IOException;

import nl.uva.sc.ql.errorwarning.CompilerException;
import nl.uva.sc.ql.errorwarning.ErrorHandler;
import nl.uva.sc.ql.gui.QLGui;
import nl.uva.sc.ql.parser.QLCompiler;
import nl.uva.sc.ql.parser.ast.Node;

public class App {
	
	public static void main(String[] args) throws IOException {
        System.out.println("Start...");

        ErrorHandler errorHandler = new ErrorHandler();
		QLCompiler compiler = new QLCompiler(errorHandler);
		
        try {
        	Node ast = compiler.compile("src/main/resources/example.ql");
        	
            QLGui gui = new QLGui(ast);
            gui.start();

        } catch (CompilerException ce) {
        	System.err.println(ce.getMessage());
        }
        
        if(errorHandler.asWarning()){
        	System.err.println(errorHandler.toString());
        }
        
		System.out.println("Finished");
	}
}
