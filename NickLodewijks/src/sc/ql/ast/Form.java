package sc.ql.ast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import sc.ql.parser.QLLexer;
import sc.ql.parser.QLParser;

public class Form extends ASTNode {

	private final String name;
	private final Block body;

	public Form(String id, Block body) {
		this.name = id;
		this.body = body;
	}

	public Block getBody() {
		return body;
	}

	public String getName() {
		return name;
	}

	public <T, U> T accept(FormVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	public static Form create(InputStream is) throws IOException {
		return Form.create(new ANTLRInputStream(is));
	}

	public static Form create(File file) throws IOException {
		return Form.create(new ANTLRFileStream(file.getAbsolutePath()));
	}

	private static Form create(CharStream cs) throws IOException {
		TokenStream tokenStream;
		QLParser parser;

		tokenStream = new CommonTokenStream(new QLLexer(cs));
		parser = new QLParser(tokenStream);

		return parser.form().result;
	}
}
