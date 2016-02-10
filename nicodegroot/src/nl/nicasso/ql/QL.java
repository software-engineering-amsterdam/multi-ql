package nl.nicasso.ql;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;

public class QL {
 
	public static void main( String[] args) throws Exception {
		System.out.print("LET'S BEGIN!\n");
		
		File file = new File("exampleQuestionnaire");
        FileInputStream fis = new FileInputStream(file);
		
		ANTLRInputStream input = new ANTLRInputStream(fis);
		
		fis.close();
		
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);		
		ParseTree tree = parser.form();
		
		//System.out.println(tree.toStringTree(parser));
		
		//show tree in GUI
        JFrame frame = new JFrame("Antlr AST");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),tree);
        viewr.setScale(1);
        panel.add(viewr);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,600);
        frame.setVisible(true);
        
        // Walk through tree
        //ParseTreeWalker walker = new ParseTreeWalker();
        //QLCustomListener listener = new QLCustomListener();
        //walker.walk(listener, tree);
        
        // VISITOR PATTERN!
        new QLCustomVisitor().visit(tree);
        
        //Gui ex = new Gui();
        //ex.setVisible(true);
    }

}