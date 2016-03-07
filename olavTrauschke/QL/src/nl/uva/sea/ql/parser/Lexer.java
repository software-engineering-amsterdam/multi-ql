package nl.uva.sea.ql.parser;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import nl.uva.sea.ql.ast.ASTNode;
import nl.uva.sea.ql.ast.expr.*;

/**
 * Class for inerpretation of the syntax of ql-files.
 * 
 * @author Olav Trauschke
 * @version 24-feb-2016
 */
public class Lexer implements Tokens {
    
    /**
     * The minimum (<code>int</code>) value of a <code>char</code> objects of
     * this class can interpretate.
     */
    public static final int MINIMUM_CHARACTER_VALUE = 0;
    
    /**
     * The (<code>int</code>) value that is used to signal that something went
     * wrong. This value can never be used to represent a character, because
     * it is smaller than {@link #MINIMUM_CHARACTER_VALUE MINIMUM_CHARACTER_VALUE}.
     */
    public static final int ERROR_CHARACTER_VALUE = MINIMUM_CHARACTER_VALUE - 1;
    
    /**
     * The base of the numeric system used
     */
    public static final int NUMERIC_SYSTEM_BASE = 10;
    
    /**
     * Message displayed to signal an unexpected character was found.
     */
    public static final String UNEXPECTED_CHAR_MESSAGE = "Unexpected character: ";
    
    /**
     * Message displayed to signal the file ended while a string was unclosed.
     */
    public static final String UNCLOSED_STRING_MESSAGE = "A string was opened, but never closed";
    
    /**
     * A map from keywords in ql to the tokens representing them.
     */
    public static final Map<String, Integer> KEYWORDS;
    
    /**
     * A set of the characters that signal the end of a line.
     */
    public static final Set<Integer> END_OF_LINE_CHARACTERS;
    
    /**
     * A set of characters that are used to seperate things only and do not have
     * semantic meaning.
     */
    public static final Set<Integer> WHITESPACE_CHARACTERS;
    
    //initialization of KEYWORDS, END_OF_LINE_CHARACTERS and WHITESPACE_CHARACTERS
    static {
        KEYWORDS = new HashMap<>();
        KEYWORDS.put("true", BOOLEAN_LITERAL);
        KEYWORDS.put("false", BOOLEAN_LITERAL);
        KEYWORDS.put("boolean", BOOLEAN);
        KEYWORDS.put("date", DATE);
        KEYWORDS.put("decimal", DECIMAL);
        KEYWORDS.put("int", INT);
        KEYWORDS.put("money", MONEY);
        KEYWORDS.put("string", STRING);
        KEYWORDS.put("if", IF);
        KEYWORDS.put("else", ELSE);
        KEYWORDS.put("form", FORM);
        
        END_OF_LINE_CHARACTERS = new HashSet<>();
        END_OF_LINE_CHARACTERS.add((int) '\n');
        END_OF_LINE_CHARACTERS.add((int) '\r');
        
        WHITESPACE_CHARACTERS = new HashSet<>(END_OF_LINE_CHARACTERS);
        WHITESPACE_CHARACTERS.add((int) ' ');
        WHITESPACE_CHARACTERS.add((int) '\t');
    }
    
    private final Reader input;
    private int character = ' '; //used only for checking no error has occured yet
    private int token;
    private ASTNode semantic;
    
    /**
     * Constructor for objects of this class.
     * 
     * @param filename a <code>String</code> containing the path to the file
     *                  the constructed <code>Lexer</code> should analyze
     * @throws FileNotFoundException if <code>filename</code> is not the path
     *                                  to a file that can be read
     */
    public Lexer(String filename) throws FileNotFoundException {
        input = new FileReader(filename);
        readNextCharacter();
    }
    
    /**
     * Constructor for objects of this class.
     * 
     * @param theInput a <code>Reader</code> reading from the source the constructed
     *                  <code>Lexer</code> should analyze
     */
    public Lexer(Reader theInput) {
        input = theInput;
        readNextCharacter();
    }
    
    private void readNextCharacter() {
        if (character >= MINIMUM_CHARACTER_VALUE) {
            try {
                character = input.read();
            }
            catch (IOException ioe) {
                character = ERROR_CHARACTER_VALUE; //an error occured, stop reading
            }
        }
    }
    
    /**
     * @return the last generated token
     */
    public int getToken() {
        return token;
    }
    
    /**
     * @return an <code>ASTNode</code> representing the semantic of the last
     *          generated token with semantic meaning
     */
    public ASTNode getSemantic() {
        return semantic;
    }
    
