package ql;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.ast.visitor.Evaluation;

public class FormEvaluation {
	
	public FormEvaluation(){
		
	}
	
	public void evaluateForm(Form form, Context context){
		Evaluation eval = new Evaluation(context);
		eval.visit(form);
		//TODO:.....
	}

}
