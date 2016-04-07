package eu.bankersen.kevin.ql.form.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import eu.bankersen.kevin.ql.form.ast.Form;
import eu.bankersen.kevin.ql.parser.QLLexer;
import eu.bankersen.kevin.ql.parser.QLParser;
import eu.bankersen.kevin.ql.parser.QLParser.FormContext;

public class FormParser {

	private final Form form;

	public FormParser(File file) throws ParseException, IOException {
		String input = readFile(file);
		form = parse(input);
	}

	public Form getForm() {
		return form;
	}

	private Form parse(String input) throws ParseException {

		ANTLRInputStream antlrStream = new ANTLRInputStream(input);

		QLLexer lexer = new QLLexer(antlrStream);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		QLParser parser = new QLParser(tokenStream);

		ErrorListener listener = new ErrorListener();

		parser.addErrorListener(listener);

		FormContext formContext = parser.form();

		if (listener.containsErrors()) {
			throw new ParseException(listener.getErrors());
		}
		return formContext.result;
	}

	private String readFile(File file) throws IOException {

		FileReader in = new FileReader(file);
		BufferedReader reader = new BufferedReader(in);

		StringBuilder out = new StringBuilder();
		String line;

		while ((line = reader.readLine()) != null) {
			out.append(line + "\n");
		}
		in.close();
		reader.close();

		return out.toString();
	}
}
