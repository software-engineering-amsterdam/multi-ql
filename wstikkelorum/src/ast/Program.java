package ast;

import java.io.File;
import java.io.IOException;
import java.util.Map.Entry;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import antlr.QLLexer;
import antlr.QLParser;
import antlr.QLParser.FileContext;
import ast.visitor.FindAllDeclaredQuestions;
import ast.visitor.OperandsAndConditionsChecker;
import ast.visitor.Type;

public class Program {

	public static void main(String[] args) throws IOException {
		ANTLRFileStream input = new ANTLRFileStream(new File(
				"resources/Questionaire.ql").getPath());
		
//		ANTLRFileStream input = new ANTLRFileStream(new File(
//				"resources/SmallQuestionaire.ql").getPath());
		
		CommonTokenStream tokens = new CommonTokenStream(new QLLexer(input));
		QLParser parser = new QLParser(tokens);

		FileContext fileContext = parser.file();

//		System.out.println("----Evaluation-----");
//		EvalVisitor evalVisitor = new EvalVisitor();
//		evalVisitor.visit(fileContext.form().result);
//		for(Map.Entry<String, Object> entry : evalVisitor.evaluationMap.entrySet()){
//			String name = entry.getKey();
//			if(entry.getValue() != null){
//				String value = entry.getValue().toString();
//				System.out.println(String.format("%s, %s", name, value));
//			}else{
//				System.out.println(String.format("%s, unknown", name));
//			}
//		}
		
		//find all variables in the ql form
		FindAllDeclaredQuestions fav = new FindAllDeclaredQuestions<>();
		fav.visit(fileContext.form().result);
//		for(Question q : fav.getContext().getDeclaredQuestions()){
//			System.out.println(q.getVariable().getName());
//		}
		
		for(Entry<String, Type> entry : fav.getContext().getSymbolTable().entrySet()){
			System.out.println(entry.toString());
		}
		System.out.println();
		
		//Check the types for operands requires the current context
		OperandsAndConditionsChecker operandsChecker = new OperandsAndConditionsChecker<>(fav.getContext());
		operandsChecker.visit(fileContext.form().result);

		//TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), fileContext);
		//viewer.open();

		//new QLFrame(fileContext.form().result);
	}
}
