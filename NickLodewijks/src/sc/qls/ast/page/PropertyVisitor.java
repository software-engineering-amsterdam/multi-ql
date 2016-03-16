package sc.qls.ast.page;

import sc.qls.ast.page.Property.ColorProperty;
import sc.qls.ast.page.Property.HeightProperty;
import sc.qls.ast.page.Property.WidthProperty;

public interface PropertyVisitor<T, U> {

	public T visit(HeightProperty property, U context);

	public T visit(WidthProperty property, U context);

	public T visit(ColorProperty property, U context);

}
