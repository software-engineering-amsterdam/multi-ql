package org.uva.sea.ql.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import org.uva.sea.ql.ast.ASTNode;

public class Lexer implements Tokens {
    
    public static final int MINIMUM_CHARACTER_VALUE = 0;
    public static final int ERROR_CHARACTER_VALUE = MINIMUM_CHARACTER_VALUE - 1;
    public static final String UNEXPECTED_CHAR_MESSAGE = "Unexpected character: ";
    
    public static final Map<String, Integer> KEYWORDS;
    static {
        KEYWORDS = new HashMap<>();
        //TODO add keywords
    }
    
    public static final Set<Integer> WHITESPACE_CHARACTERS;
    static {
        WHITESPACE_CHARACTERS = new HashSet<>();
        WHITESPACE_CHARACTERS.add(Character.getNumericValue(' '));
        WHITESPACE_CHARACTERS.add(Character.getNumericValue('\t'));
        WHITESPACE_CHARACTERS.add(Character.getNumericValue('\n'));
        WHITESPACE_CHARACTERS.add(Character.getNumericValue('\r'));
    }
    
    private final Reader input;
    private int character = ' '; //used only for checking no error has occured yet
    private int token;
    private ASTNode semantic;
    
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
    
    public int getToken() {
        return token;
    }
    
    public ASTNode getSemantic() {
        return semantic;
    }
    
    public int nextToken() {
        boolean inMultiLineComment = false;
        while (true) { //loop until a token was found and returned
            if (inMultiLineComment) {
                while (character != '*' && character >= MINIMUM_CHARACTER_VALUE) {
                    readNextCharacter();
                }
                if (character == '*') {
                    readNextCharacter();
                    if (character == '/') {
                        inMultiLineComment = false;
                        readNextCharacter();
                    }
                    continue;
                }
            }
            
            while (WHITESPACE_CHARACTERS.contains(character)) {
                readNextCharacter();
            }
            
            if (character < MINIMUM_CHARACTER_VALUE) {
                token = ENDINPUT;
                return token;
            }
            
            switch (character) {
                case '/' : {
                    readNextCharacter();
                    if (character == '*') {
                        inMultiLineComment = true;
                        readNextCharacter();
                        continue;
                    }
                    token = '/';
                    return token;
                }
                case '*' : {
                    readNextCharacter();
                    if (inMultiLineComment && character == '/') {
                        inMultiLineComment = false;
                        readNextCharacter();
                        continue;
                    }
                    token = '*';
                    return token;
                }
                
                case ')' :
                case '(' :
                case '+' :
                case '-' :
                case '!' : {
                    token = character;
                    readNextCharacter();
                    return token;
                }
                
                case '&' : {
                    readNextCharacter();
                    if (character == '&') {
                        token = AND;
                        return token;
                    }
                    throw new RuntimeException(UNEXPECTED_CHAR_MESSAGE + (char) character);
                }
                case '|' : {
                    readNextCharacter();
                    if (character == '|') {
                        token = OR;
                        return token;
                    }
                    throw new RuntimeException(UNEXPECTED_CHAR_MESSAGE + (char) character);
                }
                
                case '<' : {
                    readNextCharacter();
                    if (character == '=') {
                        token = LEQ;
                        return token;
                    }
                    token = '<';
                    return token;
                }
                case '=' : {
                    readNextCharacter();
                    if (character == '=') {
                        token = EQ;
                        return token;
                    }
                    throw new RuntimeException(UNEXPECTED_CHAR_MESSAGE + (char) character);
                }
                case '>' : {
                    readNextCharacter();
                    if (character == '=') {
                        token = GEQ;
                        return token;
                    }
                    token = '>';
                    return token;
                }
                
                default : {
                    if (Character.isDigit(character)) {
                        semantic = new Int(readNumber());
                        token = INT;
                        return token;
                    }
                    if (Character.isLetter(character)) {
                        token = readString();
                        return token;
                    }
                    throw new RuntimeException(UNEXPECTED_CHAR_MESSAGE + (char) character);
                }
            }
        }
    }

    private int readNumber() {
        int result = 0;
        do {
            result = 10 * result + (character - '0');
            readNextCharacter();
        } while (Character.isDigit(character));
        return result;
    }
    
    private int readString() {
        StringBuilder sb = new StringBuilder();
        do {
            sb.append((char) character);
        } while (Character.isLetterOrDigit(character));
        String name = sb.toString();
        if (KEYWORDS.containsKey(name)) {
            return KEYWORDS.get(name);
        }
        semantic = new Ident(name);
        return IDENT;
    }
}
