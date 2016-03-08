package ql;

import java.io.IOException;

import ql.ast.form.Form;

public class Program {
	
	//TODO: exception handling
	public static void main(String[] args) throws IOException{
		FormParser formParser = new FormParser();
//		Form form = formParser.parseForm("resources/CyclicExampleD.ql", false);
//		Form form = formParser.parseForm("resources/SmallQuestionaire.ql", false);
		Form form = formParser.parseForm("resources/Questionaire.ql", false);
		
		SemanticAnalyser semanticAnalyser = new SemanticAnalyser();
		semanticAnalyser.analyseForm(form);
//		semanticAnalyser.printData();
		
		if(semanticAnalyser.noIssues()){
			FormEvaluation formEval = new FormEvaluation(semanticAnalyser.getContext());
			formEval.evaluateForm(form);
			formEval.printData();
			
			//TODO: draw form
		}else{
			//TODO: display warnings
		}
		
		//new QLFrame(fileContext.form().result);
		System.out.println("Done");
	}
}
