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

    public FormParser(final String input) throws ANTLRParseException {
	this.input = input;
	parse();
    }

    private void parse() throws ANTLRParseException {

	Log.info("Parsing File");

	ANTLRInputStream antlrStream = new ANTLRInputStream(input);

	QLLexer lexer = new QLLexer(antlrStream);

	CommonTokenStream tokenStream = new CommonTokenStream(lexer);

	parser = new QLParser(tokenStream);
	
	ErrorListener listener = new ErrorListener();
	
	parser.addErrorListener(listener);

	formContext = parser.form();
	
	if (listener.errors()) {
	    throw new ANTLRParseException(listener.getErrors());
	}
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