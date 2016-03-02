package nl.uva.sc.ql;

import java.io.IOException;

import javax.swing.*;

import java.awt.*;

import nl.uva.sc.ql.exceptions.CompilerException;
import nl.uva.sc.ql.exceptions.ExceptionHandling;
import nl.uva.sc.ql.gui.QLGui;
import nl.uva.sc.ql.parser.QLCompiler;
import nl.uva.sc.ql.parser.ast.Node;

public class App {

	//Declare variables
    static JFrame frame1;
    static Container pane;
    static JButton btnConnect, btnDisconnect;
    static JLabel lblServer, lblUsername, lblPassword, lblPort;
    static JTextField txtServer, txtUsername, txtPassword, txtPort;
    static Insets insets;
    
	
	public static void main(String[] args) throws IOException {
        System.out.println("Start...");

		QLCompiler compiler = new QLCompiler();
		
        try {
        	Node ast = compiler.compile("src/main/resources/example.ql");
        	
            QLGui gui = new QLGui(ast);
            gui.start();

        } catch (CompilerException ce) {
        	System.err.println(ce.getMessage());
        }
        
        ExceptionHandling exceptionHandling = ExceptionHandling.getInstance();
        if(exceptionHandling.asWarning()){
        	System.err.println(exceptionHandling.toString());
        }
        
		System.out.println("Finished");
	}
}
