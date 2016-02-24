package ast;

import gui.QLFrame;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import antlr.QLLexer;
import antlr.QLParser;
import antlr.QLParser.FileContext;
import ast.visitor.EvalVisitor;
import ast.visitor.TypeChecker;

public class Program {

	public static void main(String[] args) throws IOException {
		ANTLRFileStream input = new ANTLRFileStream(new File(
				"resources/Questionaire.ql").getPath());
		CommonTokenStream tokens = new CommonTokenStream(new QLLexer(input));
		QLParser parser = new QLParser(tokens);

		FileContext fileContext = parser.file();
		// System.out.println(fileContext.form().toStringTree());

		// BasicVisitor visitor = new BasicVisitor();
		// visitor.visit(fileContext.form().result);

		System.out.println("----Type check-----");
		TypeChecker typeChecker = new TypeChecker();
		typeChecker.visit(fileContext.form().result);
		
		System.out.println("----Evaluation-----");
		EvalVisitor evalVisitor = new EvalVisitor();
		evalVisitor.visit(fileContext.form().result);
		for(Map.Entry<String, Object> entry : evalVisitor.evaluationMap.entrySet()){
			String name = entry.getKey();
			if(entry.getValue() != null){
				String value = entry.getValue().toString();
				System.out.println(String.format("%s, %s", name, value));
			}else{
				System.out.println(String.format("%s, unknown", name));
			}
		}

		TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), fileContext);
		viewer.open();

		new QLFrame(fileContext.form().result);
		System.out.println("Done");
	}
}
