package sc.ql.ast.expr;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import sc.ql.ast.ASTNode;
import sc.ql.parser.QLLexer;
import sc.ql.parser.QLParser;

public abstract class Expr extends ASTNode {

	public abstract <T, U> T accept(ExprVisitor<T, U> visitor, U context);

	public static Expr create(String text) throws IOException {
		TokenStream tokenStream;
		QLParser parser;

		tokenStream = new CommonTokenStream(new QLLexer(new ANTLRInputStream(text)));
		parser = new QLParser(tokenStream);

		return parser.expr().result;
	}
}
