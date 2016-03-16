package sc.qls.ast.widget;

import sc.qls.ast.ASTNode;

public abstract class Widget extends ASTNode {

	public abstract <T, U> T accept(WidgetVisitor<T, U> visitor, U context);

}
