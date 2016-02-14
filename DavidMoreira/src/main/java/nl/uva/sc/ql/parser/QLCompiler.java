package nl.uva.sc.ql.parser;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.uva.sc.ql.parser.ast.SymbolTable;

public class QLCompiler 
{
    public void compile(String filepath) throws IOException {
                        
        QLLexer lexer = new QLLexer(new ANTLRFileStream(filepath));
        QLParser parser = new QLParser(new CommonTokenStream(lexer));
        ParseTree tree = parser.ql();
        
        SymbolTable symbolTable = new SymbolTable();
        
        System.out.println("Starting syntax analysis...");
        SyntacticVisitor visitor = new SyntacticVisitor(symbolTable);
        visitor.visit(tree);        
        
        System.out.println("Starting semantic analysis...");
        SemanticVisitor checker = new SemanticVisitor(symbolTable);
        checker.visit(tree);
        
    }
}
