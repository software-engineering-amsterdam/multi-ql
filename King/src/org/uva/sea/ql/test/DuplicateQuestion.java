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

public class DuplicateQuestion {

	@Test
	public void testDuplicateQuestion() throws IOException {
		ANTLRInputStream input = new ANTLRFileStream(new File("resources/test/questionairetestduplicatevar.gr").getPath());
		QLLexer lexer = new QLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);

		FileContext fileContext = parser.file();
		Form ast = fileContext.form(0).result;
		TypeChecker typeChecker = new TypeChecker(ast);
		typeChecker.addOtherSymanticErrors();
		String errorKey = "duplicateQuestion";
		Message actual = typeChecker.getQLAllSemanticMessages();
		Message expected = new Message();
		expected.addError(errorKey,"The question 'hasBoughtHouse' has been declared multiple time with different type");
		assertEquals(expected.getErrors().get(errorKey), actual.getErrors().get(errorKey));
	}

}
