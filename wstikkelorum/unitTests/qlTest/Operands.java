package qlTest;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;

import ql.FormEvaluator;
import ql.FormParser;
import ql.SemanticAnalyser;
import ql.ast.form.Form;
import ql.ast.value.Value;

public class Operands {

	@Test
	public void test() throws IOException {
		Form form = FormParser.parseForm("testResources/testAllOperands.ql");
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
		
		assert(semanticAnalyser.noIssues());
		if(semanticAnalyser.noIssues()){
			FormEvaluator formEval = new FormEvaluator(semanticAnalyser.getContext());
			formEval.evaluateForm(form);
			HashMap<String, Value> values = formEval.getContext().getIdentifierToValueMap();
			
			assert((int) values.get("summation").getValue() == 6);
			assert((int) values.get("multiplication").getValue() == 12);
			assert((int) values.get("division").getValue() == 2);
			assert((int) values.get("subtraction").getValue() == 9);
			assert((int) values.get("positve").getValue() == 4);
			assert((int) values.get("negative").getValue() == -4);
			
			assert((boolean) values.get("not").getValue() == false);
			assert((boolean) values.get("or").getValue() == true);
			assert((boolean) values.get("and").getValue() == true);
			
			assert((boolean) values.get("less").getValue() == true);
			assert((boolean) values.get("lessEquals").getValue() == true);
			assert((boolean) values.get("greater").getValue() == true);
			assert((boolean) values.get("greaterEquals").getValue() == true);
			assert((boolean) values.get("equals").getValue() == true);
			assert((boolean) values.get("notEquals").getValue() == true);
		}
	}
}
