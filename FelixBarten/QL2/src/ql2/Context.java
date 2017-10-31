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
import ql2.conflict.RedefinedQuestion;
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
	private List<Conflict> errors;
	private List<Conflict> warnings;
	private HashMap<String, QuestionType> questTypes;
	private HashMap<String, Object> variables;
	
	public Context() {

		this.questions = new ArrayList<Question>();
		this.statements = new ArrayList<Statement>();
		this.questionLabels = new ArrayList<String>();
		this.errors = new ArrayList<Conflict>();
		this.warnings = new ArrayList<Conflict>();
		this.questTypes = new HashMap<String,QuestionType>();
		this.variables = new HashMap<String, Object>();
	}
	
	public void addQuestion(InputQuestion question) {
		String ID = question.getQuestionID();
		String label = question.getQuestionText();
		
		// refactor if statemetns out to method?
		
		if (questionLabels.contains(label)) {
			warnings.add(new DuplicateLabel(question, label));
		}
		
		if(questTypes.containsKey(ID)) {
			if (question.getType() == questTypes.get(ID)) {
				errors.add(new DuplicateQuestionID(question, ID));
			} else {
				errors.add(new RedefinedQuestion(question, ID, questTypes.get(ID)));
			}
		}
		questionLabels.add(question.getQuestionText());
		questions.add(question);
		questTypes.put(ID, question.getType());
		variables.put(ID, null);
	}
	
	public void addQuestion(CalculatedQuestion question) {
		String ID = question.getInput().getQuestionID();		
		String label = question.getInput().getQuestionText();
		Expr computation = question.getCalculation();
		//Check labels
		if (questionLabels.contains(label)) {
			warnings.add(new DuplicateLabel(question,label));
		}
		
		if (questTypes.containsKey(ID)) {
			if (question.getInput().getType() == questTypes.get(ID)) {
				errors.add(new DuplicateQuestionID(question, ID));
			} else {
				errors.add(new RedefinedQuestion(question, ID, questTypes.get(ID)));
			}
		}
		questionLabels.add(question.getInput().getQuestionText());
		questions.add(question);
		questTypes.put(ID, question.getInput().getType()); //
		variables.put(ID, computation); // where to typecheck.
	}
	
	public HashMap<String, Object> getVariables() {
		return variables;
	}
	
	public void addVariable(String key, Object value) {
		if (!variables.containsKey(key)) {
			if (!questTypes.containsKey(key)) {
				errors.add(new VariableNotDeclared(key, value));
			} else {
				variables.put(key, value);
			}
		}
	}
	
	public void addVariable(String key) {
		if (!variables.containsKey(key)) {
			if (!questTypes.containsKey(key)) {
				errors.add(new VariableNotDeclared(key));
			} else {
				variables.put(key, questTypes.get(key));
			}
		}
	}
	
	/**
	 * Sorts the List on severity of errors/warnings
	 */
	private void sortConflicts() {
		errors.sort(Comparator.comparing(Conflict::getConflictLevel));
	}
	
	public void report() {
		reportProblems();
		reportContent(); 
	}
	
	public boolean noWarnings() {
		return warnings.size() == 0;
	}
	public boolean noErrors() {
		return errors.size() == 0;
	}

	private void reportProblems() {
		sortConflicts();
		if (!noErrors()) {
			System.out.println(String.format("%s problem(s) found", errors.size()));
			
			for (Conflict c : errors) {
				c.logIssues();
				System.out.println(c.getClass());
			}
		}
	}
	
	private void reportContent() {
		System.out.println(String.format("Context contains: %s questions, %s statements", questions.size(), statements.size()));
	}

	public void addConflict(Conflict c) {
		errors.add(c);		
	}

	
	
	public List<Question> getQuestions() {
		return questions;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public List<String> getQuestionLabels() {
		return questionLabels;
	}

	public List<Conflict> getProblems() {
		return errors;
	}
	
	public List<Conflict> getWarnings() {
		return warnings;
	}
	
	public HashMap<String, QuestionType> getQuestTypes() {
		return questTypes;
	}

	public Object getVariable(String key) {
		if(variables.containsKey(key)) {
			return variables.get(key);
		}
		return null;
	}

	public QuestionType getQuestionType(String key) {
		if (questTypes.containsKey(key))	return questTypes.get(key);
		return null;
	}
	
	
}
