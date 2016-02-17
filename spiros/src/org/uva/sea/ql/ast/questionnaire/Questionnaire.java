package org.uva.sea.ql.ast.questionnaire;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.node.ASTNode;
import org.uva.sea.ql.ast.node.CodeFragment;


public class Questionnaire extends ASTNode {
	
	private final List<Form> forms;
	
	public Questionnaire(CodeFragment fragment,List<Form> forms) {
		super(fragment);
		this.forms = forms;
	}

	public Questionnaire(CodeFragment fragment) {
		super(fragment);
		this.forms = new ArrayList<Form>();
		}
	
	public List<Form> getForms() {
		return this.forms;
	}
	
	public ASTNode accept(QuestionnaireVisitor<ASTNode> visitor) {
		return visitor.visit(this);
	}

}