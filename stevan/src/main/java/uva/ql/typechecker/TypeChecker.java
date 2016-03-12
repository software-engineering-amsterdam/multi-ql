package uva.ql.typechecker;

import java.util.HashMap;
import java.util.Map;

import uva.ql.ast.Form;
import uva.ql.typechecker.abstracts.ErrorWarning;

public class TypeChecker {
	
	public static Map<String, Integer> checkAST( Form form ) {
		
		Map<String, Integer> msg = new HashMap<String, Integer>(0);
		
		UndefinedQuestions ud = new UndefinedQuestions();
		form.accept(ud);
		for( ErrorWarning ew : ud.getErrors()) {
			msg.put(ew.getMessage(), -1);
		}
		
		CyclicDependency cd = new CyclicDependency();
		form.accept(cd);
		for( ErrorWarning ew : cd.getErrors()) {
			msg.put(ew.getMessage(), -1);
		}
		
		DuplicateQuestionDifferentTypes dqdt = new DuplicateQuestionDifferentTypes();
		form.accept(dqdt);
		for( ErrorWarning ew : dqdt.getErrors()) {
			msg.put(ew.getMessage(), -1);
		}
		
		DuplicateLabels dl = new DuplicateLabels();
		form.accept(dl);
		for( ErrorWarning ew : dl.getErrors()) {
			msg.put(ew.getMessage(), -2);
		}
		
		ArithmeticOperator ao = new ArithmeticOperator();
		form.accept(ao);
		for( ErrorWarning ew : ao.getErrors()) {
			msg.put(ew.getMessage(), -1);
		}
		
		BinaryOperator bo = new BinaryOperator();
		form.accept(bo);
		for( ErrorWarning ew : bo.getErrors()) {
			msg.put(ew.getMessage(), -1);
		}
		
		return msg;
	}
}




















