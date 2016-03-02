/**This class represent a Abstract.
 * @goal encapsulation data.The design of this class follows "responsibility-driven design".
 * The class share the common informations among the subclasses.
 * @param Visitor is the AbstractSyntaxTree visitor.
*/
package semanticAction.tree.expressionNode;
import semanticAction.tree.AbstractSyntaxTree;
import semanticAction.tree.intermediate.InterfaceVisitExpression;
import semanticAction.tree.typeNode.*;
public abstract class AbsExpression extends AbstractSyntaxTree {

	
		public abstract <T> T accept(InterfaceVisitExpression<T> visitor);
		public abstract String toString();
		public abstract AbsType getType();
	}

