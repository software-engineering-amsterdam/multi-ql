package ql2;

import java.util.List;

import ql2.ast.Question;
import ql2.ast.Statement;

public class Context {

	
	List<Question> questions; 
	List<Statement> statements;
	
	List<String> questionLabels;
}
