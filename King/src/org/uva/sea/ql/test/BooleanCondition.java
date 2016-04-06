package org.uva.sea.ql.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.uva.sea.ql.ast.domain.Form;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.FileContext;
import org.uva.sea.ql.semantic.Message;
import org.uva.sea.ql.semantic.TypeChecker;

public class BooleanCondition {

	@Test
	public void testBooleanCondition() throws IOException {
		ANTLRInputStream input = new ANTLRFileStream(new File("resources/test/questionairetestbooleancondition.gr").getPath());
		QLLexer lexer = new QLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);

		FileContext fileContext = parser.file();
		Form ast = fileContext.form(0).result;
		TypeChecker typeChecker = new TypeChecker(ast);
		typeChecker.addOtherSymanticErrors();
		String errorKey = "condition";
		Message actual = typeChecker.getQLAllSemanticMessages();
		Message expected = new Message();
		expected.addError(errorKey,"[ name: hasBoughtHouse -> type: Money ] condition is not of the type boolean");
		assertEquals(expected.getErrors().get(errorKey), actual.getErrors().get(errorKey));
	}

}
