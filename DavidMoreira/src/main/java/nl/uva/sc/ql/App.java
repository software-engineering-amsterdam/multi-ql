package nl.uva.sc.ql;

import java.io.IOException;

import nl.uva.sc.ql.gui.Gui;
import nl.uva.sc.ql.messages.MessagesHandler;
import nl.uva.sc.ql.messages.exceptions.CompilerException;
import nl.uva.sc.ql.parser.Compiler;
import nl.uva.sc.ql.parser.ast.Node;

public class App {
	
	public static void main(String[] args) throws IOException {
        MessagesHandler messagesHandler = new MessagesHandler();
		Compiler compiler = new Compiler(messagesHandler);
		
        try {
        	Node ast = compiler.compile("/example.ql");
        	
            Gui gui = new Gui(ast);
            gui.start();

        } catch (CompilerException ce) {
        	System.err.println(ce.getMessage());
        }
        
        if(messagesHandler.asWarning()){
        	System.err.println(messagesHandler.toString());
        }        
	}
}
