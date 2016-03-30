package eu.bankersen.kevin.ql.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.parser.QLParser.FormContext;

public class FormParser {

    private final String input;
    private FormContext formContext;
    private QLParser parser;

    public FormParser(String file) throws ANTLRParseException, IOException {
	this.input = readFile(file);
	parse();
    }

    private void parse() throws ANTLRParseException {

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

    public Form getForm() {
	return formContext.result;
    }

    private String readFile(final String filePath) throws IOException {

	BufferedReader reader = Files.newBufferedReader(Paths.get(filePath));

	StringBuilder out = new StringBuilder();
	String line;

	while ((line = reader.readLine()) != null) {
	    out.append(line + "\n");
	}
	reader.close();

	return out.toString();
    }
}
