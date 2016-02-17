package org.uva.ql;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.parser.antlr.QLLexer;
import org.uva.ql.parser.antlr.QLParser;
import org.uva.ql.ui.DefaultWidgetFactory;
import org.uva.ql.ui.QLQuestionaire;

public class QLMain {
	
	public static void main(String[] args) throws IOException {
		QLQuestionaire qlQuestionnaire;
		Questionnaire questionnaire;
		QLInterpreter qlInterpreter;
		File inputFile;

		inputFile = new File("resources/Questionaire.ql");
		questionnaire = parseFile(inputFile);

		new SemanticAnalyser().analyse(questionnaire);

		qlInterpreter = new QLInterpreter(new DefaultWidgetFactory());

		qlQuestionnaire = qlInterpreter.interpret(questionnaire);
		qlQuestionnaire.show();
	}

	private static Questionnaire parseFile(File file) throws IOException {
		TokenStream tokenStream;
		CharStream is;
		QLParser parser;

		is = new ANTLRFileStream(file.getPath());

		tokenStream = new CommonTokenStream(new QLLexer(is));
		parser = new QLParser(tokenStream);
		parser.addParseListener(new QLParseTreeListener());

		return parser.file().questionnaire().result;
	}
}
