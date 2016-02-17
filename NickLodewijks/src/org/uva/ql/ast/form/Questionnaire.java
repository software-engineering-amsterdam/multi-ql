package org.uva.ql.ast.form;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTNodeVisitor;
import org.uva.ql.parser.antlr.QLLexer;
import org.uva.ql.parser.antlr.QLParser;

public class Questionnaire extends ASTNode {

	private final List<Form> forms;

	public Questionnaire(ParserRuleContext context, List<Form> forms) {
		super(context);
		this.forms = forms;
	}

	public List<Form> getForms() {
		return Collections.unmodifiableList(forms);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	public static Questionnaire create(InputStream is) throws IOException {
		return Questionnaire.create(new ANTLRInputStream(is));
	}

	public static Questionnaire create(File file) throws IOException {
		return Questionnaire.create(new ANTLRFileStream(file.getAbsolutePath()));
	}

	private static Questionnaire create(CharStream cs) throws IOException {
		TokenStream tokenStream;
		QLParser parser;

		tokenStream = new CommonTokenStream(new QLLexer(cs));
		parser = new QLParser(tokenStream);

		return parser.file().questionnaire().result;
	}
}
