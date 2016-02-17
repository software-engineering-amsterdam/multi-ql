package uva.ql;

import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import uva.ql.antlr4.QLLexer;
import uva.ql.antlr4.QLParser;
import uva.ql.ast.ASTForm;
import uva.ql.ast.ASTNode;
import uva.ql.visitors.ASTTreePrintVisitor;
import uva.ql.visitors.VisitorToAST;
import uva.ql.visitors.typechecker.DuplicateQuestions;

public class QL {
	private String filePath;
	
    public QL(String filePath, boolean internal) {
    	this.filePath = filePath;
    }
    
    public ASTNode start() throws Exception {
    	
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
		ASTForm form = (ASTForm) visitor.visit(tree);
		
		//form.accept(new ASTTreePrintVisitor());
		DuplicateQuestions dupQuestions = new DuplicateQuestions();
		form.accept(dupQuestions);
		
		System.out.println(dupQuestions.getDuplicates().entrySet());
		
		
		//TypeChecker.checkAST(form);
		
		
		return form;
    }
}