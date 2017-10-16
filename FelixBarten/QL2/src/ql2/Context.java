package ql2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ql2.ast.CalculatedQuestion;
import ql2.ast.InputQuestion;
import ql2.ast.Question;
import ql2.ast.Statement;
import ql2.ast.type.QuestionType;
import ql2.conflict.Conflict;
import ql2.conflict.DuplicateLabel;
import ql2.conflict.DuplicateQuestionID;

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
			problems.add(new DuplicateLabel(question,label));
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
	}
	
	public void getVariables() {
		
	
	}
	
	public void report() {
		if (problems.size() > 0) {
			System.out.println(String.format("%s problems found", problems.size()));
			
			for (Conflict c : problems) {
				c.logIssues();
			}
			// TODO ordering so severe errors get addressed first.
		}
	}
}
