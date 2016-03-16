package sc.ql.ui;

import java.awt.Dimension;
import java.awt.Font;

public class UIWidgetStyle {

	private final Font font;
	private final Dimension dimension;

	public UIWidgetStyle(Font font, Dimension dimension) {
		this.font = font;
		this.dimension = dimension;
	}

	public Font getFont() {
		return font;
	}

	public int getWidth() {
		return dimension.width;
	}

	public int getHeight() {
		return dimension.height;
	}
}