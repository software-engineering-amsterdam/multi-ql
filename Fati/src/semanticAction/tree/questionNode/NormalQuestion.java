package semanticAction.tree.questionNode;


import semanticAction.tree.expressionNode.literal.Identifier;
import semanticAction.tree.intermediate.InterfaceVisitQuestion;
import semanticAction.tree.typeNode.*;

public class NormalQuestion extends AbsQuestion {
	private final Identifier identifier;
	private final String Stringliteral;
	private final AbsType type;
	
	
	public NormalQuestion (Identifier questionID, String questionText, AbsType questionType) {
		this.identifier = questionID;
		this.Stringliteral = questionText;
		this.type = questionType;
	}	
	
	public Identifier getQId(){
		return identifier;
	}
	
	public String getQText(){
		return Stringliteral;
	}
	
	public AbsType getQType(){
		return type;
	}
	

	@Override
	public String toString() {
		return this.identifier.toString() + " \"" + this.Stringliteral + "\" " + this.type.toString() ;
	}
	
	@Override
	public <T> T accept(InterfaceVisitQuestion<T> visitor) {
		return visitor.visit(this);
	}
	
}