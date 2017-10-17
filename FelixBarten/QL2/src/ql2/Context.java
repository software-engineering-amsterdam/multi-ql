package ql2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import ql2.ast.CalculatedQuestion;
import ql2.ast.Expr;
import ql2.ast.InputQuestion;
import ql2.ast.Question;
import ql2.ast.Statement;
import ql2.ast.type.QuestionType;
import ql2.conflict.Conflict;
import ql2.conflict.DuplicateLabel;
import ql2.conflict.DuplicateQuestionID;
import ql2.conflict.TypeMismatch;
import ql2.conflict.VariableNotDeclared;

/**
 * 
 * @author felixbarten
 * Context class will store the context for a Form. 
 */
public class Context {

	
	private List<Question> questions; 
	private List<Statement> statements;
	
	private List<String> questionLabels;
	private List<Conflict> problems;
	private HashMap<String, QuestionType> questTypes;
	private HashMap<String, Object> variables;
	
	public Context() {

		this.questions = new ArrayList<Question>();
		this.statements = new ArrayList<Statement>();
		this.questionLabels = new ArrayList<String>();
		this.problems = new ArrayList<Conflict>();
		this.questTypes = new HashMap<String,QuestionType>();
		this.variables = new HashMap<String, Object>();
	}
	
	public void addQuestion(InputQuestion question) {
		String ID = question.getQuestionID();
		String label = question.getQuestionText();
		
		// refactor if statemetns out to method?
		
		if (questionLabels.contains(label)) {
			problems.add(new DuplicateLabel(question, label));
		}
		
		if(questTypes.containsKey(ID)) {
			problems.add(new DuplicateQuestionID(question, ID));
		}
		questionLabels.add(question.getQuestionText());
		questions.add(question);
		questTypes.put(ID, question.getType());
		
	}
	
	public void addQuestion(CalculatedQuestion question) {
		String ID = question.getInput().getQuestionID();		
		String label = question.getInput().getQuestionText();
		Expr computation = question.getCalculation();
		//Check labels
		if (questionLabels.contains(label)) {
			problems.add(new DuplicateLabel(question,label));
		}
		
		if (questTypes.containsKey(ID)) {
			problems.add(new DuplicateQuestionID(question, ID));
		}
		questionLabels.add(question.getInput().getQuestionText());
		questions.add(question);
		questTypes.put(ID, question.getInput().getType());
		variables.put(ID, computation); // where to typecheck.
	}
	
	public HashMap<String, Object> getVariables() {
		return variables;
	}
	
	public void addVariable(String key, Object value) {
		if (!variables.containsKey(key)) {
			if (!questTypes.containsKey(key)) {
				problems.add(new VariableNotDeclared(key, value));
			} else {
				variables.put(key, value);
			}
		}
	}
	
	public void addVariable(String key) {
		if (!variables.containsKey(key)) {
			if (!questTypes.containsKey(key)) {
				problems.add(new VariableNotDeclared(key));
			} else {
				variables.put(key, questTypes.get(key));
			}
		}
	}
	
	/**
	 * Sorts the List on severity of errors/warnings
	 */
	private void sortConflicts() {
		problems.sort(Comparator.comparing(Conflict::getConflictLevel));
	}
	
	public void report() {
		reportProblems();
		reportContent(); 
	}
	
	private boolean noProblems() {
		return problems.size() == 0;
	}

	private void reportProblems() {
		sortConflicts();
		if (!noProblems()) {
			System.out.println(String.format("%s problem(s) found", problems.size()));
			
			for (Conflict c : problems) {
				c.logIssues();
				System.out.println(c.getClass());
			}
		}
	}
	
	private void reportContent() {
		System.out.println(String.format("Context contains: %s questions, %s statements", questions.size(), statements.size()));
	}

	public void addConflict(Conflict c) {
		problems.add(c);		
	}
}
