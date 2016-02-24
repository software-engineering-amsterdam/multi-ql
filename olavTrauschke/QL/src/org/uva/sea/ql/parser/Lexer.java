package org.uva.sea.ql.parser;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import org.uva.sea.ql.ast.*;
import org.uva.sea.ql.ast.expr.*;

public class Lexer implements Tokens {
    
    public static final int MINIMUM_CHARACTER_VALUE = 0;
    public static final int ERROR_CHARACTER_VALUE = MINIMUM_CHARACTER_VALUE - 1;
    public static final int NUMERIC_SYSTEM_BASE = 10;
    public static final String UNEXPECTED_CHAR_MESSAGE = "Unexpected character: ";
    public static final String UNCLOSED_STRING_MESSAGE = "A string was opened, but never closed";
    
    public static final Map<String, Integer> KEYWORDS;
    public static final Set<Integer> END_OF_LINE_CHARACTERS;
    public static final Set<Integer> WHITESPACE_CHARACTERS;
    
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
    
    public Lexer(String filename) throws FileNotFoundException {
        input = new FileReader(filename);
        readNextCharacter();
    }
    
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
    
    public final int nextToken() {
        boolean inMultiLineComment = false;
        boolean inSingleLineComment = false;
        while (true) { //loop until a token was found and returned
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
                continue;
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
    
    private void readWhile(Predicate<Integer> condition) {
        while (condition.test(character) && character >= MINIMUM_CHARACTER_VALUE) {
                    readNextCharacter();
        }
    }
    
    private int readNumber() {
        int result = 0;
        do {
            result = NUMERIC_SYSTEM_BASE * result + (character - '0');
            readNextCharacter();
        } while (Character.isDigit(character));
        return result;
    }
    
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
