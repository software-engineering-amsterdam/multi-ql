package ast;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map.Entry;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import antlr.QLLexer;
import antlr.QLParser;
import antlr.QLParser.FileContext;
import ast.visitor.CyclicDependencyChecker;
import ast.visitor.FindAllDeclaredQuestions;
import ast.visitor.Type;
import ast.visitor.TypeChecker;

public class Program {

	public static void main(String[] args) throws IOException {
//		ANTLRFileStream input = new ANTLRFileStream(new File(
//				"resources/Questionaire.ql").getPath());
		
//		ANTLRFileStream input = new ANTLRFileStream(new File(
//				"resources/SmallQuestionaire.ql").getPath());
		
		ANTLRFileStream input = new ANTLRFileStream(new File(
				"resources/CyclicExampleD.ql").getPath());
		
		CommonTokenStream tokens = new CommonTokenStream(new QLLexer(input));
		QLParser parser = new QLParser(tokens);

		FileContext fileContext = parser.file();

		
		//find all variables in the ql form
		System.out.println("----Find all variables in the QL form----");
		FindAllDeclaredQuestions<Object> fav = new FindAllDeclaredQuestions<>();
		fav.visit(fileContext.form().result);
		for(Entry<String, Type> entry : fav.getContext().getSymbolTable().entrySet()){
			System.out.println(entry.toString());
		}
		System.out.println();
		
		
		//Check the types for operands and Conditions requires the current context
		System.out.println("----Check all the type of the operands and conditions----");
		TypeChecker<Object> typeChecker = new TypeChecker<>(fav.getContext());
		typeChecker.visit(fileContext.form().result);
		System.out.println();
		
		//requires a correct ast! 
//		System.out.println("----Evaluate all the expressions----");
//		EvalVisitor evalVisitor = new EvalVisitor(typeChecker.getContext());
//		evalVisitor.visit(fileContext.form().result);
//		for(Entry<String, Object> entry : evalVisitor.getContext().getVartoValueTable().entrySet()){
//			System.out.println(entry.toString());
//		}
		
		CyclicDependencyChecker<Object> cdc = new CyclicDependencyChecker<>();
		cdc.visit(fileContext.form().result);
		cdc.findCyclicDependencies();
		

		//TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), fileContext);
		//viewer.open();

		//new QLFrame(fileContext.form().result);
	}
}
