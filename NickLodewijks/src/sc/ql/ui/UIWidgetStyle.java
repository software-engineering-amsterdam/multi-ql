package sc.ql.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class UIWidgetStyle {

	private final Font font;
	private final Color color;
	private final Integer width;
	private final Integer height;

	public UIWidgetStyle(Font font, Dimension dimension, Color color) {
		this(null, font, dimension, color);
	}

	public UIWidgetStyle(UIWidgetStyle parent, Font font, Dimension dimension, Color color) {
		this.font = font;
		this.width = dimension.width;
		this.height = dimension.height;
		this.color = color;
	}

	public Font getFont() {
		return font;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Color getColor() {
		return color;
	}

	public static class Builder {

		private Font font;
		private Color color;
		private Integer width;
		private Integer height;

		public Builder(UIWidgetStyle defaultStyle) {
			this.font = defaultStyle.getFont();
			this.color = defaultStyle.getColor();
			this.width = defaultStyle.getWidth();
			this.height = defaultStyle.getHeight();

			if (font == null) {
				this.font = Font.decode("default");
			}
		}

		public Builder setFontSize(float size) {
			font = font.deriveFont(size);
			return this;
		}

		public Builder setFontName(String name) {
			font = new Font(name, font.getStyle(), font.getSize());
			return this;
		}

		public Builder setFontStyle(int style) {
			font = font.deriveFont(style);
			return this;
		}

		public Builder setColor(Color color) {
			this.color = color;
			return this;
		}

		public Builder setWidth(int width) {
			this.width = width;
			return this;
		}

		public Builder setHeight(int height) {
			this.height = height;
			return this;
		}

		public UIWidgetStyle build() {
			return new UIWidgetStyle(font, new Dimension(width, height), color);
		}
	}
}