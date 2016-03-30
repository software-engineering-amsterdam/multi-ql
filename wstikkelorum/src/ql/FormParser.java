package ql;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.antlr.QLParser.FileContext;
import ql.ast.form.Form;

public class FormParser {
	public static Form parseForm(String path) throws IOException {
		try{
			ANTLRFileStream input = new ANTLRFileStream(new File(path).getPath());
			CommonTokenStream tokens = new CommonTokenStream(new QLLexer(input));
			QLParser parser = new QLParser(tokens);
			FileContext fileContext = parser.file();
			return fileContext.form().result;
		}catch(IOException ioe){
			throw(ioe);
		}
	}
}
