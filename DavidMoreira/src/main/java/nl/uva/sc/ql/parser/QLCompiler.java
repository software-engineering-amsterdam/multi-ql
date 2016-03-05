package nl.uva.sc.ql.parser;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.uva.sc.ql.errorwarning.CompilerException;
import nl.uva.sc.ql.errorwarning.MessagesHandler;
import nl.uva.sc.ql.parser.ast.FormNode;

public class QLCompiler {
    
	private MessagesHandler messagesHandler;
	
	public QLCompiler(MessagesHandler errorHandler) {
		this.messagesHandler = errorHandler;
	}

	public FormNode compile(String filePath) throws IOException {
                        
        QLLexer lexer = new QLLexer(new ANTLRFileStream(filePath));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.ql();
        
        if(parser.getNumberOfSyntaxErrors() > 0){
        	throw new CompilerException("There are some syntax errors in the input file");
        }
        
        CreateASTTree astTree = new CreateASTTree();
        FormNode ast = (FormNode) astTree.visit(tree);
                
        SymbolTable symbolTable = new SymbolTable();
        
        TypeChecker typeChecker = new TypeChecker(symbolTable, messagesHandler);        
        ast.accept(typeChecker);
        
        if(messagesHandler.asError()){
        	throw new CompilerException("Typechecker errors:\n"+messagesHandler.toString());
        }
        
        return ast;
    }
}
