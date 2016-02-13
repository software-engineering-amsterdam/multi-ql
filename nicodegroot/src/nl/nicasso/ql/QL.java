package nl.nicasso.ql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;

import nl.nicasso.ql.ast.structure.Form;
import nl.nicasso.ql.symanticanalysis.SymanticAnalysis;

public class QL {
	
	public final static String DSLFILE = "exampleQuestionnaire";
	
	QLLexer lexer;
	CommonTokenStream tokens;
	QLParser parser;		
	ParseTree tree;
	
	public QL() {
		//Empty?
	}
	
	public void start() {
		ANTLRInputStream input = readInputDSL();
		
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);		
		ParseTree tree = parser.form();
		
		//System.out.println(tree.toStringTree(parser));
        
        // VISITOR PATTERN!
        //Form a = (Form) new QLCustomVisitor().visit(tree);
        CreateASTVisitor astVisitor = new CreateASTVisitor();
        Form ast = (Form) tree.accept(astVisitor);
        
        SymanticAnalysis semanticAnalyser = new SymanticAnalysis();
		//if (ast.accept(semanticAnalyser)) {
			//GUIVisitor guiVisitor = new GUIVisitor();
			//questionnaire.accept(guiVisitor);
		//}
        
        //System.out.println("DEZE: "+a.getId().getIdentifier());
        
        //System.out.println(a.getLocation().getStartLine());
        //System.out.println(a.getLocation().getEndLine());
        
        //Gui ex = new Gui();
        //ex.setVisible(true);
	}
	
	private ANTLRInputStream readInputDSL() {
		File file = new File(DSLFILE);
	    FileInputStream fis;
	    ANTLRInputStream input = null;
	    
		try {
			fis = new FileInputStream(file);
			input = new ANTLRInputStream(fis);
			fis.close();
			return input;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void displayParseTree() {
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
	}
	
	public static void main( String[] args) throws Exception {
		System.out.print("LET'S GO!\n");
		
		QL ql = new QL();
		ql.start();
    }

}