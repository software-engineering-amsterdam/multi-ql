package semanticAction.tree.formNode;

import java.util.List;

import semanticAction.tree.AbstractSyntaxTree;
import semanticAction.tree.intermediate.InterfaceVisitForm;
import semanticAction.tree.questionNode.AbsQuestion;

public class Form extends AbstractSyntaxTree {
	private final String formIdentifier;
	private List<AbsQuestion> qList;
	
	
	public Form (String formIdentifier, List<AbsQuestion> qList) {
		this.formIdentifier = formIdentifier;
		this.qList =  qList;
	}	
	
	public String getFormIdentifier(){
		return formIdentifier;
	}
	
	public List<AbsQuestion> getLabel(){
		return qList;
	}
	
	
	public <T> T accept(InterfaceVisitForm<T> visitor) {
		return visitor.visit(this);
	}
	
	public String toString() {String production = "FORM " +this.formIdentifier + " { ";
		for(AbsQuestion q: qList)
			production += q.toString() + "\n";
		production += " }  ";
		
		return production;
	}
}