

import jacc.QLLexer;
import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dominique
 */
public class UnitTest {
    
 
    
    @Test
    public void testLexerString(){
    String teststr = "mijnomaopeenracefiets";
        char[] characters = teststr.toCharArray();
        Reader reader; 
        reader = new CharArrayReader(characters);
        try {
            reader.read(characters);
        } catch (IOException ex) {
            Logger.getLogger(UnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        QLLexer TestLexerStr = new QLLexer(reader);
        int token = TestLexerStr.getToken();
        assertEquals(0, token);
    }
    
//    @Test
//    public void testLexerInt(){
//    int testint = 666;
//        Reader reader; 
//        reader = new IntReader(testint);
//        try {
//            reader.read(testint);
//        } catch (IOException ex) {
//            Logger.getLogger(UnitTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        QLLexer TestLexerInt = new QLLexer(testint);
//        int token = TestLexerInt.getToken();
//        assertEquals(0, token);
//    }

//    private static class IntReader extends Reader {
//
//        public IntReader(int testint) {
//        }
//    }
}
