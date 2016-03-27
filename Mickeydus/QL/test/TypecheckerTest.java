/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import AST.form.Form;
import antlr.FormulierParser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.uva.sea.ql.parser.antlr.FormulierLexer;
import ql.TreeWalker;
import typechecker.Typechecker;

/**
 *
 * @author Dominique
 */
public class TypecheckerTest {
    
  @Test
    public void buildTypechecker() throws FileNotFoundException, IOException{
        Reader input = new FileReader("/Users/Dominique/NetBeansProjects/multi-ql/Mickeydus/QL/src/ql/Form.ql");
        ANTLRInputStream chars = new ANTLRInputStream(input);
        FormulierLexer lexer = new FormulierLexer(chars);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FormulierParser parser = new FormulierParser(tokens);
        parser.setBuildParseTree(true);
        Form form = parser.form().result;
        Typechecker typechecker = new Typechecker();
        form.accept(typechecker);
        System.out.println("Start getting list of successes...");
        List<String> succesList = typechecker.getSuccesList();
        succesList.stream().forEach((s) -> {
            System.out.println(s);
        });
        System.out.println("Start getting list of errors...");
        List<String> errorList = typechecker.getErrorList();
        errorList.stream().forEach((s) -> {
            System.out.println(s);
        });
        System.out.println("Test complete");
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
