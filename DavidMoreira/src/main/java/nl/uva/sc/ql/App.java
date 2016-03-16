package nl.uva.sc.ql;

import java.io.IOException;

import nl.uva.sc.ql.errorwarning.CompilerException;
import nl.uva.sc.ql.errorwarning.MessagesHandler;
import nl.uva.sc.ql.gui.QLGui;
import nl.uva.sc.ql.parser.QLCompiler;
import nl.uva.sc.ql.parser.ast.Node;

public class App {
	
	public static void main(String[] args) throws IOException {
        System.out.println("Start...");

        MessagesHandler messagesHandler = new MessagesHandler();
		QLCompiler compiler = new QLCompiler(messagesHandler);
		
        try {
        	Node ast = compiler.compile("/example.ql");
        	
            QLGui gui = new QLGui(ast);
            gui.start();

        } catch (CompilerException ce) {
        	System.err.println(ce.getMessage());
        }
        
        if(messagesHandler.asWarning()){
        	System.err.println(messagesHandler.toString());
        }
        
		System.out.println("Finished");
	}
}
