package nl.uva.sc.ql.compiler;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.uva.sc.ql.compiler.parser.CreateASTTree;
import nl.uva.sc.ql.compiler.parser.ast.FormNode;
import nl.uva.sc.ql.compiler.typechecker.SymbolTable;
import nl.uva.sc.ql.compiler.typechecker.TypeChecker;
import nl.uva.sc.ql.messages.MessagesHandler;
import nl.uva.sc.ql.messages.exceptions.CompilerException;
import nl.uva.sc.ql.parser.QLLexer;
import nl.uva.sc.ql.parser.QLParser;

public class QLCompiler {
    
	private MessagesHandler messagesHandler;
	
	public QLCompiler(MessagesHandler errorHandler) {
		this.messagesHandler = errorHandler;
	}

	public FormNode compile(String filePath) throws IOException {
                        
        QLLexer lexer = new QLLexer(new ANTLRInputStream(getClass().getResourceAsStream(filePath)));
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
