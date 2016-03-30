package uva.ql;

import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import uva.ql.antlr4.QLLexer;
import uva.ql.antlr4.QLParser;
import uva.ql.ast.Form;
import uva.ql.gui.GUI;
import uva.ql.typechecker.TypeChecker;
import uva.ql.visitors.VisitorASTToGUI;
import uva.ql.visitors.VisitorAntlrToAST;

public class QL {
	private String filePath;
	
    public QL(String filePath, boolean internal) {
    	this.filePath = filePath;
    }
    
    public void start() throws Exception {
    	
    	InputStream in = getClass().getClassLoader().getResourceAsStream(filePath);
    	QLLexer lexer;
    	
    	if(in == null) {
    		ANTLRFileStream input = new ANTLRFileStream(filePath, "UTF-8");
    		lexer = new QLLexer(input);
    	}
    	else {
    		ANTLRInputStream input = new ANTLRInputStream(in);
    		lexer = new QLLexer(input);
    	}
    	
    	CommonTokenStream tokens = new CommonTokenStream(lexer);
    	QLParser parser = new QLParser(tokens);
		
		ParseTree tree = parser.form();
		VisitorAntlrToAST visitor = new VisitorAntlrToAST();
		Form form = (Form) visitor.visit(tree);
		
		Map<String, Integer> errorMsg = TypeChecker.checkAST(form);
		
		if( errorMsg.containsValue(-1) ) {
			Iterator<String> it = errorMsg.keySet().iterator();
			while( it.hasNext() ) {
				System.out.println(it.next());
			}
		}
		else {
			GUI gui = new GUI(form);
			VisitorASTToGUI astToGUI = new VisitorASTToGUI();
			astToGUI.visitForm(form, gui.getPanel());
		}
    }
}