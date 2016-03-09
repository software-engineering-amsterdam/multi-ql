package org.uva.ql.ast.expr;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.uva.ql.ast.ASTNode;
import org.uva.ql.ast.ASTSourceInfo;
import org.uva.ql.parser.QLLexer;
import org.uva.ql.parser.QLParser;

public abstract class Expr extends ASTNode {

	public Expr(ASTSourceInfo sourceInfo) {
		super(sourceInfo);
	}

	public abstract <T, U> T accept(ExprVisitor<T, U> visitor, U context);

	public static Expr create(String text) throws IOException {
		TokenStream tokenStream;
		QLParser parser;

		tokenStream = new CommonTokenStream(new QLLexer(new ANTLRInputStream(text)));
		parser = new QLParser(tokenStream);

		return parser.expr().result;
	}
}
