package uva.ql.typechecker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import uva.ql.ast.Form;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.questions.QuestionVanilla;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.interfaces.INode;
import uva.ql.visitors.typechecker.CollectVariables;
import uva.ql.visitors.typechecker.ConditionsNotOfTypeBoolean;
import uva.ql.visitors.typechecker.CyclicDepenUndefQuestions;
import uva.ql.visitors.typechecker.OperandsToOperators;

public class TypeChecker {
	
	public static HashMap<String, Integer> checkAST( Form form ) {
		
		HashMap<String, Integer> msg = new HashMap<String, Integer>(0);
		
		CollectVariables cv = new CollectVariables();
		form.accept(cv);
		
		msg.putAll( checkCyclicDepenUndefQuestions(form, cv) );
		msg.putAll( checkForDupQuestionsOfDiffTypes(cv) );
		msg.putAll( checkConditionsNotOfTypeBoolean(form) );
		msg.putAll( checkInvalidOperandToOperators(form) );
		msg.putAll( checkDuplicateLabels(cv) );
		
		return msg;
	}
	
	/* 
	 * Abstract message, sub class error,...
	 * Simplify!
	 * */
	
	public static HashMap<String, Integer> checkForDupQuestionsOfDiffTypes( CollectVariables cv ) {
		
		HashMap<String, Integer> msg = new HashMap<String, Integer>(0);
		ArrayList<Variable> varList = cv.getStore();
		Set<String> varNameSet = new HashSet<String>();

		for( Variable var : varList ) {
			
			if( !varNameSet.add(var.getName()) ) {
				
				msg.put( "Error: Duplicate question declaration with variable '" + var.getName() + "' of different type, starting at line: " + var.getLine() + ", column: " + var.getColumn()
						, -1 );
			}
		}
		
		return msg;
	}
	
	public static HashMap<String, Integer> checkConditionsNotOfTypeBoolean( Form form ) {
		
		ConditionsNotOfTypeBoolean cndtnsNOTB = new ConditionsNotOfTypeBoolean();
		form.accept( cndtnsNOTB );
		
		return cndtnsNOTB.getResult();
	}
	
	public static HashMap<String, Integer> checkInvalidOperandToOperators( Form form ) {
		
		OperandsToOperators oto = new OperandsToOperators();
		form.accept( oto );

		return oto.getResult();
	}
	
	public static HashMap<String, Integer> checkCyclicDepenUndefQuestions( Form form, CollectVariables cv ) {
		
		CyclicDepenUndefQuestions cd = new CyclicDepenUndefQuestions(cv.getStore());
		form.accept( cd );
		
		return cd.getResult();
	}
	
	public static HashMap<String, Integer> checkDuplicateLabels( CollectVariables cv ) {
		
		HashMap<String, Integer> msg = new HashMap<String, Integer>(0);
		ArrayList<Variable> varList = cv.getStore();
		Set<String> qLabelSet = new HashSet<String>();
		
		for( Variable var : varList ) {
			
			Node node = var.getParent();
			//System.out.println(var.getName() + " - " + var.getVarType());
			if( node.getNodeType() == INode.QUESTION ) {
				
				QuestionVanilla q = (QuestionVanilla) node;
				
				if( !qLabelSet.add(q.getLabel()) ) {
					 
					msg.put( "Warning: Duplicate label '" + q.getLabel() + "', starting at line: " + q.getLine() + ", column: " + q.getColumn()
							, -2 );
				}
			}
		}
		
		return msg;
	}
}




















