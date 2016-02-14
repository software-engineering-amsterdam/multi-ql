package eu.bankersen.kevin.ql.parser;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.parser.QLParser.FormContext;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class FormParser {


    private final String input;
    private FormContext formContext;
    private QLParser parser;


    public FormParser(final String input) {
	this.input = input;
	parse();
    }


    private void parse() {

	Log.info("Parsing File");

	ANTLRInputStream antlrStream = new ANTLRInputStream(input);

	QLLexer lexer = new QLLexer(antlrStream);

	CommonTokenStream tokenStream = new CommonTokenStream(lexer);

	parser = new QLParser(tokenStream);

	formContext = parser.form();
    }


    public final FormContext getFormContext() {
	return formContext;
    }


    public final Form getForm() {
	return formContext.result;
    }


    public final int getParseErrors() {
	return parser.getNumberOfSyntaxErrors();
    }


    public final String[] getParseRules() {
	return parser.getRuleNames();
    }
}