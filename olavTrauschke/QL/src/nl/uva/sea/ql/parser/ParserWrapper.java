package nl.uva.sea.ql.parser;

import java.io.FileNotFoundException;

/**
 * This class serves solely as a wrapper for
 * {@link nl.uva.sea.ql.parser.Parser class Parser} to give users access to the
 * public methods of this class, because jacc declares this class to have
 * package-access.
 * 
 * @author Olav Trauschke
 * @version 3-mrt-2016
 */
public class ParserWrapper extends Parser {
    
   /**
    * Constructor for objects of this class.
    * 
    * @param filename a <code>String</code> containing the path to the file
    *                  the constructed <code>Parser</code> should parse
    * @throws FileNotFoundException when the file specified by
    *                               <code>filename</code> could not be read
    */
    public ParserWrapper(String filename) throws FileNotFoundException {
        super(filename);
    }
    
   /**
    * Constructor for objects of this class.
    * 
    * @param lexer a <code>Lexer</code> analyzing the source the constructed
    *               <code>Parser</code> should parse
    */
    public ParserWrapper(Lexer lexer) {
        super(lexer);
    }
    
}