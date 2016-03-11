package sc.ql.ast.form;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import sc.ql.ast.ASTNode;
import sc.ql.parser.QLLexer;
import sc.ql.parser.QLParser;

public class QLForm extends ASTNode {

	private final String name;
	private final QLBlock body;

	public QLForm(String id, QLBlock body) {
		this.name = id;
		this.body = body;
	}

	public QLBlock getBody() {
		return body;
	}

	public String getName() {
		return name;
	}

	public <T, U> T accept(QLFormVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	public static QLForm create(InputStream is) throws IOException {
		return QLForm.create(new ANTLRInputStream(is));
	}

	public static QLForm create(File file) throws IOException {
		return QLForm.create(new ANTLRFileStream(file.getAbsolutePath()));
	}

	private static QLForm create(CharStream cs) throws IOException {
		TokenStream tokenStream;
		QLParser parser;

		tokenStream = new CommonTokenStream(new QLLexer(cs));
		parser = new QLParser(tokenStream);

		return parser.form().result;
	}
}
