package org.uva.sea.ql.parser;

import java.io.FileNotFoundException;
import static org.junit.Assert.*;
import org.junit.Test;
import org.uva.sea.ql.ast.*;
import org.uva.sea.ql.ast.expr.*;

public class ParserQuestionTest {
    
    @Test
    public void testQuestionParsing() throws FileNotFoundException {
        Lexer lexer = new Lexer("question.ql");
        Parser parser = new Parser(lexer);
        boolean parsed = parser.parse();
        assertTrue(parsed);
        Question result = parser.getResult();
        
        Ident identifier = new Ident("hasSoldHouse");
        Label label = new Label("Did you sell a house in 2010?");
        Int type = new Int(Tokens.BOOLEAN);
        Question expected = new Question(identifier, label, type);
        
        assertTrue(expected.equals(result));
    }
    
    @Test
    public void testComputedQuestionParsing() throws FileNotFoundException {
        Lexer lexer = new Lexer("computedQuestion.ql");
        Parser parser = new Parser(lexer);
        boolean parsed = parser.parse();
        assertTrue(parsed);
        Question result = parser.getResult();
        
        Ident identifier = new Ident("valueResidue");
        Label label = new Label("Value residue:");
        Int type = new Int(Tokens.MONEY);
        Expr firstIdentifier = new Ident("sellingPrice");
        Expr secondIdentifier = new Ident("privateDebt");
        Expr calculation = new Sub(firstIdentifier, secondIdentifier);
        Question expected = new ComputedQuestion(identifier, label, type, calculation);
        
        assertTrue(expected.equals(result));
    }
    
}
