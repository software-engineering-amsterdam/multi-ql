package ql;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import ql.antlr.QLLexer;
import ql.antlr.QLParser;
import ql.antlr.QLParser.FileContext;
import ql.ast.form.Form;

public class FormParser {

	public FormParser() {

	}

	// TODO:expection handling
	public Form parseForm(String path, boolean viewTree) throws IOException {
		ANTLRFileStream input = new ANTLRFileStream(new File(path).getPath());
		CommonTokenStream tokens = new CommonTokenStream(new QLLexer(input));
		QLParser parser = new QLParser(tokens);
		FileContext fileContext = parser.file();

		if (viewTree) {
			TreeViewer viewer = new TreeViewer(Arrays.asList(parser
					.getRuleNames()), fileContext);
			viewer.open();
		}

		return fileContext.form().result;
	}
}
