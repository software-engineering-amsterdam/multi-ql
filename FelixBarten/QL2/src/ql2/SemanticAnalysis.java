package ql2;

import java.io.IOException;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import ql2.ast.Form;
import ql2.ast.type.QuestionType;
import ql2.parser.generated.Ql2Lexer;
import ql2.parser.generated.Ql2Parser;

public class SemanticAnalysis {

	private Context context; 
	private String content; 
	private Form form;
	
	public SemanticAnalysis(String content) {
		context = new Context();
		this.content = content;
		
	}
	public SemanticAnalysis(String content, Context context) {
		this.context = context;
		this.content = content;
	}
	
	public void run() {
		System.out.println(String.format("\nRunning Semantic analyzer for: %s", content));
		Form form = parseData(content);
		analyseQLData(form);
		
		report();
		System.out.println("finished semantic analysis.\n");
	}
	
	private Form parseData(String path) {
		Ql2Lexer lexer;
		Form form = null;
		try {
			lexer = new Ql2Lexer(CharStreams.fromFileName(path));
			CommonTokenStream tokens2 = new CommonTokenStream( lexer );
			Ql2Parser parser = new Ql2Parser( tokens2 );	
			form = parser.form().result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return form;
	}
	
	public void analyseQLData(Form form) {
		buildContext(form);
		checkTypes(form);
		checkDependencies(form);
		this.form = form;
	}	
	private void checkDependencies(Form form) {
		DependencyVisitor<Object> visitor = new DependencyVisitor<>(context);
		visitor.visit(form);
		visitor.process();
		context = visitor.getContext();	
	}
	
	private void evaluate(Form form) {
		EvalVisitor visitor = new EvalVisitor(context);
		visitor.visit(form);
		context = visitor.getContext();
	}
	
	private void buildContext(Form form) {
		QuestionVisitor<Object> visitor = new QuestionVisitor<>(context);
		visitor.visit(form);
		context = visitor.getContext();
	}
	
	private void checkTypes(Form form) {
		TypeChecker<Object> tc = new TypeChecker<>(context);
		tc.visit(form); 
		context = tc.getContext();
	}
	
	public void report() {
		context.report();
	}
	
	public void reset(String path) {
		this.content = path; 
		this.context = new Context();
		run();
	}
	
	public Context getContext() {
		return context;
	}
	
	public void setContext(Context context) {
		this.context = context;
	}
	
	public Form getForm() {
		return form;
	}
	
}
