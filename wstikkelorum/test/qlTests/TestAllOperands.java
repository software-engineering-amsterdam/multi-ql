package qlTests;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

import ql.FormEvaluator;
import ql.FormParser;
import ql.SemanticAnalyser;
import ql.ast.form.Form;

public class TestAllOperands {

	@Test
	public void test() throws IOException {
		Form form = FormParser.parseForm("testResources/testAllOperands.ql");
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		
		assert(semanticAnalyser.noIssues());
		if(semanticAnalyser.noIssues()){
			FormEvaluator formEval = new FormEvaluator(semanticAnalyser.getContext());
			formEval.evaluateForm(form);
			HashMap<String, Object> values = formEval.getContext().getIdentifierToValueMap();
			
			assert((int) values.get("summation") == 6);
			assert((int) values.get("multiplication") == 12);
			assert((int) values.get("division") == 2);
			assert((int) values.get("subtraction") == 9);
			assert((int) values.get("positve") == 4);
			assert((int) values.get("negative") == -4);
			
			assert((boolean) values.get("not") == false);
			assert((boolean) values.get("or") == true);
			assert((boolean) values.get("and") == true);
			
			assert((boolean) values.get("less") == true);
			assert((boolean) values.get("lessEquals") == true);
			assert((boolean) values.get("greater") == true);
			assert((boolean) values.get("greaterEquals") == true);
			assert((boolean) values.get("equals") == true);
			assert((boolean) values.get("notEquals") == true);
		}
	}
}
