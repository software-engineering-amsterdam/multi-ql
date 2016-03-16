package qlTests;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

import ql.FormEvaluation;
import ql.Program;
import ql.SemanticAnalyser;
import ql.ast.form.Form;

public class TestAllOperands {

	@Test
	//TODO: exception handling
	public void test() throws IOException {
		Form form = Program.parseForm("testResources/testAllOperands.ql");
		SemanticAnalyser semanticAnalyser = Program.analyseForm(form);
		
		assert(semanticAnalyser.noIssues());
		if(semanticAnalyser.noIssues()){
			FormEvaluation formEval = Program.evaluateForm(form, semanticAnalyser.getContext());
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
