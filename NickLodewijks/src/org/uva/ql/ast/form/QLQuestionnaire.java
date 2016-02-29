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
import org.uva.ql.parser.QLLexer;
import org.uva.ql.parser.QLParser;

public class QLQuestionnaire extends ASTNode {

	private final List<QLForm> forms;

	public QLQuestionnaire(ParserRuleContext context, List<QLForm> forms) {
		super(context);
		this.forms = forms;
	}

	public List<QLForm> getForms() {
		return Collections.unmodifiableList(forms);
	}

	@Override
	public <T, U> T accept(ASTNodeVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

	public static QLQuestionnaire create(InputStream is) throws IOException {
		return QLQuestionnaire.create(new ANTLRInputStream(is));
	}

	public static QLQuestionnaire create(File file) throws IOException {
		return QLQuestionnaire.create(new ANTLRFileStream(file.getAbsolutePath()));
	}

	private static QLQuestionnaire create(CharStream cs) throws IOException {
		TokenStream tokenStream;
		QLParser parser;

		tokenStream = new CommonTokenStream(new QLLexer(cs));
		parser = new QLParser(tokenStream);

		return parser.file().questionnaire().result;
	}
}
