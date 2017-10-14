package ql2;

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

public class Context {

	
	List<Question> questions; 
	List<Statement> statements;
	
	List<String> questionLabels;
	List<Conflict> problems;
	HashMap<String, QuestionType> questTypes;
	
	public Context() {

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
}