    /**
     * Find the next token in the source.
     * @return the token that was found
     */
    public final int nextToken() {
        boolean inMultiLineComment = false;
        boolean inSingleLineComment = false;
        loop: while (true) { //loop until a token was found and returned
            if (inMultiLineComment) {
                readWhile((Integer c) -> c != '*');
                if (character == '*') {
                    readNextCharacter();
                    if (character == '/') {
                        inMultiLineComment = false;
                        readNextCharacter();
                    }
                    else continue;
                }
            }
            
            if (inSingleLineComment) {
                readWhile((Integer c) -> !END_OF_LINE_CHARACTERS.contains(c));
                if (END_OF_LINE_CHARACTERS.contains(character)) {
                    inSingleLineComment = false;
                    readNextCharacter();
                }
            }
            
            while (WHITESPACE_CHARACTERS.contains(character)) {
                readNextCharacter();
                continue loop;
            }
            
            if (character < MINIMUM_CHARACTER_VALUE) {
                token = ENDINPUT;
                return token;
            }
            
            switch (character) {
                case ')' :
                case '(' :
                case '*' :
                case '+' :
                case '-' :
                case '!' :
                case ':' :
                case '{' :
                case '}' :
                case ';' : {
                    token = character;
                    readNextCharacter();
                    return token;
                }
                
                case '/' : {
                    readNextCharacter();
                    if (character == '*') {
                        inMultiLineComment = true;
                        readNextCharacter();
                        continue;
                    }
                    if (character == '/') {
                        inSingleLineComment = true;
                        readNextCharacter();
                        continue;
                    }
                    token = '/';
                    return token;
                }
                
                case '&' : {
                    readNextCharacter();
                    if (character == '&') {
                        readNextCharacter();
                        token = AND;
                        return token;
                    }
                    throw new RuntimeException(UNEXPECTED_CHAR_MESSAGE + (char) character);
                }
                case '|' : {
                    readNextCharacter();
                    if (character == '|') {
                        readNextCharacter();
                        token = OR;
                        return token;
                    }
                    throw new RuntimeException(UNEXPECTED_CHAR_MESSAGE + (char) character);
                }
                
                case '<' : {
                    readNextCharacter();
                    if (character == '=') {
                        readNextCharacter();
                        token = LEQ;
                        return token;
                    }
                    token = '<';
                    return token;
                }
                case '=' : {
                    readNextCharacter();
                    if (character == '=') {
                        readNextCharacter();
                        token = EQ;
                        return token;
                    }
                    throw new RuntimeException(UNEXPECTED_CHAR_MESSAGE + (char) character);
                }
                case '>' : {
                    readNextCharacter();
                    if (character == '=') {
                        readNextCharacter();
                        token = GEQ;
                        return token;
                    }
                    token = '>';
                    return token;
                }
                
                case '"' : {
                    semantic = new Str(readString());
                    token = STRING_LITERAL;
                    return token;
                }
                
                default : {
                    if (Character.isDigit(character)) {
                        semantic = new Int(readNumber());
                        token = INT_LITERAL;
                        return token;
                    }
                    if (Character.isLetter(character)) {
                        token = readText();
                        return token;
                    }
                    throw new RuntimeException(UNEXPECTED_CHAR_MESSAGE + (char) character);
                }
            }
        }
    }
    
    /**
     * Call <code>readNextCharacter</code> while <code>character</code> does not
     * satisfy a specified <code>condition</code>.
     * 
     * @param condition a <code>Predicate</code> representing the condition
     *                  <code>character</code> should satisfy after this call
     */
    private void readWhile(Predicate<Integer> condition) {
        while (condition.test(character) && character >= MINIMUM_CHARACTER_VALUE) {
                    readNextCharacter();
        }
    }
    
    /**
     * Call <code>readNextCharacter</code> while the next <code>character</code>
     * contributes to a number.
     * 
     * @return an <code>int</code> containing the number represented the by the
     *          read characters
     */
    private int readNumber() {
        int result = 0;
        do {
            result = NUMERIC_SYSTEM_BASE * result + (character - '0');
            readNextCharacter();
        } while (Character.isDigit(character));
        return result;
    }
    
    /**
     * Call <code>readNextCharacter</code> while the next <code>character</code>
     * contributes to a keyword or identifier. Also sets <code>token</code> and
     * <code>semantic</code> to represent the value that was read.
     * 
     * @return the token representing the value that was read
     */
    private int readText() {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append((char) character);
            readNextCharacter();
        } while (Character.isLetterOrDigit(character));
        String name = sb.toString();
        if (KEYWORDS.containsKey(name)) {
            if (name.equals("true")) {
                semantic = new Bool(true);
            }
            else if (name.equals("false")) {
                semantic = new Bool(false);
            }
            token = KEYWORDS.get(name);
            return token;
        }
        semantic = new Ident(name);
        return IDENT;
    }
    
    /**
     * Call <code>readNextCharacter</code> while the end of a string (quotes)
     * was not found.
     * 
     * @return the <code>String</code> that was read
     */
    private String readString() {
        StringBuilder sb = new StringBuilder();
        readNextCharacter();
        while (character != '"' && character >= MINIMUM_CHARACTER_VALUE) {
            sb.append((char) character);
            readNextCharacter();
        }
        if (character == '"') {
            readNextCharacter();
            return sb.toString();
        }
        else {
            throw new IllegalStateException(UNCLOSED_STRING_MESSAGE);
        }
    }
}
