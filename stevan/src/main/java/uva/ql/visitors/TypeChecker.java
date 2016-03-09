package uva.ql.visitors;

import java.util.HashMap;

import uva.ql.ast.Form;
import uva.ql.visitors.typechecker.ArithmeticOperator;
import uva.ql.visitors.typechecker.CyclicDependency;
import uva.ql.visitors.typechecker.DuplicateLabels;
import uva.ql.visitors.typechecker.DuplicateQuestionDifferentTypes;
import uva.ql.visitors.typechecker.UndefinedQuestions;

public class TypeChecker {
	
	public static HashMap<String, Integer> checkAST( Form form ) {
		
		HashMap<String, Integer> msg = new HashMap<String, Integer>(0);
		
		UndefinedQuestions ud = new UndefinedQuestions();
		form.accept(ud);
		
		CyclicDependency cd = new CyclicDependency();
		form.accept(cd);
		
		DuplicateQuestionDifferentTypes dqdt = new DuplicateQuestionDifferentTypes();
		form.accept(dqdt);
		
		DuplicateLabels dl = new DuplicateLabels();
		form.accept(dl);
		
		ArithmeticOperator ao = new ArithmeticOperator();
		form.accept(ao);
		
		return msg;
	}
	
	/* 
	 * Abstract message, sub class error,...
	 * Simplify!
	 * */
}




















