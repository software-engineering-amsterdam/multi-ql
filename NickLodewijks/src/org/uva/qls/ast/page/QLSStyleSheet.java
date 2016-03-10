package org.uva.qls.ast.page;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.qls.ast.QLSASTNode;
import org.uva.qls.parser.QLSLexer;
import org.uva.qls.parser.QLSParser;

public class QLSStyleSheet extends QLSASTNode {

	private final List<QLSPage> pages;

	public QLSStyleSheet(String id, List<QLSPage> pages) {
		this.pages = pages;
	}

	public List<QLSPage> getPages() {
		return Collections.unmodifiableList(pages);
	}

	public QLSQuestion getQLSQuestion(QLQuestion question) {
		for (QLSPage page : pages) {
			for (QLSSection section : page.getSections()) {
				QLSQuestion qlsQuestion;

				qlsQuestion = section.getById(question.getId());

				if (qlsQuestion != null) {
					return qlsQuestion;
				}
			}
		}

		assert false : "Question not contained in stylesheet " + question;

		return null;
	}

	public static QLSStyleSheet create(InputStream is) throws IOException {
		return QLSStyleSheet.create(new ANTLRInputStream(is));
	}

	public static QLSStyleSheet create(File file) throws IOException {
		return QLSStyleSheet.create(new ANTLRFileStream(file.getAbsolutePath()));
	}

	private static QLSStyleSheet create(CharStream cs) throws IOException {
		TokenStream tokenStream;
		QLSParser parser;

		tokenStream = new CommonTokenStream(new QLSLexer(cs));
		parser = new QLSParser(tokenStream);

		return parser.stylesheet().result;
	}
}
