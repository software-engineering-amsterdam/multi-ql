package qlTest;

import java.io.IOException;

import org.junit.Test;

import ql.FormParser;
import ql.SemanticAnalyser;
import ql.ast.form.Form;
import ql.ast.visitor.Context;

public class CyclicDependency {

	@Test
	public void testA() throws IOException {
		Form form = FormParser.parseForm("testResources/CyclicExampleA.ql");
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		Context context = semanticAnalyser.getContext();
		assert(context.numberOfIssues() > 0);
	}
	
	@Test
	public void testB() throws IOException {
		Form form = FormParser.parseForm("testResources/CyclicExampleB.ql");
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		Context context = semanticAnalyser.getContext();
		assert(context.numberOfIssues() > 0);
	}
	
	@Test
	public void testC() throws IOException {
		Form form = FormParser.parseForm("testResources/CyclicExampleC.ql");
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		Context context = semanticAnalyser.getContext();
		assert(context.numberOfIssues() > 0);
	}
	
	@Test
	public void testD() throws IOException {
		Form form = FormParser.parseForm("testResources/CyclicExampleD.ql");
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		Context context = semanticAnalyser.getContext();
		assert(context.numberOfIssues() > 0);
	}
}
