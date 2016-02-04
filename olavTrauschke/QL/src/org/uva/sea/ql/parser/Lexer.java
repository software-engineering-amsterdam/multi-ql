package org.uva.sea.ql.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.*;
import org.uva.sea.ql.ast.ASTNode;

public class Lexer implements Tokens {
    
    public static final int MINIMUM_CHARACTER_VALUE = 0;
    public static final int ERROR_CHARACTER_VALUE = MINIMUM_CHARACTER_VALUE - 1;
    
    public static final Map<String, Integer> KEYWORDS;
    static {
        KEYWORDS = new HashMap<>();
        KEYWORDS.put("boolean", BOOLEAN);
        KEYWORDS.put("string", STRING);
        KEYWORDS.put("true", LITERAL_TRUE);
        KEYWORDS.put("false", LITERAL_FALSE);
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
                //TODO handle &, |, <, = and > (see examples below)
                case ')' :
                case '(' :
                case '+' :
                case '-' :
                case '!' : {
                    token = character;
                    readNextCharacter();
                    return token;
                }
                default: {
                    //TODO handle default case
                }
            }
        }
    }
}

/*
    case '&': {
        nextChar(); 
        if  (c == '&') {
                nextChar();
                return token = AND;
        }
        throw new RuntimeException("Unexpected character: " + (char)c);
    }
    case '|': {
        nextChar(); 
        if  (c == '|') {
                nextChar();
                return token = OR;
        }
        throw new RuntimeException("Unexpected character: " + (char)c);
    }
    case '<': {
        nextChar();
        if (c == '=') {
                nextChar();
                return token = LEQ;
        }
        return '<';
    }
    case '=': { 
        nextChar(); 
        if  (c == '=') {
                return token = EQ;
        }
        throw new RuntimeException("Unexpected character: " + (char)c);
    }
    case '>': {
        nextChar();
        if (c == '=') {
                nextChar();
                return token = GEQ;
        }
        return token = '>';
    }
    default: {
        if (Character.isDigit(c)) {
                int n = 0; 
                do {
                        n = 10 * n + (c - '0');
                        nextChar(); 
                } while (Character.isDigit(c)); 
                yylval = new Int(n);
                return token = INT;
        }
        if (Character.isLetter(c)) {
                StringBuilder sb = new StringBuilder();
                do {
                        sb.append((char)c);
                        nextChar();
                }
                while (Character.isLetterOrDigit(c));
                String name = sb.toString();
                if (KEYWORDS.containsKey(name)) {
                        return token = KEYWORDS.get(name);
                }
                        yylval = new Ident(name);
                return token = IDENT;
        }
        throw new RuntimeException("Unexpected character: " + (char)c);
*/
