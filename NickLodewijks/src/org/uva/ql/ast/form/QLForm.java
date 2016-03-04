package org.uva.ql.ast.form;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.parser.QLLexer;
import org.uva.ql.parser.QLParser;

public class QLForm extends ASTNode {

	private final String name;
	private final QLBlock body;

	public QLForm(ParserRuleContext context, String id, QLBlock body) {
		super(context);
		this.name = id;
		this.body = body;
	}

	public List<QLQuestion> getQuestions() {
		return body.getQuestions();
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

		return parser.file().form().result;
	}
}
