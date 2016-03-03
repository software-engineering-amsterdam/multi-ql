package ql;

import java.io.IOException;

import ql.ast.form.Form;

public class Program {
	
	//TODO: exception handling
	public static void main(String[] args) throws IOException{
		FormParser fp = new FormParser();
		Form form = fp.parseForm("resources/CyclicExampleD.ql", false);
//		Form form = fp.parseForm("resources/SmallQuestionaire.ql", false);
//		Form form = fp.parseForm("resources/Questionaire.ql", false);
		
		SemanticAnalyser sa = new SemanticAnalyser();
		sa.analyseForm(form);
		
		if(sa.noIssues()){
			FormEvaluation formEval = new FormEvaluation();
			formEval.evaluateForm(form, sa.getContext());
			
			//TODO: draw form
		}else{
			//TODO: display warnings
		}
		
		//new QLFrame(fileContext.form().result);
	}
}
