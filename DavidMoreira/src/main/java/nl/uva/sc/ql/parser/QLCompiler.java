package nl.uva.sc.ql.parser;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.uva.sc.ql.exceptions.CompilerException;
import nl.uva.sc.ql.exceptions.ExceptionHandling;
import nl.uva.sc.ql.parser.ast.Node;

public class QLCompiler 
{
    public Node compile(String filePath) throws IOException {
                        
        QLLexer lexer = new QLLexer(new ANTLRFileStream(filePath));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.ql();   
        
        if(parser.getNumberOfSyntaxErrors() > 0){
        	throw new CompilerException("There are some syntax errors in the input file");
        }
        
        SymbolTable<Node> symbolTable = new SymbolTable<Node>();
        
        System.out.println("Starting AST construction...");
        CreateASTTree astTree = new CreateASTTree(symbolTable);
        Node ast = astTree.visit(tree);
        
        ExceptionHandling exceptionHandling = ExceptionHandling.getInstance();
        if(exceptionHandling.asError()){
        	throw new CompilerException("AST Tree errors:\n"+exceptionHandling.toString());
        }
        
        System.out.println("Starting typechecker...");
        Typecheker typeChecker = new Typecheker();
        ast.accept(typeChecker);
        
        if(exceptionHandling.asError()){
        	throw new CompilerException("Typechecker errors:\n"+exceptionHandling.toString());
        }
        
        return ast;
    }
}
