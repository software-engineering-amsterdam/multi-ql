package uva.ql;

import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import uva.ql.antlr4.QLLexer;
import uva.ql.antlr4.QLParser;
import uva.ql.ast.ANode;
import uva.ql.ast.Form;
import uva.ql.visitors.ASTTreePrintVisitor;
import uva.ql.visitors.VisitorToAST;
import uva.ql.visitors.typechecker.ConditionsNotOfTypeBoolean;
import uva.ql.visitors.typechecker.DuplicateQuestions;

public class QL {
	private String filePath;
	
    public QL(String filePath, boolean internal) {
    	this.filePath = filePath;
    }
    
    public ANode start() throws Exception {
    	
    	InputStream in = getClass().getClassLoader().getResourceAsStream(filePath);
    	QLLexer lexer;
    	
    	if (in == null) {
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
		VisitorToAST visitor = new VisitorToAST();
		Form form = (Form) visitor.visit(tree);
		
		//form.accept(new ASTTreePrintVisitor());
		
		
		DuplicateQuestions dupQuestions = new DuplicateQuestions();
		form.accept(dupQuestions);
		System.out.println(dupQuestions.getResult().entrySet());
		
		ConditionsNotOfTypeBoolean cndtnsNOTB = new ConditionsNotOfTypeBoolean();
		form.accept(cndtnsNOTB);
		System.out.println(cndtnsNOTB.getResult().entrySet());
		
		
		
		
		//TypeChecker.checkAST(form);
		
		
		return form;
    }
}